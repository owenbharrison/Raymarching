package raymarching;

import com.github.owenbharrison.sketch.Sketch;

import raymarching.shapes.*;
import raymarching.structs.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

public class Display extends Sketch{
	private static final long serialVersionUID = 1L;
	
	private Vec2D startPos = new Vec2D((double)getWidth()/2d, (double)getHeight()/2d);
	private Vec2D mousePos = new Vec2D(0, 0);
	
	private ArrayList<Shape> shapes;
	
	public static void main(String[] args) {
		Display d = new Display();
		d.setPreferredSize(new Dimension(840, 840));
		d.start();
	}
	
	@Override
	public void setup(){
		shapes = new ArrayList<Shape>();
		for(int i=0;i<5;i++) {
			double x = map(Math.random(), 0d, 1d, 0d, getWidth());
			double y = map(Math.random(), 0d, 1d, 0d, getHeight());
			double radius = map(Math.random(), 0d, 1d, 5d, 25d);
			shapes.add(new Circle(new Vec2D(x, y), radius, makeRandomColor()));
		}
		
		for(int i=0;i<5;i++) {
			double x = map(Math.random(), 0d, 1d, 0d, getWidth());
			double y = map(Math.random(), 0d, 1d, 0d, getHeight());
			double w = map(Math.random(), 0d, 1d, 15d, 50d);
			double h = map(Math.random(), 0d, 1d, 15d, 50d);
			shapes.add(new Rect(new Vec2D(x, y), new Vec2D(w, h), makeRandomColor()));
		}
		
		for(int i=0;i<5;i++) {
			double x = map(Math.random(), 0d, 1d, 0d, getWidth());
			double y = map(Math.random(), 0d, 1d, 0d, getHeight());
			Vec2D pos = new Vec2D(x, y);
			double d0 = map(Math.random(), 0d, 1d, 25d, 50d);
			double d1 = map(Math.random(), 0d, 1d, 25d, 50d);
			double d2 = map(Math.random(), 0d, 1d, 25d, 50d);
			Vec2D p0 = Vec2D.fromAngle(0d * Math.PI*2d/3d).mult(d0).add(pos);
			Vec2D p1 = Vec2D.fromAngle(1d * Math.PI*2d/3d).mult(d1).add(pos);
			Vec2D p2 = Vec2D.fromAngle(2d * Math.PI*2d/3d).mult(d2).add(pos);
			shapes.add(new Triangle(p0, p1, p2, makeRandomColor()));
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth()*2, getHeight()*2);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//draw blue at start
		g.setColor(Color.BLUE);
		g.fillOval((int)startPos.x-2, (int)startPos.y-2, 4, 4);
		g.setColor(Color.WHITE);
		for(Shape s:shapes)s.show(g);
		
		double angle = Vec2D.sub(mousePos, startPos).heading();
		Vec2D lastPos = startPos.copy();
		double distStepped = 0d;
		boolean hit = false;
		Shape chosenShape = null;
		while(distStepped<getWidth()) {
			double record = Double.POSITIVE_INFINITY;
			g.setColor(Color.WHITE);
			for(Shape s:shapes) {
				double dst = s.signedDistance(lastPos);
				if(dst<record) {
					record = dst;
					chosenShape = s;
				}
			}
			Vec2D vecToAdd = Vec2D.fromAngle(angle).mult(record);
			new Circle(lastPos, record, new Color(255, 255, 255)).show(g);
			lastPos.add(vecToAdd);
			distStepped += record;
			if(record<0.1d) {
				hit = true;
				break;
			}
		}
		g.drawLine((int)startPos.x, (int)startPos.y, (int)lastPos.x, (int)lastPos.y);
		if(hit) {
			//draw red at end
			g.setColor(chosenShape.getColor());
			g.fillOval((int)lastPos.x-4, (int)lastPos.y-4, 8, 8);
		}
	}
	
	@Override
	public void update() {
		mousePos = new Vec2D(mouseX, mouseY);
		if(leftMouseButton) {
			startPos = mousePos;
		}
	}
	
	private Color makeRandomColor() {
		int b1 = (int)map(Math.random(), 0d, 1d, 80d, 255d);
		int s1 = (int)map(Math.random(), 0d, 1d, 20d, 80d);
		int s2 = (int)map(Math.random(), 0d, 1d, 20d, 80d);
		double randChoice = Math.random();
		if(randChoice<0.3333d) {
			return new Color(b1, s1, s2);
		}else if(randChoice<0.6667d){
			return new Color(s2, b1, s1);
		}else {
			return new Color(s1, s2, b1);
		}
	}
	
	public static double sign(double d) {
		return d / Math.abs(d);
	}
	
	public double map(double p, double A, double B, double C, double D){
		return (p-A)*(D-C)/(B-A)+C;
	}
	
	public static double clamp(double val, double min, double max){
		return Math.min(max, Math.max(min, val));
	}
}
package raymarching.shapes;

import java.awt.Color;
import java.awt.Graphics;

import raymarching.structs.Vec2D;

public abstract class Shape {
	protected Vec2D pos;
	protected Color color;
	
	public abstract double signedDistance(Vec2D pos);
	public abstract void show(Graphics g);
	
	public Vec2D getPos() {return this.pos;}
	
	public Color getColor() {return this.color;}
}

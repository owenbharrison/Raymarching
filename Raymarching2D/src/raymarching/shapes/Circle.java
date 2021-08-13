package raymarching.shapes;

import java.awt.Color;
import java.awt.Graphics;

import raymarching.structs.Vec2D;

public class Circle extends Shape{
	public double radius;
	
	public Circle(final Vec2D pos, final double radius, final Color color) {
		this.pos = pos.copy();
		this.radius = radius;
		this.color = color;
	}
	
	public double signedDistance(final Vec2D p) {
		return Vec2D.sub(this.pos, p).mag()-this.radius;
	}
	
	public void show(final Graphics g) {
		g.drawOval((int)(this.pos.x-this.radius), (int)(this.pos.y-this.radius), (int)(this.radius*2d), (int)(this.radius*2d));
	}
}

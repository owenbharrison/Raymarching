package raymarching.shapes;

import java.awt.Color;
import java.awt.Graphics;

import raymarching.Display;
import raymarching.structs.Vec2D;

public class Triangle extends Shape{
	private Vec2D p0, p1, p2;

	public Triangle(final Vec2D p0, final Vec2D p1, final Vec2D p2, final Color color) {
		this.p0 = p0;
		this.p1 = p1;
		this.p2 = p2;
		this.color = color;
	}

	public void show(Graphics g) {
		g.drawLine((int)this.p0.x, (int)this.p0.y, (int)this.p1.x, (int)this.p1.y);
		g.drawLine((int)this.p1.x, (int)this.p1.y, (int)this.p2.x, (int)this.p2.y);
		g.drawLine((int)this.p2.x, (int)this.p2.y, (int)this.p0.x, (int)this.p0.y);
	}

	public double signedDistance(Vec2D p){
		Vec2D e0 = Vec2D.sub(p1, p0), e1 = Vec2D.sub(p2, p1), e2 = Vec2D.sub(p0, p2);
		Vec2D v0 = Vec2D.sub(p, p0), v1 = Vec2D.sub(p, p1), v2 = Vec2D.sub(p, p2);
		Vec2D pq0 = Vec2D.sub(v0, Vec2D.mult(e0, Display.clamp(Vec2D.dot(v0,e0)/Vec2D.dot(e0,e0), 0d, 1d)));
		Vec2D pq1 = Vec2D.sub(v1, Vec2D.mult(e1, Display.clamp(Vec2D.dot(v1,e1)/Vec2D.dot(e1,e1), 0d, 1d)));
		Vec2D pq2 = Vec2D.sub(v2, Vec2D.mult(e2, Display.clamp(Vec2D.dot(v2,e2)/Vec2D.dot(e2,e2), 0d, 1d)));
	  double s = Display.sign(e0.x*e2.y - e0.y*e2.x );
	  Vec2D d = Vec2D.min(Vec2D.min(new Vec2D(Vec2D.dot(pq0,pq0), s*(v0.x*e0.y-v0.y*e0.x)),
	  		new Vec2D(Vec2D.dot(pq1,pq1), s*(v1.x*e1.y-v1.y*e1.x))),
	  		new Vec2D(Vec2D.dot(pq2,pq2), s*(v2.x*e2.y-v2.y*e2.x)));
	    return -Math.sqrt(d.x)*Display.sign(d.y);
	}
}

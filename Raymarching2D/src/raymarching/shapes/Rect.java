package raymarching.shapes;

import java.awt.Color;
import java.awt.Graphics;

import raymarching.structs.Vec2D;

public class Rect extends Shape{
	private Vec2D size;
	
	public Rect(final Vec2D pos, final Vec2D size, final Color color) {
		this.pos = pos;
		this.size = Vec2D.div(size, 2d);
		this.color = color;
	}

	public double signedDistance(final Vec2D p) {
		Vec2D d = Vec2D.sub(Vec2D.abs(Vec2D.sub(p, this.pos)), this.size);
		return Vec2D.max(d, new Vec2D(0d, 0d)).mag() + Math.min(Math.max(d.x,d.y),0.0);
	}

	public void show(final Graphics g) {
		g.drawRect((int)(this.pos.x-this.size.x), (int)(this.pos.y-this.size.y), (int)this.size.x*2, (int)this.size.y*2);
	}
}

package raymarching.structs;

public class Vec2D {
	public double x, y;
	
	public Vec2D(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vec2D add(final Vec2D v) {
		this.x += v.x;
		this.y += v.y;
		return this;
	}
	
	public Vec2D sub(final Vec2D v) {
		this.x -= v.x;
		this.y -= v.y;
		return this;
	}
	
	public Vec2D mult(final double d) {
		this.x *= d;
		this.y *= d;
		return this;
	}
	
	public Vec2D div(final double d) {
		this.x /= d;
		this.y /= d;
		return this;
	}
	
	public double mag() {
		return Math.sqrt(Vec2D.dot(this, this));
	}
	
	public double heading() {
		return Math.atan2(this.y, this.x);
	}
	
	public Vec2D copy() {
		return new Vec2D(this.x, this.y);
	}
	
	public void normalize() {
		this.div(this.mag());
	}
	
	public static double dot(final Vec2D a, final Vec2D b) {
		return a.x*b.x+a.y*b.y;
	}
	
	public static Vec2D add(final Vec2D a, final Vec2D b) {
		Vec2D a_ = a.copy();
		a_.sub(b);
		return a_;
	}
	
	public static Vec2D sub(final Vec2D a, final Vec2D b) {
		Vec2D a_ = a.copy();
		a_.sub(b);
		return a_;
	}
	
	public static Vec2D mult(final Vec2D v, final double d) {
		Vec2D v_ = v.copy();
		v_.mult(d);
		return v_;
	}
	
	public static Vec2D div(final Vec2D v, final double d) {
		Vec2D v_ = v.copy();
		v_.div(d);
		return v_;
	}
	
	public static Vec2D fromAngle(final double d) {
		return new Vec2D(Math.cos(d), Math.sin(d));
	}

	public static Vec2D abs(final Vec2D v) {
		return new Vec2D(Math.abs(v.x), Math.abs(v.y));
	}
	
	public static Vec2D min(final Vec2D a, final Vec2D b){
	  return new Vec2D(a.x<b.x? a.x:b.x, a.y<b.y?a.y:b.y);
	}
	
	public static Vec2D max(final Vec2D a, final Vec2D b){
	  return new Vec2D(a.x>b.x? a.x:b.x, a.y>b.y?a.y:b.y);
	}
}

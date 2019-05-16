package base;

public class Vector2 
{
	double x;
	double y;
	
	public Vector2(double _x, double _y)
	{
		x = _x;
		y = _y;
	}
	
	public Vector2(final Vector2 other)
	{
		x = other.x;
		y = other.y;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public void add(final Vector2 other)
	{
		x += other.x;
		y += other.y;
	}
	
	public void mult(final Vector2 other)
	{
		x *= other.x;
		y += other.y;
	}
	
	public void mult(double factor)
	{
		x *= factor;
		y *= factor;
	}

	
	/*
	 * rotate around the X-axis
	 * X axis defined as pointing up
	 * */
	public void rotate(double angle)
	{
		final double oldX = x;
		x = x*Math.cos(angle) + y*Math.sin(angle);
		y = y*Math.cos(angle) - oldX*Math.sin(angle);
	}
	
	public static Vector2 zero()
	{
		return new Vector2(0,0);
	}
	
	
}

package base;

public class Vector3 
{
	private double x;
	private double y;
	private double z;
	
	private Vector3(double _x, double _y, double _z)
	{
		x = _x;
		y = _y;
		z = _z;
	}
	public Vector3(Vector3 movement) 
	{
		x = movement.x;
		y = movement.y;
		z = movement.z;	
	}
	
	public double getX() 
	{
		return x;
	}
	
	public double getY() 
	{
		return y;
	}
	
	public static Vector3 zero() 
	{
		return new Vector3(0,0,0);
	}
	
	public void add(Vector3 movement) 
	{
		x += movement.x;
		y += movement.y;
		z += movement.z;
	}
	
	public void mult(Vector3 factors)
	{
		x *= factors.x;
		y *= factors.y;
		z *= factors.z;
	}
	
	public void mult(double factor)
	{
		x *= factor;
		y *= factor;
		z *= factor;
	}
	
}

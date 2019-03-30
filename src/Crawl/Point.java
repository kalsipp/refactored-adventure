package Crawl;

public class Point
{
    private int x;
    private int y;
    public Point(int _x, int _y)
    {
        x = _x;
        y = _y;
    }
    
    public static Point zero()
    {
    	return new Point(0,0);
    }
    
    public static Point right()
    {
    	return new Point(1,0);
    }
    
    public static Point up()
    {
    	return new Point(0,1);
    }
    
    public Point(Point otherPoint) {
    	x = otherPoint.x;
    	y = otherPoint.y;
	}

	public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    
    public void add(Point other)
    {
    	x += other.x;
    	y += other.y;
    }
    
    public void subtract(Point other)
    {
    	x -= other.x;
    	y -= other.y;
    }
    
    public boolean equals(Point other)
    {
    	return x == other.x && y == other.y;
    }

	public void multiply(int factor) 
	{
		x *= factor;
		y *= factor;
	}
}
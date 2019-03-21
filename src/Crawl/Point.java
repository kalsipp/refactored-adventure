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
    
    public boolean equals(Point other)
    {
    	return x == other.x && y == other.y;
    }
}
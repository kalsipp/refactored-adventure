package Crawl;

public class Square {
	Point position;
	Point size;
	public Square(Point _position, Point _size)
	{
		position = _position;
		size = _size;
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	public Point getSize()
	{
		return size;
	}
	
	public boolean contains(Point pos)
	{
        return pos.getX() >= position.getX() && 
                pos.getY() >= position.getY() &&
                pos.getX() < position.getX() + size.getX() &&
                pos.getY() < position.getY() + size.getY();
	}
	
}

package base;

public class Square {
	private final Point position;
	private final Point size;
	public Square(Point _position, Point _size)
	{
		position = _position;
		size = _size;
	}
	
	public Square(Square dimensions) 
	{
		position = new Point(dimensions.position);
		size = new Point(dimensions.size);
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

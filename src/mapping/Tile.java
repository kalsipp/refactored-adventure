package mapping;
import java.util.Random;

import base.Point;
import graphics.Pixel;
import graphics.Sprite;

public class Tile 
{
	protected boolean isPassable;
	protected Sprite sprite;
	final private int defaultColor = 20; // Should be a recognizable color
	final protected Pixel defaultPixel = new Pixel(defaultColor);
	public Tile()
	{
		isPassable = false;
	}
	
	public Tile(Tile tile) 
	{
		isPassable = tile.isPassable;
	}

	final public boolean isPassable()
	{
		return isPassable;
	}
	
	final public void open() 
	{
		isPassable = true;
	}
	
	final public void close()
	{
		isPassable = false;
	}
	
	public Point getSpriteSize()
	{
		return new Point(1,1);
	}
	
	public Pixel getPixel(Point spritePosition)
	{
		return defaultPixel; 
	}
}

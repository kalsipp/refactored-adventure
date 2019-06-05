package mapping;

import base.Point;
import graphics.Canvas;
import graphics.Pixel;
import graphics.Sprite;

public class Tile 
{
	boolean isPassable;
	final Point tileSpriteSize = new Point(32,32);
	Sprite sprite;
	public Tile()
	{
		isPassable = false;
	}
	
	public Tile(Tile tile) 
	{
		isPassable = tile.isPassable;
		sprite = new Sprite(tile.sprite);
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
	
	final public Sprite getSpriteRef()
	{
		if (sprite == null)
		{
			/* Technically not meant to happen as renderable children will populate sprite */
			sprite = new Sprite(tileSpriteSize);
			sprite.clearSpriteToPixel(new Pixel(20));
		}
		return sprite;
	}
}

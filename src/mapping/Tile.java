package mapping;

import base.Point;
import graphics.Canvas;
import graphics.Pixel;
import graphics.Sprite;

import java.io.IOException;
import java.util.Random;

public class Tile 
{
	boolean isPassable;
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
	
	final public Sprite getSpriteRef() throws IOException
	{
		if (sprite == null)
		{
			/* Technically not meant to happen as renderable children will populate sprite */
			sprite = Sprite.loadSpriteFromFile("sprites/stonewall_simple_6464.img");
		}
		return sprite;
	}
}

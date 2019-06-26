package mapping;

import base.Point;
import graphics.Canvas;
import graphics.Pixel;
import graphics.Sprite;
import org.json.JSONObject;

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
			sprite = Sprite.loadSpriteFromFile("sprites/bestimg.img");
		}
		return sprite;
	}
}

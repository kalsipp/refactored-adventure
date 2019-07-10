package mapping;

import base.Point;
import graphics.Canvas;
import graphics.Pixel;
import graphics.Sprite;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

class TileSettings
{
	public TileSettings
			(
					boolean isPassable_,
					boolean isVisible_,
					Sprite sprite_
			)
	{
		isPassable = isPassable;
		isVisible = isVisible_;
		sprite = sprite_;
	}

	boolean isPassable;
	boolean isVisible;
	Sprite sprite;
}

public class Tile 
{
	TileSettings settings;
	public Tile()

	{
		TileSettings sett = new TileSettings(
				true,
				true,
				null);
		setSettings(sett);
	}

	final protected void setSettings(TileSettings settings_)
	{
		settings = settings_;
	}

	final public boolean isVisible() { return settings.isVisible; }
	final public boolean isPassable()
	{
		return settings.isPassable;
	}
	final public void open() 
	{
		settings.isPassable = true;
	}
	final public void close()
	{
		settings.isPassable = false;
	}
	
	final public Sprite getSpriteRef() throws IOException
	{
		if (settings.sprite == null)
		{
			/* Technically not meant to happen as renderable children will populate sprite */
			settings.sprite = Sprite.loadSpriteFromFile("sprites/bestimg.img");
		}
		return settings.sprite;
	}
}

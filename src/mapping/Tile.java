package mapping;

import graphics.Sprite;

import java.io.IOException;

class TileSettings
{
	public TileSettings
			(
					boolean isPassable_,
					boolean isVisible_,
					Sprite sprite_
			)
	{
		isPassable = isPassable_;
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
			settings.sprite = Sprite.loadSpriteFromFile("sprites/bestimg2.img");
		}
		return settings.sprite;
	}
}

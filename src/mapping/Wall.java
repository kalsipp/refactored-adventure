package mapping;

import graphics.Sprite;

import java.io.IOException;

public class Wall extends Tile 
{
	public Wall() throws IOException
	{
		TileSettings sett = new TileSettings(
				false,
				true,
				Sprite.loadSpriteFromFile("sprites/stonewall_simple_6464.img")
		);
		setSettings(sett);
	}

}

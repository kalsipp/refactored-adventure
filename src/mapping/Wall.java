package mapping;

import base.Point;
import graphics.Pixel;
import graphics.Sprite;
import org.json.JSONObject;

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

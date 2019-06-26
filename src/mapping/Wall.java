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
		sprite = Sprite.loadSpriteFromFile("sprites/stonewall_simple_6464.img");
		isPassable = false;
	}

}

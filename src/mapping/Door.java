package mapping;

import graphics.Sprite;
import org.json.JSONObject;

import java.io.IOException;

public final class Door extends Tile
{
	boolean locked;
	public Door (JSONObject details, JSONObject defaultInfo) throws IOException
	{
		locked = details.getBoolean("locked");
		isPassable = defaultInfo.getBoolean("passable");
		if(locked) isPassable = false;
		sprite = Sprite.loadSpriteFromFile(defaultInfo.getString("sprite"));
	}
}

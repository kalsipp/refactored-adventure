package mapping;

import graphics.Sprite;

import java.io.IOException;

public class StairsUp extends Tile
{
    public StairsUp() throws IOException
    {
        isPassable = true;
        sprite = Sprite.loadSpriteFromFile("sprites/blueyellow.img");
    }
}

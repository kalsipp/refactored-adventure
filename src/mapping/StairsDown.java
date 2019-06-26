package mapping;

import graphics.Sprite;

import java.io.IOException;

public class StairsDown extends Tile
{
    public StairsDown() throws IOException
    {
        isPassable = true;
        sprite = Sprite.loadSpriteFromFile("sprites/redgreen.img");
    }
}

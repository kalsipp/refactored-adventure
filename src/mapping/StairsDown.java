package mapping;

import graphics.Sprite;

import java.io.IOException;

public class StairsDown extends Tile
{
    public StairsDown() throws IOException
    {
        TileSettings sett = new TileSettings(
                true,
                false,
                Sprite.loadSpriteFromFile("sprites/redgreen.img")
        );
        setSettings(sett);
    }
}

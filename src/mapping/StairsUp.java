package mapping;

import graphics.Sprite;

import java.io.IOException;

public class StairsUp extends Tile
{
    public StairsUp() throws IOException
    {
        TileSettings sett = new TileSettings(
                true,
                false,
                Sprite.loadSpriteFromFile("sprites/blueyellow.img")
        );
        setSettings(sett);
    }
}

package mapping;

import graphics.Sprite;

import java.io.IOException;

public class OpenTile extends Tile
{
    public OpenTile() throws IOException
    {
        TileSettings sett = new TileSettings(
                true,
                false,
                Sprite.loadSpriteFromFile("sprites/bestimg2.img")
        );
        setSettings(sett);
    }
}

package mapping;

import crawl.SceneHandler;
import graphics.Sprite;

import java.io.IOException;

public class StairsUp extends Tile
{
    public StairsUp() throws IOException
    {
        TileSettings sett = new TileSettings(
                true,
                true,
                Sprite.loadSpriteFromFile("sprites/blueyellow.img")
        );
        setSettings(sett);
    }

    @Override
    public void onTileEnter() throws Exception
    {
        SceneHandler.loadShallowerMap();
    }
}

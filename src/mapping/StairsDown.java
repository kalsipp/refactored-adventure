package mapping;

import crawl.SceneHandler;
import graphics.Sprite;

import java.io.IOException;

public class StairsDown extends Tile
{
    public StairsDown() throws IOException
    {
        TileSettings sett = new TileSettings(
                true,
                true,
                Sprite.loadSpriteFromFile("sprites/redgreen.img")
        );
        setSettings(sett);
    }
    @Override
    public void onTileEnter() throws Exception
    {
        SceneHandler.loadDeeperMap();
    }

}

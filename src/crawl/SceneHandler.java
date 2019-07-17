package crawl;

import base.Point;
import base.Vector2;
import graphics.Camera;
import mapping.Map;
import mapping.MapLoader;

import java.util.ArrayList;

public class SceneHandler
{

    static int activeMapIndex = 0;
    static boolean transitionedDeeper = false;
    static ArrayList<Map> maps;
    static String[] mapPaths = {
            "maps/map1.yeoldemappe",
            "maps/map2.yeoldemappe",
            "maps/map3.yeoldemappe"
    };

    public static void loadShallowerMap() throws Exception
    {
        activeMapIndex -= 1;
        if(activeMapIndex < 0 || activeMapIndex >= maps.size())
        {
            throw new Exception("Tried going out of bounds with maps");
        }
        transitionedDeeper = false;
        Crawler.setNewMapLoaded();
    }

    public static void loadDeeperMap() throws Exception
    {
        activeMapIndex += 1;
        if(activeMapIndex < 0 || activeMapIndex >= maps.size())
        {
            throw new Exception("Tried going out of bounds with maps");
        }
        transitionedDeeper = true;
        Crawler.setNewMapLoaded();
    }

    public static boolean transitionedDeeper()
    {
        return transitionedDeeper;
    }

    public static Map getActiveMap()
    {

        return maps.get(activeMapIndex);
    }


    public static void initialize() throws Exception
    {
        maps = new ArrayList<>();
        for (String path: mapPaths)
        {
            Map newMap = MapLoader.loadMapFromFile(path);
            maps.add(newMap);
        }
    }


}

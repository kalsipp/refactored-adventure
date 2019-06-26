package mapping;

import base.Helpers;
import base.Point;

public class MapLoader
{
    static public Map loadMapFromFile(String basePath) throws Exception
    {
        StringBuilder baseSB = new StringBuilder();
        Helpers.readFileToString(basePath, baseSB);
        Point mapSize = getSizeOfMap(baseSB.toString());
        Map newMap = new Map(mapSize);
        buildMapFromText(baseSB.toString(), newMap);
        return newMap;
    }

    private static Map buildMapFromText(String string, Map newMap) throws Exception
    {
        Point currentPos = Point.zero();
        for(int index = 0; index < string.length(); index++)
        {
            char cell = string.charAt(index);
            if(cell == '\n')
            {
                currentPos.add(Point.up());
                currentPos.setX(0);
            }
            else
            {
                Tile newTile = getTileFromChar(cell);
                newMap.SetTile(currentPos, newTile);
                currentPos.add(Point.right());
            }
        }
        return newMap;
    }

    private static Point getSizeOfMap(String string)
    {
        String [] lines = string.split("\n");
        return new Point(lines[0].length(), lines.length);
    }

    private static Tile getTileFromChar(char cell) throws Exception
    {
        Tile newTile;
        switch(cell)
        {
            case '#':
                newTile = new Wall();
                break;
            case '.':
                newTile = new OpenTile();
                break;
            case '^':
                newTile = new StairsUp();
                break;
            case 'v':
                newTile = new StairsDown();
                break;
            default:
                throw new ClassNotFoundException("Unknown cell");
        }
        return newTile;
    }

}

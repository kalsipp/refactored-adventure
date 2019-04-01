package mapping;

import java.io.FileNotFoundException;
import java.io.IOException;

import base.Helpers;
import base.Point;

public class MapLoader 
{
	static public Map loadMapFromFile(String path) throws FileNotFoundException, IOException 
	{
		StringBuilder mapText = new StringBuilder();
		Helpers.readFileToString(path, mapText);
		return buildMapFromText(mapText.toString());
	}

	private static Map buildMapFromText(String string) 
	{
		
		Point mapSize = getSizeOfMap(string);
		Map newMap = new Map(mapSize);
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
		return null;
	}

	private static Point getSizeOfMap(String string) {
		String [] lines = string.split("\n");
		return new Point(lines[0].length(), lines.length);
	}

	private static Tile getTileFromChar(char cell) {
		Tile newTile = new Tile();
		switch(cell)
		{
		case '#':
			newTile.close();
			break;
		case '.':
			newTile.open();
			break;
		}
		return newTile;
	}
}

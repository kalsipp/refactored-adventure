package mapping;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import base.Helpers;
import base.Point;

public class MapLoader 
{
	private final static String basemapEnding = "yeoldemappe";
	private final static String overrideEnding = "json";
	
	static public Map loadMapFromFile(String path) throws IOException
	{
		StringBuilder basePath = new StringBuilder();
		StringBuilder overridePath = new StringBuilder();
		FindRequiredFiles(path, basePath, overridePath);
		StringBuilder mapText = new StringBuilder();
		Helpers.readFileToString(basePath.toString(), mapText);
		Map newMap = buildMapFromText(mapText.toString());
		StringBuilder overrideText = new StringBuilder();
		try
		{
			Helpers.readFileToString(overridePath.toString(), overrideText);
			overrideMap(overrideText.toString(), newMap);
		}
		catch (FileNotFoundException e)
		{
			//Override is not needed if it does not exist
		}
		return newMap;
	}

	private static void overrideMap(String overrideText, Map newMap) {
		JSONObject obj = new JSONObject(overrideText);
		JSONArray tiles = (JSONArray) obj.get("tiles");
		for(int i = 0; i < tiles.length(); i++)
		{
			JSONObject tileJson = tiles.getJSONObject(i);
			Tile newTile = parseJsonTile(tileJson);
			JSONObject positionJson = tileJson.getJSONObject("position");
			int x = positionJson.getInt("x");
			int y = positionJson.getInt("y");
			Point position = new Point(x,y);
			newMap.SetTile(position, newTile);
		}
		
		
	}
	
	private static Tile parseJsonTile(JSONObject tileJson) 
	{
		String type = tileJson.getString("type");
		Tile newTile;
		switch(type)
		{
		case "door":
			newTile = CreateDoorTile(tileJson);
			break;
		default:
			throw new TypeNotPresentException("Unknown tiletype " + type, null);
		}
		return newTile;
	}

	private static Tile CreateDoorTile(JSONObject tileJson) 
	{
		String target = tileJson.getString("target");
		return new Door(target);
	}

	private static void FindRequiredFiles(String path, 
			StringBuilder basePath, 
			StringBuilder overridePath) 
	{
		String[] parts = path.split("\\.");
		String ending = parts[parts.length-1];
		String pathWithoutEnding = path.substring(0, path.length()-ending.length());
		basePath.append(pathWithoutEnding.concat(basemapEnding));
		overridePath.append(pathWithoutEnding.concat(overrideEnding));
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
		return newMap;
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

package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import base.Point;
import mapping.Door;
import mapping.Map;
import mapping.MapLoader;
import mapping.Tile;

class MapLoaderTest 
{
	
	private final String baseFilename = "testfile.yeoldemappe";
	private final String overrideFilename = "testfile.json";
	void writeDataToFile(String filename, String data) throws IOException
	{
    	try(FileWriter fileWriter = new FileWriter(filename))
    	{
    		fileWriter.write(data);
    	}
	}

	void removeAllFiles()
	{
		
		File f = new File(baseFilename);
		if(f.exists())
		{
			f.delete();
		}
		f = new File(overrideFilename);
		if(f.exists())
		{
			f.delete();
		}

	}
	
	void baseMapShouldBe(String mapref, Map map)
	{
		String[] lines = mapref.split("\n");
		for(int y = 0; y < lines.length; y++)
		{
			for(int x = 0; x < lines[y].length(); x++)
			{
				switch(lines[y].charAt(x))
				{
				case '#':
					tileShouldBeWall(map.GetTile(new Point(x,y)));
					break;
				case '.':
					tileShouldBeOpen(map.GetTile(new Point(x,y)));
					break;
				default:
					fail();
					break;
				}
			}
		}
	}
	
	void tileShouldBeWall(Tile tile)
	{
		assertFalse(tile.isPassable());
	}
	
	void tileShouldBeOpen(Tile tile)
	{
		assertTrue(tile.isPassable());
	}
	
	@Test
	void loadMapFromFileTest() throws IOException 
	{
		{ /* Load a 3x3 map base only */
			removeAllFiles();
			String baseMap = "###\n"
						   + "#.#\n"
						   + "###";
			writeDataToFile(baseFilename, baseMap);
			Map newMap = MapLoader.loadMapFromFile(baseFilename);
			baseMapShouldBe(baseMap, newMap);
			
		}		
		{ /* Load a 1x3 map base only */
			removeAllFiles();
			String baseMap = "#\n"
						   + ".\n"
						   + "#";
			writeDataToFile(baseFilename, baseMap);
			Map newMap = MapLoader.loadMapFromFile(baseFilename);
			baseMapShouldBe(baseMap, newMap);
		}
		{ /* Load a 3x3 with an override */
			removeAllFiles();
			String baseMap = "###\n"
					       + "#.#\n"
					       + "###";
			writeDataToFile(baseFilename, baseMap);
			JSONObject positionJson = new JSONObject();
			positionJson.put("x", 0);
			positionJson.put("y", 1);
			JSONObject tileJson = new JSONObject();
			tileJson.put("position", positionJson);
			tileJson.put("type", "door");
			tileJson.put("target", "next_floor");
			JSONObject mapJson = new JSONObject();
			JSONObject[] tiles = {tileJson};
			mapJson.put("tiles", tiles);
			writeDataToFile(overrideFilename, mapJson.toString());
			Map newMap = MapLoader.loadMapFromFile(baseFilename);
			baseMapShouldBe(baseMap, newMap);
			Door door = (Door)newMap.GetTile(new Point(0,1));
			assertEquals("next_floor", door.getTargetFloor());
		}
	}

}

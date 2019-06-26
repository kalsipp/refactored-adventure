package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import mapping.*;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import base.Point;

class MapLoaderTest 
{
	
	private final String baseFilename = "testfile.yeoldemappe";
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
					assertTrue(map.GetTile(new Point(x,y)) instanceof Wall);
					break;
				case '.':
					assertTrue(map.GetTile(new Point(x,y)) instanceof OpenTile);
					break;
				case '^':
					assertTrue(map.GetTile(new Point(x,y)) instanceof StairsUp);
					break;
				case 'v':
					assertTrue(map.GetTile(new Point(x,y)) instanceof StairsDown);
					break;
				default:
					fail();
					break;
				}
			}
		}
	}

	@Test
	void loadMapFromFileTest() throws Exception
	{
		{ /* Load a 3x3 map */
			removeAllFiles();
			String baseMap = "###\n"
						   + "#.#\n"
						   + "###";
			writeDataToFile(baseFilename, baseMap);
			Map newMap = MapLoader.loadMapFromFile(baseFilename);
			baseMapShouldBe(baseMap, newMap);
			
		}		
		{ /* Load a 1x3 map */
			removeAllFiles();
			String baseMap = "#\n"
						   + ".\n"
						   + "#";
			writeDataToFile(baseFilename, baseMap);
			Map newMap = MapLoader.loadMapFromFile(baseFilename);
			baseMapShouldBe(baseMap, newMap);
		}
		{ /* Load a map with different tiles */
			removeAllFiles();
			String baseMap = "^v#\n"
					       + "#.#\n"
					       + "###";
			writeDataToFile(baseFilename, baseMap);
			Map newMap = MapLoader.loadMapFromFile(baseFilename);
			baseMapShouldBe(baseMap, newMap);

		}
	}

}

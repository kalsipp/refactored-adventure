package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import Crawl.Point;
import Crawl.Sprite;
import Crawl.SpriteLoader;

class SpriteLoaderTest {
	
	void writeDataToFile(String filename, String data) throws IOException
	{
    	try(FileWriter fileWriter = new FileWriter(filename))
    	{
    		fileWriter.write(data);
    	}
	}

	@Test
	void loadSpriteFromFile_SizeShouldBeRight() throws IOException 
	{
		{
			writeDataToFile("testfile.txt", "145 145 145 145 145 145");
			Sprite sprite = SpriteLoader.loadSpriteFromFile("testfile.txt");
			assertTrue(sprite.getSize().equals(new Point(6, 1)));
		}
		{
			writeDataToFile("testfile.txt", "145 145 145\n 20 1 3000");
			Sprite sprite = SpriteLoader.loadSpriteFromFile("testfile.txt");
			assertTrue(sprite.getSize().equals(new Point(3, 2)));
		}
		{
			writeDataToFile("testfile.txt", "145 145 145 \n20 1 3000");
			Sprite sprite = SpriteLoader.loadSpriteFromFile("testfile.txt");
			assertTrue(sprite.getSize().equals(new Point(3, 2)));
		}
		{
			writeDataToFile("testfile.txt", "145 145 145 \n20 1 3000\n");
			Sprite sprite = SpriteLoader.loadSpriteFromFile("testfile.txt");
			assertTrue(sprite.getSize().equals(new Point(3, 2)));
		}
	}

	
}

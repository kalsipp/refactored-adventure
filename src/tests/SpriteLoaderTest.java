package tests;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import base.Point;
import graphics.Pixel;
import graphics.Sprite;
import graphics.SpriteLoader;

import static org.junit.jupiter.api.Assertions.*;

class SpriteLoaderTest {
	
	void writeDataToFile(String filename, String data) throws IOException
	{
    	try(FileWriter fileWriter = new FileWriter(filename))
    	{
    		fileWriter.write(data);
    	}
	}

	void SpriteShouldHavePixelvals(Sprite sprite, int[][] values)
	{
		for(int y = 0; y < values.length; y++)
		{
			for(int x = 0; x < values[0].length; x++)
			{
				Pixel pix = sprite.getPixel(new Point(x,y));
				assertNotNull(pix);
				assertEquals(pix.getColor(), values[y][x]);
			}
		}
	}
	
	@Test
	void loadSpriteFromFile_SizeShouldBeRight() throws IOException 
	{
		{
			writeDataToFile("testfile.txt", "145 145 145 145 145 145");
			Sprite sprite = SpriteLoader.loadSpriteFromFile("testfile.txt");
			assertTrue(sprite.getSize().equals(new Point(6, 1)));
			int [][] expected = {{145, 145, 145, 145, 145, 145}};
			SpriteShouldHavePixelvals(sprite, expected);
		}
		{
			writeDataToFile("testfile.txt", "145 145 145\n 20 1 3000");
			Sprite sprite = SpriteLoader.loadSpriteFromFile("testfile.txt");
			assertTrue(sprite.getSize().equals(new Point(3, 2)));
			int [][] expected = {{145, 145, 145}, {20, 1, 3000}};
			SpriteShouldHavePixelvals(sprite, expected);
		}
		{
			writeDataToFile("testfile.txt", "145 145 145 \n20 1 3000");
			Sprite sprite = SpriteLoader.loadSpriteFromFile("testfile.txt");
			assertTrue(sprite.getSize().equals(new Point(3, 2)));
			int [][] expected = {{145, 145, 145}, {20, 1, 3000}};
			SpriteShouldHavePixelvals(sprite, expected);
		}
		{
			writeDataToFile("testfile.txt", "145 145 145 \n20 1 3000\n");
			Sprite sprite = SpriteLoader.loadSpriteFromFile("testfile.txt");
			assertTrue(sprite.getSize().equals(new Point(3, 2)));
			int [][] expected = {{145, 145, 145}, {20, 1, 3000}};
			SpriteShouldHavePixelvals(sprite, expected);
		}
	}

	
}

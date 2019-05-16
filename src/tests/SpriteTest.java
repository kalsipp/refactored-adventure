package tests;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import base.Point;
import graphics.Pixel;
import graphics.Sprite;

import static org.junit.jupiter.api.Assertions.*;

class SpriteTest {
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
	void pasteToSameSizeTest() throws IOException
	{
		writeDataToFile("testfile.txt", "10 20 30\n 40 50 60");
		Sprite srcSprite = Sprite.loadSpriteFromFile("testfile.txt");
		Sprite targetSprite = new Sprite(new Point(3,2));
		targetSprite.paste(srcSprite, Point.zero());
		int[][] expected = {{10, 20, 30}, {40, 50, 60}};
		SpriteShouldHavePixelvals(targetSprite, expected);
	}
	
	@Test
	void pasteToSmallerTest() throws IOException
	{
		writeDataToFile("testfile.txt", "10 20 30\n 40 50 60");
		Sprite srcSprite = Sprite.loadSpriteFromFile("testfile.txt");
		Sprite targetSprite = new Sprite(new Point(2,1));
		targetSprite.paste(srcSprite, Point.zero());
		int[][] expected = {{10, 20}};
		SpriteShouldHavePixelvals(targetSprite, expected);
	}
	
	@Test
	void pasteToLargerTest() throws IOException
	{
		writeDataToFile("testfile.txt", "10 20 30\n 40 50 60");
		Sprite srcSprite = Sprite.loadSpriteFromFile("testfile.txt");
		Sprite targetSprite = new Sprite(new Point(4,3));
		targetSprite.paste(srcSprite, Point.zero());
		int[][] expected = {{10, 20, 30, 0}, {40, 50, 60, 0}, {0, 0, 0, 0}};
		SpriteShouldHavePixelvals(targetSprite, expected);
	}
}

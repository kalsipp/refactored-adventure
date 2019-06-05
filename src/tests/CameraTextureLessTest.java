package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import base.Point;
import base.Vector2;
import graphics.CameraTextureLess;
import graphics.Canvas;
import graphics.Camera;
import graphics.Pixel;
import graphics.Sprite;
import mapping.Map;
import mapping.MapLoader;

class CameraTextureLessTest
{

	void writeDataToFile(String filename, String data) throws IOException
	{
    	try(FileWriter fileWriter = new FileWriter(filename))
    	{
    		fileWriter.write(data);
    	}
	}

	int[][] SpriteToArray(Sprite sp)
	{
		int[][] out = new int[sp.getSize().getY()][];
		for(int y = 0; y < sp.getSize().getY(); y++)
		{
			int[] outX = new int[sp.getSize().getX()];
			for(int x = 0; x < sp.getSize().getX(); x++)
			{
				outX[x] = sp.getPixel(new Point(x,y)).getColor();
			}
			out[y] = outX;
		}
		return out;
	}
	
	void majorityOfScreenShouldBeColor(Sprite sprite, int color)
	{
		int sizeX = sprite.getSize().getX();
		int sizeY = sprite.getSize().getY();
		int nof_color = 0;
		for(int y = 0; y < sizeY; y++)
		{
			for(int x= 0; x < sizeX; x++)
			{
				Point pos = new Point(x,y);
				Pixel pix = sprite.getPixel(pos);
				if(pix.getColor() == color) 
				{
					nof_color++;
				}
			}
		}
		
		boolean majority = nof_color > (sizeX*sizeY/2);
		assertTrue(majority);
	}
	
	@Test
	void renderTextureLessTest() throws IOException 
	{
		final String baseFilename = "cameraTestfile.yeoldemappe";
		String baseMap = "###\n"
					   + "#.#\n"
					   + "###";
		writeDataToFile(baseFilename, baseMap);
		Map activeMap = MapLoader.loadMapFromFile(baseFilename);
		Camera cam = new Camera();
		Canvas.initialize();
		cam.setCameraPos(new Vector2(1.5,1.5));
		cam.setCameraDirection(new Vector2(-1,0));
		cam.setCameraPlane(new Vector2(0, 0.5));
		cam.renderScreen(activeMap);
//		Sprite plannedImg = Canvas.GetPlannedImage();
		/* Not testing anything yet */
	}
	
}

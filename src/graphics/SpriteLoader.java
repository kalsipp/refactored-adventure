package graphics;

import java.io.FileNotFoundException;
import java.io.IOException;

import base.Helpers;
import base.Point;

public class SpriteLoader {
 
    /**
     * Loads the image in the path and returns a Sprite from it. 
     * Images have to be perfectly rectangular. 
     */
    static public Sprite loadSpriteFromFile(String path) throws FileNotFoundException, IOException
    {
    	StringBuilder dataDump = new StringBuilder();
    	Helpers.readFileToString(path, dataDump);
    	Sprite newSprite = extractPixelsFromString(dataDump.toString());
    	return newSprite;
    }
    
    private static Sprite extractPixelsFromString(String readChars) 
    {
    	Point spriteSize = getFilesSpriteDimensions(readChars);
    	
    	Sprite newSprite = new Sprite(spriteSize);
    	String[] rows = readChars.split("\n");
    	for(int y = 0; y < rows.length; y++)
    	{
    		String[] colorvals = rows[y].trim().split(" ");
    		for(int x = 0; x < colorvals.length; x++)
    		{
    			int colorVal = Integer.parseInt(colorvals[x]);
    			Pixel newPixel = new Pixel(colorVal);
    			newSprite.setPixel(new Point(x,y), newPixel);
    		}
    	}
    	return newSprite;
    }
    
    private static Point getFilesSpriteDimensions(String readChars) 
    {
    	String[] lines = readChars.split("\n");
    	String[] pixelsvals = lines[0].split(" ");
    	return new Point(pixelsvals.length, lines.length);
    }
    
}

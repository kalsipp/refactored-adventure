package Crawl;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class Sprite
{
    Pixel[][] pixels;
    Square dimensions;
    public Sprite(Square _dimensions)
    {
    	dimensions = _dimensions;
    	pixels = new Pixel[dimensions.getSize().getY()][dimensions.getSize().getX()];
    }
    
    static public Sprite loadSpriteFromFile(String path) throws FileNotFoundException, IOException
    {
    	return SpriteLoader.loadSpriteFromFile(path);
    }

    public Point getSize()
    {
        return dimensions.getSize();
    }
    
    public Point getPosition()
    {
    	return dimensions.getPosition();
    }
    
    public Pixel getPixel(Point index)
    {
    	if(dimensions.contains(index))
    	{
    		return pixels[index.getY()][index.getX()];
    	}
    	else
    	{
    		return null;
    	}
    }
    
    public void setPixel(Point index, Pixel newPixel)
    {
    	if(dimensions.contains(index))
    	{
    		pixels[index.getY()][index.getX()] = newPixel;
    	}
    }
    
    public void paste(Sprite otherSprite)
    {
    	for(int y = 0; y < otherSprite.dimensions.getSize().getY(); y++)
    	{
    		for(int x = 0; x < otherSprite.dimensions.getSize().getX(); x++)
    		{
    			Point pos = new Point(x,y);
				Pixel otherPixel = otherSprite.getPixel(pos);
				if(otherPixel != null)
				{
					setPixel(pos, otherPixel);
				}
    		}
    	}
    }
}
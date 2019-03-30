package Tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import Crawl.Canvas;
import Crawl.Pixel;
import Crawl.Point;
import Crawl.Sprite;
import Crawl.Square;

class CanvasTest {

	int canvasDefaultVal = 0;
	void spriteShouldOnlyHaveValue(Sprite sprite, int val)
	{
		spriteShouldContainPixels(sprite, val, new Square(new Point(0,0), sprite.getSize()));
	}
	
	Sprite createSpriteWithVal(Point size, int val)
	{
		Sprite tmpSprite = new Sprite(size);
		for(int y = 0; y < size.getY(); y++)
		{
			for(int x = 0; x < size.getX(); x++)
			{
				tmpSprite.setPixel(new Point(x,y), new Pixel(val));
			}
		}
		return tmpSprite;
	}
	
	void spriteShouldContainPixels(Sprite sprite, int val, Square area)
	{
		int maxTotalX = sprite.getSize().getX();
		int maxTotalY = sprite.getSize().getY();
		int minTotalX = 0;
		int minTotalY = 0;
		for(int y = minTotalY; y < maxTotalY; y++)
		{
			for(int x = minTotalX; x < maxTotalX; x++)
			{
				Pixel pix = sprite.getPixel(new Point(x,y));
				int pixVal = pix.getColor();
				if(area.contains(new Point(x,y)))
				{
					assertTrue(pix.getColor() == val);
				}
				else
				{
					assertTrue(pix.getColor() == canvasDefaultVal);
				}
			}
		}
	}

	
	@Test
	void initialize_test() throws IOException 
	{
		Canvas.initialize();
		Sprite img = Canvas.GetPlannedImage();
		spriteShouldOnlyHaveValue(img, canvasDefaultVal);
	}
	
	
	@Test
	void paintSpriteTest() throws IOException
	{
		{ /* painting img at 0 should work */
			Canvas.initialize();
			Point printSize = new Point(10,2);
			int pixelVal = 10;
			Sprite img = createSpriteWithVal(printSize, pixelVal);
			Point printPos = Point.zero();
			
			Canvas.paintSprite(img, printPos);
			
			Sprite plannedImg = Canvas.GetPlannedImage();
			Square area = new Square(printPos, printSize);
			spriteShouldContainPixels(plannedImg, pixelVal, area);
		}
		{ /* painting img larger than screen should just print what's needed */
			Canvas.initialize();
			Point canvasSize = Canvas.getScreenSize();
			canvasSize.multiply(2);
			Point printSize = new Point(canvasSize);
			int pixelVal = 10;
			Sprite img = createSpriteWithVal(printSize, pixelVal);
			Point printPos = Point.zero();
			
			Canvas.paintSprite(img, printPos);
			
			Sprite plannedImg = Canvas.GetPlannedImage();
			spriteShouldOnlyHaveValue(plannedImg, pixelVal);
		}
		{ /* painting img at non-zero pos print correctly */
			Canvas.initialize();
			Point printSize = new Point(10,10);
			int pixelVal = 10;
			Sprite img = createSpriteWithVal(printSize, pixelVal);
			Point printPos = new Point(1,1);			
			Canvas.paintSprite(img, printPos);
			
			Sprite plannedImg = Canvas.GetPlannedImage();
			Square area = new Square(printPos, printSize);
			spriteShouldContainPixels(plannedImg, pixelVal, area);
		}

	}
}

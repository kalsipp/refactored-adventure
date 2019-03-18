package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Crawl.Point;
import Crawl.Sprite;
import Crawl.Square;

import org.junit.jupiter.api.Test;

class SpriteTest {
    static final char START_OF_NEW_PIXEL = '@';
	final String examplePixelBig = "\033[48;5;" + 150 + "m  " + "\033[0m";
	final String examplePixelMedium = "\033[48;5;" + 50 + "m  " + "\033[0m";
	final String examplePixelSmall = "\033[48;5;" + 5 + "m  " + "\033[0m";
	@Test
	void loadSpriteFromFileTest() throws FileNotFoundException, IOException 
	{
		Sprite newSprite = Sprite.loadSpriteFromFile("C:\\Users\\marti\\eclipse-workspace\\CrawlEclipse\\Media\\left.img");
		System.out.println("Hello");
	}
	void populateListWithAllBigPixels(Point size, ArrayList<Byte> bytelist)
	{
		for(int height = 0; height < size.getY(); height++)
		{
			
			for(int width = 0; width < size.getX(); width++)
			{
				bytelist.add((byte)START_OF_NEW_PIXEL);
				for(int cha = 0; cha < examplePixelBig.length(); cha++)
				{
					char newCHar = examplePixelBig.charAt(cha);
					bytelist.add((byte)(newCHar));
				}
				bytelist.add((byte)' ');
			}
			bytelist.add((byte)'\n');
		}		
	}
	
	@Test
	void extractPixelsFromByteListTest()
	{
		{
			ArrayList<Byte> fakeImg = new ArrayList<Byte>();
			populateListWithAllBigPixels(new Point(10,10), fakeImg);
//			Sprite newSprite = Sprite.ExtractPixelsFromByteList(fakeImg);
//			assertTrue(newSprite.GetSize().Equals(new Point(10,10)));
		}
	}
	 
}

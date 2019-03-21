package Crawl;
//import java.awt.Graphics2D;

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Crawler 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
        Canvas.initialize();
        int x = 0;
        Console console = System.console();

        for(int i = 0; i < 10; i++)
        {
        	String input = console.readLine();

        	if(x%2 == 0)
        	{
        		Sprite exampleSprite = Sprite.loadSpriteFromFile("C:/Users/marti/eclipse-workspace/CrawlEclipse/Media/left.img");
        		Canvas.paintSprite(exampleSprite);
        	}
        	else
        	{
        		Sprite exampleSprite = Sprite.loadSpriteFromFile("C:/Users/marti/eclipse-workspace/CrawlEclipse/Media/path6.img");
        		Canvas.paintSprite(exampleSprite);
        	}
        	x++;
	    	Canvas.print();
        }
    }
}	
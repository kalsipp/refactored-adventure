package Crawl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

final public class Canvas
{
    static Sprite mainScreen;
    static Sprite previousMainScreen;
    static BufferedWriter printer;
    private Canvas(){}

    public static void initialize() throws IOException
    {

        Point size = getScreenSize();
        Square screenBox = new Square(new Point(0,0), size);
        mainScreen = new Sprite(screenBox);
        printer = new BufferedWriter(new OutputStreamWriter(System.out));
        setCursorPosition(0,0);
        clearScreen();
    }

    private static Point getScreenSize()
    {
//        return new Point(190,63);  //Hard coded for full-sized window.
        return new Point(110,59);  //Hard coded for full-sized window.
    }

    public static void teardown()
    {
    	
    }

    static void setCursorPosition(int px, int py) throws IOException
    {
        px++; //escape is 1 base
        py++;
        printer.write("\033["+py+";"+px+"H");
    } 
    static void clearScreen() throws IOException{
    	printer.write("\033[2J");
    }
    
    public static void paintSprite(Sprite texture)
    {
    	mainScreen.paste(texture);
    }

    public static void print() throws IOException
    {
    	if(previousMainScreen == null)
    	{
    		fullPrint();
    	}
    	else
    	{
    		selectivePrint();
    	}
    	previousMainScreen = new Sprite(mainScreen);
    }

    private static void selectivePrint() throws IOException
    {
    	for(int y = 0; y < mainScreen.getSize().getY(); y++)
    	{
        	for(int x = 0; x < mainScreen.getSize().getX(); x++)
        	{
        		Point pos = new Point(x,y);
        		Pixel nextPixel = mainScreen.getPixel(pos);
        		Pixel oldPixel = previousMainScreen.getPixel(pos);
        		int oldVal = oldPixel.getColor();
        		int nextVal = nextPixel.getColor();
        		if(! nextPixel.equals(oldPixel))
        		{
        			setCursorPosition(Pixel.pixelWidth()*x,y);
        			printer.write(nextPixel.getValue());
        			printer.flush();
        		}
        	}
    	}
    }
    
    private static void fullPrint() throws IOException
    {
    	clearScreen();
    	setCursorPosition(0,0);
    	for(int y = 0; y < mainScreen.getSize().getY(); y++)
    	{
    		String nextLine = "";
        	for(int x = 0; x < mainScreen.getSize().getX(); x++)
        	{
        		Point pos = new Point(x,y);
        		Pixel nextPixel = mainScreen.getPixel(pos);
        		nextLine += nextPixel.getValue();
        	}
        	printer.write(nextLine+"\n");
    	}
        printer.flush();
    }
    
}
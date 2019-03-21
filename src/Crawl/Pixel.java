package Crawl;

public class Pixel
{
    int color;

    public Pixel(int _color)
    {
        color = _color;
    }
    
    /*
     * Returns how many cells wide each pixel is on the terminal.
     */
    public static int pixelWidth()
    {
    	return 2;
    }
    public int getColor()
    {
    	return color;
    }
    
    public String getValue()
    {
        return "\033[48;5;" + color + "m  " + "\033[0m";
    }
    
    public boolean equals(Pixel other)
    {
    	return other.color == color;
    }
    
}
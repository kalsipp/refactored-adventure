package Crawl;

public class Pixel
{
    int color;

    public Pixel(int _color)
    {
        color = _color;
    }
    public String getValue()
    {
        return "\033[48;5;" + color + "m  " + "\033[0m";
    }
}
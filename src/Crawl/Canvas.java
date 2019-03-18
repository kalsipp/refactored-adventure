package Crawl;


final public class Canvas
{
    static Sprite mainScreen;

    private Canvas(){}

    public static void initialize()
    {

        Point size = getScreenSize();
        Square screenBox = new Square(new Point(0,0), size);
        mainScreen = new Sprite(screenBox);
        setCursorPosition(0,0);
        clearScreen();
        System.out.print("HelloWorld!");
    }

    private static Point getScreenSize()
    {
        return new Point(190,63);  //Hard coded for full-sized window.
    }

    public static void teardown()
    {

    }

    static void setCursorPosition(int px, int py)
    {
        px++; //escape is 1 base
        py++;
        System.out.print("\033["+py+";"+px+"H");
    } 
    static void clearScreen(){
        System.out.print("\033[2J");
    }
    
    public static void paintSprite(Sprite texture)
    {
    	mainScreen.paste(texture);
    }

    public static void print()
    {

    }

}
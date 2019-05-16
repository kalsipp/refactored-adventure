warning: LF will be replaced by CRLF in .gitignore.
The file will have its original line endings in your working directory.
warning: LF will be replaced by CRLF in Media/img_conv_stolen.py.
The file will have its original line endings in your working directory.
warning: LF will be replaced by CRLF in testfile.txt.
The file will have its original line endings in your working directory.
[1mdiff --git a/.gitignore b/.gitignore[m
[1mindex 5184592..b4acc29 100644[m
[1m--- a/.gitignore[m
[1m+++ b/.gitignore[m
[36m@@ -11,4 +11,5 @@[m
 .settings/[m
 .buildpath[m
 .classpath[m
[31m-.project[m
\ No newline at end of file[m
[32m+[m[32m.project[m
[32m+[m[32m/bin/[m
[1mdiff --git a/Media/img_conv_stolen.py b/Media/img_conv_stolen.py[m
[1mindex afb956b..da68150 100644[m
[1m--- a/Media/img_conv_stolen.py[m
[1m+++ b/Media/img_conv_stolen.py[m
[36m@@ -358,6 +358,8 @@[m [mRGB2SHORT_DICT, SHORT2RGB_DICT = _create_dicts()[m
 if __name__ == '__main__':[m
     from PIL import Image[m
     parts = sys.argv[1].split('.')[m
[32m+[m[32m    if len(parts) != 2:[m
[32m+[m[32m        raise AssertionError("Incorrect arguments, Example usage: script.py name.jpg")[m
     output_filename = parts[0] + '.img'[m
     f = open(output_filename, 'w')[m
     im = Image.open(sys.argv[1])[m
[1mdiff --git a/src/Crawl/Canvas.java b/src/Crawl/Canvas.java[m
[1mindex eebc1c5..a4584ad 100644[m
[1m--- a/src/Crawl/Canvas.java[m
[1m+++ b/src/Crawl/Canvas.java[m
[36m@@ -1,41 +1,46 @@[m
 package Crawl;[m
 [m
[32m+[m[32mimport java.io.BufferedWriter;[m
[32m+[m[32mimport java.io.IOException;[m
[32m+[m[32mimport java.io.OutputStreamWriter;[m
 [m
 final public class Canvas[m
 {[m
     static Sprite mainScreen;[m
[31m-[m
[32m+[m[32m    static Sprite previousMainScreen;[m
[32m+[m[32m    static BufferedWriter printer;[m
     private Canvas(){}[m
 [m
[31m-    public static void initialize()[m
[32m+[m[32m    public static void initialize() throws IOException[m
     {[m
 [m
         Point size = getScreenSize();[m
         Square screenBox = new Square(new Point(0,0), size);[m
         mainScreen = new Sprite(screenBox);[m
[32m+[m[32m        printer = new BufferedWriter(new OutputStreamWriter(System.out));[m
         setCursorPosition(0,0);[m
         clearScreen();[m
[31m-        System.out.print("HelloWorld!");[m
     }[m
 [m
     private static Point getScreenSize()[m
     {[m
[31m-        return new Point(190,63);  //Hard coded for full-sized window.[m
[32m+[m[32m//        return new Point(190,63);  //Hard coded for full-sized window.[m
[32m+[m[32m        return new Point(110,59);  //Hard coded for full-sized window.[m
     }[m
 [m
     public static void teardown()[m
     {[m
[31m-[m
[32m+[m[41m    	[m
     }[m
 [m
[31m-    static void setCursorPosition(int px, int py)[m
[32m+[m[32m    static void setCursorPosition(int px, int py) throws IOException[m
     {[m
         px++; //escape is 1 base[m
         py++;[m
[31m-        System.out.print("\033["+py+";"+px+"H");[m
[32m+[m[32m        printer.write("\033["+py+";"+px+"H");[m
     } [m
[31m-    static void clearScreen(){[m
[31m-        System.out.print("\033[2J");[m
[32m+[m[32m    static void clearScreen() throws IOException{[m
[32m+[m[41m    [m	[32mprinter.write("\033[2J");[m
     }[m
     [m
     public static void paintSprite(Sprite texture)[m
[36m@@ -43,9 +48,56 @@[m [mfinal public class Canvas[m
     	mainScreen.paste(texture);[m
     }[m
 [m
[31m-    public static void print()[m
[32m+[m[32m    public static void print() throws IOException[m
     {[m
[31m-[m
[32m+[m[41m    [m	[32mif(previousMainScreen == null)[m
[32m+[m[41m    [m	[32m{[m
[32m+[m[41m    [m		[32mfullPrint();[m
[32m+[m[41m    [m	[32m}[m
[32m+[m[41m    [m	[32melse[m
[32m+[m[41m    [m	[32m{[m
[32m+[m[41m    [m		[32mselectivePrint();[m
[32m+[m[41m    [m	[32m}[m
[32m+[m[41m    [m	[32mpreviousMainScreen = new Sprite(mainScreen);[m
     }[m
 [m
[32m+[m[32m    private static void selectivePrint() throws IOException[m
[32m+[m[32m    {[m
[32m+[m[41m    [m	[32mfor(int y = 0; y < mainScreen.getSize().getY(); y++)[m
[32m+[m[41m    [m	[32m{[m
[32m+[m[41m        [m	[32mfor(int x = 0; x < mainScreen.getSize().getX(); x++)[m
[32m+[m[41m        [m	[32m{[m
[32m+[m[41m        [m		[32mPoint pos = new Point(x,y);[m
[32m+[m[41m        [m		[32mPixel nextPixel = mainScreen.getPixel(pos);[m
[32m+[m[41m        [m		[32mPixel oldPixel = previousMainScreen.getPixel(pos);[m
[32m+[m[41m        [m		[32mint oldVal = oldPixel.getColor();[m
[32m+[m[41m        [m		[32mint nextVal = nextPixel.getColor();[m
[32m+[m[41m        [m		[32mif(! nextPixel.equals(oldPixel))[m
[32m+[m[41m        [m		[32m{[m
[32m+[m[41m        [m			[32msetCursorPosition(Pixel.pixelWidth()*x,y);[m
[32m+[m[41m        [m			[32mprinter.write(nextPixel.getValue());[m
[32m+[m[41m        [m			[32mprinter.flush();[m
[32m+[m[41m        [m		[32m}[m
[32m+[m[41m        [m	[32m}[m
[32m+[m[41m    [m	[32m}[m
[32m+[m[32m    }[m
[32m+[m[41m    [m
[32m+[m[32m    private static void fullPrint() throws IOException[m
[32m+[m[32m    {[m
[32m+[m[41m    [m	[32mclearScreen();[m
[32m+[m[41m    [m	[32msetCursorPosition(0,0);[m
[32m+[m[41m    [m	[32mfor(int y = 0; y < mainScreen.getSize().getY(); y++)[m
[32m+[m[41m    [m	[32m{[m
[32m+[m[41m    [m		[32mString nextLine = "";[m
[32m+[m[41m        [m	[32mfor(int x = 0; x < mainScreen.getSize().getX(); x++)[m
[32m+[m[41m        [m	[32m{[m
[32m+[m[41m        [m		[32mPoint pos = new Point(x,y);[m
[32m+[m[41m        [m		[32mPixel nextPixel = mainScreen.getPixel(pos);[m
[32m+[m[41m        [m		[32mnextLine += nextPixel.getValue();[m
[32m+[m[41m        [m	[32m}[m
[32m+[m[41m        [m	[32mprinter.write(nextLine+"\n");[m
[32m+[m[41m    [m	[32m}[m
[32m+[m[32m        printer.flush();[m
[32m+[m[32m    }[m
[32m+[m[41m    [m
 }[m
\ No newline at end of file[m
[1mdiff --git a/src/Crawl/Crawler.java b/src/Crawl/Crawler.java[m
[1mindex d044ab3..a883480 100644[m
[1m--- a/src/Crawl/Crawler.java[m
[1m+++ b/src/Crawl/Crawler.java[m
[36m@@ -1,10 +1,35 @@[m
 package Crawl;[m
 //import java.awt.Graphics2D;[m
 [m
[32m+[m[32mimport java.io.Console;[m
[32m+[m[32mimport java.io.FileNotFoundException;[m
[32m+[m[32mimport java.io.IOException;[m
[32m+[m[32mimport java.util.Scanner;[m
[32m+[m
 public class Crawler [m
 {[m
[31m-    public static void main( String[] args )[m
[32m+[m[32m    public static void main( String[] args ) throws FileNotFoundException, IOException[m
     {[m
         Canvas.initialize();[m
[32m+[m[32m        int x = 0;[m
[32m+[m[32m        Console console = System.console();[m
[32m+[m
[32m+[m[32m        for(int i = 0; i < 10; i++)[m
[32m+[m[32m        {[m
[32m+[m[41m        [m	[32mString input = console.readLine();[m
[32m+[m
[32m+[m[41m        [m	[32mif(x%2 == 0)[m
[32m+[m[41m        [m	[32m{[m
[32m+[m[41m        [m		[32mSprite exampleSprite = Sprite.loadSpriteFromFile("C:/Users/marti/eclipse-workspace/CrawlEclipse/Media/left.img");[m
[32m+[m[41m        [m		[32mCanvas.paintSprite(exampleSprite);[m
[32m+[m[41m        [m	[32m}[m
[32m+[m[41m        [m	[32melse[m
[32m+[m[41m        [m	[32m{[m
[32m+[m[41m        [m		[32mSprite exampleSprite = Sprite.loadSpriteFromFile("C:/Users/marti/eclipse-workspace/CrawlEclipse/Media/path6.img");[m
[32m+[m[41m        [m		[32mCanvas.paintSprite(exampleSprite);[m
[32m+[m[41m        [m	[32m}[m
[32m+[m[41m        [m	[32mx++;[m
[32m+[m	[41m    [m	[32mCanvas.print();[m
[32m+[m[32m        }[m
     }[m
 }	[m
\ No newline at end of file[m
[1mdiff --git a/src/Crawl/Pixel.java b/src/Crawl/Pixel.java[m
[1mindex e8e9b68..8041f9a 100644[m
[1m--- a/src/Crawl/Pixel.java[m
[1m+++ b/src/Crawl/Pixel.java[m
[36m@@ -8,
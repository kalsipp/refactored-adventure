package graphics;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import base.Point;

final public class Canvas {


    /* ---- Data ---- */
    private static Sprite mainScreen;
    private static Sprite previousMainScreen;
    private static BufferedWriter printer;

    /* ---- Public ---- */

    public static void initialize() throws IOException {

        Point size = getInternalScreenSize();
        mainScreen = new Sprite(size);
        printer = new BufferedWriter(new OutputStreamWriter(System.out));
        setCursorPosition(0, 0);
        clearScreen();
    }

    /*
     * Returns a copy of the currently planned image to render.
     */
    public static Sprite GetPlannedImage() {
        return new Sprite(mainScreen);
    }

    public static void teardown() {
    }

    /*
     * Will paint the sprite to be printed in the
     * next rendering.
     */
    public static void paintSprite(Sprite texture, Point position) {
        mainScreen.paste(texture, position);
    }

    /*
     * "Renders" or prints the image onto the terminal.
     */
    public static void render() throws IOException {
        if (previousMainScreen == null) {
            fullPrint();
        } else {
            selectivePrint();
        }
        previousMainScreen = new Sprite(mainScreen);
        mainScreen.clearSpriteToPixel(new Pixel(0));
    }

    public static Point getScreenSize() {
        return getInternalScreenSize();
    }

    /* ---- Private ---- */

    private Canvas() {
    }

    private static Point getInternalScreenSize() {
        return new Point(110, 59);  //Hard coded for full-sized window.
    }

    /*
     * Will print an escape command to moveForward the terminal cursor
     * to the requested cell.
     */
    private static void setCursorPosition(int px, int py) throws IOException {
        px++; //escape is 1 base
        py++;
        printer.write("\033[" + py + ";" + px + "H");
    }

    /*
     * Will print an escape command to clear the screen.
     */
    private static void clearScreen() throws IOException {
        printer.write("\033[2J");
    }

    private static void selectivePrint() throws IOException {
        for (int y = 0; y < mainScreen.getSize().getY(); y++) {
            for (int x = 0; x < mainScreen.getSize().getX(); x++) {
                Point pos = new Point(x, y);
                Pixel nextPixel = mainScreen.getPixel(pos);
                Pixel oldPixel = previousMainScreen.getPixel(pos);
                if (!nextPixel.equals(oldPixel)) {
                    setCursorPosition(Pixel.pixelWidth() * x, y);
                    printer.write(nextPixel.getString());
                    printer.flush();
                }
            }
        }
    }

    private static void fullPrint() throws IOException {
        clearScreen();
        setCursorPosition(0, 0);
        for (int y = 0; y < mainScreen.getSize().getY(); y++) {
            StringBuilder nextLine = new StringBuilder();
            for (int x = 0; x < mainScreen.getSize().getX(); x++) {
                Point pos = new Point(x, y);
                Pixel nextPixel = mainScreen.getPixel(pos);
                nextLine.append(nextPixel.getString());
            }
            printer.write(nextLine + "\n");
        }
        printer.flush();
    }

}
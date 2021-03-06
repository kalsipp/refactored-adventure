package graphics;

public class Pixel {
    private final int color;

    public Pixel(int _color) {
        color = _color;
    }

    public Pixel(Pixel otherPixel) {
        color = otherPixel.color;
    }

    /*
     * Returns how many cells wide each pixel is on the terminal.
     */
    @SuppressWarnings("SameReturnValue")
    public static int pixelWidth() {
        return 2;
    }

    public int getColor() {
        return color;
    }

    public String getString() {
        return "\033[48;5;" + color + "m  " + "\033[0m";
    }

    public boolean equals(Pixel other) {
        return other.color == color;
    }

}
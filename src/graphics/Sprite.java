package graphics;

import java.io.IOException;

import base.Point;
import base.Square;

public class Sprite {
    private final Pixel[][] pixels;
    private final Point size;

    public Sprite(Point _size) {
        size = _size;
        pixels = new Pixel[size.getY()][size.getX()];
        clearSpriteToPixel(new Pixel(0));
    }

    public Sprite(Point _size, Pixel pix) {
        size = _size;
        pixels = new Pixel[size.getY()][size.getX()];
        clearSpriteToPixel(pix);
    }

    public Sprite(Sprite another) {
        size = new Point(another.size);
        pixels = new Pixel[size.getY()][size.getX()];
        paste(another, Point.zero());
    }

    static public Sprite loadSpriteFromFile(String path) throws IOException {
        return SpriteLoader.loadSpriteFromFile(path);
    }

    public Point getSize() {
        return new Point(size);
    }

    public Pixel getPixel(Point index) {
        Square area = new Square(Point.zero(), size);
        if (area.contains(index)) {
            return new Pixel(pixels[index.getY()][index.getX()]);
        } else {
            return null;
        }
    }

    public void setPixel(Point index, Pixel newPixel) {
        Square area = new Square(Point.zero(), size);
        if (area.contains(index)) {
            pixels[index.getY()][index.getX()] = newPixel;
        }
    }

    /*
     * Set the whole sprite to this pixel's color.
     */
    public void clearSpriteToPixel(Pixel pix) {
        for (int y = 0; y < size.getY(); y++) {
            for (int x = 0; x < size.getX(); x++) {
                Point pos = new Point(x, y);
                setPixel(pos, pix);
            }
        }
    }

    /*
     * Pastes other sprite onto me.
     */
    public void paste(Sprite otherSprite, Point offset) {
        int maxY = otherSprite.size.getY();
        int maxX = otherSprite.size.getX();
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                Point posInOther = new Point(x, y);
                Pixel otherPixel = otherSprite.getPixel(posInOther);
                posInOther.add(offset); // now pos in me
                setPixel(posInOther, otherPixel);
            }
        }
    }
}
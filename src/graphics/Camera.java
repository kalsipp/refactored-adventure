package graphics;


import java.util.logging.Level;
import java.util.logging.Logger;

import base.Point;
import base.Vector2;
import mapping.Map;
import mapping.Tile;

public class Camera
{

    private final static Logger LOGGER = Logger.getLogger(Camera.class.getName());
    final boolean useTextures = true;
    private Vector2 cameraPosition;
    private Vector2 cameraDirection;
    private Vector2 cameraPlane = new Vector2(0, 1); // Always perpendicular to cameraDir

    public Camera()
    {
        cameraPosition = Vector2.zero();
        cameraDirection = new Vector2(-1, 0);
    }


    public void setCameraPos(Vector2 position)
    {
        cameraPosition = new Vector2(position);
    }

    public void setCameraDirection(Vector2 direction)
    {
        cameraDirection = new Vector2(direction);
    }

    public void setCameraPlane(Vector2 direction)
    {
        cameraPlane = new Vector2(direction);
    }

    public void move(double amount)
    {
        Vector2 dir = new Vector2(cameraDirection);
        dir.mult(amount);
        cameraPosition.add(dir);
    }

    public void rotate(double amount)
    {
        cameraDirection.rotate(amount);
        cameraPlane.rotate(amount);
    }

    public void renderScreen(final Map currentMap)
    {
        final Point screenSize = Canvas.getScreenSize();
        final int screenWidth = screenSize.getX();
        final int screenHeight = screenSize.getY();
        final double playerX = cameraPosition.getX();
        final double playerY = cameraPosition.getY();
        for (int sliceX = 0; sliceX < screenWidth; sliceX++)
        {
            final double cameraX = (2 * sliceX / ((double) screenWidth)) - 1; //x-coordinate in camera space
            final double rayDirX = cameraDirection.getX() + cameraPlane.getX() * cameraX;
            final double rayDirY = cameraDirection.getY() + cameraPlane.getY() * cameraX;

            final int playerMapPosX = (int) playerX; //no round, expecting truncation
            final int playerMapPosY = (int) playerY;

            //length of ray from one x or y-side to next x or y-side
            final double deltaDistX = Math.abs(1 / rayDirX); //Simplified Pythagoras
            final double deltaDistY = Math.abs(1 / rayDirY);

            //what direction to step in x or y-direction (either +1 or -1)
            int stepX;
            int stepY;
            //length of ray from current position to next x or y-side
            double sideDistX;
            double sideDistY;

            //calculate step and initial sideDist
            //Compensates if player is not perfectly in a tile
            if (rayDirX < 0)
            {
                stepX = -1;
                sideDistX = (playerX - playerMapPosX) * deltaDistX;
            }
            else
            {
                stepX = 1;
                sideDistX = (playerMapPosX + 1.0 - playerX) * deltaDistX;
            }
            if (rayDirY < 0)
            {
                stepY = -1;
                sideDistY = (playerY - playerMapPosY) * deltaDistY;
            }
            else
            {
                stepY = 1;
                sideDistY = (playerMapPosY + 1.0 - playerY) * deltaDistY;
            }

            boolean hit = false; //was there a wall hit?
            int mapX = playerMapPosX;
            int mapY = playerMapPosY;
            int side = 0; //was a NS or a EW wall hit?
            //perform DDA
            while (!hit)
            {
                //jump to next map square, OR in x-direction, OR in y-direction
                if (sideDistX < sideDistY)
                {
                    sideDistX += deltaDistX;
                    mapX += stepX;
                    side = 0;
                }
                else
                {
                    sideDistY += deltaDistY;
                    mapY += stepY;
                    side = 1;
                }
                //Check if ray has hit a wall
                if (!currentMap.IsTilePassableAt(new Point(mapX, mapY)))
                {
                    hit = true;
                }
            }
            double perpWallDist;
            //Calculate distance projected on camera direction (Euclidean distance will give fisheye effect!)
            if (side == 0)
            {
                perpWallDist = (mapX - playerX + (1 - stepX) / 2) / rayDirX;
            }

            else
            {
                perpWallDist = (mapY - playerY + (1 - stepY) / 2) / rayDirY;
            }

            final int lineHeight = (int) (screenHeight / perpWallDist);

            //calculate lowest and highest pixel to fill in current stripe
            int drawStart = -lineHeight / 2 + screenHeight / 2;
            if (drawStart < 0)
            {
                drawStart = 0;
            }
            int drawEnd = lineHeight / 2 + screenHeight / 2;
            if (drawEnd >= screenHeight)
            {
                drawEnd = screenHeight - 1;
            }

            Tile hitTile = currentMap.GetTile(new Point(mapX, mapY));

            renderHitTextureLess(drawStart, drawEnd, sliceX, hitTile, side);
        }
    }

    private void renderHitTextureLess(
            final int drawStart,
            final int drawEnd,
            final int xScreenPos,
            final Tile renderTile,
            final int side)
    {
        if (renderTile == null)
        {
            LOGGER.log(Level.WARNING, "Tried to render a null tile");
        }
        else
        {
            int color = renderTile.getPixel(new Point(0, 0)).getColor();
            if (side == 1)
            {
                color = color / 2;
            }
            Pixel pix = new Pixel(color);
            Sprite img = new Sprite(new Point(1, drawEnd - drawStart));
            img.clearSpriteToPixel(pix);
            Canvas.paintSprite(img, new Point(xScreenPos, drawStart));
        }

    }
}

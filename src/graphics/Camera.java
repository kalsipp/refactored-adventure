package graphics;

import base.Point;
import base.Vector2;
import mapping.Map;
import mapping.Tile;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Camera
{
    private final static Logger LOGGER = Logger.getLogger(CameraTextureLess.class.getName());
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

    public void renderScreen(final Map currentMap) throws IOException
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

            final double perpWallDist = getPerpWallDist(playerX, playerY, rayDirX, rayDirY, stepX, stepY, mapX, mapY, side);

            final int lineHeight = (int) (screenHeight / perpWallDist);

            //calculate lowest and highest pixel to fill in current stripe
            final int drawStart = getDrawStart(screenHeight, lineHeight);
            final int drawEnd = getDrawEnd(screenHeight, lineHeight);


            final Tile hitTile = currentMap.GetTile(new Point(mapX, mapY));
            final Sprite hitSprite = hitTile.getSpriteRef();
            if(hitSprite == null)
            {
                LOGGER.log(Level.WARNING, "Tried to render null sprite");
                continue;
            }
            final Point spriteSize = hitSprite.getSize();
            int textureXPos = getTextureXPos(playerX, playerY, rayDirX, rayDirY, side, perpWallDist, spriteSize);

            Sprite renderPalette = new Sprite(new Point(1, drawEnd-drawStart));

            for(int cameraPosY = drawStart; cameraPosY < drawEnd; cameraPosY++)
            {
                final int textureYPos = getTextureYPos(screenHeight, lineHeight, spriteSize, cameraPosY);
                Pixel hitPix = hitSprite.getPixel(new Point(textureXPos, textureYPos));
                if(hitPix != null)
                {

                    if(side == 0)
                    {
                        hitPix = new Pixel(hitPix.getColor() + 1); /* Mess up the color slightly to cause a contrast */
                    }
                    renderPalette.setPixel(new Point(0, cameraPosY - drawStart), hitPix);
                }
                else
                {
                    /* Seems to render fine even if we randomly get null pixels */
                    //LOGGER.log(Level.WARNING, "Tried to render null pixel");
                }
            }
            Canvas.paintSprite(renderPalette, new Point(sliceX, 0));
        }
    }

    private int getTextureYPos(int screenHeight, int lineHeight, Point spriteSize, int cameraPosY)
    {
        final int d  = (256*cameraPosY+lineHeight*128)-screenHeight*128;
        return ((d * spriteSize.getY()) / lineHeight)/256;
    }

    private double getPerpWallDist(double playerX, double playerY, double rayDirX, double rayDirY, int stepX, int stepY, int mapX, int mapY, int side)
    {
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
        return perpWallDist;
    }

    private int getDrawEnd(int screenHeight, int lineHeight)
    {
        int drawEnd = lineHeight / 2 + screenHeight / 2;
        if (drawEnd >= screenHeight)
        {
            drawEnd = screenHeight - 1;
        }
        return drawEnd;
    }

    private int getDrawStart(final int screenHeight, final int lineHeight)
    {
        int drawStart = -lineHeight / 2 + screenHeight / 2;
        if (drawStart < 0)
        {
            drawStart = 0;
        }
        return drawStart;
    }

    private int getTextureXPos(double playerX, double playerY, double rayDirX, double rayDirY, int side, double perpWallDist, Point spriteSize)
    {
        //calculate value of wallX
        double wallX; //where exactly the wall was hit
        if (side == 0)
        {
            wallX = playerY + perpWallDist * rayDirY;
        }
        else
        {
            wallX = playerX + perpWallDist * rayDirX;
        }
        wallX -= Math.floor(wallX);


        //x coordinate on the texture
        int textureXPos = (int)(wallX * (double)spriteSize.getX());
        if(side == 0 && rayDirX > 0)
        {
            textureXPos = spriteSize.getX() - textureXPos - 1;
        }
        if(side == 1 && rayDirY < 0)
        {
            textureXPos = spriteSize.getX() - textureXPos - 1;
        }
        return textureXPos;
    }

}

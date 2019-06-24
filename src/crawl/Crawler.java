package crawl;

import java.io.Console;
import java.io.IOException;

import base.Helpers;
import base.Point;
import base.Vector2;
import graphics.Camera;
import graphics.Canvas;
import graphics.Pixel;
import graphics.Sprite;
import mapping.Map;
import mapping.MapLoader;

import javax.swing.*;

class Crawler
{
    public static void main( String[] args ) throws IOException
    {
        Canvas.initialize();
        Console console = System.console();
        final String baseFilename = "testfile.yeoldemappe";
        String baseMap = 
        		  "########\n"
        		+ "#......#\n"
        		+ "#......#\n"
        		+ "#...#..#\n"
				+ "#...#..#\n"
				+ "#...#..#\n"
				+ "########";
        Helpers.writeDataToFile(baseFilename, baseMap);
		Map activeMap = MapLoader.loadMapFromFile(baseFilename);
		Camera cam = new Camera();
		cam.setCameraPos(new Vector2(2.5,2.5));
		JFrame frame = new JFrame("Fugg");
		frame.setVisible(true);
		frame.setSize(400, 400);
		KeySniffer keyboard = new KeySniffer();
		frame.addKeyListener(keyboard);
		Point screenSize = Canvas.getScreenSize();
		Sprite background = new Sprite(screenSize, new Pixel(7));
		Sprite grid = Sprite.loadSpriteFromFile("sprites/grid.img");

		Canvas.paintSprite(background, Point.zero());
		Canvas.paintSprite(grid, Point.zero());
		cam.renderScreen(activeMap);
		Canvas.render();
		while(true)
		{

			if(keyboard.newKeyPressed())
			{
				Canvas.paintSprite(background, Point.zero());
				Canvas.paintSprite(grid, Point.zero());
				move(keyboard.getKeyPressed(), cam, activeMap);
				cam.renderScreen(activeMap);
				Canvas.render();
			}

		}
    }

	private static void move(final char keyPressed, Camera cam, final Map map)
	{



		int plannedMovement = 0;
		if(keyPressed == 'w')
		{
			plannedMovement = 1;
		}
		else if(keyPressed == 's')
		{
			plannedMovement = -1;
		}
		else if (keyPressed == 'q')
		{
			cam.rotate(-90);
		}
		else if(keyPressed == 'e')
		{
			cam.rotate(90);
		}

		/* Only move if the tile is free */
		if(plannedMovement != 0)
		{
			Vector2 playerPos = cam.getCameraPos();
			Vector2 playerDir = cam.getCameraDir();

			playerDir.mult(plannedMovement);
			playerPos.add(playerDir);
			playerPos.add(new Vector2(-0.5, -0.5));
			if(map.IsTilePassableAt(new Point(playerPos)))
			{
				cam.moveForward(plannedMovement);
			}
		}
	}
}	
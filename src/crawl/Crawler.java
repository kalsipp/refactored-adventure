package crawl;

import java.io.IOException;

import base.Helpers;
import base.Point;
import base.Vector2;
import graphics.Camera;
import graphics.Canvas;
import graphics.Pixel;
import graphics.Sprite;
import mapping.*;

import javax.swing.*;

class Crawler
{

	enum GAMESTATE
	{
		LOADING_MAP,
		EXPLORING
	}
	static GAMESTATE gamestate = GAMESTATE.EXPLORING;
	public static void setNewMapLoaded()
	{
		gamestate = GAMESTATE.LOADING_MAP;
	}

    public static void main( String[] args ) throws Exception
    {
        Canvas.initialize();
        SceneHandler.initialize();
        final String baseFilename = "maps/map2.yeoldemappe";
//        String baseMap =
//				  "########\n"
//        		+ "##^#####\n"
//        		+ "#......#\n"
//        		+ "#......#\n"
//        		+ "#...#..#\n"
//				+ "#...#..#\n"
//				+ "#...#..#\n"
//				+ "########";
//        Helpers.writeDataToFile(baseFilename, baseMap);
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
			switch (gamestate)
			{
				case LOADING_MAP:
					loadMapState(cam);
					break;
				case EXPLORING:
					exploringState(keyboard, cam, background, grid);
					break;

			}
		}
    }

	private static void exploringState(KeySniffer keyboard, Camera cam, Sprite background, Sprite grid) throws Exception
	{
		if(keyboard.newKeyPressed())
		{
			handleUpdate(keyboard.getKeyPressed(), cam, background, grid);
			keyboard.resetNewKeyPressedState();
		}
	}

	private static void loadMapState(Camera cam) throws Exception
	{
		Map activeMap = SceneHandler.getActiveMap();

		Point entryTile = getEntryTile(activeMap);
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		Point startPos = null;
		for(int i = 0; i < dx.length; i++)
		{
			Point newPoint = new Point(entryTile);
			newPoint.add(dx[i], dy[i]);
			if(activeMap.isInsideMap(newPoint))
			{
				if(activeMap.isTilePassableAt(newPoint))
				{
					startPos = newPoint;
				}
			}
		}

		if(startPos == null) throw new Exception("No free area around starting point");
		cam.setCameraPos(new Vector2(startPos));
		cam.renderScreen(activeMap);
		Canvas.render();
		gamestate = GAMESTATE.EXPLORING;
	}

	private static Point getEntryTile(Map activeMap) throws Exception
	{
		Point size = activeMap.getSize();
		Point startPos = null;
		for(int y = 0; y < size.getY(); y++)
		{
			for(int x = 0; x < size.getX(); x++)
			{
				Point pos = new Point(x,y);
				Tile t = activeMap.getTile(pos);

				if(SceneHandler.transitionedDeeper())
				{
					if (t instanceof StairsUp)
					{
						startPos = pos;
					}
				}
				else
				{
					if (t instanceof StairsDown)
					{
						startPos = pos;
					}
				}
			}
		}

		if(startPos == null) throw new Exception("Could not find eligible starting point in map");
		return startPos;
	}

	private static synchronized void handleUpdate(final char keyPressed, Camera cam, Sprite background, Sprite grid) throws Exception
	{
		Map activeMap = SceneHandler.getActiveMap();
		Canvas.paintSprite(background, Point.zero());
		Canvas.paintSprite(grid, Point.zero());
		Vector2 oldPos = cam.getCameraPos();
		move(keyPressed, cam, activeMap);
		Vector2 newPos = cam.getCameraPos();
		if(!oldPos.isEqual(newPos))
		{
			Tile tile = activeMap.getTile(new Point(newPos));
			tile.onTileEnter();
		}
		cam.renderScreen(activeMap);
		Canvas.render();
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
			if(map.isTilePassableAt(new Point(playerPos)))
			{
				cam.moveForward(plannedMovement);
			}
		}
	}
}
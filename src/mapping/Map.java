package mapping;

import base.Point;

public class Map 
{
	private final Tile[][] tiles;
	public final static double tileSize = 1;
	public Map(Point size)
	{
		tiles = new Tile[size.getY()][size.getX()];
		for(int y = 0; y < size.getY(); y++)
		{
			for(int x = 0; x < size.getX(); x++)
			{
				setTile(new Point(x,y), new Tile());
			}
		}
	}

	public Point getSize()
	{

		int y = tiles.length;
		int x = tiles[0].length;
		return new Point(x, y);
	}

	public boolean isInsideMap(Point pos)
	{
		return tilesContainPos(pos);
	}
	
	public void setTile(Point pos, Tile newTile)
	{
		if(tilesContainPos(pos))
		{
			tiles[pos.getY()][pos.getX()] = newTile;
		}
	}

	public boolean isTileVisibleAt(Point pos)
	{
		Tile tile = getTile(pos);
		if(tile != null)
		{
			return tile.isVisible();
		}
		else
		{
			return false;
		}
	}

	public boolean isTilePassableAt(Point pos)
	{
		Tile tile = getTile(pos);
		if(tile != null)
		{
			return tile.isPassable();
		}
		else
		{	
			return false;
		}
	}
	
	public Tile getTile(Point pos)
	{
		if(tilesContainPos(pos))
		{
			return tiles[pos.getY()][pos.getX()];
		}
		else
		{
			return null;
		}
		
	}
	
	private boolean tilesContainPos(Point pos)
	{
		final int y = pos.getY();
		final int x = pos.getX();
		if(tiles.length > y && y >= 0)
		{
			return tiles[y].length > x && x >= 0;
		}
		return false;
	}

	public Point getStartingPosition()
	{
		Point startPos = null;
		for(int y = 0; y < tiles.length; y++)
		{
			for(int x = 0; x < tiles[y].length; x++)
			{
				Point pos = new Point(x,y);
				Tile tile = getTile(pos);
				boolean start = tile.isStart();
				if(start)
				{
					startPos = pos;
				}
			}
		}
		return startPos;
	}
}

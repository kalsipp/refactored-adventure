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
				SetTile(new Point(x,y), new Tile());
			}
		}
	}
	
	public void SetTile(Point pos, Tile newTile)
	{
		if(tilesContainPos(pos))
		{
			tiles[pos.getY()][pos.getX()] = newTile;
		}
	}

	public boolean IsTileVisibleAt(Point pos)
	{
		Tile tile = GetTile(pos);
		if(tile != null)
		{
			return tile.isVisible();
		}
		else
		{
			return false;
		}
	}

	public boolean IsTilePassableAt(Point pos)
	{
		Tile tile = GetTile(pos);
		if(tile != null)
		{
			return tile.isPassable();
		}
		else
		{	
			return false;
		}
	}
	
	public Tile GetTile(Point pos)
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
}

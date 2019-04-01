package mapping;

import base.Point;

public class Map 
{
	Tile[][] tiles;
	Point size;
	
	public Map(Point _size)
	{
		size = _size;
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
		int y = pos.getY();
		int x = pos.getX();
		if(tiles.length > y)
		{
			if(tiles[y].length > x)
			{
				tiles[y][x] = newTile;
			}
		}
	}
	
	public Tile GetTile(Point pos)
	{
		int y = pos.getY();
		int x = pos.getX();
		if(tiles.length > y)
		{
			if(tiles[y].length > x)
			{
				return new Tile(tiles[y][x]);
			}
		}
		
		return null;
	}
	
}

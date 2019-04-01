package mapping;

import base.Point;

public class Tile 
{
	boolean isWall;
	public Tile()
	{
		isWall = true;
	}
	
	public Tile(Tile tile) 
	{
		isWall = tile.isWall;
	}

	public void open() 
	{
		isWall =  true;
	}
	public void close()
	{
		isWall = false;
	}
}

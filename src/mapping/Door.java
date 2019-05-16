package mapping;

public final class Door extends Tile 
{
	private final String targetFloor;
	public Door(String _targetFloor)
	{
		targetFloor = _targetFloor;
		isPassable = false;
	}
	
	public String getTargetFloor()
	{
		return targetFloor;
	}
}

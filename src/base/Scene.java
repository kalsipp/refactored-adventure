package base;

import mapping.Map;

public class Scene 
{
	
	Map currentMap;
	public void setActiveMap(Map map)
	{
		currentMap = map;
	}

	public Map getActiveMap() 
	{
		return currentMap;
	}

}

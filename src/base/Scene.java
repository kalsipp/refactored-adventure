package base;

import mapping.Map;

class Scene
{
	
	private Map currentMap;
	public void setActiveMap(Map map)
	{
		currentMap = map;
	}

	public Map getActiveMap() 
	{
		return currentMap;
	}

}

package mapping;

import base.Point;
import graphics.Pixel;
import graphics.Sprite;

public class Wall extends Tile 
{
	public Wall(Sprite _sprite)
	{
		sprite = _sprite;
	}
	
	@Override
	public Pixel getPixel(Point position)
	{
		Pixel pix = sprite.getPixel(position);
		if(pix != null)
		{
			return pix;
		}
		else
		{
			return defaultPixel;
		}
	}
	
	@Override
	public Point getSpriteSize()
	{
		return sprite.getSize();
	}
	
}

package base;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class KeySniffer implements KeyListener
{
	static boolean[] keyStatuses;
	static boolean[] keyStatusesNoClear;
	static final int bufferSize = 1000;
	
	public KeySniffer()
	{
		keyStatuses = new boolean[bufferSize];
		keyStatusesNoClear = new boolean[bufferSize];
	}
	
	public void reset()
	{
		Arrays.fill(keyStatuses, false);
	}
	
	public boolean isKeyDown(int keycode)
	{
		return keyStatusesNoClear[keycode];
	}
	
	public boolean keyPressed(int keycode)
	{
		
		return keyStatuses[keycode];
	}

    @Override
    public void keyTyped(KeyEvent e) 
    {

    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
		System.out.println("up " + e.getKeyCode());

    	keyStatuses[e.getKeyCode()] = false;
    	keyStatusesNoClear[e.getKeyCode()] = false;
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
		System.out.println("down " + e.getKeyCode());
    	keyStatuses[e.getKeyCode()] = true;
    	keyStatusesNoClear[e.getKeyCode()] = true;
    }
}

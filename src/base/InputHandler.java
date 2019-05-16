package base;

import javax.swing.JFrame;

public final class InputHandler 
{
	private static KeySniffer myListener;
	public static void initialize()
	{
		myListener = new KeySniffer();
		JFrame f=new JFrame();
		f.addKeyListener(myListener);

	}
	
	public static void reset()
	{
		myListener.reset();
	}
	
	public static boolean isKeyDown(int keycode)
	{
		return myListener.isKeyDown(keycode);
	}
	
	public static boolean keyPressed(int keycode)
	{
		
		return myListener.keyPressed(keycode);
	}
        
}


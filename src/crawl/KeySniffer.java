package crawl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeySniffer implements KeyListener
{

    final static public char UNKNOWN_CHAR = (char)0;
    char pressedKey = UNKNOWN_CHAR;
    enum KEYPRESSSTATE
    {
        PRESSED,
        NOT_PRESSED
    }
    boolean newKeyPressed = false;
    KEYPRESSSTATE keyPressState = KEYPRESSSTATE.NOT_PRESSED;

    public synchronized void resetNewKeyPressedState()
    {
        newKeyPressed = false;
    }

    synchronized void handleKeyPressed(KeyEvent e)
    {
        if(keyPressState == KEYPRESSSTATE.NOT_PRESSED)
        {
            pressedKey = e.getKeyChar();
            keyPressState = KEYPRESSSTATE.PRESSED;
            newKeyPressed = true;
        }
    }

    synchronized void handleKeyReleased(KeyEvent e)
    {
        if(keyPressState == KEYPRESSSTATE.PRESSED && e.getKeyChar() == pressedKey)
        {
            keyPressState = KEYPRESSSTATE.NOT_PRESSED;
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        handleKeyPressed(e);
    }

    public synchronized boolean newKeyPressed()
    {
        return newKeyPressed;
    }

    public synchronized char getKeyPressed()
    {
        return pressedKey;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        handleKeyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e)
    { /* Not used, mandatory function */ }
}

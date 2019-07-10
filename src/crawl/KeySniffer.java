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
    };
    boolean newKeyPressed = false;
    KEYPRESSSTATE keyPressState = KEYPRESSSTATE.NOT_PRESSED;
    KEYPRESSSTATE lastFrameKeyPressState = KEYPRESSSTATE.NOT_PRESSED;

    public synchronized void resetNewKeyPressedState()
    {
        newKeyPressed = false;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(keyPressState == KEYPRESSSTATE.NOT_PRESSED)
        {
            pressedKey = e.getKeyChar();
            keyPressState = KEYPRESSSTATE.PRESSED;
            newKeyPressed = true;
        }

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

        if(keyPressState == KEYPRESSSTATE.PRESSED && e.getKeyChar() == pressedKey)
        {
            keyPressState = KEYPRESSSTATE.NOT_PRESSED;
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    { /* Not used, mandatory function */ }
}

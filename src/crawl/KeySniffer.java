package crawl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeySniffer implements KeyListener
{
    public void keyPressed(KeyEvent evt) { /* Not used */ }

    boolean pressedKeyValidValue = false;
    char pressedKey;
    enum KEYPRESSSTATE
    {
        PRESSED,
        NOT_PRESSED
    };
    KEYPRESSSTATE keyPressState = KEYPRESSSTATE.NOT_PRESSED;
    KEYPRESSSTATE lastFrameKeyPressState = KEYPRESSSTATE.NOT_PRESSED;
    public void updateKeypressState()
    {
        lastFrameKeyPressState = keyPressState;
    }

    public boolean newKeyPressed()
    {
        return lastFrameKeyPressState == KEYPRESSSTATE.NOT_PRESSED && keyPressState == KEYPRESSSTATE.PRESSED;
    }

    public char getKeyPressed()
    {
        return pressedKey;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(pressedKeyValidValue && e.getKeyChar() == pressedKey)
        {
            pressedKeyValidValue = false;
            keyPressState = KEYPRESSSTATE.NOT_PRESSED;
        }
        updateKeypressState();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        if(!pressedKeyValidValue)
        {
            pressedKeyValidValue = true;
            pressedKey = e.getKeyChar();
            keyPressState = KEYPRESSSTATE.PRESSED;
        }
        updateKeypressState();

    }
}

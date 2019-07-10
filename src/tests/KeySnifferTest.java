package tests;

import crawl.KeySniffer;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class DummyComp extends Component
{

}



public class KeySnifferTest
{
    KeyEvent makeDummyKeyEvent(char key)
    {
        Component cmp = new DummyComp();
        KeyEvent event = new KeyEvent(cmp, 0, 0, 0, 0, key);
        return event;
    }
    @Test
    void keyPressedTest()
    {
        { /* No button has been pressed so far*/
            KeySniffer sniff = new KeySniffer();
            sniff.keyPressed(makeDummyKeyEvent('w'));
            assertEquals('w', sniff.getKeyPressed());
            assertTrue(sniff.newKeyPressed());
        }
        { /* Press two buttons, first one saved, still new key pressed*/
            KeySniffer sniff = new KeySniffer();
            sniff.keyPressed(makeDummyKeyEvent('w'));
            sniff.keyPressed(makeDummyKeyEvent('s'));
            assertEquals('w', sniff.getKeyPressed());
            assertTrue(sniff.newKeyPressed());
        }
        { /* Press button, then notify that you have handled it */
            KeySniffer sniff = new KeySniffer();
            sniff.keyPressed(makeDummyKeyEvent('w'));
            sniff.resetNewKeyPressedState();
            assertEquals('w', sniff.getKeyPressed());
            assertFalse(sniff.newKeyPressed());
        }
    }

    @Test
    void keyReleasedTest()
    {
        { /* Release when no button pressed, should return 0 char */
            KeySniffer sniff = new KeySniffer();
            sniff.keyReleased(makeDummyKeyEvent('w'));
            assertEquals(KeySniffer.UNKNOWN_CHAR, sniff.getKeyPressed());
            assertFalse(sniff.newKeyPressed());
        }
        { /* Release same key as we pressed. Should still remember last pressed key */
            KeySniffer sniff = new KeySniffer();
            sniff.keyPressed(makeDummyKeyEvent('w'));
            sniff.keyReleased(makeDummyKeyEvent('w'));
            assertEquals('w', sniff.getKeyPressed());
            assertTrue(sniff.newKeyPressed());
        }
        { /* Release other key*/
            KeySniffer sniff = new KeySniffer();
            sniff.keyPressed(makeDummyKeyEvent('w'));
            sniff.keyReleased(makeDummyKeyEvent('s'));
            assertEquals('w', sniff.getKeyPressed());
            assertTrue(sniff.newKeyPressed());
        }
    }

}

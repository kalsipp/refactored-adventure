package crawl;

import javax.swing.*;
import java.util.ArrayDeque;

public final class WindowLogger
{
    static JTextArea textObj;
    static ArrayDeque<String> textLines;
    static final int maxLength = 20;
    static void initialize(JFrame frame)
    {
        textObj = new JTextArea();
        textObj.setEditable(false);
        frame.add(textObj);
        textLines = new ArrayDeque<>();
    }

    static synchronized void logLine(String newLine)
    {
        textLines.add(newLine + '\n');
        if(textLines.size() > maxLength)
        {
            textLines.pop();
        }

        StringBuilder outSB = new StringBuilder();

        textLines.forEach(outSB::append);


        textObj.setText(outSB.toString());
    }
}

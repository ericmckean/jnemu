package jnemu;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;

public class Console extends JDialog
{
    int scWidth = 500;
    int scHeight = 400;
    private static JTextPane txt;
    private static StringBuilder sb;

    Console()
    {
        /********************** Main Window ***********************/
        setTitle("Console");
        setSize(scWidth,scHeight);
        setResizable(false);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dim = t.getScreenSize();
        setLocation((dim.width / 2) - (scWidth / 2), (dim.height / 2) - (scHeight / 2));

        /*********************** Console output area ************************/
        txt = new JTextPane();
        txt.setEditable(false);
        JScrollPane jp = new JScrollPane(txt);
        jp.setLocation(0,0);
        add(jp);

        SimpleAttributeSet sa = new SimpleAttributeSet();
        StyleConstants.setAlignment(sa, StyleConstants.ALIGN_LEFT);
        txt.setParagraphAttributes(sa, true);

        sb = new StringBuilder();
    }

    public static void print(String str)
    {
        sb.append(str);
        sb.append("\n");
        txt.setText(sb.toString());
        
    }

    public static void clearConsole()
    {
        sb = null;
        txt.setText(sb.toString());
    }

}

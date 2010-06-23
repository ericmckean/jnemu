package jnemu;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Console extends JDialog
{
    int scWidth = 600;
    int scHeight = 500;
    private static JTextPane txt;
    private static StringBuilder sb;
    public static SimpleAttributeSet sa;

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
        txt.setDoubleBuffered(true);
        txt.setBackground(Color.white);
        JScrollPane jp = new JScrollPane(txt);
        jp.setLocation(0,0);
        add(jp);

        sa = new SimpleAttributeSet();
        StyleConstants.setAlignment(sa, StyleConstants.ALIGN_LEFT);
        StyleConstants.setForeground(sa, Color.darkGray);
        txt.setParagraphAttributes(sa, true);
        sb = new StringBuilder();

        /******************************* OnClose *********************************/
        addWindowListener(new WindowAdapter()
        {
            @Override
            @SuppressWarnings("static-access")
            public void windowClosing(WindowEvent e)
            {
                Main.win.myConsole.setSelected(false);
            }
        });
    }

    public static void print(String str)
    {
        sb.append(str + "\n");
        txt.setText(sb.toString());
        txt.setCaretPosition(txt.getText().length());
    }

    public static void clearConsole()
    {
        sb.delete(0, sb.length());
        sb.append("*******************************************\n");
        sb.append("*                 JNemu                   *\n");
        sb.append("*        Java base Nes emulator           *\n");
        sb.append("*******************************************\n");
        txt.setText(sb.toString());
    }

    @SuppressWarnings("static-access")
    public static void displayFrame()
    {
        Main.con.setVisible(true);
        Main.win.myConsole.setSelected(true);
    }

    public static void hideFrame()
    {
        Main.con.setVisible(false);
    }

}

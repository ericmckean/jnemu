package jnemu;

import java.awt.*;
import javax.swing.*;

public class NesGraphics extends JDialog
{
    int scWidth = 400;
    int scHeight = 300;

    NesGraphics()
    {
        /********************** Main Window ***********************/
        setTitle("Graphics");
        setSize(scWidth,scHeight);
        setResizable(false);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dim = t.getScreenSize();
        setLocation((dim.width / 2) - (scWidth / 2), (dim.height / 2) - (scHeight / 2));
        setAlwaysOnTop(true);
    }

}


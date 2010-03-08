package jnemu;

import java.awt.*;
import javax.swing.*;

public class NesSound extends JFrame
{
    int scWidth = 400;
    int scHeight = 300;

    NesSound()
    {
        /********************** Main Window ***********************/
        setTitle("Sound");
        setSize(scWidth,scHeight);
        setResizable(false);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dim = t.getScreenSize();
        setLocation((dim.width / 2) - (scWidth / 2), (dim.height / 2) - (scHeight / 2));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}

package jnemu;

import java.awt.*;
import javax.swing.*;

public class NesDebugger extends JFrame
{
    int scWidth = 400;
    int scHeight = 300;

    NesDebugger()
    {
        /********************** Main Window ***********************/
        setTitle("Debugger");
        setSize(scWidth,scHeight);
        setResizable(false);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dim = t.getScreenSize();
        setLocation((dim.width / 2) - (scWidth / 2), (dim.height / 2) - (scHeight / 2));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}

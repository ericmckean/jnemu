package jnemu;

import java.awt.*;
import javax.swing.*;

public class NesFolder extends JDialog
{
    int scWidth = 400;
    int scHeight = 300;

    NesFolder()
    {
        /********************** Main Window ***********************/
        setTitle("Folder");
        setSize(scWidth,scHeight);
        setResizable(false);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dim = t.getScreenSize();
        setLocation((dim.width / 2) - (scWidth / 2), (dim.height / 2) - (scHeight / 2));
    }

}

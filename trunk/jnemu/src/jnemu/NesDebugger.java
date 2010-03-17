package jnemu;

import java.awt.*;
import javax.swing.*;

public class NesDebugger extends JDialog
{
    int scWidth = 500;
    int scHeight = 400;

    NesDebugger()
    {
        /********************** Main Window ***********************/
        setTitle("Debugger");
        setSize(scWidth,scHeight);
        setResizable(false);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dim = t.getScreenSize();
        setLocation((dim.width / 2) - (scWidth / 2), (dim.height / 2) - (scHeight / 2));
        
        /*************************** TAB ***************************/

        JTabbedPane tab = new JTabbedPane();
        add(tab);

        //Assembly............................
        JPanel wASM = new JPanel();
        tab.add("Assembly",wASM);

        //Memory............................
        JPanel wMEM = new JPanel();
        tab.add("Memory",wMEM);

        //Assembly............................
        JPanel wPPU = new JPanel();
        tab.add("PPU",wPPU);
    }

}

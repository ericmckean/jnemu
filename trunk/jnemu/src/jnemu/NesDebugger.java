package jnemu;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class NesDebugger extends JDialog
{
    int scWidth = 500 - 30;
    int scHeight = 400;

    public static JTextPane jt;

    NesDebugger()
    {
        /********************** Main Window ***********************/
        setTitle("Debugger");
        setSize(scWidth,scHeight);
        setResizable(false);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dim = t.getScreenSize();
        setLocation((dim.width / 2) - (scWidth / 2), (dim.height / 2) - (scHeight / 2));
        setAlwaysOnTop(true);

        /*************************** TAB ***************************/

        JTabbedPane tab = new JTabbedPane();
        add(tab);

        //Assembly............................
        //JPanel wASM = new JPanel();
        //tab.add("Assembly",wASM);

        //Memory............................
        JPanel wMEM = new JPanel();
        tab.add("Memory",wMEM);

        //Assembly............................
        JPanel wPPU = new JPanel();
        tab.add("PPU",wPPU);

        //PRG............................
        JPanel wPRG = new JPanel();
        tab.add("PRG",wPRG);

        //CHR............................
        JPanel wCHR = new JPanel();
        tab.add("CHR",wCHR);
        
        //NES ROM............................
        JPanel wROM = new JPanel();
        tab.add("NES ROM",wROM);
        wROM.setLayout(null);

        jt = new JTextPane();
        jt.setEditable(false);
        jt.setBackground(Color.lightGray);
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(0, 0, 490 - 30, 344);
        wROM.add(sp);

        SimpleAttributeSet sa = new SimpleAttributeSet();
        StyleConstants.setAlignment(sa, StyleConstants.ALIGN_JUSTIFIED);
        jt.setParagraphAttributes(sa, true);
    }

}

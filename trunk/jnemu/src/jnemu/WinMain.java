/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jnemu;

/**
 *
 * @author Michael
 */
import javax.swing.*;
import java.awt.*;
import java.awt.Color;

class WinMain extends JFrame
{

    WinMain()
    {
        int scWidth = 256;
        int scHeight = 240;

        /********************** Main Window ***********************/
        setTitle("JNemu");
        setSize(scWidth,scHeight);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dim = t.getScreenSize();
        setLocation((dim.width / 2) - (scWidth / 2), (dim.height / 2) - (scHeight / 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);

        /*********************** Menu Bar ***********************/
        JMenuBar bar = new JMenuBar();

        // Menu
        JMenu myFile = new JMenu("File");
        bar.add(myFile);
        JMenu myConfig = new JMenu("Config");
        bar.add(myConfig);
        JMenu myMisc = new JMenu("Misc");
        bar.add(myMisc);

        // File
        JMenuItem myOpenRom = new JMenuItem("Open Rom");
        myFile.add(myOpenRom);

        JMenuItem myExit = new JMenuItem("Exit");
        myExit.addActionListener(new ActionModule());
        myFile.add(myExit);

        //Misc
        JMenuItem myAbout = new JMenuItem("About");
        myMisc.add(myAbout);

        //Show MenuBar................
        setJMenuBar(bar);
    }
}

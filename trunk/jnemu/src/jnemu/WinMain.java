package jnemu;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;

public class WinMain extends JFrame
{
    public static JMenuItem myStart;
    public static JMenuItem myStop;
    public static JCheckBoxMenuItem myConsole;

    WinMain()
    {
        /*
         * Nes Actual width = 256
         * Nes Actual height = 240
         */
        int scWidth = 320;
        int scHeight = 240;

        /********************** Main Window ***********************/
        setTitle("JNemu");
        setSize(scWidth,scHeight);
        setLayout(null);
        setResizable(false);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dim = t.getScreenSize();
        setLocation((dim.width / 2) - (scWidth / 2), (dim.height / 2) - (scHeight / 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setAlwaysOnTop(true);

        /*********************** Menu Bar ***********************/
        JMenuBar bar = new JMenuBar();

        // Menu
        JMenu myFile = new JMenu("File");
        myFile.setMnemonic('F');
        bar.add(myFile);

        JMenu myEmu = new JMenu("Emulation");
        myEmu.setMnemonic('E');
        bar.add(myEmu);

        JMenu myConfig = new JMenu("Config");
        myConfig.setMnemonic('C');
        bar.add(myConfig);
        
        JMenu myMisc = new JMenu("Misc");
        myMisc.setMnemonic('M');
        bar.add(myMisc);


        // File
        JMenuItem myOpenRom = new JMenuItem("Open Rom");
        myOpenRom.setMnemonic('O');
        myOpenRom.addActionListener(new ActionHandler());
        myFile.add(myOpenRom);

        myFile.addSeparator();

        JMenuItem myExit = new JMenuItem("Exit");
        myExit.setMnemonic('x');
        myExit.addActionListener(new ActionHandler());
        myFile.add(myExit);

        //Emulation

        myStart = new JMenuItem("Start");
        myStart.setMnemonic('a');
        myStart.addActionListener(new ActionHandler());
        myEmu.add(myStart);
        myStart.setEnabled(false);

        myStop = new JMenuItem("Stop");
        myStop.setMnemonic('o');
        myStop.addActionListener(new ActionHandler());
        myEmu.add(myStop);
        myStop.setEnabled(false);

        // Config
        JMenuItem myGraphics = new JMenuItem("Graphics");
        myGraphics.setMnemonic('G');
        myGraphics.addActionListener(new ActionHandler());
        myConfig.add(myGraphics);

        JMenuItem mySound = new JMenuItem("Sound");
        mySound.setMnemonic('S');
        mySound.addActionListener(new ActionHandler());
        myConfig.add(mySound);

        JMenuItem myController = new JMenuItem("Controller");
        myController.setMnemonic('C');
        myController.addActionListener(new ActionHandler());
        myConfig.add(myController);

        JMenuItem myFolder = new JMenuItem("Option");
        myFolder.setMnemonic('O');
        myFolder.addActionListener(new ActionHandler());
        myConfig.add(myFolder);

        //Misc
        myConsole = new JCheckBoxMenuItem("Console");
        myConsole.addActionListener(new ActionHandler());
        myMisc.add(myConsole);

        JMenuItem myDebugger = new JMenuItem("Debugger");
        myDebugger.setMnemonic('D');
        myDebugger.addActionListener(new ActionHandler());
        myMisc.add(myDebugger);

        JMenuItem myAbout = new JMenuItem("About");
        myAbout.setMnemonic('A');
        myAbout.addActionListener(new ActionHandler());
        myMisc.add(myAbout);

        //Show MenuBar................
        setJMenuBar(bar);

    }
}

package jnemu;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import NesPpu.PpuRenderer;

public class WinMain extends JFrame
{
    public static JMenuItem mStart;
    public static JMenuItem mStop;
    public static JCheckBoxMenuItem mConsole;
    public JPanel p;

    WinMain()
    {
        /*
         * Nes Actual width = 256
         * Nes Actual height = 240
         */
        int nesScrWidth = 256;
        int nesScrHeight = 240;
        int scWidth = nesScrWidth + 5;
        int scHeight = nesScrHeight + 48;

        /********************** Main Window ***********************/
        setTitle("JNemu");
        setSize(scWidth,scHeight);
        setLayout(null);
        setResizable(false);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dim = t.getScreenSize();
        setLocation((dim.width / 2) - (scWidth / 2), (dim.height / 2) - (scHeight / 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);

        p = new PpuRenderer();
        p.setBounds(0, 0, nesScrWidth, nesScrHeight); //NTSC Resolution...
        add(p);
        //p.setVisible(false);
        /********************** Status Bar **********************/
        //JStatusBar status = new JStatusBar();

        /*********************** Menu Bar ***********************/
        JMenuBar bar = new JMenuBar();

        // Menu
        JMenu mFile = new JMenu("File");
        mFile.setMnemonic('F');
        bar.add(mFile);

        JMenu mEmu = new JMenu("Emulation");
        mEmu.setMnemonic('E');
        bar.add(mEmu);

        JMenu mConfig = new JMenu("Config");
        mConfig.setMnemonic('C');
        bar.add(mConfig);
        
        JMenu mMisc = new JMenu("Misc");
        mMisc.setMnemonic('M');
        bar.add(mMisc);


        // File
        JMenuItem mOpenRom = new JMenuItem("Open Rom");
        mOpenRom.setMnemonic('O');
        mOpenRom.addActionListener(new ActionHandler());
        mFile.add(mOpenRom);

        mFile.addSeparator();

        JMenuItem mExit = new JMenuItem("Exit");
        mExit.setMnemonic('x');
        mExit.addActionListener(new ActionHandler());
        mFile.add(mExit);

        //Emulation

        mStart = new JMenuItem("Start");
        mStart.setMnemonic('a');
        mStart.addActionListener(new ActionHandler());
        mEmu.add(mStart);
        mStart.setEnabled(false);

        mStop = new JMenuItem("Stop");
        mStop.setMnemonic('o');
        mStop.addActionListener(new ActionHandler());
        mEmu.add(mStop);
        mStop.setEnabled(false);

        // Config
        JMenuItem mGraphics = new JMenuItem("Graphics");
        mGraphics.setMnemonic('G');
        mGraphics.addActionListener(new ActionHandler());
        mConfig.add(mGraphics);

        JMenuItem mSound = new JMenuItem("Sound");
        mSound.setMnemonic('S');
        mSound.addActionListener(new ActionHandler());
        mConfig.add(mSound);

        JMenuItem mController = new JMenuItem("Controller");
        mController.setMnemonic('C');
        mController.addActionListener(new ActionHandler());
        mConfig.add(mController);

        JMenuItem mFolder = new JMenuItem("Option");
        mFolder.setMnemonic('O');
        mFolder.addActionListener(new ActionHandler());
        mConfig.add(mFolder);

        //Misc
        mConsole = new JCheckBoxMenuItem("Console");
        mConsole.addActionListener(new ActionHandler());
        mMisc.add(mConsole);
        if(EmuConfig.CfgInfo.showConsole)
        {
            mConsole.setSelected(true);
        }
        else
        {
            mConsole.setSelected(false);
        }


        JMenuItem mDebugger = new JMenuItem("Debugger");
        mDebugger.setMnemonic('D');
        mDebugger.addActionListener(new ActionHandler());
        mMisc.add(mDebugger);

        JMenuItem mAbout = new JMenuItem("About");
        mAbout.setMnemonic('A');
        mAbout.addActionListener(new ActionHandler());
        mMisc.add(mAbout);

        //Show MenuBar................
        setJMenuBar(bar);

    }
}

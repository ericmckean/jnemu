package jnemu;

import CARTRIDGE.GAME;
import CARTRIDGE.ROMS;
import MISC.INOUT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener
{
    //@SuppressWarnings("static-access")
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Exit"))
        {
            System.exit(0);
        }
        else if(e.getActionCommand().equals("Open Rom"))
        {
            //load roms to cartridge
            ROMS.loadNesROM(INOUT.getFilePath());
            if(ROMS.isLoaded)
            {
                switch (GAME.MAPPER_NUMBER)
                {
                    case 0 : emuCORE.startEmulation();break;
                    default : Console.print("[ERROR] Unsupported mapper.");break;
                }
            }
        }
        else if(e.getActionCommand().equals("Controller"))
        {
            Main.cont.setVisible(true);
        }
        else if(e.getActionCommand().equals("Graphics"))
        {
            Main.graph.setVisible(true);
        }
        else if(e.getActionCommand().equals("Sound"))
        {
            Main.sound.setVisible(true);
        }
        else if(e.getActionCommand().equals("Debugger"))
        {
            Main.deb.setVisible(true);
        }
        else if(e.getActionCommand().equals("About"))
        {
            Main.about.setVisible(true);
        }
        else if(e.getActionCommand().equals("Start"))
        {
            WinMain.myStart.setEnabled(false);
            WinMain.myStop.setEnabled(true);
            emuCORE.GO();
        }
        else if(e.getActionCommand().equals("Stop"))
        {
            WinMain.myStart.setEnabled(true);
            WinMain.myStop.setEnabled(false);
            emuCORE.STOP();
        }
        else if(e.getActionCommand().equals("Option"))
        {
            Main.opt.setVisible(true);
        }
        else if(e.getActionCommand().equals("Console"))
        {
            if(WinMain.myConsole.isSelected())
            {
                Console.displayFrame();
            }
            else
            {
                Console.hideFrame();
            }
        }
        else if(e.getActionCommand().equals("Step Into"))
        {
            emuCORE.StepInto();
        }
       
    }
}

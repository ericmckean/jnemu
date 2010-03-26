package jnemu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Exit"))
        {
            System.exit(0);
        }
        else if(e.getActionCommand().equals("Open Rom"))
        {
           //load roms to cartridge
           Cartridge.loadNesROM(Emu_MOD.getFilePath());
           if(Cartridge.isLoaded)
           {
               //run the core emulation
               Core.runEmulation();
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
            Core.startEmulation();
        }
        else if(e.getActionCommand().equals("Stop"))
        {
            WinMain.myStart.setEnabled(true);
            WinMain.myStop.setEnabled(false);
            Core.stopEmulation();
        }
        else if(e.getActionCommand().equals("Folder"))
        {
            Main.fold.setVisible(true);
        }
       
    }
}

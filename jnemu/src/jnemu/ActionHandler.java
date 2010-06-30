package jnemu;

import CARTRIDGE.ROM_INFO;
import CARTRIDGE.ROM_IO;
import CPU.CPU_MEMORY;
import DEBUGGER.NesDebugger;
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
            ROM_IO.loadNesROM(INOUT.getFilePath());
            if(ROM_IO.isLoaded)
            {
                switch (ROM_INFO.MAPPER_NUMBER)
                {
                    case 0 : emuCORE.init();break;
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
            emuCORE.GO();
        }
        else if(e.getActionCommand().equals("Stop"))
        {
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
        else if(e.getActionCommand().equals("Refresh"))
        {
            emuCORE.updateDebugger();
        }
        else if(e.getSource().equals(NesDebugger.mv_btn))
        {
            int mStart = 0, mEnd = 0;
            String c;

            c = NesDebugger.mv_txt.getSelectedItem().toString();

            if(c.equals("Zero Page $0000-$00FF"))
            {
                mStart = 0x0000;
                mEnd = 0x00FF;
                NesDebugger.mv.setText(CPU_MEMORY.getMemContent(mStart, mEnd));
            }
            else if(c.equals("Stack $0100-$01FF"))
            {
                mStart = 0x0100;
                mEnd = 0x01FF;
                NesDebugger.mv.setText(CPU_MEMORY.getMemContent(mStart, mEnd));
            }
            else if(c.equals("RAM $02FF-$07FF"))
            {
                mStart = 0x0200;
                mEnd = 0x07FF;
                NesDebugger.mv.setText(CPU_MEMORY.getMemContent(mStart, mEnd));
            }
            else if(c.equals("SRAM $6000-$7FFF"))
            {
                mStart = 0x6000;
                mEnd = 0x7FFF;
                NesDebugger.mv.setText(CPU_MEMORY.getMemContent(mStart, mEnd));
            }
        }
       
    }
}

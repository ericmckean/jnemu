package jnemu;

import CARTRIDGE.ROM_INFO;
import CARTRIDGE.ROM_IO;
import CPU.CPU_MEMORY;
import DEBUGGER.NES_DEBUGGER;
import MISC.INOUT;
import PPU.OAM_MEMORY;
import PPU.PPU_MEMORY;

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
        else if(e.getSource().equals(NES_DEBUGGER.mv_btn))
        {
            int mStart = 0, mEnd = 0;
            String c;

            c = NES_DEBUGGER.mv_txt.getSelectedItem().toString();

            if(c.equals("Zero Page $0000-$00FF"))
            {
                mStart = 0x0000;
                mEnd = 0x00FF;
                NES_DEBUGGER.mv.setText(CPU_MEMORY.getMemContent(mStart, mEnd));
            }
            else if(c.equals("Stack $0100-$01FF"))
            {
                mStart = 0x0100;
                mEnd = 0x01FF;
                NES_DEBUGGER.mv.setText(CPU_MEMORY.getMemContent(mStart, mEnd));
            }
            else if(c.equals("RAM $02FF-$07FF"))
            {
                mStart = 0x0200;
                mEnd = 0x07FF;
                NES_DEBUGGER.mv.setText(CPU_MEMORY.getMemContent(mStart, mEnd));
            }
            else if(c.equals("SRAM $6000-$7FFF"))
            {
                mStart = 0x6000;
                mEnd = 0x7FFF;
                NES_DEBUGGER.mv.setText(CPU_MEMORY.getMemContent(mStart, mEnd));
            }
            else if(c.equals("PRG-ROM Bank 1 $8000-$BFFF"))
            {
                mStart = 0x8000;
                mEnd = 0xBFFF;
                NES_DEBUGGER.mv.setText(CPU_MEMORY.getMemContent(mStart, mEnd));
            }
            else if(c.equals("PRG-ROM Bank 2 $C000-$FFFF"))
            {
                mStart = 0xC000;
                mEnd = 0xFFFF;
                NES_DEBUGGER.mv.setText(CPU_MEMORY.getMemContent(mStart, mEnd));
            }
            else if(c.equals("Pattern Table 0"))
            {
                mStart = 0x0000;
                mEnd = 0x0FFF;
                NES_DEBUGGER.mv.setText(PPU_MEMORY.getMemContent(mStart, mEnd));
            }
            else if(c.equals("Pattern Table 1"))
            {
                mStart = 0x1000;
                mEnd = 0x1FFF;
                NES_DEBUGGER.mv.setText(PPU_MEMORY.getMemContent(mStart, mEnd));
            }
            else if(c.equals("Name Tables"))
            {
                mStart = 0x2000;
                mEnd = 0x2FFF;
                NES_DEBUGGER.mv.setText(PPU_MEMORY.getMemContent(mStart, mEnd));
            }
            else if(c.equals("Palette"))
            {
                mStart = 0x3F00;
                mEnd = 0x3FFF;
                NES_DEBUGGER.mv.setText(PPU_MEMORY.getMemContent(mStart, mEnd));
            }
            else if(c.equals("OAM"))
            {
                mStart = 0x00;
                mEnd = 0xff;
                NES_DEBUGGER.mv.setText(OAM_MEMORY.getMemContent(mStart, mEnd));
            }
            else if(c.equals("Mirror of $2000-$2007"))
            {
                mStart = 0x2000;
                mEnd = 0x3fff;
                NES_DEBUGGER.mv.setText(CPU_MEMORY.getMemContent(mStart, mEnd));
            }
            else if(c.equals("APU and I/O Register $4000-$4019"))
            {
                mStart = 0x4000;
                mEnd = 0x4019;
                NES_DEBUGGER.mv.setText(CPU_MEMORY.getMemContent(mStart, mEnd));
            }
            else if(c.equals("Expansion ROM $4020-$5FFF"))
            {
                mStart = 0x4020;
                mEnd = 0x5FFF;
                NES_DEBUGGER.mv.setText(CPU_MEMORY.getMemContent(mStart, mEnd));
            }
        }
       
    }
}

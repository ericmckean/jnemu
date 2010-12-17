package jnemu;

import NesRomCartridge.RomInfo;
import NesRomCartridge.RomIo;
import NesCpu.CpuMemory;
import EmuDebugger.NesDebugger;
import EmuMisc.InOut;
import NesPpu.OamMemory;
import NesPpu.PpuMemory;

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
            RomIo.loadNesROM(InOut.getFilePath());
            if(RomIo.isLoaded)
            {
                switch (RomInfo.mapperNumber)
                {
                    case 0 : EmuCore.init();break;
                    default : System.out.println("[ERROR] Unsupported mapper.");break;
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
            EmuCore.GO();
        }
        else if(e.getActionCommand().equals("Stop"))
        {
            EmuCore.STOP();
        }
        else if(e.getActionCommand().equals("Option"))
        {
            Main.opt.setVisible(true);
        }
        else if(e.getActionCommand().equals("Console"))
        {
            if(WinMain.mConsole.isSelected())
            {
                Main.con.show();
            }
            else
            {
                Main.con.hide();
            }
        }
        else if(e.getActionCommand().equals("Step Into"))
        {
            EmuCore.StepInto();
        }
        else if(e.getActionCommand().equals("Refresh"))
        {
            EmuCore.updateDebugger();
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
                NesDebugger.mv.setText(CpuMemory.getMemContent(mStart, mEnd));
            }
            else if(c.equals("Stack $0100-$01FF"))
            {
                mStart = 0x0100;
                mEnd = 0x01FF;
                NesDebugger.mv.setText(CpuMemory.getMemContent(mStart, mEnd));
            }
            else if(c.equals("RAM $02FF-$07FF"))
            {
                mStart = 0x0200;
                mEnd = 0x07FF;
                NesDebugger.mv.setText(CpuMemory.getMemContent(mStart, mEnd));
            }
            else if(c.equals("SRAM $6000-$7FFF"))
            {
                mStart = 0x6000;
                mEnd = 0x7FFF;
                NesDebugger.mv.setText(CpuMemory.getMemContent(mStart, mEnd));
            }
            else if(c.equals("PRG-ROM Bank 1 $8000-$BFFF"))
            {
                mStart = 0x8000;
                mEnd = 0xBFFF;
                NesDebugger.mv.setText(CpuMemory.getMemContent(mStart, mEnd));
            }
            else if(c.equals("PRG-ROM Bank 2 $C000-$FFFF"))
            {
                mStart = 0xC000;
                mEnd = 0xFFFF;
                NesDebugger.mv.setText(CpuMemory.getMemContent(mStart, mEnd));
            }
            else if(c.equals("Pattern Table 0"))
            {
                mStart = 0x0000;
                mEnd = 0x0FFF;
                NesDebugger.mv.setText(PpuMemory.getMemContent(mStart, mEnd));
            }
            else if(c.equals("Pattern Table 1"))
            {
                mStart = 0x1000;
                mEnd = 0x1FFF;
                NesDebugger.mv.setText(PpuMemory.getMemContent(mStart, mEnd));
            }
            else if(c.equals("Name Tables"))
            {
                mStart = 0x2000;
                mEnd = 0x2FFF;
                NesDebugger.mv.setText(PpuMemory.getMemContent(mStart, mEnd));
            }
            else if(c.equals("Palette"))
            {
                mStart = 0x3F00;
                mEnd = 0x3FFF;
                NesDebugger.mv.setText(PpuMemory.getMemContent(mStart, mEnd));
            }
            else if(c.equals("OAM"))
            {
                mStart = 0x00;
                mEnd = 0xff;
                NesDebugger.mv.setText(OamMemory.getMemContent(mStart, mEnd));
            }
            else if(c.equals("Mirror of $2000-$2007"))
            {
                mStart = 0x2000;
                mEnd = 0x3fff;
                NesDebugger.mv.setText(CpuMemory.getMemContent(mStart, mEnd));
            }
            else if(c.equals("APU and I/O Register $4000-$4019"))
            {
                mStart = 0x4000;
                mEnd = 0x4019;
                NesDebugger.mv.setText(CpuMemory.getMemContent(mStart, mEnd));
            }
            else if(c.equals("Expansion ROM $4020-$5FFF"))
            {
                mStart = 0x4020;
                mEnd = 0x5FFF;
                NesDebugger.mv.setText(CpuMemory.getMemContent(mStart, mEnd));
            }
        }
       
    }
}

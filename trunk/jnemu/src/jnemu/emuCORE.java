package jnemu;

import CARTRIDGE.RomIo;
import CARTRIDGE.MapperCore;
import CONFIG.CfgInfo;
import CPU.CpuMemory;
import CPU.CpuCore;
import CPU.CpuRegister;
import PPU.PpuCore;
import DEBUGGER.*;
import INSTRUCTIONS.InstInterrupt;
import LOGS.EmuLogger;
import MISC.Converter;



//*****************************************************
//                   Core Thread
//*****************************************************
public class EmuCore
{
    public static boolean isRunning = false;
    public static Thread emuThread;
    static coreTHREAD core;

    public static void init()
    {
        //if the file is loaded to the cartridge, start emulation.
        //else dont do anything...................................
        
        if(RomIo.noSelectedFile == false)
        {
            isRunning = false;
            
            WinMain.mStop.setEnabled(false);
            WinMain.mStart.setEnabled(true);
            
            //**************************************
            //              Init CPU
            //**************************************
            System.out.println("Initializing CPU...");
            CpuMemory.init();
            //Init CPU REGISTER...
            CpuRegister.init();

            //**************************************
            //              Init PPU
            //**************************************
            System.out.println("Initializing PPU...");
            PpuCore.init();

            //init mapper.....
            MapperCore.init();

            //Jump to Reset Vector.....
            CpuRegister.PC = CpuMemory.getResetVector();

            OpcodeFetcher.loadOpcode(CpuRegister.PC);
            //Show data on the debugger.................
            NesDebugger.REG_A.setText(MiscFunctions.forceTo8Bit(CpuRegister.A));
            NesDebugger.REG_X.setText(MiscFunctions.forceTo8Bit(CpuRegister.X));
            NesDebugger.REG_Y.setText(MiscFunctions.forceTo8Bit(CpuRegister.Y));
            NesDebugger.REG_PC.setText(MiscFunctions.forceTo16Bit(CpuRegister.PC));
        }
    }

    public static void StepInto()
    {
        if(CpuRegister.PC != 0)
        {
            //count cycle and execute opcode...
            CpuCore.cpuCycle += CpuCore.exec(CpuMemory.fastRead8Bit(CpuRegister.PC));
            PpuCore.execPPU();
            if(PpuCore.isNMI)
            {
                InstInterrupt.NMI();
            }
            updateDebugger();
        }
        else
        {
            System.out.println("[emuCORE] The PC does not contain a value.");
        }
    }

    public static void updateDebugger()
    {
        int ctr, tmp = 0;
        boolean found = false;
        //search the entire content of REG_Viewer.....
            for(ctr=0; ctr<=100; ctr++)
            {
                if(NesDebugger.REG_Viewer.getItem(ctr).contains(NesDebugger.REG_PC.getText()))
                {
                    found = true;
                    tmp = ctr;
                }
            }

            if(found)
            {
                NesDebugger.REG_Viewer.select(tmp);
            }
            else
            {
                OpcodeFetcher.loadOpcode(CpuRegister.PC);
                for(ctr=0; ctr<=100; ctr++)
                {
                    if(NesDebugger.REG_Viewer.getItem(ctr).contains(NesDebugger.REG_PC.getText()))
                    {
                        tmp = ctr;
                    }
                }
                NesDebugger.REG_Viewer.select(tmp);
            }
            

            //Show data on the debugger.................
            NesDebugger.REG_A.setText(MiscFunctions.forceTo8Bit(CpuRegister.A));
            NesDebugger.REG_X.setText(MiscFunctions.forceTo8Bit(CpuRegister.X));
            NesDebugger.REG_Y.setText(MiscFunctions.forceTo8Bit(CpuRegister.Y));
            NesDebugger.REG_PC.setText(MiscFunctions.forceTo16Bit(CpuRegister.PC));
            NesDebugger.REG_SP.setText(MiscFunctions.forceTo8Bit(CpuRegister.SP));

            NesDebugger.F_N.setText("" + CpuRegister.getNegativeFlag());
            NesDebugger.F_Z.setText("" + CpuRegister.getZeroFlag());
            NesDebugger.F_C.setText("" + CpuRegister.getCarryFlag());
            NesDebugger.F_I.setText("" + CpuRegister.getInterruptFlag());
            NesDebugger.F_V.setText("" + CpuRegister.getOverflowFlag());
            NesDebugger.F_D.setText("" + CpuRegister.getDecimalFlag());
            NesDebugger.F_B.setText("" + CpuRegister.getBRKFlag());

            NesDebugger.MEM_2000.setText("" + MiscFunctions.forceTo8Bit(CpuMemory.fastRead8Bit(0x2000)));
            NesDebugger.MEM_2001.setText("" + MiscFunctions.forceTo8Bit(CpuMemory.fastRead8Bit(0x2001)));
            NesDebugger.MEM_2002.setText("" + MiscFunctions.forceTo8Bit(CpuMemory.fastRead8Bit(0x2002)));
            NesDebugger.MEM_2003.setText("" + MiscFunctions.forceTo8Bit(CpuMemory.fastRead8Bit(0x2003)));
            NesDebugger.MEM_2004.setText("" + MiscFunctions.forceTo8Bit(CpuMemory.fastRead8Bit(0x2004)));
            NesDebugger.MEM_2005.setText("" + MiscFunctions.forceTo8Bit(CpuMemory.fastRead8Bit(0x2005)));
            NesDebugger.MEM_2006.setText("" + MiscFunctions.forceTo8Bit(CpuMemory.fastRead8Bit(0x2006)));
            NesDebugger.MEM_2007.setText("" + MiscFunctions.forceTo8Bit(CpuMemory.fastRead8Bit(0x2007)));

            NesDebugger.CPU_CYCLE.setText("" + CpuCore.cpuCycle);
            NesDebugger.PPU_SCANLINE.setText("" + PpuCore.SCANLINE);
    }

    public static void STOP()
    {
        WinMain.mStart.setEnabled(true);
        WinMain.mStop.setEnabled(false);
        System.out.println("Stopping Core emulation...");
        Main.win.setTitle("JNemu");
        isRunning = false;
        emuThread.interrupt();
    }

    public static void GO()
    {
        WinMain.mStart.setEnabled(false);
        WinMain.mStop.setEnabled(true);
        System.out.println("Starting Core emulation...");
        isRunning = true;
        core = new coreTHREAD();
        emuThread = new Thread(core);
        emuThread.start();
        Main.win.setTitle("JNemu - running");
    }
}

class coreTHREAD implements Runnable
{
    int pc;
    StringBuilder LogTmp = new StringBuilder(50);
    
    coreTHREAD()
    {
        try
        {
            pc = Converter.stringHexToInt(NesDebugger.codeBreak.getText()); //Stop point...
        }
        catch(Exception e)
        {
            System.out.println("[ERROR] " + e.toString());
        }
    }
    public void run()
    {
        while(EmuCore.isRunning)
        {
            if(NesDebugger.codeCheck.isSelected())
            {
                if(CpuRegister.PC == pc)
                {
                    EmuCore.STOP();
                }
            }

            try
            {
                if(CfgInfo.enableOpcodeLog)
                {
                    LogTmp.append("<tr><td>");
                    LogTmp.append(Integer.toHexString(CpuRegister.PC).toUpperCase());
                    LogTmp.append("</td>");
                    LogTmp.append("<td>");
                    LogTmp.append("A:");
                    LogTmp.append(Converter.intTo8BitStringHex(CpuRegister.A));
                    LogTmp.append(" ");
                    LogTmp.append("X:");
                    LogTmp.append(Converter.intTo8BitStringHex(CpuRegister.X));
                    LogTmp.append(" ");
                    LogTmp.append("Y:");
                    LogTmp.append(Converter.intTo8BitStringHex(CpuRegister.Y));
                    LogTmp.append(" ");
                    LogTmp.append("SR:");
                    LogTmp.append(Converter.intTo8BitStringHex(CpuRegister.SR));
                    LogTmp.append(" ");
                    LogTmp.append("SP:");
                    LogTmp.append(Converter.intTo8BitStringHex(CpuRegister.SP));
                    LogTmp.append("</td></tr>");
                    EmuLogger.write(LogTmp.toString());
                    LogTmp.delete(0,LogTmp.length());
                }
                CpuCore.cpuCycle += CpuCore.exec(CpuMemory.fastRead8Bit(CpuRegister.PC));
                PpuCore.execPPU();
            }
            catch(Exception e)
            {
                StringBuilder msg;

                for(int ctr=0; ctr<=(e.getStackTrace().length - 1);ctr++)
                {
                    msg = new StringBuilder(5);
                    msg.append(e.toString()).append(": ");
                    msg.append(e.getStackTrace()[ctr].getFileName()).append(": ");
                    msg.append(e.getStackTrace()[ctr].getMethodName()).append(": ");
                    msg.append("Line ").append(e.getStackTrace()[ctr].getLineNumber());
                    System.out.println("[ERROR] coreTHREAD - " + msg.toString());
                }
                EmuCore.STOP();
            }

            //****************************************
            //             Cyclic Task
            //****************************************
            if(PpuCore.isNMI)
            {
                InstInterrupt.NMI();
            }
            try
            {
                Thread.sleep(0);
            }
            catch(InterruptedException e)
            {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

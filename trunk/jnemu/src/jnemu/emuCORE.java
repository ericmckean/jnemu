package jnemu;

import CARTRIDGE.ROM_IO;
import CARTRIDGE.mapperCORE;
import CPU.CPU_MEMORY;
import CPU.cpuCORE;
import CPU.CPU_REGISTER;
import PPU.ppuCORE;
import DEBUGGER.*;
import INSTRUCTIONS.INTERRUPT;
import MISC.CONVERTER;

public class emuCORE
{
    public static boolean isRunning = false;
    public static Thread emuThread;
    static coreTHREAD core;

    public static void init()
    {
        //if the file is loaded to the cartridge, start emulation.
        //else dont do anything...................................
        
        if(ROM_IO.noSelectedFile == false)
        {
            isRunning = false;
            
            WinMain.myStop.setEnabled(false);
            WinMain.myStart.setEnabled(true);
            
            //**************************************
            //              Init CPU
            //**************************************
            Console.print("Initializing CPU...");
            CPU_MEMORY.init();
            //Init CPU REGISTER...
            CPU_REGISTER.init();

            //**************************************
            //              Init PPU
            //**************************************
            Console.print("Initializing PPU...");
            ppuCORE.init();

            //init mapper.....
            mapperCORE.init();

            //Jump to Reset Vector.....
            CPU_REGISTER.PC = CPU_MEMORY.getResetVector();

            OPCODE_FETCHER.loadOpcode(CPU_REGISTER.PC);
            //Show data on the debugger.................
            NES_DEBUGGER.REG_A.setText(MISC_FUNCTIONS.forceTo8Bit(CPU_REGISTER.A));
            NES_DEBUGGER.REG_X.setText(MISC_FUNCTIONS.forceTo8Bit(CPU_REGISTER.X));
            NES_DEBUGGER.REG_Y.setText(MISC_FUNCTIONS.forceTo8Bit(CPU_REGISTER.Y));
            NES_DEBUGGER.REG_PC.setText(MISC_FUNCTIONS.forceTo16Bit(CPU_REGISTER.PC));
        }
    }

    public static void StepInto()
    {
        if(CPU_REGISTER.PC != 0)
        {
            //count cycle and execute opcode...
            cpuCORE.CYCLE += cpuCORE.exec(CPU_MEMORY.fastRead8Bit(CPU_REGISTER.PC));
            ppuCORE.execPPU();
            if(ppuCORE.isNMI)
            {
                INTERRUPT.NMI();
            }
            updateDebugger();
        }
        else
        {
            Console.print("[emuCORE] The PC does not contain a value.");
        }
    }

    public static void updateDebugger()
    {
        int ctr, tmp = 0;
        boolean found = false;
        //search the entire content of REG_Viewer.....
            for(ctr=0; ctr<=100; ctr++)
            {
                if(NES_DEBUGGER.REG_Viewer.getItem(ctr).contains(NES_DEBUGGER.REG_PC.getText()))
                {
                    found = true;
                    tmp = ctr;
                }
            }

            if(found)
            {
                NES_DEBUGGER.REG_Viewer.select(tmp);
            }
            else
            {
                OPCODE_FETCHER.loadOpcode(CPU_REGISTER.PC);
                for(ctr=0; ctr<=100; ctr++)
                {
                    if(NES_DEBUGGER.REG_Viewer.getItem(ctr).contains(NES_DEBUGGER.REG_PC.getText()))
                    {
                        tmp = ctr;
                    }
                }
                NES_DEBUGGER.REG_Viewer.select(tmp);
            }
            

            //Show data on the debugger.................
            NES_DEBUGGER.REG_A.setText(MISC_FUNCTIONS.forceTo8Bit(CPU_REGISTER.A));
            NES_DEBUGGER.REG_X.setText(MISC_FUNCTIONS.forceTo8Bit(CPU_REGISTER.X));
            NES_DEBUGGER.REG_Y.setText(MISC_FUNCTIONS.forceTo8Bit(CPU_REGISTER.Y));
            NES_DEBUGGER.REG_PC.setText(MISC_FUNCTIONS.forceTo16Bit(CPU_REGISTER.PC));
            NES_DEBUGGER.REG_SP.setText(MISC_FUNCTIONS.forceTo8Bit(CPU_REGISTER.SP));

            NES_DEBUGGER.F_N.setText("" + CPU_REGISTER.getNegativeFlag());
            NES_DEBUGGER.F_Z.setText("" + CPU_REGISTER.getZeroFlag());
            NES_DEBUGGER.F_C.setText("" + CPU_REGISTER.getCarryFlag());
            NES_DEBUGGER.F_I.setText("" + CPU_REGISTER.getInterruptFlag());
            NES_DEBUGGER.F_V.setText("" + CPU_REGISTER.getOverflowFlag());
            NES_DEBUGGER.F_D.setText("" + CPU_REGISTER.getDecimalFlag());
            NES_DEBUGGER.F_B.setText("" + CPU_REGISTER.getBRKFlag());

            NES_DEBUGGER.MEM_2000.setText("" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.fastRead8Bit(0x2000)));
            NES_DEBUGGER.MEM_2001.setText("" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.fastRead8Bit(0x2001)));
            NES_DEBUGGER.MEM_2002.setText("" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.fastRead8Bit(0x2002)));
            NES_DEBUGGER.MEM_2003.setText("" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.fastRead8Bit(0x2003)));
            NES_DEBUGGER.MEM_2004.setText("" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.fastRead8Bit(0x2004)));
            NES_DEBUGGER.MEM_2005.setText("" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.fastRead8Bit(0x2005)));
            NES_DEBUGGER.MEM_2006.setText("" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.fastRead8Bit(0x2006)));
            NES_DEBUGGER.MEM_2007.setText("" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.fastRead8Bit(0x2007)));

            NES_DEBUGGER.CPU_CYCLE.setText("" + cpuCORE.CYCLE);
            NES_DEBUGGER.PPU_SCANLINE.setText("" + ppuCORE.SCANLINE);
    }

    public static void STOP()
    {
        WinMain.myStart.setEnabled(true);
        WinMain.myStop.setEnabled(false);
        Console.print("Stopping Core emulation...");
        Main.win.setTitle("JNemu");
        isRunning = false;
        emuThread.interrupt();
    }

    public static void GO()
    {
        WinMain.myStart.setEnabled(false);
        WinMain.myStop.setEnabled(true);
        Console.print("Starting Core emulation...");
        isRunning = true;
        core = new coreTHREAD();
        emuThread = new Thread(core);
        emuThread.start();
        Main.win.setTitle("JNemu - running");
    }
}


//*****************************************************
//                   Core Thread
//*****************************************************

class coreTHREAD implements Runnable
{
    int pc;
    coreTHREAD()
    {
        try
        {
            pc = CONVERTER.stringHexToInt(NES_DEBUGGER.codeBreak.getText()); //Stop point...
        }
        catch(Exception e)
        {
            Console.print("[ERROR] " + e.toString());
        }
    }
    public void run()
    {
        while(emuCORE.isRunning)
        {
            if(NES_DEBUGGER.codeCheck.isSelected())
            {
                if(CPU_REGISTER.PC == pc)
                {
                    emuCORE.STOP();
                }
            }

            try
            {
                cpuCORE.CYCLE += cpuCORE.exec(CPU_MEMORY.fastRead8Bit(CPU_REGISTER.PC));
                ppuCORE.execPPU();
            }
            catch(Exception e)
            {
                StringBuilder msg;

                for(int ctr=0; ctr<=(e.getStackTrace().length - 1);ctr++)
                {
                    msg = new StringBuilder(5);
                    msg.append(e.toString() + ": ");
                    msg.append(e.getStackTrace()[ctr].getFileName() + ": ");
                    msg.append(e.getStackTrace()[ctr].getMethodName() + ": ");
                    msg.append("Line " + e.getStackTrace()[ctr].getLineNumber());
                    Console.print("[ERROR] " + msg.toString());
                }
                emuCORE.STOP();
            }

            //****************************************
            //             Cyclic Task
            //****************************************
            if(ppuCORE.isNMI)
            {
                INTERRUPT.NMI();
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

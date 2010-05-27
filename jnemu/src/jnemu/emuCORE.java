package jnemu;

import CARTRIDGE.ROMS;
import CARTRIDGE.MAPPER;
import CPU.CPU_MEMORY;
import CPU.cpuCORE;
import CPU.CPU_REGISTER;
import PPU.ppuCORE;
import DEBUGGER.*;

public class emuCORE
{
    public static boolean isRunning = false;

    public static void startEmulation()
    {
        //if the file is loaded to the cartridge, start emulation.
        //else dont do anything...................................
        
        if(ROMS.noSelectedFile == false)
        {
            isRunning = true;
            //Main.win.setTitle("JNemu - running");
            //enable stop............
            WinMain.myStop.setEnabled(false);
            WinMain.myStart.setEnabled(true);

            //initialize Nes Components..
            Console.print("Initializing cpu memory...");
            CPU_MEMORY.init();

             //init mapper.....
            MAPPER.init();
            CPU_MEMORY.showMemInDebugger();

            Console.print("Initializing cpu...");
            cpuCORE.init();

            Console.print("Initializing ppu...");
            ppuCORE.init();

            OpcodeFetcher.loadOpcode(CPU_MEMORY.getResetVector());
            //Show data on the debugger.................
            NesDebugger.REG_A.setText(MISC_FUNCTIONS.forceTo8Bit(CPU_REGISTER.A));
            NesDebugger.REG_X.setText(MISC_FUNCTIONS.forceTo8Bit(CPU_REGISTER.X));
            NesDebugger.REG_Y.setText(MISC_FUNCTIONS.forceTo8Bit(CPU_REGISTER.Y));
            NesDebugger.REG_PC.setText(MISC_FUNCTIONS.forceTo16Bit(CPU_REGISTER.PC));
            
            //Console.print("Start Core emulation...");
            //RUN();
        }
    }

    public static void StepInto()
    {
        if(CPU_REGISTER.PC != 0)
        {
            //count cycle and execute opcode...
            cpuCORE.CYCLE += cpuCORE.exec(CPU_MEMORY.read8BitForOtherFunctions(CPU_REGISTER.PC));
            ppuCORE.execPPU();
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
        //search the entire content of REG_Viewer.....
            for(ctr=0; ctr<=100; ctr++)
            {
                if(NesDebugger.REG_Viewer.getItem(ctr).contains(NesDebugger.REG_PC.getText()))
                {
                    tmp = ctr;
                }
            }

            NesDebugger.REG_Viewer.select(tmp);

            //Show data on the debugger.................
            NesDebugger.REG_A.setText(MISC_FUNCTIONS.forceTo8Bit(CPU_REGISTER.A));
            NesDebugger.REG_X.setText(MISC_FUNCTIONS.forceTo8Bit(CPU_REGISTER.X));
            NesDebugger.REG_Y.setText(MISC_FUNCTIONS.forceTo8Bit(CPU_REGISTER.Y));
            NesDebugger.REG_PC.setText(MISC_FUNCTIONS.forceTo16Bit(CPU_REGISTER.PC));
            NesDebugger.REG_SP.setText(MISC_FUNCTIONS.forceTo8Bit(CPU_REGISTER.SP));

            NesDebugger.F_N.setText("" + CPU_REGISTER.getNegativeFlag());
            NesDebugger.F_Z.setText("" + CPU_REGISTER.getZeroFlag());
            NesDebugger.F_C.setText("" + CPU_REGISTER.getCarryFlag());
            NesDebugger.F_I.setText("" + CPU_REGISTER.getInterruptFlag());
            NesDebugger.F_V.setText("" + CPU_REGISTER.getOverflowFlag());
            NesDebugger.F_D.setText("" + CPU_REGISTER.getDecimalFlag());
            NesDebugger.F_B.setText("" + CPU_REGISTER.getBRKFlag());

            NesDebugger.MEM_2000.setText("" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(0x2000)));
            NesDebugger.MEM_2001.setText("" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(0x2001)));
            NesDebugger.MEM_2002.setText("" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(0x2002)));
            NesDebugger.MEM_2003.setText("" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(0x2003)));
            NesDebugger.MEM_2004.setText("" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(0x2004)));
            NesDebugger.MEM_2005.setText("" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(0x2005)));
            NesDebugger.MEM_2006.setText("" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(0x2006)));
            NesDebugger.MEM_2007.setText("" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(0x2007)));

            NesDebugger.CPU_CYCLE.setText("" + cpuCORE.CYCLE);
            NesDebugger.PPU_SCANLINE.setText("" + ppuCORE.SCANLINE);
    }

    public static void STOP()
    {
        Console.print("Stop Core emulation...");
        isRunning = false;
        Main.win.setTitle("JNemu");
    }

    public static void GO()
    {
        Console.print("Start Core emulation...");
        isRunning = true;
        RUN();
        Main.win.setTitle("JNemu - running");
    }

    private static void RUN()
    {
        while(isRunning)
        {
            cpuCORE.CYCLE += cpuCORE.exec(CPU_MEMORY.read8BitForOtherFunctions(CPU_REGISTER.PC));
            ppuCORE.execPPU();
        }
    }

}

package jnemu;

import CARTRIDGE.ROMS;
import CARTRIDGE.MAPPER;
import CPU.MEMORY;
import CPU.cpuCORE;
import CPU.REGISTER;
import MISC.NES_DEBUGGER;

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
            MEMORY.init();

             //init mapper.....
            MAPPER.init();
            MEMORY.showMemInDebugger();

            Console.print("Initializing cpu...");
            cpuCORE.init();

            NesDebugger.loadOpcode();
            //Show data on the debugger.................
            NesDebugger.REG_A.setText(NES_DEBUGGER.forceTo8Bit(REGISTER.A));
            NesDebugger.REG_X.setText(NES_DEBUGGER.forceTo8Bit(REGISTER.X));
            NesDebugger.REG_Y.setText(NES_DEBUGGER.forceTo8Bit(REGISTER.Y));
            NesDebugger.REG_PC.setText(NES_DEBUGGER.forceTo16Bit(REGISTER.PC));

            NesDebugger.F_N.setText("" + REGISTER.negativeFlag);
            NesDebugger.F_Z.setText("" + REGISTER.zeroFlag);
            NesDebugger.F_C.setText("" + REGISTER.carryFlag);
            NesDebugger.F_I.setText("" + REGISTER.interruptFlag);
            NesDebugger.F_V.setText("" + REGISTER.overflowFlag);
            
            //Console.print("Start Core emulation...");
            //RUN();
        }
    }

    public static void StepInto()
    {
        int ctr, tmp = 0;

        if(REGISTER.PC != 0)
        {
            //execute opcode...
            cpuCORE.exec(MEMORY.read8Bit(REGISTER.PC));

            //search the entire content of REG_Viewer.....
            for(ctr=0; ctr<=255; ctr++)
            {
                if(NesDebugger.REG_Viewer.getItem(ctr).contains(NesDebugger.REG_PC.getText()))
                {
                    tmp = ctr;
                }
            }

            NesDebugger.REG_Viewer.select(tmp);

            //Show data on the debugger.................
            NesDebugger.REG_A.setText(NES_DEBUGGER.forceTo8Bit(REGISTER.A));
            NesDebugger.REG_X.setText(NES_DEBUGGER.forceTo8Bit(REGISTER.X));
            NesDebugger.REG_Y.setText(NES_DEBUGGER.forceTo8Bit(REGISTER.Y));
            NesDebugger.REG_PC.setText(NES_DEBUGGER.forceTo16Bit(REGISTER.PC));

            NesDebugger.F_N.setText("" + REGISTER.negativeFlag);
            NesDebugger.F_Z.setText("" + REGISTER.zeroFlag);
            NesDebugger.F_C.setText("" + REGISTER.carryFlag);
            NesDebugger.F_I.setText("" + REGISTER.interruptFlag);
            NesDebugger.F_V.setText("" + REGISTER.overflowFlag);
            NesDebugger.F_D.setText("" + REGISTER.decimalFlag);

            NesDebugger.MEM_2000.setText("" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(0x2000)));
            NesDebugger.MEM_2001.setText("" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(0x2001)));
            NesDebugger.MEM_2002.setText("" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(0x2002)));
        }
        else
        {
            Console.print("[emuCORE] The PC does not have a value.");
        }
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
        isRunning = false;
        RUN();
        Main.win.setTitle("JNemu - running");
    }

    private static void RUN()
    {
        while(isRunning)
        {
            isRunning = false;
        }
    }

}

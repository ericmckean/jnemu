package jnemu;

import CARTRIDGE.ROMS;
import CARTRIDGE.MAPPER;
import CPU.MEMORY;
import CPU.cpuCORE;

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
            Main.win.setTitle("JNemu - running");
            //enable stop............
            WinMain.myStop.setEnabled(true);
            WinMain.myStart.setEnabled(false);

            //initialize Nes Components..
            Console.print("Initializing cpu...");
            cpuCORE.init();
            Console.print("Initializing cpu memory...");
            MEMORY.init();

            //init mapper.....
            MAPPER.init();
            MEMORY.showMemInDebugger();

            Console.print("Start Core emulation...");
            RUN();
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
        Console.print("Resume Core emulation...");
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

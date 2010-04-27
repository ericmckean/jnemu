package jnemu;

public class Core
{
    public static boolean isRunning = false;

    public static void startEmulation()
    {
        //if the file is loaded to the cartridge, start emulation.
        //else dont do anything...................................
        
        if(Cartridge.noSelectedFile == false)
        {
            isRunning = true;
            Main.win.setTitle("JNemu - running");
            //enable stop............
            WinMain.myStop.setEnabled(true);
            WinMain.myStart.setEnabled(false);
            
            //initialize Nes Components..
            Console.print("Initializing memory...");
            eMEM.init();
            

            //init mapper.....
            mapper.init();
            eMEM.showMemInDebugger();

            Console.print("Start Core emulation...");
            RUN();
        }
    }

    public static void stopEmulation()
    {
        Console.print("Stop Core emulation...");
        isRunning = false;
        Main.win.setTitle("JNemu");
    }

    private static void RUN()
    {
        while(isRunning)
        {
            isRunning = false;
        }
    }

}

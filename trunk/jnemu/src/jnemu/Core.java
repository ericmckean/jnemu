package jnemu;

public class Core
{
    public static boolean isRunning = false;

    public static void runEmulation()
    {
        //if the file is loaded to the cartridge, start emulation.
        //else dont do anything...................................
        if(Cartridge.noSelectedFile == false)
        {
            Console.print("Starting core emulation");
            isRunning = true;
            Main.win.setTitle("JNemu - running");
            //enable stop............
            WinMain.myStop.setEnabled(true);
            WinMain.myStart.setEnabled(false);
            //put emulation code here................
        }
    }

    public static void startEmulation()
    {
        Console.print("Starting core emulation");
        isRunning = true;
        Main.win.setTitle("JNemu - running");
    }

    public static void stopEmulation()
    {
        Console.print("Stopping core emulation");
        isRunning = false;
        Main.win.setTitle("JNemu");
    }
}

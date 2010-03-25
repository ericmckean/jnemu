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
            isRunning = true;
            Main.win.setTitle("JNemu - running");
            //enable stop............
            WinMain.myStop.setEnabled(true);
            //put emulation code here................
        }
    }

    public static void startEmulation()
    {
        isRunning = true;
        Main.win.setTitle("JNemu - running");
    }

    public static void stopEmulation()
    {
        isRunning = false;
        Main.win.setTitle("JNemu");
    }
}
package jnemu;
import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;
import DEBUGGER.NesDebugger;
import PPU.ppuCORE;
public class Main
{
    public static WinMain win;
    public static NesController cont;
    public static NesDebugger deb;
    public static NesGraphics graph;
    public static NesSound sound;
    public static About about;
    public static Option opt;
    public static Console con;
    
    public static void main(String[] args)
    {
        //Load Emu Config...........

        //Load Controller Window..
        cont = new NesController();
        cont.setVisible(false);

        //Load Debugger Window..
        deb = new NesDebugger();
        deb.setVisible(false);

        //Load Graphics Window..
        graph = new NesGraphics();
        graph.setVisible(false);

        //Load Sound Window..
        sound = new NesSound();
        sound.setVisible(false);

        //Load About Window..
        about = new About();
        about.setVisible(false);

        //Load Folder Window..
        opt = new Option();
        opt.setVisible(false);

        //Console..
        con = new Console();
        con.setVisible(false);

         //Show Main Window..........
        win = new WinMain();
        //Show Console before main window..
        Console.displayFrame();
        win.setVisible(true);
        
        //Clear the Console......
        Console.clearConsole();

    }

}


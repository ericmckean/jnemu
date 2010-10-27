package jnemu;

import CONFIG.*;
import DEBUGGER.NES_DEBUGGER;
import LOGS.LOGGER;

public class Main
{
    public static WinMain win;
    public static NesController cont;
    public static NES_DEBUGGER deb;
    public static NesGraphics graph;
    public static NesSound sound;
    public static About about;
    public static Option opt;
    public static Console con;
    
    public static void main(String[] args)
    {
        //init default config...
        cfgCORE.initDefault();
        //Console..
        con = new Console(CFG.getDefaultConsoleTitle(), 0, 0, 500, 400);
        if(CONFIG.CFG.showConsole)
        {
            con.show();
        }
        else
        {
            con.hide();
        }

        System.out.println("JNemu - Java based Nes emulator.");
        System.out.println("");

        //Load Controller Window..
        cont = new NesController();
        cont.setVisible(false);

        //Load Debugger Window..
        deb = new NES_DEBUGGER();
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

         //Show Main Window..........
        win = new WinMain();
        win.setVisible(true);
        

        //**************************************
        //           Init CPU Logger
        //**************************************
        System.out.println("Initializing LOGGER...");
        LOGGER.init();
    }

}


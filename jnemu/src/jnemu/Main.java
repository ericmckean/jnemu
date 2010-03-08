package jnemu;

public class Main
{
    public static WinMain win;
    public static NesController cont;
    public static NesDebugger deb;
    public static NesGraphics graph;
    public static NesSound sound;
    public static About about;
    
    public static void main(String[] args)
    {
        //Load Emu Config...........

        //Show Main Window..........
        win = new WinMain();
        win.setVisible(true);

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
    }

}


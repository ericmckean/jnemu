package jnemu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener
{
    String x;
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Exit"))
        {
            System.exit(0);
        }
        else if(e.getActionCommand().equals("Open Rom"))
        {
           x = FileOpen.getFilePath();
        }
        else if(e.getActionCommand().equals("Controller"))
        {
            Main.cont.setVisible(true);
        }
        else if(e.getActionCommand().equals("Graphics"))
        {
            Main.graph.setVisible(true);
        }
        else if(e.getActionCommand().equals("Sound"))
        {
            Main.sound.setVisible(true);
        }
        else if(e.getActionCommand().equals("Debugger"))
        {
            Main.deb.setVisible(true);
        }
        else if(e.getActionCommand().equals("About"))
        {
            Main.about.setVisible(true);
        }
    }
}

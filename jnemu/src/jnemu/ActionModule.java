package jnemu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionModule implements ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Exit"))
        {
            System.exit(0);
        }
    }
            
}

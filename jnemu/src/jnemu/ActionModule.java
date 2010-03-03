/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jnemu;

/**
 *
 * @author Michael
 */
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

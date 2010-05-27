package jnemu;

import java.awt.event.*;

public class Key_Listener extends KeyAdapter
{
    @Override
    public void keyPressed(KeyEvent evt)
    {
        switch(evt.getKeyCode())
        {
            case KeyEvent.VK_F11 :
                emuCORE.StepInto();
                break;
            case KeyEvent.VK_F10 :
                emuCORE.updateDebugger();
                break;
        }
    }
}

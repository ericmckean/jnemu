package jnemu;

import java.awt.event.*;

public class KeyListener extends KeyAdapter
{
    @Override
    public void keyPressed(KeyEvent evt)
    {
        switch(evt.getKeyCode())
        {
            case KeyEvent.VK_F11 :
                EmuCore.StepInto();
                break;
            case KeyEvent.VK_F10 :
                EmuCore.updateDebugger();
                break;
        }
    }
}

package jnemu;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class Console extends WindowAdapter implements WindowListener,  ActionListener, Runnable
{
    private JFrame CON;
    private JTextArea CON_OUT;
    private Thread TReader;
    private Thread TReader2;
    private boolean END;

    private final PipedInputStream pin = new PipedInputStream();
    private final PipedInputStream pin2 = new PipedInputStream();

    Thread mThrower;

    public Console(String name, int x, int y, int width, int height)
    {
        CON = new JFrame(name);

        CON.setBounds(x , y, width, height);

        CON_OUT = new JTextArea();
        CON_OUT.setEditable(false);
        CON_OUT.setBackground(Color.BLACK);
        CON_OUT.setForeground(Color.LIGHT_GRAY);
        Font font = new Font("Terminal", Font.BOLD, 12);
        CON_OUT.setFont(font);

        DefaultCaret caret = (DefaultCaret)CON_OUT.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        CON.getContentPane().setLayout(new BorderLayout());
        CON.getContentPane().add(new JScrollPane(CON_OUT),BorderLayout.CENTER);
        CON.setVisible(false);

        CON.addWindowListener(this);

        try
        {
                PipedOutputStream pout = new PipedOutputStream(this.pin);
                System.setOut(new PrintStream(pout,true));
        }
        catch (java.io.IOException io)
        {
                CON_OUT.append("[Console Error] Cannot redirect STDOUT\n" + io.getMessage());
        }
        catch (SecurityException se)
        {
                CON_OUT.append("[Console Error] Cannot redirect STDOUT\n" + se.getMessage());
        }

        try
        {
                PipedOutputStream pout2=new PipedOutputStream(this.pin2);
                System.setErr(new PrintStream(pout2,true));
        }
        catch (java.io.IOException io)
        {
                CON_OUT.append("[Console Error] Cannot redirect STDERR\n" + io.getMessage());
        }
        catch (SecurityException se)
        {
                CON_OUT.append("[Console Error] Cannot redirect STDERR\n" + se.getMessage());
        }

        END = false;

        TReader=new Thread(this);
        TReader.setDaemon(true);
        TReader.start();

        TReader2=new Thread(this);
        TReader2.setDaemon(true);
        TReader2.start();

    }

    @Override
    public synchronized void windowClosed(WindowEvent evt)
    {
        END = true;
        this.notifyAll();
        try {
            TReader.join(1000);pin.close();
        }
        catch (Exception e)
        {
        }

        try {
            TReader2.join(1000);pin2.close();
        }
        catch (Exception e)
        {
        }
        System.exit(0);
    }

    @Override
    public synchronized void windowClosing(WindowEvent evt)
    {
        CON.setVisible(false);
        CON.dispose();
    }

    public synchronized void actionPerformed(ActionEvent evt)
    {
        CON_OUT.setText("");
    }

    public synchronized void run()
    {
        try
        {
            while (Thread.currentThread() == TReader)
            {
                try {
                    this.wait(100);
                }catch(InterruptedException ie)
                {
                }
                if (pin.available()!=0)
                {
                    String input=this.readLine(pin);
                    CON_OUT.append(input);
                }
                if (END)
                {
                    return;
                }
            }

            while (Thread.currentThread() == TReader2)
            {
                try {
                    this.wait(100);
                }
                catch(InterruptedException ie)
                {
                }
                if (pin2.available()!=0)
                {
                    String input=this.readLine(pin2);
                    CON_OUT.append(input);
                }
                if (END) return;
            }
        }
        catch (Exception e)
        {
            CON_OUT.append("\n[Console Error] " + e);
        }
    }

    public synchronized String readLine(PipedInputStream in) throws IOException
    {
        String input="";
        do
        {
            int available = in.available();
            if (available == 0)
            {
                break;
            }
            byte b[] = new byte[available];
            in.read(b);
            input = input + new String(b,0,b.length);
        }
        while(!input.endsWith("\n") &&  !input.endsWith("\r\n") && !END);
        return input;
    }

    public void show()
    {
        CON.setVisible(true);
    }

    public void hide()
    {
        CON.setVisible(false);
    }

    public void setTitle(String title)
    {
        CON.setTitle(title);
    }

}

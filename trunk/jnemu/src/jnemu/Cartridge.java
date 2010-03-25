package jnemu;

import java.io.*;

public class Cartridge
{
    public static boolean noSelectedFile;
    public static byte[] tmp;

    public static void loadNesROM(String path)
    {
        if(path.equals(""))
        {
            noSelectedFile = true;
        }
        else
        {
            noSelectedFile = false;
            //load the nes file to cartridge..........
            try
            {
                File f = new File(path);
                tmp = new byte[(int)f.length()];
                tmp = Emu_MOD.getBytesFromFile(f);
                //System.out.print((int)f.length());
                showInDebugger(tmp);
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
            }
        }
    }

    static void showInDebugger(byte[] bytes)
    {
        int length = (int)bytes.length;
        int ctr, ctr2;
        
        StringBuilder temp = new StringBuilder();
        
        ctr2 = 0;
        for(ctr=0; ctr<length; ctr++)
        {
            ctr2 = ctr2 + 1;
            try
            {
                if(ctr2 != 16)
                {
                    temp.append(bytes[ctr]);
                    temp.append(" ");
                }
                else
                {
                    temp.append(bytes[ctr]);
                    temp.append("\n");
                    ctr2 = 0;
                }
            }
            catch (Exception e){}
        }
        NesDebugger.jt.setText(temp.toString());
    }
}

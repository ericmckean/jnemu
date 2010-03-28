package jnemu;

import java.io.*;
import java.text.*;

public class Cartridge
{
    public static boolean noSelectedFile;
    private static byte[] RomContent;
    
    //check if the file loaded was a nes rom file.
    public static boolean isNes, isLoaded;

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
                Console.print("Loading file '" + f.getName() + "'");
                RomContent = new byte[(int)f.length()];
                RomContent = Emu_MOD.getBytesFromFile(f);
                //System.out.print((int)f.length());
                checkNesROM(RomContent);
                if(Cartridge.isNes)
                {
                    showInDebugger(RomContent);
                    //show 16kb Rom bank..
                    Console.print("16kb Rom Bank : " + get16kbRomBank(RomContent));
                    //show 8kb VRom bank..
                    Console.print("8kb VRom Bank : " + get8kbVRomBank(RomContent));
                     //show 8kb Ram bank..
                    Console.print("8kb Ram Bank : " + get8kbRamBank(RomContent));
                    isLoaded = true;
                }
                else
                {
                    Console.print("Error : Not a Nes rom file.");
                    isLoaded = false;
                }
            }
            catch(Exception e)
            {
                Console.print(e.toString());
            }
        }
    }

    private static void showInDebugger(byte[] bytes)
    {
        int length = (int)bytes.length;
        int ctr, ctr2, lineCTR;
        String Spacer = " ";
        String Spacer2 = "  :  ";
        int BASE = 16;

        DecimalFormat df = new DecimalFormat("0000000");
        DecimalFormat dfUpper = new DecimalFormat("00");
        
        StringBuilder temp = new StringBuilder();
        
        ctr2 = 0;
        lineCTR = BASE;

        //Upper counter...........................
        temp.append("            ");
        for(int upperCTR=0; upperCTR<BASE; upperCTR++)
        {
            temp.append(dfUpper.format(upperCTR));
            temp.append(Spacer);
        }
        temp.append("\n");
        temp.append("\n");

        //Left counter.............................
        temp.append(df.format(lineCTR));
        temp.append(Spacer2);
        //Content..................................
        for(ctr=0; ctr<length; ctr++)
        {
            ctr2 = ctr2 + 1;
            try
            {
                if(ctr2 != BASE)
                {
                    temp.append(Emu_MOD.byteToStringHex(bytes[ctr]).toUpperCase());
                    temp.append(Spacer);
                }
                else
                {
                    temp.append(Emu_MOD.byteToStringHex(bytes[ctr]).toUpperCase());
                    temp.append("\n");
                    lineCTR = lineCTR + BASE;
                    temp.append(df.format(lineCTR));
                    temp.append(Spacer2);
                    ctr2 = 0;
                }
            }
            catch (Exception e){Console.print(e.toString());}
        }
        NesDebugger.jt.setText(temp.toString());
    }

    private static void checkNesROM(byte[] bytes)
    {
        String str = new String(bytes);
        if(str.substring(0, 3).equals("NES"))
        {
            isNes = true;
        }
        else
        {
            isNes = false;
        }
    }

    private static String get16kbRomBank(byte[] b)
    {
        return Emu_MOD.byteToStringInt(b[4]);
    }

    private static String get8kbVRomBank(byte[] b)
    {
        return Emu_MOD.byteToStringInt(b[5]);
    }

    private static String get8kbRamBank(byte[] b)
    {
        return Emu_MOD.byteToStringInt(b[8]);
    }
}

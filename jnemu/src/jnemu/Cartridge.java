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
            //Clear the Console.....
            Console.clearConsole();
            //load the nes file to cartridge..........
            try
            {
                File f = new File(path);
                GAME.fSize = f.length();
                Console.print("Loading file [" + f.getName() + "]");
                RomContent = new byte[(int)f.length()];
                RomContent = Emu_MOD.getBytesFromFile(f);
                //System.out.print((int)f.length());
                checkNesROM(RomContent);
                if(Cartridge.isNes)
                {
                    showInDebugger(RomContent);
                    //Get the Cartridge info........
                    GAME.NumberOf16KbRomBank = Integer.parseInt(get16kbRomBank(RomContent));
                    GAME.NumberOf8KbVRomBank = Integer.parseInt(get8kbVRomBank(RomContent));
                    GAME.RamBank_8KB = Integer.parseInt(get8kbRamBank(RomContent));
                    GAME.MAPPER = getMapper(RomContent);
                    GAME.MIRRORING = getMirroring(RomContent);
                    GAME.TVSystem = getTVSystem(RomContent);

                    if(isBatteryBacked(RomContent).equals("1"))
                    {
                        GAME.isBatteryBacked = true;
                    }
                    else if(isBatteryBacked(RomContent).equals("0"))
                    {
                        GAME.isBatteryBacked = false;
                    }
                    
                    if(get512ByteTrainer(RomContent).equals("1"))
                    {
                        GAME.hasTrainer = true;
                    }
                    else if(get512ByteTrainer(RomContent).equals("0"))
                    {
                        GAME.hasTrainer = false;
                    }

                    if(getFourScreenVRamLayout(RomContent).equals("1"))
                    {
                        GAME.is4ScreenVRamLayout = true;
                    }
                    else if(getFourScreenVRamLayout(RomContent).equals("0"))
                    {
                        GAME.is4ScreenVRamLayout = false;
                    }

                    //Get the 512kb Trainer if existing..
                    if(GAME.hasTrainer)
                    {
                        get512Trainer(RomContent);
                    }
                    //Get the 16kb Rom Bank...
                    getRomBank(RomContent);
                    //Get the 8kb VRom Bank...
                    getVRomBank(RomContent);

                    //show rom info...........
                    GAME.showInfo();

                    isLoaded = true;
                }
                else
                {
                    Console.print("[ERROR] Not a Nes rom file.");
                    isLoaded = false;
                }
            }
            catch(Exception e)
            {
                isLoaded = false;
                Console.print("[JAVA] " + e.toString());
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
        lineCTR = 0;

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
            catch (Exception e){Console.print("[JAVA]" + e.toString());}
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

    private static int getMapper(byte[] b)
    {
        int tmp;
        String str;

        String lower = Emu_MOD.byteToStringHex(b[6]);
        String higher = Emu_MOD.byteToStringHex(b[7]);

        str =  higher.substring(0,1) + lower.substring(0,1);
        tmp = Integer.parseInt(str,16);

        return tmp;
    }

    private static String getMirroring(byte[] b)
    {
        String r = new String();
        String tmp = Emu_MOD.getCharFromString(1, Emu_MOD.byteToStringBinary(b[6]));
        if(tmp.equals("1"))
        {
            r = "VERTICAL";
        }
        else if(tmp.equals("0"))
        {
            r = "HORIZONTAL";
        }
        return r;
    }

    private static String isBatteryBacked(byte[] b)
    {
        String z = Emu_MOD.byteToStringBinary(b[6]);
        if(z.length() <= 1)
        {
            return "0";
        }
        else
        {
            String tmp = Emu_MOD.getCharFromString(2, z);
            return tmp;
        }
    }

    private static String get512ByteTrainer(byte[] b)
    {
        String z = Emu_MOD.byteToStringBinary(b[6]);
        if(z.length() <= 1)
        {
            return "0";
        }
        else
        {
            String tmp = Emu_MOD.getCharFromString(3, z);
            return tmp;
        }
    }

    private static String getFourScreenVRamLayout(byte[] b)
    {
        String z = Emu_MOD.byteToStringBinary(b[6]);
        if(z.length() <= 1)
        {
            return "0";
        }
        else
        {
            String tmp = Emu_MOD.getCharFromString(4, z);
            return tmp;
        }
    }

    private static String getTVSystem(byte[] b)
    {
        String z = Emu_MOD.byteToStringBinary(b[9]);
        String tmp = Emu_MOD.getCharFromString(1, z);
        String r = "";

        if(tmp.equals("0"))
        {
            r = "NTSC";
        }
        else if(tmp.equals("1"))
        {
            r = "PAL";
        }
        return r;
    }

    private static void get512Trainer(byte[] b)
    {
        int start = 16;
        int size = 512; //512 bytes.
        byte[] tmp;
        tmp = new byte[size]; 
        
        for(int a=0; a<size; a++)
        {
            tmp[a] = b[a + start];
        }
        
        GAME.Trainer = new byte[size];
        GAME.Trainer = tmp;
    }

    private static void getRomBank(byte[] b)
    {
        int start, x;
        int size = 16384; //16kb Rom bank.
        byte[][] tmp;

        if(GAME.hasTrainer)
        {
            start = 15 + 512;
            x = start;
        }
        else
        {
            start = 16;
            x = 15;
        }

        tmp = new byte[size][GAME.NumberOf16KbRomBank];
        
        for(int ctr=1; ctr<=GAME.NumberOf16KbRomBank; ctr++)
        {
            for(int a=0; a<size; a++)
            {
                tmp[a][ctr-1] = b[a + start];
            }
            start = x + (size * ctr);
        }

        GAME.RomBank_16KB = new byte[size][GAME.NumberOf16KbRomBank];
        GAME.RomBank_16KB = tmp;
    }

    private static void getVRomBank(byte[] b)
    {
        int start, x;
        int size = 8192; //8kb VRom bank.
        byte[][] tmp;

        if(GAME.hasTrainer)
        {
            start = 15 + 512 + (16384 * GAME.NumberOf16KbRomBank);
            x = start;
        }
        else
        {
            start = 16 + (16384 * GAME.NumberOf16KbRomBank);
            x = 15 + (16384 * GAME.NumberOf16KbRomBank);
        }

        tmp = new byte[size][GAME.NumberOf8KbVRomBank];

        for(int ctr=1; ctr<=GAME.NumberOf8KbVRomBank; ctr++)
        {
            for(int a=0; a<size; a++)
            {
                tmp[a][ctr-1] = b[a + start];
            }
            start = x + (size * ctr);
        }

        GAME.VRomBank_8KB = new byte[size][GAME.NumberOf8KbVRomBank];
        GAME.VRomBank_8KB = tmp;
    }
}

package CARTRIDGE;

import jnemu.Console;
import jnemu.NesDebugger;
import MISC.CONVERTER;
import MISC.INOUT;

import java.io.*;
import java.text.*;

public class ROMS
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
                RomContent = INOUT.getBytesFromFile(f);
                //System.out.print((int)f.length());
                checkNesROM(RomContent);
                if(ROMS.isNes)
                {
                    showInDebugger(RomContent);
                    //Get the Cartridge info........
                    GAME.NumberOf16KbRomBank = Integer.parseInt(get16kbRomBank(RomContent));
                    GAME.NumberOf8KbVRomBank = Integer.parseInt(get8kbVRomBank(RomContent));
                    GAME.RamBank_8KB = Integer.parseInt(get8kbRamBank(RomContent));
                    GAME.MAPPER_NUMBER = getMapper(RomContent);
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

                    //show rom info...........
                    GAME.showInfo();

                    //Get the 16kb Rom Bank...
                    getRomBank(RomContent);
                    //Get the 8kb VRom Bank...
                    getVRomBank(RomContent);

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
                Console.print("[loadNesROM] " + e.toString());
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
                    temp.append(CONVERTER.byteToStringHex(bytes[ctr]).toUpperCase());
                    temp.append(Spacer);
                }
                else
                {
                    temp.append(CONVERTER.byteToStringHex(bytes[ctr]).toUpperCase());
                    temp.append("\n");
                    lineCTR = lineCTR + BASE;
                    temp.append(df.format(lineCTR));
                    temp.append(Spacer2);
                    ctr2 = 0;
                }
            }
            catch (Exception e){Console.print("[showInDebugger]" + e.toString());}
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
        return CONVERTER.byteToStringInt(b[4]);
    }

    private static String get8kbVRomBank(byte[] b)
    {
        return CONVERTER.byteToStringInt(b[5]);
    }

    private static String get8kbRamBank(byte[] b)
    {
        return CONVERTER.byteToStringInt(b[8]);
    }

    private static int getMapper(byte[] b)
    {
        int tmp;
        String str;

        String lower = CONVERTER.byteToStringHex(b[6]);
        String higher = CONVERTER.byteToStringHex(b[7]);

        str =  higher.substring(0,1) + lower.substring(0,1);
        tmp = Integer.parseInt(str,16);

        return tmp;
    }

    private static String getMirroring(byte[] b)
    {
        String r = new String();
        String tmp = INOUT.getCharFromString(8, CONVERTER.byteToStringBinary(b[6]));
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
        String z = CONVERTER.byteToStringBinary(b[6]);
        if(z.length() <= 1)
        {
            return "0";
        }
        else
        {
            String tmp = INOUT.getCharFromString(7, z);
            return tmp;
        }
    }

    private static String get512ByteTrainer(byte[] b)
    {
        String z = CONVERTER.byteToStringBinary(b[6]);
        if(z.length() <= 1)
        {
            return "0";
        }
        else
        {
            String tmp = INOUT.getCharFromString(6, z);
            return tmp;
        }
    }

    private static String getFourScreenVRamLayout(byte[] b)
    {
        String z = CONVERTER.byteToStringBinary(b[6]);
        if(z.length() <= 1)
        {
            return "0";
        }
        else
        {
            String tmp = INOUT.getCharFromString(5, z);
            return tmp;
        }
    }

    private static String getTVSystem(byte[] b)
    {
        String z = CONVERTER.byteToStringBinary(b[9]);
        String tmp = INOUT.getCharFromString(1, z);
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
        int start, end, i;
        int size = 16384; //16kb Rom bank.

        if(GAME.hasTrainer)
        {
            start = 16 + 512;
        }
        else
        {
            start = 16;
        }

        end = start + size;
        i = 0;
        
        GAME.RomBank_16KB = new byte[size][GAME.NumberOf16KbRomBank];

        switch (GAME.NumberOf16KbRomBank)
        {
            case 0 : //do nothing, just to prevent the error showing.;
                break;
            case 1 : //load 1 Rom bank.
                for(int ctr=start; ctr<end; ctr++)
                {
                    GAME.RomBank_16KB[i][0] = b[ctr];
                    i++;
                }
                break;
            case 2 : //load 2 Rom bank.
                //0..
                for(int ctr=start; ctr<end; ctr++)
                {
                    GAME.RomBank_16KB[i][0] = b[ctr];
                    i++;
                }
                i = 0;
                start = end;
                end = start + size;
                //1..
                for(int ctr=start; ctr<end; ctr++)
                {
                    GAME.RomBank_16KB[i][1] = b[ctr];
                    i++;
                }
                break;
            default :
                Console.print("[ERROR] Unsupported Rom Bank count.");
                break;
        }
    }

    private static void getVRomBank(byte[] b)
    {
        int start, end, i;
        int size = 8192; //8kb VRom bank.

        if(GAME.hasTrainer)
        {
            start = 16 + 512 + (16384 * GAME.NumberOf16KbRomBank);
        }
        else
        {
            start = 16 + (16384 * GAME.NumberOf16KbRomBank);
        }

        end = start + size;
        i = 0;

        GAME.VRomBank_8KB = new byte[size][GAME.NumberOf8KbVRomBank];

        switch (GAME.NumberOf8KbVRomBank)
        {
            case 0 : //do nothing, just to prevent the error showing.;
                break;
            case 1 : //load 1 VRom bank.
                for(int ctr=start; ctr<end; ctr++)
                {
                    GAME.VRomBank_8KB[i][0] = b[ctr];
                    i++;
                }
                break;
            default :
                Console.print("[ERROR] Unsupported VRom Bank count.");
                break;
        }
    }
}

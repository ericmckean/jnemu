package CARTRIDGE;

import CONFIG.*;
import DEBUGGER.NES_DEBUGGER;
import MISC.CONVERTER;
import MISC.INOUT;

import java.io.*;
import java.text.*;
import jnemu.Main;

public class ROM_IO
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
            //Console.clearConsole();
            //load the nes file to cartridge..........
            try
            {
                File f = new File(path);
                ROM_INFO.fSize = f.length();
                System.out.println("");
                System.out.println("Loading file [" + f.getName() + "]");
                Main.con.setTitle(CFG.getDefaultConsoleTitle() + " - " + f.getName());
                RomContent = new byte[(int)f.length()];
                RomContent = INOUT.getBytesFromFile(f);
                //System.out.print((int)f.length());
                checkNesROM(RomContent);
                if(ROM_IO.isNes)
                {
                    showInDebugger(RomContent);
                    //Get the Cartridge info........
                    ROM_INFO.NumberOf16KbRomBank = Integer.parseInt(get16kbRomBank(RomContent));
                    ROM_INFO.NumberOf8KbVRomBank = Integer.parseInt(get8kbVRomBank(RomContent));
                    ROM_INFO.RamBank_8KB = Integer.parseInt(get8kbRamBank(RomContent));
                    ROM_INFO.MAPPER_NUMBER = getMapper(RomContent);
                    ROM_INFO.MIRRORING = getMirroring(RomContent);
                    ROM_INFO.TVSystem = getTVSystem(RomContent);

                    if(isBatteryBacked(RomContent).equals("1"))
                    {
                        ROM_INFO.isBatteryBacked = true;
                    }
                    else if(isBatteryBacked(RomContent).equals("0"))
                    {
                        ROM_INFO.isBatteryBacked = false;
                    }
                    
                    if(get512ByteTrainer(RomContent).equals("1"))
                    {
                        ROM_INFO.hasTrainer = true;
                    }
                    else if(get512ByteTrainer(RomContent).equals("0"))
                    {
                        ROM_INFO.hasTrainer = false;
                    }

                    if(getFourScreenVRamLayout(RomContent).equals("1"))
                    {
                        ROM_INFO.is4ScreenVRamLayout = true;
                    }
                    else if(getFourScreenVRamLayout(RomContent).equals("0"))
                    {
                        ROM_INFO.is4ScreenVRamLayout = false;
                    }

                    //Get the 512kb Trainer if existing..
                    if(ROM_INFO.hasTrainer)
                    {
                        get512Trainer(RomContent);
                    }

                    //show rom info...........
                    ROM_INFO.showInfo();

                    //Get the 16kb Rom Bank...
                    getRomBank(RomContent);
                    //Get the 8kb VRom Bank...
                    getVRomBank(RomContent);

                    isLoaded = true;
                }
                else
                {
                    System.out.println("[ERROR] Not a Nes rom file.");
                    isLoaded = false;
                }
            }
            catch(Exception e)
            {
                isLoaded = false;
                System.out.println("[loadNesROM] " + e.toString());
            }
        }
    }

    private static void showInDebugger(byte[] bytes)
    {
        int length = (int)bytes.length;
        int ctr, ctr2;
        String Spacer = " ";
        String Spacer3 = "   ";
        int BASE = 16;

        DecimalFormat dfUpper = new DecimalFormat("00");
        
        StringBuilder temp = new StringBuilder();
        StringBuilder unicode = new StringBuilder(50);
        
        ctr2 = 0;

        //Upper counter...........................
        for(int upperCTR=0; upperCTR < BASE; upperCTR++)
        {
            temp.append(dfUpper.format(upperCTR));
            temp.append(Spacer);
        }
        temp.append("\n");
        temp.append("\n");

        //Content..................................
        for(ctr=0; ctr<length; ctr++)
        {
            ctr2 += 1;
            if(ctr2 != BASE)
            {
                temp.append(CONVERTER.byteTo8BitStringHex(bytes[ctr]));
                temp.append(Spacer);
                unicode.append(CONVERTER.byteToChar(bytes[ctr]));
                unicode.append(Spacer);
            }
            else
            {
                temp.append(CONVERTER.byteTo8BitStringHex(bytes[ctr]));
                temp.append(Spacer3);
                temp.append(unicode.toString());
                unicode.delete(0, unicode.length());
                temp.append("\n");
                ctr2 = 0;
            }
            
        }
        NES_DEBUGGER.jt.setText(temp.toString());
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

        String lower = CONVERTER.byteTo8BitStringHex(b[6]);
        String higher = CONVERTER.byteTo8BitStringHex(b[7]);

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
        
        ROM_INFO.Trainer = new byte[size];
        ROM_INFO.Trainer = tmp;
    }

    private static void getRomBank(byte[] b)
    {
        int start, end, i;
        int size = 16384; //16kb Rom bank.

        if(ROM_INFO.hasTrainer)
        {
            start = 16 + 512;
        }
        else
        {
            start = 16;
        }

        end = start + size;
        i = 0;
        
        ROM_INFO.RomBank_16KB = new byte[size][ROM_INFO.NumberOf16KbRomBank];

        switch (ROM_INFO.NumberOf16KbRomBank)
        {
            case 0 : //do nothing, just to prevent the error showing.;
                break;
            case 1 : //load 1 Rom bank.
                for(int ctr=start; ctr<end; ctr++)
                {
                    ROM_INFO.RomBank_16KB[i][0] = b[ctr];
                    i++;
                }
                break;
            case 2 : //load 2 Rom bank.
                //0..
                for(int ctr=start; ctr<end; ctr++)
                {
                    ROM_INFO.RomBank_16KB[i][0] = b[ctr];
                    i++;
                }
                i = 0;
                start = end;
                end = start + size;
                //1..
                for(int ctr=start; ctr<end; ctr++)
                {
                    ROM_INFO.RomBank_16KB[i][1] = b[ctr];
                    i++;
                }
                break;
            default :
                System.out.println("[ERROR] Unsupported Rom Bank count.");
                break;
        }
    }

    private static void getVRomBank(byte[] b)
    {
        int start, end, i;
        int size = 8192; //8kb VRom bank.

        if(ROM_INFO.hasTrainer)
        {
            start = 16 + 512 + (16384 * ROM_INFO.NumberOf16KbRomBank);
        }
        else
        {
            start = 16 + (16384 * ROM_INFO.NumberOf16KbRomBank);
        }

        end = start + size;
        i = 0;

        ROM_INFO.VRomBank_8KB = new byte[size][ROM_INFO.NumberOf8KbVRomBank];

        switch (ROM_INFO.NumberOf8KbVRomBank)
        {
            case 0 : //do nothing, just to prevent the error showing.;
                break;
            case 1 : //load 1 VRom bank.
                for(int ctr=start; ctr<end; ctr++)
                {
                    ROM_INFO.VRomBank_8KB[i][0] = b[ctr];
                    i++;
                }
                break;
            default :
                System.out.println("[ERROR] Unsupported VRom Bank count.");
                break;
        }
    }
}

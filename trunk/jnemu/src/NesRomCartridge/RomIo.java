package NesRomCartridge;

import EmuConfig.*;
import EmuDebugger.NesDebugger;
import EmuMisc.Converter;
import EmuMisc.InOut;

import java.io.*;
import java.text.*;
import jnemu.Main;

public class RomIo
{
    public static boolean noSelectedFile;
    private static byte[] romContent;
    
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
                RomInfo.fSize = f.length();
                System.out.println("");
                System.out.println("Loading file [" + f.getName() + "]");
                Main.con.setTitle(CfgInfo.getDefaultConsoleTitle() + " - " + f.getName());
                romContent = new byte[(int)f.length()];
                romContent = InOut.getBytesFromFile(f);
                //System.out.print((int)f.length());
                checkNesROM(romContent);
                if(RomIo.isNes)
                {
                    showInDebugger(romContent);
                    //Get the Cartridge info........
                    RomInfo.numberOf16KbRomBank = Integer.parseInt(get16kbRomBank(romContent));
                    RomInfo.numberOf8KbVRomBank = Integer.parseInt(get8kbVRomBank(romContent));
                    RomInfo.ramBank_8KB = Integer.parseInt(get8kbRamBank(romContent));
                    RomInfo.mapperNumber = getMapper(romContent);
                    RomInfo.mirroringInfo = getMirroring(romContent);
                    RomInfo.tvSystem = getTVSystem(romContent);

                    if(isBatteryBacked(romContent).equals("1"))
                    {
                        RomInfo.isBatteryBacked = true;
                    }
                    else if(isBatteryBacked(romContent).equals("0"))
                    {
                        RomInfo.isBatteryBacked = false;
                    }
                    
                    if(get512ByteTrainer(romContent).equals("1"))
                    {
                        RomInfo.hasTrainer = true;
                    }
                    else if(get512ByteTrainer(romContent).equals("0"))
                    {
                        RomInfo.hasTrainer = false;
                    }

                    if(getFourScreenVRamLayout(romContent).equals("1"))
                    {
                        RomInfo.is4ScreenVRamLayout = true;
                    }
                    else if(getFourScreenVRamLayout(romContent).equals("0"))
                    {
                        RomInfo.is4ScreenVRamLayout = false;
                    }

                    //Get the 512kb Trainer if existing..
                    if(RomInfo.hasTrainer)
                    {
                        get512Trainer(romContent);
                    }

                    //show rom info...........
                    RomInfo.showInfo();

                    //Get the 16kb Rom Bank...
                    getRomBank(romContent);
                    //Get the 8kb VRom Bank...
                    getVRomBank(romContent);

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
                temp.append(Converter.byteTo8BitStringHex(bytes[ctr]));
                temp.append(Spacer);
                unicode.append(Converter.byteToChar(bytes[ctr]));
                unicode.append(Spacer);
            }
            else
            {
                temp.append(Converter.byteTo8BitStringHex(bytes[ctr]));
                temp.append(Spacer3);
                temp.append(unicode.toString());
                unicode.delete(0, unicode.length());
                temp.append("\n");
                ctr2 = 0;
            }
            
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
        return Converter.byteToStringInt(b[4]);
    }

    private static String get8kbVRomBank(byte[] b)
    {
        return Converter.byteToStringInt(b[5]);
    }

    private static String get8kbRamBank(byte[] b)
    {
        return Converter.byteToStringInt(b[8]);
    }

    private static int getMapper(byte[] b)
    {
        int tmp;
        String str;

        String lower = Converter.byteTo8BitStringHex(b[6]);
        String higher = Converter.byteTo8BitStringHex(b[7]);

        str =  higher.substring(0,1) + lower.substring(0,1);
        tmp = Integer.parseInt(str,16);

        return tmp;
    }

    private static String getMirroring(byte[] b)
    {
        String r = new String();
        String tmp = InOut.getCharFromString(8, Converter.byteToStringBinary(b[6]));
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
        String z = Converter.byteToStringBinary(b[6]);
        if(z.length() <= 1)
        {
            return "0";
        }
        else
        {
            String tmp = InOut.getCharFromString(7, z);
            return tmp;
        }
    }

    private static String get512ByteTrainer(byte[] b)
    {
        String z = Converter.byteToStringBinary(b[6]);
        if(z.length() <= 1)
        {
            return "0";
        }
        else
        {
            String tmp = InOut.getCharFromString(6, z);
            return tmp;
        }
    }

    private static String getFourScreenVRamLayout(byte[] b)
    {
        String z = Converter.byteToStringBinary(b[6]);
        if(z.length() <= 1)
        {
            return "0";
        }
        else
        {
            String tmp = InOut.getCharFromString(5, z);
            return tmp;
        }
    }

    private static String getTVSystem(byte[] b)
    {
        String z = Converter.byteToStringBinary(b[9]);
        String tmp = InOut.getCharFromString(1, z);
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
        
        RomInfo.trainerInfo = new byte[size];
        RomInfo.trainerInfo = tmp;
    }

    private static void getRomBank(byte[] b)
    {
        int start, end, i;
        int size = 16384; //16kb Rom bank.

        if(RomInfo.hasTrainer)
        {
            start = 16 + 512;
        }
        else
        {
            start = 16;
        }

        end = start + size;
        i = 0;
        
        RomInfo.romBank_16KB = new byte[size][RomInfo.numberOf16KbRomBank];

        switch (RomInfo.numberOf16KbRomBank)
        {
            case 0 : //do nothing, just to prevent the error showing.;
                break;
            case 1 : //load 1 Rom bank.
                for(int ctr=start; ctr<end; ctr++)
                {
                    RomInfo.romBank_16KB[i][0] = b[ctr];
                    i++;
                }
                break;
            case 2 : //load 2 Rom bank.
                //0..
                for(int ctr=start; ctr<end; ctr++)
                {
                    RomInfo.romBank_16KB[i][0] = b[ctr];
                    i++;
                }
                i = 0;
                start = end;
                end = start + size;
                //1..
                for(int ctr=start; ctr<end; ctr++)
                {
                    RomInfo.romBank_16KB[i][1] = b[ctr];
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

        if(RomInfo.hasTrainer)
        {
            start = 16 + 512 + (16384 * RomInfo.numberOf16KbRomBank);
        }
        else
        {
            start = 16 + (16384 * RomInfo.numberOf16KbRomBank);
        }

        end = start + size;
        i = 0;

        RomInfo.vRomBank_8KB = new byte[size][RomInfo.numberOf8KbVRomBank];

        switch (RomInfo.numberOf8KbVRomBank)
        {
            case 0 : //do nothing, just to prevent the error showing.;
                break;
            case 1 : //load 1 VRom bank.
                for(int ctr=start; ctr<end; ctr++)
                {
                    RomInfo.vRomBank_8KB[i][0] = b[ctr];
                    i++;
                }
                break;
            default :
                System.out.println("[ERROR] Unsupported VRom Bank count.");
                break;
        }
    }
}

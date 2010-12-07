package MAPPER;

import CARTRIDGE.RomInfo;
import CPU.*;
import PPU.*;

public class nRom
{
    public static void LoadRom()
    {
        int i=0;
        switch (RomInfo.numberOf16KbRomBank)
        {
            case 1 :
                System.out.println("Loading Bank 0 to address $8000-$BFFF...");
                for(int ctr=0x8000; ctr<=0xBFFF; ctr++)
                {
                    CpuMemory.write8Bit(ctr, RomInfo.romBank_16KB[i][0]);
                    i++;
                }
                i = 0;
                System.out.println("Loading Bank 0 to address $C000-$FFFF...");
                for(int ctr=0xC000; ctr<=0xFFFF; ctr++)
                {
                    CpuMemory.write8Bit(ctr, RomInfo.romBank_16KB[i][0]);
                    i++;
                }
                break;
            case 2 :
                System.out.println("Loading Bank 0 to address $8000-$BFFF...");
                for(int ctr=0x8000; ctr<=0xBFFF; ctr++)
                {
                    CpuMemory.write8Bit(ctr, RomInfo.romBank_16KB[i][0]);
                    i++;
                }
                i = 0;
                System.out.println("Loading Bank 1 to address $C000-$FFFF...");
                for(int ctr=0xC000; ctr<=0xFFFF; ctr++)
                {
                    CpuMemory.write8Bit(ctr, RomInfo.romBank_16KB[i][1]);
                    i++;
                }
                break;
        }
    }

    public static void LoadVRom()
    {
        int i=0;

        switch(RomInfo.numberOf8KbVRomBank)
        {
            case 0 :
                //Do nothing....
                break;
            case 1 :
                //Load Pattern Table....
                System.out.println("Loading Pattern Table...");
                for(int ctr=0x0000; ctr<=0x0FFF; ctr++)
                {
                    PpuMemory.writePPUMemory(ctr, RomInfo.vRomBank_8KB[i][0]);
                    i++;
                }
        }
    }
}

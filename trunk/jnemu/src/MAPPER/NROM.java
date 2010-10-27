package MAPPER;

import CARTRIDGE.ROM_INFO;
import CPU.*;
import PPU.*;

public class NROM
{
    public static void LoadRom()
    {
        int i=0;
        switch (ROM_INFO.NumberOf16KbRomBank)
        {
            case 1 :
                System.out.println("Loading Bank 0 to address $8000-$BFFF...");
                for(int ctr=0x8000; ctr<=0xBFFF; ctr++)
                {
                    CPU_MEMORY.write8Bit(ctr, ROM_INFO.RomBank_16KB[i][0]);
                    i++;
                }
                i = 0;
                System.out.println("Loading Bank 0 to address $C000-$FFFF...");
                for(int ctr=0xC000; ctr<=0xFFFF; ctr++)
                {
                    CPU_MEMORY.write8Bit(ctr, ROM_INFO.RomBank_16KB[i][0]);
                    i++;
                }
                break;
            case 2 :
                System.out.println("Loading Bank 0 to address $8000-$BFFF...");
                for(int ctr=0x8000; ctr<=0xBFFF; ctr++)
                {
                    CPU_MEMORY.write8Bit(ctr, ROM_INFO.RomBank_16KB[i][0]);
                    i++;
                }
                i = 0;
                System.out.println("Loading Bank 1 to address $C000-$FFFF...");
                for(int ctr=0xC000; ctr<=0xFFFF; ctr++)
                {
                    CPU_MEMORY.write8Bit(ctr, ROM_INFO.RomBank_16KB[i][1]);
                    i++;
                }
                break;
        }
    }

    public static void LoadVRom()
    {
        int i=0;

        switch(ROM_INFO.NumberOf8KbVRomBank)
        {
            case 0 :
                //Do nothing....
                break;
            case 1 :
                //Load Pattern Table....
                System.out.println("Loading Pattern Table...");
                for(int ctr=0x0000; ctr<=0x0FFF; ctr++)
                {
                    PPU_MEMORY.writePPUMemory(ctr, ROM_INFO.VRomBank_8KB[i][0]);
                    i++;
                }
        }
    }
}

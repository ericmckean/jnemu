package CARTRIDGE;

import CPU.CPU_MEMORY;
import jnemu.Console;

public class MAPPER
{
    public static void init()
    {
        switch(GAME.MAPPER_NUMBER)
        {
            case 0: init_mapper_0(); break;
        }
    }

    private static void init_mapper_0()
    {
        int i=0;
        switch (GAME.NumberOf16KbRomBank)
        {
            case 1 :
                Console.print("Loading Bank 0 to address $8000-$BFFF...");
                for(int ctr=0x8000; ctr<=0xBFFF; ctr++)
                {
                    CPU_MEMORY.write8Bit(ctr, GAME.RomBank_16KB[i][0]);
                    i++;
                }
                i = 0;
                Console.print("Loading Bank 0 to address $C000-$FFFF...");
                for(int ctr=0xC000; ctr<=0xFFFF; ctr++)
                {
                    CPU_MEMORY.write8Bit(ctr, GAME.RomBank_16KB[i][0]);
                    i++;
                }
                break;
            case 2 :
                Console.print("Loading Bank 0 to address $8000-$BFFF...");
                for(int ctr=0x8000; ctr<=0xBFFF; ctr++)
                {
                    CPU_MEMORY.write8Bit(ctr, GAME.RomBank_16KB[i][0]);
                    i++;
                }
                i = 0;
                Console.print("Loading Bank 1 to address $C000-$FFFF...");
                for(int ctr=0xC000; ctr<=0xFFFF; ctr++)
                {
                    CPU_MEMORY.write8Bit(ctr, GAME.RomBank_16KB[i][1]);
                    i++;
                }
                break;
        }
    }
}

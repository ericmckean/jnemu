package CARTRIDGE;

import PROC.MEMORY;
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
                Console.print("Loading Bank 0 to address 0x8000-0xBFFF...");
                for(int ctr=0x8000; ctr<=0xBFFF; ctr++)
                {
                    MEMORY.write8Bit(ctr, GAME.RomBank_16KB[i][0]);
                    i++;
                }
                i = 0;
                Console.print("Loading Bank 0 to address 0xC000-0xFFFF...");
                for(int ctr=0xC000; ctr<=0xFFFF; ctr++)
                {
                    MEMORY.write8Bit(ctr, GAME.RomBank_16KB[i][0]);
                    i++;
                }
                break;
            case 2 :
                Console.print("Loading Bank 0 to address 0x8000-0xBFFF...");
                for(int ctr=0x8000; ctr<=0xBFFF; ctr++)
                {
                    MEMORY.write8Bit(ctr, GAME.RomBank_16KB[i][0]);
                    i++;
                }
                i = 0;
                Console.print("Loading Bank 1 to address 0xC000-0xFFFF...");
                for(int ctr=0xC000; ctr<=0xFFFF; ctr++)
                {
                    MEMORY.write8Bit(ctr, GAME.RomBank_16KB[i][1]);
                    i++;
                }
                break;
        }
    }
}

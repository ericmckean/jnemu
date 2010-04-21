package jnemu;

public class mapper
{
    public static void init()
    {
        switch(GAME.MAPPER)
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
                Console.print("Loading Bank 0 to 0x0000-0xBFFF and 0xC000-0xFFFF...");
                for(byte ctr=(byte)0x8000; ctr<=(byte)0xBFFF; ctr++)
                {
                    eMEM.write8Bit(ctr, GAME.RomBank_16KB[i][0]);
                    i++;
                }
                i = 0;
                for(byte ctr=(byte)0xC000; ctr<=(byte)0xFFFF; ctr++)
                {
                    eMEM.write8Bit(ctr, GAME.RomBank_16KB[i][0]);
                    i++;
                }
                break;
            case 2 :
                Console.print("Loading Bank 0 to 0x0000-0xBFFF and Bank 1 to 0xC000-0xFFFF...");
                for(byte ctr=(byte)0x8000; ctr<=(byte)0xBFFF; ctr++)
                {
                    eMEM.write8Bit(ctr, GAME.RomBank_16KB[i][0]);
                    i++;
                }
                i = 0;
                for(byte ctr=(byte)0xC000; ctr<=(byte)0xFFFF; ctr++)
                {
                    eMEM.write8Bit(ctr, GAME.RomBank_16KB[i][1]);
                    i++;
                }
                break;
        }
    }
}

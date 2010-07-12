package CARTRIDGE;

import MAPPER.*;

public class mapperCORE
{
    public static void init()
    {
        switch(ROM_INFO.MAPPER_NUMBER)
        {
            case 0: 
                NROM.LoadRom();
                NROM.LoadVRom();
                break;
        }
    }
}

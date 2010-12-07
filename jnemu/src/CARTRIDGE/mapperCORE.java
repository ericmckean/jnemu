package CARTRIDGE;

import MAPPER.*;

public class MapperCore
{
    public static void init()
    {
        switch(RomInfo.mapperNumber)
        {
            case 0: 
                nRom.LoadRom();
                nRom.LoadVRom();
                break;
        }
    }
}

package PPU;

import CARTRIDGE.*;

public class MIRRORING
{
    public static void Mirror(int Addr, int Value)
    {
        if(ROM_INFO.MIRRORING.equals("HORIZONTAL"))
        {
            PPU_MEMORY.writePPUMemory(Addr + 0x400, Value);
        }
        else
        {
            PPU_MEMORY.writePPUMemory(Addr + 0x800, Value);
        }
    }
}

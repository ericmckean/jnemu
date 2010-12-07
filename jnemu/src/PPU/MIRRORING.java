package PPU;

import CARTRIDGE.*;

public class Mirroring
{
    public static void Mirror(int Addr, int Value)
    {
        if(RomInfo.mirroringInfo.equals("HORIZONTAL"))
        {
            PpuMemory.writePPUMemory(Addr + 0x400, Value);
        }
        else
        {
            PpuMemory.writePPUMemory(Addr + 0x800, Value);
        }
    }
}

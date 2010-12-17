package NesPpu;

import NesRomCartridge.*;

public class Mirroring
{
    public static void Mirror(int Addr, int Value)
    {
        if(RomInfo.mirroringInfo.equals("HORIZONTAL"))
        {
            PpuMemory.writePpuMemory(Addr + 0x400, Value);
        }
        else
        {
            PpuMemory.writePpuMemory(Addr + 0x800, Value);
        }
    }
}

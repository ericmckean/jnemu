package EmuHack;

public class Ppu
{
    public static int getActualPpuMemoryAddr(int addr)
    {
        return (addr & 0xfff) + 0x2000;
        //return addr;
    }
}

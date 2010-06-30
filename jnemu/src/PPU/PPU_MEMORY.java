package PPU;

import jnemu.Console;

public class PPU_MEMORY
{
    private static int[][] PPU_MEMORY_MAP;

    public static void init()
    {
        PPU_MEMORY_MAP = new int[0x100][0x100];
    }

    public static int readPPUMemory(int address)
    {
        return PPU_MEMORY_MAP[address >> 8][address & 0xff];
    }

    public static void writePPUMemory(int address, int value)
    {
        int page;

        page = address >> 8;
        try
        {
            PPU_MEMORY_MAP[page][address & 0xff] = value;
            //********************************************
            //           PPU Memory Mirroring
            //********************************************
            if(address >= 0x2000 && address <= 0x2eff)
            {
                //Mirrors of Name Tables....
                PPU_MEMORY_MAP[page + 0x1000][address & 0xff] = value;
            }
            else if(address >= 0x3f00 && address <= 0x3f1f)
            {
                //Mirrors of palette....
                PPU_MEMORY_MAP[page + 0x0020][address & 0xff] = value;
                PPU_MEMORY_MAP[page + 0x0040][address & 0xff] = value;
                PPU_MEMORY_MAP[page + 0x0060][address & 0xff] = value;
                PPU_MEMORY_MAP[page + 0x0080][address & 0xff] = value;
            }
        }
        catch(Exception e)
        {
            Console.print("[ERROR] " + e.toString());
        }
    }
}

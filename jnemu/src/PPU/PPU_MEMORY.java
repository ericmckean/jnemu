package PPU;

import MISC.CONVERTER;
import java.text.DecimalFormat;
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
        int addr = address;
        return PPU_MEMORY_MAP[addr >> 8][addr & 0xff];
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
                PPU_MEMORY_MAP[page + 0x10][address & 0xff] = value;
            }
            else if(address >= 0x3f00 && address <= 0x3f1f)
            {
                //Mirrors of palette....
                PPU_MEMORY_MAP[page + 0x20][address & 0xff] = value;
                PPU_MEMORY_MAP[page + 0x40][address & 0xff] = value;
                PPU_MEMORY_MAP[page + 0x60][address & 0xff] = value;
                PPU_MEMORY_MAP[page + 0x80][address & 0xff] = value;
            }
        }
        catch(Exception e)
        {
            Console.print("[ERROR] PPU MEM WRITE - $" + Integer.toHexString(address) + " : " + e.toString());
        }
    }

    public static int getActualPpuMemoryAddr(int addr)
    {
        int tmp = 0;

        if(addr >= 0x3f00 && addr <= 0x3fff)
        {
            tmp = addr; //Palette fetching
        }
        else
        {
            tmp = (addr & 0xfff) | 0x2000; //Nametable fetching
        }
        return tmp;
    }

    public static String getMemContent(int start, int end)
    {
        int ctr, ctr2, tmp;
        int BASE = 16;
        String space = " ";
        String space2 = " : ";
        String space3 = "   ";
        DecimalFormat dfUpper = new DecimalFormat("00");
        StringBuilder x = new StringBuilder();
        StringBuilder UTF8 = new StringBuilder(50);

        try
        {
            x.append("        ");
            for(ctr=0;ctr<BASE;ctr++)
            {
                x.append(dfUpper.format(ctr));
                x.append(space);
            }
            x.append("\n\n");
            x.append("$" + CONVERTER.intTo16BitStringHex(start));
            x.append(space2);

            ctr2 = 0;

            for(ctr=start; ctr<=end; ctr++)
            {
                ctr2 += 1;
                tmp = PPU_MEMORY.readPPUMemory(ctr);
                if(ctr2 != BASE)
                {
                    if(tmp != 0)
                    {
                        x.append(CONVERTER.intTo8BitStringHex(tmp));
                        x.append(space);
                        UTF8.append(CONVERTER.intToChar(tmp));
                        UTF8.append(space);
                    }
                    else
                    {
                        x.append("00");
                        x.append(space);
                        UTF8.append(".");
                        UTF8.append(space);
                    }
                }
                else
                {
                    if(tmp != 0)
                    {
                        x.append(CONVERTER.intTo8BitStringHex(tmp));
                        UTF8.append(CONVERTER.intToChar(tmp));
                        UTF8.append(space);
                        x.append(space3);
                        x.append(UTF8.toString());
                        UTF8.delete(0, UTF8.length());
                        x.append("\n");
                        x.append("$" + CONVERTER.intTo16BitStringHex(ctr + 1));
                        x.append(space2);
                    }
                    else
                    {
                        x.append("00");
                        UTF8.append(".");
                        UTF8.append(space);
                        x.append(space3);
                        x.append(UTF8.toString());
                        UTF8.delete(0, UTF8.length());
                        x.append("\n");
                        x.append("$" + CONVERTER.intTo16BitStringHex(ctr + 1));
                        x.append(space2);
                    }
                    ctr2 = 0;
                }
            }
        }
        catch(Exception e)
        {
            Console.print("[ERROR] Load the Rom first.");
        }
        return x.toString();
    }
}

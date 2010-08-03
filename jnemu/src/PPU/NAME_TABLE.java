package PPU;

public class NAME_TABLE
{
    private static int MSB, LSB;

    public static void readNameTable()
    {
        if(ppuCORE.isAccessingPPUADDR)
        {
            if(ppuCORE.isFirstWrite)
            {
                MSB = PPU_REGISTER.getPPUAddr();
                ppuCORE.isFirstWrite = false;
            }
            else
            {
                LSB = PPU_REGISTER.getPPUAddr();
                ppuCORE.PPU_ADDR = ((MSB << 8) | LSB); //get the actual PPU address..
                //FIXME: put value of memory is PPUDATA according to
                //PPUADDR's address content for reading purposes....
                //Console.print("[PPU_ADDR] " + Integer.toHexString(PPU_ADDR));
                PPU_REGISTER.setPPUData(PPU_MEMORY.readPPUMemory(ppuCORE.PPU_ADDR));
                ppuCORE.isFirstWrite = true;
                ppuCORE.isAccessingPPUADDR = false;
            }
        }
    }

    public static void fetchNameTable()
    {
        int AddrTmp;
        if(ppuCORE.isWritingPPUDATA)
        {
            //FIXME: write the value of PPUDATA to PPU memory according to
            //PPUADDR's address content...
            //Console.print("[$2007] " + Integer.toHexString(PPU_REGISTER.getPPUData()));
            AddrTmp = getActualPpuMemoryAddr(ppuCORE.PPU_ADDR);
            PPU_MEMORY.writePPUMemory(AddrTmp, PPU_REGISTER.getPPUData());
            MIRRORING.Mirror(AddrTmp, PPU_REGISTER.getPPUData());
            if(PPU_REGISTER.getVramAddressInc() == 0)
            {
                ppuCORE.PPU_ADDR++;
                ppuCORE.PPU_ADDR &= 0x3fff;
            }
            else
            {
                ppuCORE.PPU_ADDR += 32;
                ppuCORE.PPU_ADDR &= 0x3fff;
            }
            ppuCORE.isWritingPPUDATA = false;
        }
        else if(ppuCORE.isReadingPPUDATA)
        {
            PPU_REGISTER.setPPUData(PPU_MEMORY.readPPUMemory(ppuCORE.PPU_ADDR));
            if(PPU_REGISTER.getVramAddressInc() == 0)
            {
                ppuCORE.PPU_ADDR++;
                ppuCORE.PPU_ADDR &= 0x3fff;
            }
            else
            {
                ppuCORE.PPU_ADDR += 32;
                ppuCORE.PPU_ADDR &= 0x3fff;
            }
            ppuCORE.isReadingPPUDATA = false;
        }
    }

    private static int getActualPpuMemoryAddr(int addr)
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
}

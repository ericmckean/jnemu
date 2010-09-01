package PPU;

public class VRAM
{
    private static int MSB, LSB;

    public static void checkForSetAddr()
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
                ppuCORE.PPU_ADDR_LATCH = ((MSB << 8) | LSB); //get the actual PPU address..
                //FIXME: put value of memory is PPUDATA according to
                //PPUADDR's address content for reading purposes....
                //Console.print("[PPU_ADDR] " + Integer.toHexString(PPU_ADDR));
                PPU_REGISTER.setPPUData(ppuCORE.InternalBuffer);
                ppuCORE.InternalBuffer = PPU_MEMORY.readPPUMemory(ppuCORE.PPU_ADDR_LATCH);
                ppuCORE.isFirstWrite = true;
                ppuCORE.isAccessingPPUADDR = false;
            }
        }
    }

    public static void checkForRead()
    {
        if(ppuCORE.isReadingPPUDATA)
        {
            if(PPU_REGISTER.getVramAddressInc() == 0)
            {
                ppuCORE.PPU_ADDR_LATCH++;
                ppuCORE.PPU_ADDR_LATCH &= 0x3fff;
            }
            else
            {
                ppuCORE.PPU_ADDR_LATCH += 32;
                ppuCORE.PPU_ADDR_LATCH &= 0x3fff;
            }
            ppuCORE.InternalBuffer = PPU_MEMORY.readPPUMemory(ppuCORE.PPU_ADDR_LATCH);
            ppuCORE.isReadingPPUDATA = false;
        }
    }

    public static void checkForWrite()
    {
        int AddrTmp;
        if(ppuCORE.isWritingPPUDATA)
        {
            //FIXME: write the value of PPUDATA to PPU memory according to
            //PPUADDR's address content...
            //Console.print("[$2007] " + Integer.toHexString(PPU_REGISTER.getPPUData()));
            AddrTmp = getActualPpuMemoryAddr(ppuCORE.PPU_ADDR_LATCH);
            PPU_MEMORY.writePPUMemory(AddrTmp, PPU_REGISTER.getPPUData());
            MIRRORING.Mirror(AddrTmp, PPU_REGISTER.getPPUData());
            if(PPU_REGISTER.getVramAddressInc() == 0)
            {
                ppuCORE.PPU_ADDR_LATCH++;
                ppuCORE.PPU_ADDR_LATCH &= 0x3fff;
            }
            else
            {
                ppuCORE.PPU_ADDR_LATCH += 32;
                ppuCORE.PPU_ADDR_LATCH &= 0x3fff;
            }
            ppuCORE.isWritingPPUDATA = false;
        }
    }

    private static int getActualPpuMemoryAddr(int addr)
    {
        return (addr & 0xfff) + 0x2000;
        //return addr;
    }
}

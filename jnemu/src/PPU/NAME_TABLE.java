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
        if(ppuCORE.isWritingPPUDATA)
        {
            //FIXME: write the value of PPUDATA to PPU memory according to
            //PPUADDR's address content...
            //Console.print("[$2007] " + Integer.toHexString(PPU_REGISTER.getPPUData()));
            PPU_MEMORY.writePPUMemory(PPU_MEMORY.getActualPpuMemoryAddr(ppuCORE.PPU_ADDR), PPU_REGISTER.getPPUData());
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
}

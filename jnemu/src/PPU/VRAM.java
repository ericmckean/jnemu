package PPU;

//import CPU.*;

public class VRam
{
    private static int MSB, LSB;

    public static void checkForSetAddr()
    {
        if(PpuCore.isAccessingPPUADDR)
        {
            if(PpuCore.isFirstWrite)
            {
                MSB = PpuRegister.getPPUAddr();
                PpuCore.isFirstWrite = false;
            }
            else
            {
                LSB = PpuRegister.getPPUAddr();
                PpuCore.PPU_ADDR_LATCH = ((MSB << 8) | LSB); //get the actual PPU address..
                //FIXME: put value of memory is PPUDATA according to
                //PPUADDR's address content for reading purposes....
                //System.out.println("[PPU] pc=$" + Integer.toHexString(CPU_REGISTER.PC) + "; addr=" + Integer.toHexString(ppuCORE.PPU_ADDR_LATCH));
                PpuRegister.setPPUData(PpuCore.InternalBuffer);
                PpuCore.InternalBuffer = PpuMemory.readPPUMemory(PpuCore.PPU_ADDR_LATCH);
                PpuCore.isFirstWrite = true;
                PpuCore.isAccessingPPUADDR = false;
            }
        }
    }

    public static void checkForRead()
    {
        if(PpuCore.isReadingPPUDATA)
        {
            if(PpuRegister.getVramAddressInc() == 0)
            {
                PpuCore.PPU_ADDR_LATCH++;
                PpuCore.PPU_ADDR_LATCH &= 0x3fff;
            }
            else
            {
                PpuCore.PPU_ADDR_LATCH += 32;
                PpuCore.PPU_ADDR_LATCH &= 0x3fff;
            }
            PpuCore.InternalBuffer = PpuMemory.readPPUMemory(PpuCore.PPU_ADDR_LATCH);
            PpuCore.isReadingPPUDATA = false;
        }
    }

    public static void checkForWrite()
    {
        int AddrTmp;
        if(PpuCore.isWritingPPUDATA)
        {
            //FIXME: write the value of PPUDATA to PPU memory according to
            //PPUADDR's address content...
            //System.out.println("[$2007] " + Integer.toHexString(PPU_REGISTER.getPPUData()));
            AddrTmp = getActualPpuMemoryAddr(PpuCore.PPU_ADDR_LATCH);
            PpuMemory.writePPUMemory(AddrTmp, PpuRegister.getPPUData());
            Mirroring.Mirror(AddrTmp, PpuRegister.getPPUData());
            if(PpuRegister.getVramAddressInc() == 0)
            {
                PpuCore.PPU_ADDR_LATCH++;
                PpuCore.PPU_ADDR_LATCH &= 0x3fff;
            }
            else
            {
                PpuCore.PPU_ADDR_LATCH += 32;
                PpuCore.PPU_ADDR_LATCH &= 0x3fff;
            }
            PpuCore.isWritingPPUDATA = false;
        }
    }

    private static int getActualPpuMemoryAddr(int addr)
    {
        return (addr & 0xfff) + 0x2000;
        //return addr;
    }
}

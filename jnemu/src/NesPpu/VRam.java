package NesPpu;

//import CPU.*;

public class VRam
{
    private static int MSB, LSB;

    public static void checkForSetAddr()
    {
        if(PpuCore.isAccessingPpuAddr)
        {
            if(PpuCore.isFirstWrite)
            {
                MSB = PpuRegister.getPpuAddr();
                PpuCore.isFirstWrite = false;
            }
            else
            {
                LSB = PpuRegister.getPpuAddr();
                PpuCore.PPU_ADDR_LATCH = ((MSB << 8) | LSB); //get the actual PPU address..
                //FIXME: put value of memory is PPUDATA according to
                //PPUADDR's address content for reading purposes....
                //System.out.println("[PPU] pc=$" + Integer.toHexString(CPU_REGISTER.PC) + "; addr=" + Integer.toHexString(ppuCORE.PPU_ADDR_LATCH));
                PpuRegister.setPpuData(PpuCore.InternalBuffer);
                PpuCore.InternalBuffer = PpuMemory.readPpuMemory(PpuCore.PPU_ADDR_LATCH);
                PpuCore.isFirstWrite = true;
                PpuCore.isAccessingPpuAddr = false;
            }
        }
    }

    public static void checkForRead()
    {
        if(PpuCore.isReadingPpuData)
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
            PpuCore.InternalBuffer = PpuMemory.readPpuMemory(PpuCore.PPU_ADDR_LATCH);
            PpuCore.isReadingPpuData = false;
        }
    }

    public static void checkForWrite()
    {
        int AddrTmp;
        if(PpuCore.isWritingPpuData)
        {
            //FIXME: write the value of PPUDATA to PPU memory according to
            //PPUADDR's address content...
            //System.out.println("[$2007] " + Integer.toHexString(PPU_REGISTER.getPPUData()));
            AddrTmp = EmuHack.Ppu.getActualPpuMemoryAddr(PpuCore.PPU_ADDR_LATCH);
            PpuMemory.writePpuMemory(AddrTmp, PpuRegister.getPpuData());
            Mirroring.Mirror(AddrTmp, PpuRegister.getPpuData());
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
            PpuCore.isWritingPpuData = false;
        }
    }
}

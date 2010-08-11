package PPU;

public class oamCORE
{
    public static void checkForRead()
    {
        if(ppuCORE.isAccessingOAMADDR)
        {
            ppuCORE.OAM_ADDR = PPU_REGISTER.getOAMADDR();
            PPU_REGISTER.setOAM_DMA(OAM_MEMORY.Read(ppuCORE.OAM_ADDR));
            ppuCORE.isAccessingOAMADDR = false;
        }
    }

    public static void checkForWrite()
    {
        if(ppuCORE.isWritingOAMDATA)
        {
            OAM_MEMORY.Write(ppuCORE.OAM_ADDR, PPU_REGISTER.getOAMDATA());
            ppuCORE.OAM_ADDR++;
            ppuCORE.OAM_ADDR &= 0xff;
            ppuCORE.isWritingOAMDATA = false;
        }
    }
}

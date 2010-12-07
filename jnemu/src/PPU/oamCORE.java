package PPU;

public class OamCore
{
    public static void checkForRead()
    {
        if(PpuCore.isAccessingOAMADDR)
        {
            PpuCore.OAM_ADDR = PpuRegister.getOAMADDR();
            PpuRegister.setOAM_DMA(OamMemory.Read(PpuCore.OAM_ADDR));
            PpuCore.isAccessingOAMADDR = false;
        }
    }

    public static void checkForWrite()
    {
        if(PpuCore.isWritingOAMDATA)
        {
            OamMemory.Write(PpuCore.OAM_ADDR, PpuRegister.getOAMDATA());
            PpuCore.OAM_ADDR++;
            PpuCore.OAM_ADDR &= 0xff;
            PpuCore.isWritingOAMDATA = false;
        }
    }
}

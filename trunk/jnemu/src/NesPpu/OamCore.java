package NesPpu;

public class OamCore
{
    public static void checkForRead()
    {
        if(PpuCore.isAccessingOamAddr)
        {
            PpuCore.OAM_ADDR = PpuRegister.getOamAddr();
            PpuRegister.setOamDma(OamMemory.oamRead(PpuCore.OAM_ADDR));
            PpuCore.isAccessingOamAddr = false;
        }
    }

    public static void checkForWrite()
    {
        if(PpuCore.isWritingOamData)
        {
            OamMemory.oamWrite(PpuCore.OAM_ADDR, PpuRegister.getOamData());
            PpuCore.OAM_ADDR++;
            PpuCore.OAM_ADDR &= 0xff;
            PpuCore.isWritingOamData = false;
        }
    }
}

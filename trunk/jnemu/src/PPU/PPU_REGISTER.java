package PPU;

import CPU.CPU_MEMORY;

public class PPU_REGISTER
{
    //*******************************************************************
    //                       VBlank Flag
    //*******************************************************************
    public static void setVBlankFlag()
    {
        int tmp;

        tmp = CPU_MEMORY.fastRead8Bit(0x2002);
        tmp = tmp | 0x80;
        CPU_MEMORY.write8Bit(0x2002, tmp);
    }

    public static void clearVBlankFlag()
    {
        int tmp;

        tmp = CPU_MEMORY.fastRead8Bit(0x2002);
        tmp = tmp & 0x7F;
        CPU_MEMORY.write8Bit(0x2002, tmp);
    }

    public static int getVBlankFlag()
    {
        int tmp;

        tmp = CPU_MEMORY.fastRead8Bit(0x2002);
        tmp = (tmp & 0x80) >> 0x07;
        return tmp;
    }
    
    //*******************************************************************
    //                       NMI Flag
    //*******************************************************************

     public static void setNMIFlag()
    {
        int tmp;

        tmp = CPU_MEMORY.fastRead8Bit(0x2000);
        tmp = tmp | 0x80;
        CPU_MEMORY.write8Bit(0x2000, tmp);
    }

    public static void clearNMIFlag()
    {
        int tmp;

        tmp = CPU_MEMORY.fastRead8Bit(0x2000);
        tmp = tmp & 0x7F;
        CPU_MEMORY.write8Bit(0x2000, tmp);
    }

    public static int getNMIFlag()
    {
        int tmp;

        tmp = CPU_MEMORY.fastRead8Bit(0x2000);
        tmp = (tmp & 0x80) >> 0x07;
        return tmp;
    }
}

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

    //********************************************************
    //                   PPUADDR - $2006
    //********************************************************
    public static int getPPUAddr()
    {
        return CPU_MEMORY.fastRead8Bit(0x2006);
    }

    //********************************************************
    //                   PPUDATA - $2007
    //********************************************************
    public static int getPPUData()
    {
        return CPU_MEMORY.fastRead8Bit(0x2007);
    }

    public static void setPPUData(int Value)
    {
        CPU_MEMORY.write8Bit(0x2007, Value);
    }
    //********************************************************
    //                   VRam Address increment
    //********************************************************
    public static int getVramAddressInc()
    {
        int tmp;
        tmp = CPU_MEMORY.fastRead8Bit(0x2000);
        tmp &= 0x04;
        return tmp >> 2;
    }

    //*********************************************************
    //                 Base Name Table Address
    //*********************************************************

    public static int getBaseNameTableAddr()
    {
        int tmp, addr = 0;

        tmp = CPU_MEMORY.fastRead8Bit(0x2000);
        tmp &= 0x03;
        switch(tmp)
        {
            case 0 :
                addr = 0x2000;
                break;
            case 1 :
                addr = 0x2400;
                break;
            case 2 :
                addr = 0x2800;
                break;
            case 3 :
                addr = 0x2C00;
                break;
        }
        return addr;
    }

    //*********************************************************
    //            Background Pattern Table Address
    //*********************************************************

    public static int getBgPatternTableAddr()
    {
        int tmp, addr = 0;

        tmp = CPU_MEMORY.fastRead8Bit(0x2000);
        tmp &= 0x10;
        switch(tmp)
        {
            case 0 :
                addr = 0x0000;
                break;
            case 1 :
                addr = 0x1000;
                break;
        }
        return addr;
    }

    //*********************************************************
    //              Sprite Pattern Table Address
    //*********************************************************

    public static int getSprPatternTableAddr()
    {
        int tmp, addr = 0;

        tmp = CPU_MEMORY.fastRead8Bit(0x2000);
        tmp &= 0x08;
        switch(tmp)
        {
            case 0 :
                addr = 0x0000;
                break;
            case 1 :
                addr = 0x1000;
                break;
        }
        return addr;
    }

    //*********************************************************
    //                      Sprite Size
    //*********************************************************

    public static int getSprSize()
    {
        // 0 for 8x8, 1 for 8x16..........................
        int tmp;

        tmp = CPU_MEMORY.fastRead8Bit(0x2000);
        tmp &= 0x20;
        return tmp;
    }

    //*********************************************************
    //                           OAM
    //*********************************************************

    public static int getOAMADDR()
    {
        return CPU_MEMORY.fastRead8Bit(0x2003);
    }

    public static int getOAMDATA()
    {
        return CPU_MEMORY.fastRead8Bit(0x2004);
    }

    public static void setOAM_DMA(int Value)
    {
        CPU_MEMORY.write8Bit(0x4014, Value);
    }
}

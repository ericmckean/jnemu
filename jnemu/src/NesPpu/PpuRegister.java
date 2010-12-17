package NesPpu;

import NesCpu.CpuMemory;

public class PpuRegister
{
    //*******************************************************************
    //                       VBlank Flag
    //*******************************************************************
    public static void setVblankFlag()
    {
        int tmp;

        tmp = CpuMemory.fastRead8Bit(0x2002);
        tmp = tmp | 0x80;
        CpuMemory.write8Bit(0x2002, tmp);
    }

    public static void clearVblankFlag()
    {
        int tmp;

        tmp = CpuMemory.fastRead8Bit(0x2002);
        tmp = tmp & 0x7F;
        CpuMemory.write8Bit(0x2002, tmp);
    }

    public static int getVblankFlag()
    {
        int tmp;

        tmp = CpuMemory.fastRead8Bit(0x2002);
        tmp = (tmp & 0x80) >> 0x07;
        return tmp;
    }
    
    //*******************************************************************
    //                       NMI Flag
    //*******************************************************************

     public static void setNmiFlag()
    {
        int tmp;

        tmp = CpuMemory.fastRead8Bit(0x2000);
        tmp = tmp | 0x80;
        CpuMemory.write8Bit(0x2000, tmp);
    }

    public static void clearNmiFlag()
    {
        int tmp;

        tmp = CpuMemory.fastRead8Bit(0x2000);
        tmp = tmp & 0x7F;
        CpuMemory.write8Bit(0x2000, tmp);
    }

    public static int getNmiFlag()
    {
        int tmp;

        tmp = CpuMemory.fastRead8Bit(0x2000);
        tmp = (tmp & 0x80) >> 0x07;
        return tmp;
    }

    //********************************************************
    //                   PPUADDR - $2006
    //********************************************************
    public static int getPpuAddr()
    {
        return CpuMemory.fastRead8Bit(0x2006);
    }

    //********************************************************
    //                   PPUDATA - $2007
    //********************************************************
    public static int getPpuData()
    {
        return CpuMemory.fastRead8Bit(0x2007);
    }

    public static void setPpuData(int Value)
    {
        CpuMemory.write8Bit(0x2007, Value);
    }
    //********************************************************
    //                   VRam Address increment
    //********************************************************
    public static int getVramAddressInc()
    {
        int tmp;
        tmp = CpuMemory.fastRead8Bit(0x2000);
        tmp &= 0x04;
        return tmp >> 2;
    }

    //*********************************************************
    //                 Base Name Table Address
    //*********************************************************

    public static int getBaseNameTableAddr()
    {
        int tmp, addr = 0;

        tmp = CpuMemory.fastRead8Bit(0x2000);
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

        tmp = CpuMemory.fastRead8Bit(0x2000);
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

        tmp = CpuMemory.fastRead8Bit(0x2000);
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

        tmp = CpuMemory.fastRead8Bit(0x2000);
        tmp &= 0x20;
        return tmp;
    }

    //*********************************************************
    //                           OAM
    //*********************************************************

    public static int getOamAddr()
    {
        return CpuMemory.fastRead8Bit(0x2003);
    }

    public static int getOamData()
    {
        return CpuMemory.fastRead8Bit(0x2004);
    }

    public static void setOamDma(int Value)
    {
        CpuMemory.write8Bit(0x4014, Value);
    }
}

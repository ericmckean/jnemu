package CPU;

import java.util.Arrays;
import MISC.CONVERTER;
import PPU.PPU_REGISTER;
import PPU.ppuCORE;

public class CPU_MEMORY
{
    private static int[][] MEMORY_MAP;

    public static void init()
    {
        MEMORY_MAP = new int[0x100][0x100];
        //power up memory state...
        //FIXME: need to use unsigned(0-255) byte as java only uses signed(-128  to 127).
        for(int ctr=0x0000; ctr<=0x07FF; ctr++)
        {
            if(ctr == 0x0008)
            {
                write8Bit(ctr, 0xF7);
            }
            else if(ctr == 0x0009)
            {
                write8Bit(ctr, 0xEF);
            }
            else if(ctr == 0x000A)
            {
                write8Bit(ctr, 0xDF);
            }
            else if(ctr == 0x000F)
            {
                write8Bit(ctr, 0xBF);
            }
            else
            {
                write8Bit(ctr, 0xFF);
            }
        }
        
        write8Bit(0x4017, 0x00); //frame irq enabled...
        write8Bit(0x4015, 0x00); //all channels disabled...

        //FIXME: not sure about $4010-$4013...
        for(int ctr=0x4000; ctr<=0x400F; ctr++)
        {
            write8Bit(ctr, 0x00);
        }
    }

    public static int read8Bit(int address)
    {
        //***************************************************
        //      Read functions for Opcode Operand Only
        //***************************************************
        if(address == 0x2002 && PPU_REGISTER.getVBlankFlag() == 1)
        {
            //Force ending of VBlank when CPU reads at $2002 during VBlank state...
            ppuCORE.VBlankPremEnd = true;
        }
        return MEMORY_MAP[address >> 8][address & 0xFF] & 0xFF;
    }

    public static int fastRead8Bit(int address)
    {
        //***************************************************
        //            Multipurpose read functions
        //                  for performace
        //***************************************************
        return MEMORY_MAP[address >> 8][address & 0xFF] & 0xFF;
    }

    public static void write8Bit(int address, int value)
    {
        int page;
        page = address >> 8;
        MEMORY_MAP[page][address & 0xFF] = value;
        //***************************************************
        //                MEMORY Mirroring
        //***************************************************
        if(page >= 0 && page <= 7) //Mirror from page 0-7 (3x)
        {
            MEMORY_MAP[page + 8][address & 0xFF] = value;     //Mirror 1
            MEMORY_MAP[page + 16][address & 0xFF] = value;    //Mirror 2
            MEMORY_MAP[page + 24][address & 0xFF] = value;    //Mirror 3
        }
    }

    public static void clear()
    {
        Arrays.fill(MEMORY_MAP, 0);
    }

    public static int getResetVector()
    {
        StringBuilder tmp = new StringBuilder(2);

        tmp.append(Integer.toHexString(fastRead8Bit(0xFFFD) & 0xFF));
        tmp.append(Integer.toHexString(fastRead8Bit(0xFFFC) & 0xFF));

        switch(tmp.toString().length())
        {
            case 3 :
                tmp.append("0");
                break;
            case 2 :
                tmp.append("00");
                break;
            case 1 :
                tmp.append("0000");
                break;
            default :
                //do nothing...
        }
        return CONVERTER.stringHexToInt(tmp.toString());
    }

    public static int getNMIVector()
    {
        StringBuilder tmp = new StringBuilder(2);

        tmp.append(Integer.toHexString(fastRead8Bit(0xFFFB) & 0xFF));
        tmp.append(Integer.toHexString(fastRead8Bit(0xFFFA) & 0xFF));

        switch(tmp.toString().length())
        {
            case 3 :
                tmp.append("0");
                break;
            case 2 :
                tmp.append("00");
                break;
            case 1 :
                tmp.append("0000");
                break;
            default :
                //do nothing...
        }
        return CONVERTER.stringHexToInt(tmp.toString());
    }

    public static int getIRQVector()
    {
        StringBuilder tmp = new StringBuilder(2);

        tmp.append(Integer.toHexString(fastRead8Bit(0xFFFF) & 0xFF));
        tmp.append(Integer.toHexString(fastRead8Bit(0xFFFE) & 0xFF));

        switch(tmp.toString().length())
        {
            case 3 :
                tmp.append("0");
                break;
            case 2 :
                tmp.append("00");
                break;
            case 1 :
                tmp.append("0000");
                break;
            default :
                //do nothing...
        }
        return CONVERTER.stringHexToInt(tmp.toString());
    }

    public static int getPage(int address)
    {
        return address >> 8;
    }
}

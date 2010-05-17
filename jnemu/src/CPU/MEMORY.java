package CPU;

import java.util.Arrays;
import jnemu.Console;
import MISC.CONVERTER;

public class MEMORY
{
    private static int[] MEMORY_MAP;

    public static void init()
    {
        MEMORY_MAP = new int[0x10000];
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
        int tmp = 0;
        if(address > 0xFFFF)
        {
            Console.print("[ERROR] Invalid memory access at " + Integer.toHexString(address));
        }
        else
        {
            tmp = MEMORY_MAP[address] & 0xFF;
        }
        return tmp;
    }

    public static void write8Bit(int address, int value)
    {
        MEMORY_MAP[address] = value;
    }

    public static void clear()
    {
        Arrays.fill(MEMORY_MAP, 0);
    }

    public static int getInitialPC()
    {
        StringBuilder tmp = new StringBuilder(2);

        tmp.append(Integer.toHexString(read8Bit(0xFFFD) & 0xFF));
        tmp.append(Integer.toHexString(read8Bit(0xFFFC) & 0xFF));

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

    public static void showMemInDebugger()
    {
        //FIXME:
    }
}

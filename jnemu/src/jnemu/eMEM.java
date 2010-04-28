package jnemu;
import java.util.Arrays;

public class eMEM
{
    private static byte[] MEMORY_MAP;

    public static void init()
    {
        MEMORY_MAP = new byte[0x10000];
        //power up memory state...
        //FIXME: need to use unsigned(0-255) byte as java only uses signed(-128  to 127).
        for(int ctr=0x0000; ctr<=0x07FF; ctr++)
        {
            if(ctr == 0x0008)
            {
                write8Bit(ctr, (byte) 0xF7);
            }
            else if(ctr == 0x0009)
            {
                write8Bit(ctr, (byte) 0xEF);
            }
            else if(ctr == 0x000A)
            {
                write8Bit(ctr, (byte) 0xDF);
            }
            else if(ctr == 0x000F)
            {
                write8Bit(ctr, (byte) 0xBF);
            }
            else
            {
                write8Bit(ctr, (byte) 0xFF);
            }
        }
        
        write8Bit(0x4017, (byte) 0x00); //frame irq enabled...
        write8Bit(0x4015, (byte) 0x00); //all channels disabled...

        //FIXME: not sure about $4010-$4013...
        for(int ctr=0x4000; ctr<=0x400F; ctr++)
        {
            write8Bit(ctr, (byte) 0x00);
        }
    }

    public static byte read8Bit(int address)
    {
        return MEMORY_MAP[address];
    }

    public static void write8Bit(int address, byte value)
    {
        MEMORY_MAP[address] = value;
    }

    public static void clear()
    {
        Arrays.fill(MEMORY_MAP, (byte)0);
    }

    public static void showMemInDebugger()
    {
        //FIXME:
    }
}

package jnemu;
import java.util.Arrays;

public class eMEM
{
    private static byte[] MEMORY_MAP;

    public static void initMEMORY()
    {
        MEMORY_MAP = new byte[0xFFFF];
    }

    public static byte read8Bit(byte address)
    {
        return MEMORY_MAP[address];
    }

    public static void write8Bit(byte address, byte value)
    {
        MEMORY_MAP[address] = value;
    }

    public static void clear()
    {
        Arrays.fill(MEMORY_MAP, (byte)0);
    }
}

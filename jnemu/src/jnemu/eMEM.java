package jnemu;
import java.util.Arrays;

public class eMEM
{
    private static byte[] MEMORY_MAP;

    public static void init()
    {
        MEMORY_MAP = new byte[0x10000];
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

    }
}

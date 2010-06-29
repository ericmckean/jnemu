package PPU;

public class PPU_MEMORY
{
    private static int[][] PPU_MEMORY_MAP;

    public static void init()
    {
        PPU_MEMORY_MAP = new int[0x40][0x100];
    }

    public static int readPPUMemory(int address)
    {
        return PPU_MEMORY_MAP[address >> 8][address & 0xFF];
    }

    public static void writePPUMemory(int address, int value)
    {
        PPU_MEMORY_MAP[address >> 8][address & 0xFF] = value;
    }
}

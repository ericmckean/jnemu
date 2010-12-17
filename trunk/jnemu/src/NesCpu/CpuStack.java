package NesCpu;

public class CpuStack
{
    public static void Push(int i)
    {
        CpuMemory.write8Bit(((CpuRegister.SP & 0xFF) | 0x100), i);
        CpuRegister.SP--;
    }

    public static int Pull()
    {
        CpuRegister.SP++;
        return CpuMemory.fastRead8Bit((CpuRegister.SP & 0xFF) | 0x100);
    }
}

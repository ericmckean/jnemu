package CPU;

public class STACK
{
    public static void Push(int i)
    {
        CPU_MEMORY.write8Bit(((CPU_REGISTER.SP & 0xFF) | 0x100), i);
        CPU_REGISTER.SP--;
    }

    public static int Pull()
    {
        CPU_REGISTER.SP++;
        return CPU_MEMORY.fastRead8Bit((CPU_REGISTER.SP & 0xFF) | 0x100);
    }
}

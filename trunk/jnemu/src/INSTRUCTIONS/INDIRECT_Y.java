package INSTRUCTIONS;

import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;

public class INDIRECT_Y
{
    public static void STA()
    {
        CPU_MEMORY.write8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.Y), CPU_REGISTER.A);
        CPU_REGISTER.PC += 2;
    }
}

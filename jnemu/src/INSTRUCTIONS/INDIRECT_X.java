package INSTRUCTIONS;

import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;

public class INDIRECT_X
{
    public static void STA()
    {
        CPU_MEMORY.write8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF, CPU_REGISTER.A);
        CPU_REGISTER.PC += 2;
    }
}

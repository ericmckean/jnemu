package INSTRUCTIONS;

import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;

public class ZEROPAGE_Y
{
    public static void STX()
    {
        CPU_MEMORY.write8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.Y) & 0xFF, CPU_REGISTER.X);
        CPU_REGISTER.PC += 2;
    }
}

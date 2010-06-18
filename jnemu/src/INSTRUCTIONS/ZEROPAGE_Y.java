package INSTRUCTIONS;

import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;
import CPU.FLAG;

public class ZEROPAGE_Y
{
    public static void STX()
    {
        CPU_MEMORY.write8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.Y) & 0xFF, CPU_REGISTER.X);
        CPU_REGISTER.PC += 2;
    }

    public static void LDX()
    {
        int Value, addr;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.Y) & 0xFF;
        Value =  CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.X = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        CPU_REGISTER.PC += 2;
    }
}

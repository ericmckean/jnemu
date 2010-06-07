package INSTRUCTIONS;

import CPU.CPU_REGISTER;
import CPU.CPU_MEMORY;
import CPU.FLAG;

public class ZEROPAGE
{
    public static void ADC()
    {
        int tmp, Value;

        Value = CPU_MEMORY.read8Bit(ADDRESS.get8BitAddressOperand());
        tmp = CPU_REGISTER.A + Value;
        FLAG.CHECK_OVERFLOW(CPU_REGISTER.A, Value, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY(tmp);
        CPU_REGISTER.A = tmp & 0xFF;

        CPU_REGISTER.PC += 2;
    }

    public static void STA()
    {
        CPU_MEMORY.write8Bit(ADDRESS.get8BitAddressOperand(), CPU_REGISTER.A);
        CPU_REGISTER.PC += 2;
    }

    public static void STY()
    {
        CPU_MEMORY.write8Bit(ADDRESS.get8BitAddressOperand(), CPU_REGISTER.Y);
        CPU_REGISTER.PC += 2;
    }
}

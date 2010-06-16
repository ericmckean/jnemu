package INSTRUCTIONS;

import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;
import CPU.FLAG;

public class INDIRECT_X
{
    public static void STA()
    {
        int addr, MSB, LSB;
        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        addr = (MSB << 8) | ((LSB + CPU_REGISTER.X) & 0xFF);
        
        CPU_MEMORY.write8Bit(addr, CPU_REGISTER.A);
        CPU_REGISTER.PC += 2;
    }

    public static void ADC()
    {
        int tmp, Value, addr, MSB, LSB;

        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        addr = (MSB << 8) | ((LSB + CPU_REGISTER.X) & 0xFF);

        Value = CPU_MEMORY.read8Bit(addr);
        tmp = CPU_REGISTER.A + Value;
        FLAG.CHECK_OVERFLOW(CPU_REGISTER.A, Value, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY(tmp);
        CPU_REGISTER.A = tmp & 0xFF;

        CPU_REGISTER.PC += 2;
    }
}

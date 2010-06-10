package INSTRUCTIONS;

import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;

public class INDIRECT
{
    public static void JMP()
    {
        int addr, LSB, MSB;

        LSB = CPU_MEMORY.read8Bit(ADDRESS.get16BitAddressOperand());
        MSB = CPU_MEMORY.read8Bit(ADDRESS.get16BitAddressOperand() + 1);
        addr = (MSB << 8) | LSB;
        CPU_REGISTER.PC = addr;
    }
}

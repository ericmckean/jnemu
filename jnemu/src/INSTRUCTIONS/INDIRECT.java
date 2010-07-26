package INSTRUCTIONS;

import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;

public class INDIRECT
{
    public static void JMP()
    {
        int addr, LSB, MSB, tmp1, tmp2;

        tmp1 = ADDRESS.get16BitAddressOperand();
        tmp2 = (ADDRESS.get16BitAddressOperand() + 1) & 0xff;
        LSB = CPU_MEMORY.read8Bit(tmp1);
        MSB = CPU_MEMORY.read8Bit(tmp2 | (tmp1 & 0xff00));
        addr = (MSB << 8) | LSB;
        CPU_REGISTER.PC = addr;
    }
}

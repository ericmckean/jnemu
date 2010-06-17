package INSTRUCTIONS;

import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;

public class ADDRESS
{
    public static int get16BitAddressOperand()
    {
        int tmp, LSB, MSB;

        MSB = CPU_MEMORY.fastRead8Bit(CPU_REGISTER.PC + 2);
        LSB = CPU_MEMORY.fastRead8Bit(CPU_REGISTER.PC + 1);
        tmp = (MSB << 8) | LSB;

        return tmp;
    }

    public static int get8BitAddressOperand()
    {
        int tmp = 0;
        tmp = CPU_MEMORY.fastRead8Bit(CPU_REGISTER.PC + 1);
        return tmp;
    }
}

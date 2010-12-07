package INSTRUCTIONS;

import CPU.CpuMemory;
import CPU.CpuRegister;

public class InstAddress
{
    public static int get16BitAddressOperand()
    {
        int tmp, LSB, MSB;

        MSB = CpuMemory.fastRead8Bit(CpuRegister.PC + 2);
        LSB = CpuMemory.fastRead8Bit(CpuRegister.PC + 1);
        tmp = (MSB << 8) | LSB;

        return tmp;
    }

    public static int get8BitAddressOperand()
    {
        int tmp = 0;
        tmp = CpuMemory.fastRead8Bit(CpuRegister.PC + 1);
        return tmp;
    }
}

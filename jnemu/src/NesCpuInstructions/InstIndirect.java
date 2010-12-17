package NesCpuInstructions;

import NesCpu.CpuMemory;
import NesCpu.CpuRegister;

public class InstIndirect
{
    public static void JMP()
    {
        int addr, LSB, MSB, tmp1, tmp2;

        tmp1 = InstAddress.get16BitAddressOperand();
        tmp2 = (InstAddress.get16BitAddressOperand() + 1) & 0xff;
        LSB = CpuMemory.read8Bit(tmp1);
        MSB = CpuMemory.read8Bit(tmp2 | (tmp1 & 0xff00));
        addr = (MSB << 8) | LSB;
        CpuRegister.PC = addr;
    }
}

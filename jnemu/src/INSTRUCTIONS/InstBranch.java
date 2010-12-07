package INSTRUCTIONS;

import CPU.*;

public class InstBranch
{
    public static void execBranch()
    {
        int tmp;

        CpuRegister.PC = (CpuRegister.PC + 1) & 0xffff;
        tmp = CpuMemory.read8Bit(CpuRegister.PC);
        CpuRegister.PC = (CpuRegister.PC + (byte)tmp) & 0xffff;
        CpuRegister.PC = (CpuRegister.PC + 1) & 0xffff;
    }
}

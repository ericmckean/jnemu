package INSTRUCTIONS;

import CPU.*;

public class BRANCH
{
    public static void execBranch()
    {
        int tmp;

        CPU_REGISTER.PC++;
        tmp = CPU_MEMORY.read8Bit(CPU_REGISTER.PC);
        CPU_REGISTER.PC = (CPU_REGISTER.PC + (byte)tmp);
        CPU_REGISTER.PC++;
    }
}

package INSTRUCTIONS;

import CPU.*;

public class BRANCH
{
    public static void execBranch()
    {
        int tmp;

        REGISTER.PC++;
        tmp = MEMORY.read8Bit(REGISTER.PC);
        REGISTER.PC = (REGISTER.PC + (byte)tmp);
        REGISTER.PC++;
    }
}

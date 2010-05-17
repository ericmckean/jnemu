package INSTRUCTIONS;

import CPU.REGISTER;

public class IMPLIED
{
    public static void SEI()
    {
        REGISTER.interruptFlag = 1;
        REGISTER.PC += 1;
    }

    public static void CLD()
    {
        REGISTER.decimalFlag = 0;
        REGISTER.PC += 1;
    }
}

package INSTRUCTIONS;

import CPU.REGISTER;

public class IMPLIED
{
    public static void SEI()
    {
        REGISTER.setInterruptFlag();
        REGISTER.PC += 1;
    }

    public static void CLD()
    {
        REGISTER.clearDecimalFlag();
        REGISTER.PC += 1;
    }
}

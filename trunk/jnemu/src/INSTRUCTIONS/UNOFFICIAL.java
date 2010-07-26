package INSTRUCTIONS;

import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;
import CPU.FLAG;

public class UNOFFICIAL
{
    //****************************************
    //                  NOP
    //****************************************
    public static void _NOP()
    {
        CPU_REGISTER.PC += 1;
    }

    public static void DOP()
    {
        CPU_REGISTER.PC += 2;
    }

    public static void TOP()
    {
        CPU_REGISTER.PC += 3;
    }

    //****************************************
    //                  AAC
    //****************************************

    public static void AAC()
    {
        int tmp;

        tmp = CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1);
        CPU_REGISTER.A &= tmp;
        if((CPU_REGISTER.A & 0x80) == 0x80)
        {
            CPU_REGISTER.setCarryFlag();
            CPU_REGISTER.setNegativeFlag();
        }
        else
        {
            CPU_REGISTER.clearCarryFlag();
            CPU_REGISTER.clearNegativeFlag();
        }
        FLAG.CHECK_ZERO(CPU_REGISTER.A);
        CPU_REGISTER.PC += 2;
    }
}

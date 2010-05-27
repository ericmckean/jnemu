package INSTRUCTIONS;

import CPU.CPU_REGISTER;
import CPU.STACK;

public class IMPLIED
{
    public static void SEI()
    {
        CPU_REGISTER.setInterruptFlag();
        CPU_REGISTER.PC += 1;
    }

    public static void CLD()
    {
        CPU_REGISTER.clearDecimalFlag();
        CPU_REGISTER.PC += 1;
    }

    public static void DEX()
    {
        CPU_REGISTER.X -= 1;
        if(CPU_REGISTER.X == 0)
        {
            CPU_REGISTER.setZeroFlag();
        }
        else
        {
            CPU_REGISTER.clearZeroFlag();
        }

        if((CPU_REGISTER.X & 0x80) == 0x80)
        {
            CPU_REGISTER.setNegativeFlag();
        }
        else
        {
            CPU_REGISTER.clearNegativeFlag();
        }
        CPU_REGISTER.PC += 1;
    }
    
    public static void TSX()
    {
        CPU_REGISTER.X = CPU_REGISTER.SP;
        if(CPU_REGISTER.X == 0)
        {
            CPU_REGISTER.setZeroFlag();
        }
        else
        {
            CPU_REGISTER.clearZeroFlag();
        }

        if((CPU_REGISTER.X & 0x80) == 0x80)
        {
            CPU_REGISTER.setNegativeFlag();
        }
        else
        {
            CPU_REGISTER.clearNegativeFlag();
        }
        CPU_REGISTER.PC += 1;
    }

    public static void TXS()
    {
        CPU_REGISTER.SP = CPU_REGISTER.X;
        if(CPU_REGISTER.SP == 0)
        {
            CPU_REGISTER.setZeroFlag();
        }
        else
        {
            CPU_REGISTER.clearZeroFlag();
        }

        if((CPU_REGISTER.SP & 0x80) == 0x80)
        {
            CPU_REGISTER.setNegativeFlag();
        }
        else
        {
            CPU_REGISTER.clearNegativeFlag();
        }
        CPU_REGISTER.PC += 1;
    }

    public static void NOP()
    {
        //Do nothing LOL :D...........
        CPU_REGISTER.PC++;
    }

    public static void RTS()
    {
        int addr;
        addr = STACK.Pull();
        addr += (STACK.Pull() << 8) + 1;
        CPU_REGISTER.PC = addr;
    }
}

package INSTRUCTIONS;

import CPU.CPU_REGISTER;
import CPU.FLAG;
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
        CPU_REGISTER.X = (CPU_REGISTER.X - 1) & 0xFF;
        FLAG.CHECK_ZERO(CPU_REGISTER.X);
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.X);
        CPU_REGISTER.PC += 1;
    }
    
    public static void TSX()
    {
        CPU_REGISTER.X = CPU_REGISTER.SP;
        FLAG.CHECK_ZERO(CPU_REGISTER.X);
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.X);
        CPU_REGISTER.PC += 1;
    }

    public static void TXS()
    {
        CPU_REGISTER.SP = CPU_REGISTER.X;
        FLAG.CHECK_ZERO(CPU_REGISTER.SP);
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.SP);
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

    public static void INX()
    {
        CPU_REGISTER.X = (CPU_REGISTER.X + 1) & 0xFF;
        FLAG.CHECK_ZERO(CPU_REGISTER.X);
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.X);
        CPU_REGISTER.PC += 1;
    }

    public static void DEY()
    {
        CPU_REGISTER.Y = (CPU_REGISTER.Y - 1) & 0xFF;
        FLAG.CHECK_ZERO(CPU_REGISTER.Y);
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.Y);
        CPU_REGISTER.PC += 1;
    }
}

package INSTRUCTIONS;

import CPU.CPU_MEMORY;
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
        CPU_REGISTER.PC += 1;
    }

    public static void NOP()
    {
        //Do nothing LOL :D...........
        CPU_REGISTER.PC++;
    }

    public static void RTS()
    {
        int addr, LSB, MSB;
        LSB = STACK.Pull();
        MSB = STACK.Pull();
        addr = (MSB << 8) | LSB;
        CPU_REGISTER.PC = addr;
        CPU_REGISTER.PC++;
        CPU_REGISTER.PC &= 0xFFFF;
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

    public static void TAX()
    {
        CPU_REGISTER.X = CPU_REGISTER.A;
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.X);
        FLAG.CHECK_ZERO(CPU_REGISTER.X);
        CPU_REGISTER.PC += 1;
    }

    public static void TAY()
    {
        CPU_REGISTER.Y = CPU_REGISTER.A;
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.Y);
        FLAG.CHECK_ZERO(CPU_REGISTER.Y);
        CPU_REGISTER.PC += 1;
    }

    public static void TXA()
    {
        CPU_REGISTER.A = CPU_REGISTER.X;
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.A);
        FLAG.CHECK_ZERO(CPU_REGISTER.A);
        CPU_REGISTER.PC += 1;
    }

    public static void TYA()
    {
        CPU_REGISTER.A = CPU_REGISTER.Y;
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.A);
        FLAG.CHECK_ZERO(CPU_REGISTER.A);
        CPU_REGISTER.PC += 1;
    }

    public static void PHA()
    {
        STACK.Push(CPU_REGISTER.A);
        CPU_REGISTER.PC += 1;
    }

    public static void PHP()
    {
        STACK.Push((CPU_REGISTER.SR | 0x30)); //Set Bit 5 and 4 during push...
        CPU_REGISTER.PC += 1;
    }

    public static void PLA()
    {
        CPU_REGISTER.A = STACK.Pull();
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.A);
        FLAG.CHECK_ZERO(CPU_REGISTER.A);
        CPU_REGISTER.PC += 1;
    }

    public static void PLP()
    {
        CPU_REGISTER.SR = ((STACK.Pull() | 0x20) & 0xef); //Enable bit 5 disable bit 4...
        CPU_REGISTER.PC += 1;
    }

    public static void SED()
    {
        CPU_REGISTER.setDecimalFlag();
        CPU_REGISTER.PC += 1;
    }

    public static void SEC()
    {
        CPU_REGISTER.setCarryFlag();
        CPU_REGISTER.PC += 1;
    }

    public static void CLV()
    {
        CPU_REGISTER.clearOverflowFlag();
        CPU_REGISTER.PC += 1;
    }

    public static void CLI()
    {
        CPU_REGISTER.clearInterruptFlag();
        CPU_REGISTER.PC += 1;
    }

    public static void CLC()
    {
        CPU_REGISTER.clearCarryFlag();
        CPU_REGISTER.PC += 1;
    }

    public static void INY()
    {
        CPU_REGISTER.Y = (CPU_REGISTER.Y + 1) & 0xFF;
        FLAG.CHECK_ZERO(CPU_REGISTER.Y);
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.Y);
        CPU_REGISTER.PC += 1;
    }

    public static void RTI()
    {
        int MSB, LSB;

        CPU_REGISTER.SR = (STACK.Pull() & 0xef) | 0x20;
        LSB = STACK.Pull();
        MSB = STACK.Pull();
        CPU_REGISTER.PC = (MSB << 8) | LSB;
    }

    public static void BRK()
    {
        int pc;

        pc = CPU_REGISTER.PC + 2;
        STACK.Push(pc & 0xFF); //LSB
        STACK.Push(pc >> 8);   //MSB
        STACK.Push((CPU_REGISTER.SR | 0x30)); //Set Bit 4 and 5 during push...

        CPU_REGISTER.PC = CPU_MEMORY.getIRQVector();
    }
}

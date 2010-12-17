package NesCpuInstructions;

import NesCpu.CpuMemory;
import NesCpu.CpuRegister;
import NesCpu.CpuFlag;
import NesCpu.CpuStack;

public class InstImplied
{
    public static void SEI()
    {
        CpuRegister.setInterruptFlag();
        CpuRegister.PC += 1;
    }

    public static void CLD()
    {
        CpuRegister.clearDecimalFlag();
        CpuRegister.PC += 1;
    }

    public static void DEX()
    {
        CpuRegister.X = (CpuRegister.X - 1) & 0xFF;
        CpuFlag.checkZero(CpuRegister.X);
        CpuFlag.checkNegative(CpuRegister.X);
        CpuRegister.PC += 1;
    }
    
    public static void TSX()
    {
        CpuRegister.X = CpuRegister.SP;
        CpuFlag.checkZero(CpuRegister.X);
        CpuFlag.checkNegative(CpuRegister.X);
        CpuRegister.PC += 1;
    }

    public static void TXS()
    {
        CpuRegister.SP = CpuRegister.X;
        CpuRegister.PC += 1;
    }

    public static void NOP()
    {
        //Do nothing LOL :D...........
        CpuRegister.PC++;
    }

    public static void RTS()
    {
        int addr, LSB, MSB;
        LSB = CpuStack.Pull();
        MSB = CpuStack.Pull();
        addr = (MSB << 8) | LSB;
        CpuRegister.PC = addr;
        CpuRegister.PC++;
        CpuRegister.PC &= 0xFFFF;
    }

    public static void INX()
    {
        CpuRegister.X = (CpuRegister.X + 1) & 0xFF;
        CpuFlag.checkZero(CpuRegister.X);
        CpuFlag.checkNegative(CpuRegister.X);
        CpuRegister.PC += 1;
    }

    public static void DEY()
    {
        CpuRegister.Y = (CpuRegister.Y - 1) & 0xFF;
        CpuFlag.checkZero(CpuRegister.Y);
        CpuFlag.checkNegative(CpuRegister.Y);
        CpuRegister.PC += 1;
    }

    public static void TAX()
    {
        CpuRegister.X = CpuRegister.A;
        CpuFlag.checkNegative(CpuRegister.X);
        CpuFlag.checkZero(CpuRegister.X);
        CpuRegister.PC += 1;
    }

    public static void TAY()
    {
        CpuRegister.Y = CpuRegister.A;
        CpuFlag.checkNegative(CpuRegister.Y);
        CpuFlag.checkZero(CpuRegister.Y);
        CpuRegister.PC += 1;
    }

    public static void TXA()
    {
        CpuRegister.A = CpuRegister.X;
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 1;
    }

    public static void TYA()
    {
        CpuRegister.A = CpuRegister.Y;
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 1;
    }

    public static void PHA()
    {
        CpuStack.Push(CpuRegister.A);
        CpuRegister.PC += 1;
    }

    public static void PHP()
    {
        CpuStack.Push((CpuRegister.SR | 0x30)); //Set Bit 5 and 4 during push...
        CpuRegister.PC += 1;
    }

    public static void PLA()
    {
        CpuRegister.A = CpuStack.Pull();
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 1;
    }

    public static void PLP()
    {
        CpuRegister.SR = (CpuStack.Pull() & 0xef) | 0x20; //Enable bit 5 disable bit 4...
        CpuRegister.PC += 1;
    }

    public static void SED()
    {
        CpuRegister.setDecimalFlag();
        CpuRegister.PC += 1;
    }

    public static void SEC()
    {
        CpuRegister.setCarryFlag();
        CpuRegister.PC += 1;
    }

    public static void CLV()
    {
        CpuRegister.clearOverflowFlag();
        CpuRegister.PC += 1;
    }

    public static void CLI()
    {
        CpuRegister.clearInterruptFlag();
        CpuRegister.PC += 1;
    }

    public static void CLC()
    {
        CpuRegister.clearCarryFlag();
        CpuRegister.PC += 1;
    }

    public static void INY()
    {
        CpuRegister.Y = (CpuRegister.Y + 1) & 0xFF;
        CpuFlag.checkZero(CpuRegister.Y);
        CpuFlag.checkNegative(CpuRegister.Y);
        CpuRegister.PC += 1;
    }

    public static void RTI()
    {
        int MSB, LSB;

        CpuRegister.SR = (CpuStack.Pull() & 0xef) | 0x20;
        LSB = CpuStack.Pull();
        MSB = CpuStack.Pull();
        CpuRegister.PC = (MSB << 8) | LSB;
    }

    public static void BRK()
    {
        int pc;

        pc = CpuRegister.PC + 2;
        CpuStack.Push(pc >> 8);   //MSB
        CpuStack.Push(pc & 0xff); //LSB
        CpuStack.Push((CpuRegister.SR | 0x30)); //Set Bit 4 and 5 during push...

        CpuRegister.PC = CpuMemory.getIRQVector();
    }
}

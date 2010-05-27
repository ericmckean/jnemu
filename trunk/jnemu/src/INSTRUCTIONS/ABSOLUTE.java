package INSTRUCTIONS;

import CPU.CPU_REGISTER;
import CPU.CPU_MEMORY;
import CPU.STACK;

public class ABSOLUTE
{
    public static void ADC()
    {
        int tmp, Value;
        
        Value = CPU_MEMORY.read8Bit(ADDRESS.get16BitAddressOperand());
        tmp = CPU_REGISTER.A + Value;

        //overflow flag.....
        if((CPU_REGISTER.A & 0x80) == 0 && (Value & 0x80) == 0 && (tmp & 0x80) == 0x80)
        {
            CPU_REGISTER.setOverflowFlag();
        }
        else if((CPU_REGISTER.A & 0x80) != 0 && (Value & 0x80) != 0 && (tmp & 0x80) != 0x80)
        {
            CPU_REGISTER.setOverflowFlag();
        }
        else
        {
            CPU_REGISTER.clearOverflowFlag();
        }

        //ZERO flag...
        if(tmp == 0)
        {
            CPU_REGISTER.setZeroFlag();
        }
        else
        {
            CPU_REGISTER.clearZeroFlag();
        }

        //NEGATIVE flag...
        if(tmp >= 0x80)
        {
            CPU_REGISTER.setNegativeFlag();
        }
        else
        {
            CPU_REGISTER.clearNegativeFlag();
        }

        //carry flag and the result.....
        if((tmp & 0x100) == 0x100)
        {
            CPU_REGISTER.setCarryFlag();
            CPU_REGISTER.A = tmp & 0xFF;
        }
        else
        {
            CPU_REGISTER.clearCarryFlag();
            CPU_REGISTER.A = tmp;
        }

        CPU_REGISTER.PC += 3;
    }

    public static void STA()
    {
        CPU_MEMORY.write8Bit(ADDRESS.get16BitAddressOperand(), CPU_REGISTER.A);
        CPU_REGISTER.PC += 3;
    }

    public static void LDA()
    {
        int Value;

        Value = CPU_MEMORY.read8Bit(ADDRESS.get16BitAddressOperand());
        CPU_REGISTER.A = Value;

        //ZERO flag...
        if(Value == 0)
        {
            CPU_REGISTER.setZeroFlag();
        }
        else
        {
            CPU_REGISTER.clearZeroFlag();
        }

        //NEGATIVE flag...
        if((Value & 0x80) == 0x80)
        {
            CPU_REGISTER.setNegativeFlag();
        }
        else
        {
            CPU_REGISTER.clearNegativeFlag();
        }

        CPU_REGISTER.PC += 3;
    }

    public static void JSR()
    {
        STACK.Push(CPU_REGISTER.PC >> 8);
        STACK.Push(CPU_REGISTER.PC & 0xFF);
        CPU_REGISTER.PC = ADDRESS.get16BitAddressOperand();
    }

    public static void STY()
    {
        CPU_MEMORY.write8Bit(ADDRESS.get16BitAddressOperand(), CPU_REGISTER.Y);
        CPU_REGISTER.PC += 3;
    }
}

package INSTRUCTIONS;

import CPU.CPU_REGISTER;
import CPU.CPU_MEMORY;

public class IMMIDIATE
{
    public static void ADC()
    {
        int tmp, Value;

        Value =  CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1);
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

        
        CPU_REGISTER.PC += 2;
    }

    public static void LDA()
    {
        int Value;

        Value =  CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1);
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

        CPU_REGISTER.PC += 2;
    }

    public static void LDX()
    {
        int Value;

        Value =  CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1);
        CPU_REGISTER.X = Value;
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
        if(Value >= 0x80)
        {
            CPU_REGISTER.setNegativeFlag();
        }
        else
        {
            CPU_REGISTER.clearNegativeFlag();
        }

        CPU_REGISTER.PC += 2;
    }

    public static void LDY()
    {
        int Value;

        Value =  CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1);
        CPU_REGISTER.Y = Value;
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
        if(Value >= 0x80)
        {
            CPU_REGISTER.setNegativeFlag();
        }
        else
        {
            CPU_REGISTER.clearNegativeFlag();
        }

        CPU_REGISTER.PC += 2;
    }

}

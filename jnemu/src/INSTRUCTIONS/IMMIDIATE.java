package INSTRUCTIONS;

import MISC.CONVERTER;
import CPU.REGISTER;
import CPU.MEMORY;

public class IMMIDIATE
{
    public static void ADC()
    {
        int tmp, Value;
        StringBuilder b_tmp = new StringBuilder(1);

        Value =  MEMORY.read8Bit(REGISTER.PC + 1);
        tmp = REGISTER.A + Value;
        b_tmp.append(CONVERTER.intToStringBinary(tmp));

        //overflow flag.....
        if((REGISTER.A & 0x80) == 0 && (Value & 0x80) == 0 && (tmp & 0x80) == 0x80)
        {
            REGISTER.setOverflowFlag();
        }
        else if((REGISTER.A & 0x80) != 0 && (Value & 0x80) != 0 && (tmp & 0x80) != 0x80)
        {
            REGISTER.setOverflowFlag();
        }
        else
        {
            REGISTER.clearOverflowFlag();
        }

        //ZERO flag...
        if(tmp == 0)
        {
            REGISTER.setZeroFlag();
        }
        else
        {
            REGISTER.clearZeroFlag();
        }

        //NEGATIVE flag...
        if(tmp >= 0x80)
        {
            REGISTER.setNegativeFlag();
        }
        else
        {
            REGISTER.clearNegativeFlag();
        }

        //carry flag and the result.....
        if(b_tmp.toString().length() > 8)
        {
            REGISTER.setCarryFlag();
            REGISTER.A = Integer.parseInt(b_tmp.toString().substring(1),2);
        }
        else
        {
            REGISTER.clearCarryFlag();
            REGISTER.A = tmp;
        }

        
        REGISTER.PC += 2;
    }

    public static void LDA()
    {
        int Value;

        Value =  MEMORY.read8Bit(REGISTER.PC + 1);
        REGISTER.A = Value;
        //ZERO flag...
        if(Value == 0)
        {
            REGISTER.setZeroFlag();
        }
        else
        {
            REGISTER.clearZeroFlag();
        }

        //NEGATIVE flag...
        if((Value & 0x80) == 0x80)
        {
            REGISTER.setNegativeFlag();
        }
        else
        {
            REGISTER.clearNegativeFlag();
        }

        REGISTER.PC += 2;
    }

    public static void LDX()
    {
        int Value;

        Value =  MEMORY.read8Bit(REGISTER.PC + 1);
        REGISTER.X = Value;
        //ZERO flag...
        if(Value == 0)
        {
            REGISTER.setZeroFlag();
        }
        else
        {
            REGISTER.clearZeroFlag();
        }

        //NEGATIVE flag...
        if(Value >= 0x80)
        {
            REGISTER.setNegativeFlag();
        }
        else
        {
            REGISTER.clearNegativeFlag();
        }

        REGISTER.PC += 2;
    }

}

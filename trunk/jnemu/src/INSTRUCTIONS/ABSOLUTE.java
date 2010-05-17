package INSTRUCTIONS;

import MISC.CONVERTER;
import MISC.INSTRUCTIONS;
import CPU.REGISTER;
import CPU.MEMORY;

public class ABSOLUTE
{
    public static void ADC()
    {
        int tmp, Value;
        StringBuilder b_tmp = new StringBuilder(1);
        StringBuilder addr = new StringBuilder(2);

        addr.append(INSTRUCTIONS.intTo8BitHexString(MEMORY.read8Bit(REGISTER.PC + 2)));
        addr.append(INSTRUCTIONS.intTo8BitHexString(MEMORY.read8Bit(REGISTER.PC + 1)));

        Value = MEMORY.read8Bit(CONVERTER.stringHexToInt(addr.toString()));
        tmp = REGISTER.A + Value;
        b_tmp.append(CONVERTER.intToStringBinary(tmp));

        //overflow flag.....
        if((REGISTER.A & 0x80) == 0 && (Value & 0x80) == 0 && (tmp & 0x80) == 0x80)
        {
            REGISTER.overflowFlag = 1;
        }
        else if((REGISTER.A & 0x80) != 0 && (Value & 0x80) != 0 && (tmp & 0x80) != 0x80)
        {
            REGISTER.overflowFlag = 1;
        }
        else
        {
            REGISTER.overflowFlag = 0;
        }

        //ZERO flag...
        if(tmp == 0)
        {
            REGISTER.zeroFlag = 1;
        }
        else
        {
            REGISTER.zeroFlag = 0;
        }

        //NEGATIVE flag...
        if(tmp >= 0x80)
        {
            REGISTER.negativeFlag = 1;
        }
        else
        {
            REGISTER.negativeFlag = 0;
        }

        //carry flag and the result.....
        if(b_tmp.toString().length() > 8)
        {
            REGISTER.carryFlag = 1;
            REGISTER.A = Integer.parseInt(b_tmp.toString().substring(1),2);
        }
        else
        {
            REGISTER.carryFlag = 0;
            REGISTER.A = tmp;
        }


        REGISTER.PC += 3;
    }

    public static void STA()
    {
        StringBuilder addr = new StringBuilder(2);

        addr.append(INSTRUCTIONS.intTo8BitHexString(MEMORY.read8Bit(REGISTER.PC + 2)));
        addr.append(INSTRUCTIONS.intTo8BitHexString(MEMORY.read8Bit(REGISTER.PC + 1)));

        MEMORY.write8Bit(CONVERTER.stringHexToInt(addr.toString()), REGISTER.A);
        REGISTER.PC += 3;
    }

    public static void LDA()
    {
        int Value;
        StringBuilder addr = new StringBuilder(2);

        addr.append(INSTRUCTIONS.intTo8BitHexString(MEMORY.read8Bit(REGISTER.PC + 2)));
        addr.append(INSTRUCTIONS.intTo8BitHexString(MEMORY.read8Bit(REGISTER.PC + 1)));

        Value = MEMORY.read8Bit(CONVERTER.stringHexToInt(addr.toString()));
        REGISTER.A = Value;


        //ZERO flag...
        if(Value == 0)
        {
            REGISTER.zeroFlag = 1;
        }
        else
        {
            REGISTER.zeroFlag = 0;
        }

        //NEGATIVE flag...
        if(Value >= 0x80)
        {
            REGISTER.negativeFlag = 1;
        }
        else
        {
            REGISTER.negativeFlag = 0;
        }

        REGISTER.PC += 3;
    }
}

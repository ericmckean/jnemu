package INSTRUCTIONS;

import MISC.CONVERTER;
import MISC.INSTRUCTIONS;
import CPU.REGISTER;
import CPU.MEMORY;

public class ABSOLUTE_X
{
    public static void ADC()
    {
        int tmp, Value;
        StringBuilder b_tmp = new StringBuilder(1);
        StringBuilder addr = new StringBuilder(2);

        addr.append(INSTRUCTIONS.intTo8BitHexString(MEMORY.read8Bit(REGISTER.PC + 2)));
        addr.append(INSTRUCTIONS.intTo8BitHexString(MEMORY.read8Bit(REGISTER.PC + 1)));

        Value = MEMORY.read8Bit(CONVERTER.stringHexToInt(addr.toString()) + REGISTER.X);
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


        REGISTER.PC += 3;
    }
}

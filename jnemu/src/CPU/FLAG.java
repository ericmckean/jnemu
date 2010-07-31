package CPU;

public class FLAG
{
    public static void CHECK_CARRY(int i)
    {
        if((i >> 8) != 0)
        {
            CPU_REGISTER.setCarryFlag();
        }
        else
        {
            CPU_REGISTER.clearCarryFlag();
        }
    }

    public static void CHECK_CARRY_SBC(int i, int i2)
    {
        if((i-i2) >= 0)
        {
            CPU_REGISTER.setCarryFlag();
        }
        else
        {
            CPU_REGISTER.clearCarryFlag();
        }
    }

    public static void CHECK_ZERO(int i)
    {
        if(i == 0)
        {
            CPU_REGISTER.setZeroFlag();
        }
        else
        {
            CPU_REGISTER.clearZeroFlag();
        }
    }

    public static void CHECK_NEGATIVE(int i)
    {
        if((i & 0x80) == 0x80)
        {
            CPU_REGISTER.setNegativeFlag();
        }
        else
        {
            CPU_REGISTER.clearNegativeFlag();
        }
    }

    public static void CHECK_OVERFLOW(int Value1, int Value2, int Result)
    {
        if((Value1 & 0x80) == 0 && (Value2 & 0x80) == 0 && (Result & 0x80) == 0x80)
        {
            CPU_REGISTER.setOverflowFlag();
        }
        else if((Value1 & 0x80) != 0 && (Value2 & 0x80) != 0 && (Result & 0x80) != 0x80)
        {
            CPU_REGISTER.setOverflowFlag();
        }
        else
        {
            CPU_REGISTER.clearOverflowFlag();
        }
    }

    public static void CHECK_OVERFLOW_SBC(int AC, int Value, int Result)
    {
        if(((AC ^ Result) & 0x80) == 0x80 && ((AC ^ Value) & 0x80) == 0x80)
        {
            CPU_REGISTER.setOverflowFlag();
        }
        else
        {
            CPU_REGISTER.clearOverflowFlag();
        }
    }
}

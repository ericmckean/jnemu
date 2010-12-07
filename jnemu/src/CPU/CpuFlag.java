package CPU;

public class CpuFlag
{
    public static void checkCarry(int i)
    {
        if((i >> 8) != 0)
        {
            CpuRegister.setCarryFlag();
        }
        else
        {
            CpuRegister.clearCarryFlag();
        }
    }

    public static void checkCarrySbc(int i, int i2)
    {
        if((i-i2) >= 0)
        {
            CpuRegister.setCarryFlag();
        }
        else
        {
            CpuRegister.clearCarryFlag();
        }
    }

    public static void checkZero(int i)
    {
        if(i == 0)
        {
            CpuRegister.setZeroFlag();
        }
        else
        {
            CpuRegister.clearZeroFlag();
        }
    }

    public static void checkNegative(int i)
    {
        if((i & 0x80) == 0x80)
        {
            CpuRegister.setNegativeFlag();
        }
        else
        {
            CpuRegister.clearNegativeFlag();
        }
    }

    public static void checkOverflow(int Value1, int Value2, int Result)
    {
        if((Value1 & 0x80) == 0 && (Value2 & 0x80) == 0 && (Result & 0x80) == 0x80)
        {
            CpuRegister.setOverflowFlag();
        }
        else if((Value1 & 0x80) != 0 && (Value2 & 0x80) != 0 && (Result & 0x80) != 0x80)
        {
            CpuRegister.setOverflowFlag();
        }
        else
        {
            CpuRegister.clearOverflowFlag();
        }
    }

    public static void checkOverflowSbc(int AC, int Value, int Result)
    {
        if(((AC ^ Result) & 0x80) == 0x80 && ((AC ^ Value) & 0x80) == 0x80)
        {
            CpuRegister.setOverflowFlag();
        }
        else
        {
            CpuRegister.clearOverflowFlag();
        }
    }
}

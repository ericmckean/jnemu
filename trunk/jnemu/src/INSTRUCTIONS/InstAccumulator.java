package INSTRUCTIONS;

//import CPU.CPU_MEMORY;
import CPU.CpuRegister;
import CPU.CpuFlag;

public class InstAccumulator
{
    public static void ROL()
    {
        int Value;

        Value = ((CpuRegister.A << 1) | ((CpuRegister.getCarryFlag()==1) ? 1:0)) & 0xff;
        //Carry Flag...
        if((CpuRegister.A & 0x80) == 0x80)
        {
            CpuRegister.setCarryFlag();
        }
        else
        {
            CpuRegister.clearCarryFlag();
        }
        CpuRegister.A = Value;
        CpuFlag.checkNegative(Value);
        CpuFlag.checkZero(Value);
        CpuRegister.PC += 1;
    }

    public static void LSR()
    {
        int Value;

        if((CpuRegister.A & 1) == 1)
        {
            CpuRegister.setCarryFlag();
        }
        else
        {
            CpuRegister.clearCarryFlag();
        }
        Value = CpuRegister.A >> 1;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuRegister.A = Value;
        CpuRegister.PC += 1;
    }

    public static void ASL()
    {
        int Value;

        Value = CpuRegister.A;
        //Carry Flag...
        if((Value & 0x80) == 0x80)
        {
            CpuRegister.setCarryFlag();
        }
        else
        {
            CpuRegister.clearCarryFlag();
        }
        CpuRegister.A = (Value << 1) & 0xff;
        CpuFlag.checkZero(CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);

        CpuRegister.PC += 1;
    }

    public static void ROR()
    {
        int tmp, Value;

        tmp = CpuRegister.getCarryFlag() << 7;
        if((CpuRegister.A & 1) == 1)
        {
            CpuRegister.setCarryFlag();
        }
        else
        {
            CpuRegister.clearCarryFlag();
        }
        Value = (CpuRegister.A >> 1) | tmp;
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        CpuRegister.PC += 1;
    }
}

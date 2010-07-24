package INSTRUCTIONS;

//import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;
import CPU.FLAG;

public class ACCUMULATOR
{
    public static void ROL()
    {
        int Value;

        Value = ((CPU_REGISTER.A << 1) | ((CPU_REGISTER.getCarryFlag()==1) ? 1:0)) & 0xff;
        //Carry Flag...
        if((CPU_REGISTER.A & 0x80) == 0x80)
        {
            CPU_REGISTER.setCarryFlag();
        }
        else
        {
            CPU_REGISTER.clearCarryFlag();
        }
        CPU_REGISTER.A = Value;
        FLAG.CHECK_NEGATIVE(Value);
        FLAG.CHECK_ZERO(Value);
        CPU_REGISTER.PC += 1;
    }

    public static void LSR()
    {
        int Value;

        if((CPU_REGISTER.A & 1) == 1)
        {
            CPU_REGISTER.setCarryFlag();
        }
        else
        {
            CPU_REGISTER.clearCarryFlag();
        }
        Value = CPU_REGISTER.A >> 1;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.A = Value;
        CPU_REGISTER.PC += 1;
    }

    public static void ASL()
    {
        int Value;

        Value = CPU_REGISTER.A;
        //Carry Flag...
        if((Value & 0x80) == 0x80)
        {
            CPU_REGISTER.setCarryFlag();
        }
        else
        {
            CPU_REGISTER.clearCarryFlag();
        }
        CPU_REGISTER.A = (Value << 1) & 0xff;
        FLAG.CHECK_ZERO(CPU_REGISTER.A);
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.A);

        CPU_REGISTER.PC += 1;
    }

    public static void ROR()
    {
        int tmp, Value;

        tmp = CPU_REGISTER.getCarryFlag() << 7;
        if((CPU_REGISTER.A & 1) == 1)
        {
            CPU_REGISTER.setCarryFlag();
        }
        else
        {
            CPU_REGISTER.clearCarryFlag();
        }
        Value = (CPU_REGISTER.A >> 1) | tmp;
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        CPU_REGISTER.PC += 1;
    }
}

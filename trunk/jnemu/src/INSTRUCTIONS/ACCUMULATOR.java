package INSTRUCTIONS;

//import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;
import CPU.FLAG;

public class ACCUMULATOR
{
    public static void ROL()
    {
        int Value;

        Value = (CPU_REGISTER.A << 1) | CPU_REGISTER.getCarryFlag();
        FLAG.CHECK_CARRY(CPU_REGISTER.A);
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
        FLAG.CHECK_CARRY(Value);
        CPU_REGISTER.A = Value << 1;
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

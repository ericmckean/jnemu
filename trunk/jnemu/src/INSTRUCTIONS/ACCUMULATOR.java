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
}

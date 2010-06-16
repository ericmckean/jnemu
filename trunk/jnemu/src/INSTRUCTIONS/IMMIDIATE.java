package INSTRUCTIONS;

import CPU.CPU_REGISTER;
import CPU.CPU_MEMORY;
import CPU.FLAG;

public class IMMIDIATE
{
    public static void ADC()
    {
        int tmp, Value;

        Value =  CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1);
        tmp = CPU_REGISTER.A + Value;
        FLAG.CHECK_OVERFLOW(CPU_REGISTER.A, Value, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY(tmp);
        CPU_REGISTER.A = tmp & 0xFF;
        
        CPU_REGISTER.PC += 2;
    }

    public static void LDA()
    {
        int Value;

        Value =  CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        CPU_REGISTER.PC += 2;
    }

    public static void LDX()
    {
        int Value;

        Value =  CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1);
        CPU_REGISTER.X = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        CPU_REGISTER.PC += 2;
    }

    public static void LDY()
    {
        int Value;

        Value =  CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1);
        CPU_REGISTER.Y = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        CPU_REGISTER.PC += 2;
    }

    public static void CMP()
    {
        int Value;
        
        Value = CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1);
        //check for Carry Flag...
        if(CPU_REGISTER.A >= Value)
        {
            CPU_REGISTER.setCarryFlag();
        }
        //Check for ZERO Flag...
        if(CPU_REGISTER.A == Value)
        {
            CPU_REGISTER.setZeroFlag();
        }

        //Check for Negative Flag...
        FLAG.CHECK_NEGATIVE(Value);

        CPU_REGISTER.PC += 2;
    }

    public static void AND()
    {
        int Value;

        Value = CPU_REGISTER.A & CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.PC += 2;
    }

}
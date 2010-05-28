package INSTRUCTIONS;

import CPU.CPU_REGISTER;
import CPU.CPU_MEMORY;
import CPU.FLAG;
import CPU.STACK;

public class ABSOLUTE
{
    public static void ADC()
    {
        int tmp, Value;
        
        Value = CPU_MEMORY.read8Bit(ADDRESS.get16BitAddressOperand());
        tmp = CPU_REGISTER.A + Value;
        FLAG.CHECK_OVERFLOW(CPU_REGISTER.A, Value, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY(tmp);
        CPU_REGISTER.A = tmp & 0xFF;

        CPU_REGISTER.PC += 3;
    }

    public static void STA()
    {
        CPU_MEMORY.write8Bit(ADDRESS.get16BitAddressOperand(), CPU_REGISTER.A);
        CPU_REGISTER.PC += 3;
    }

    public static void LDA()
    {
        int Value;

        Value = CPU_MEMORY.read8Bit(ADDRESS.get16BitAddressOperand());
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        CPU_REGISTER.PC += 3;
    }

    public static void JSR()
    {
        STACK.Push(CPU_REGISTER.PC >> 8);
        STACK.Push(CPU_REGISTER.PC & 0xFF);
        CPU_REGISTER.PC = ADDRESS.get16BitAddressOperand();
    }

    public static void STY()
    {
        CPU_MEMORY.write8Bit(ADDRESS.get16BitAddressOperand(), CPU_REGISTER.Y);
        CPU_REGISTER.PC += 3;
    }
}
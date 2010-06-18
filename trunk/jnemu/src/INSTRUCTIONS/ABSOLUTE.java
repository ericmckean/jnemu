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

    public static void ROL()
    {
        int Value, addr;

        addr = ADDRESS.get16BitAddressOperand();
        Value = (CPU_MEMORY.read8Bit(addr) << 1) | CPU_REGISTER.getCarryFlag();
        FLAG.CHECK_CARRY(CPU_MEMORY.read8Bit(addr));
        CPU_MEMORY.write8Bit(addr, Value);
        FLAG.CHECK_NEGATIVE(Value);
        FLAG.CHECK_ZERO(Value);
        CPU_REGISTER.PC += 3;
    }

    public static void JMP()
    {
        CPU_REGISTER.PC = ADDRESS.get16BitAddressOperand();
        //Does not increment PC.....
    }

    public static void LDX()
    {
        int Value;

        Value = CPU_MEMORY.read8Bit(ADDRESS.get16BitAddressOperand());
        CPU_REGISTER.X = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        CPU_REGISTER.PC += 3;
    }

    public static void STX()
    {
        CPU_MEMORY.write8Bit(ADDRESS.get16BitAddressOperand(), CPU_REGISTER.X);
        CPU_REGISTER.PC += 3;
    }

    public static void SBC()
    {
        int tmp, Value;

        Value = CPU_MEMORY.read8Bit(ADDRESS.get16BitAddressOperand());
        tmp = CPU_REGISTER.A - Value;
        FLAG.CHECK_OVERFLOW(CPU_REGISTER.A, Value, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY_SBC(tmp);
        CPU_REGISTER.A = tmp & 0xFF;

        CPU_REGISTER.PC += 3;
    }

    public static void ASL()
    {
        int Value, addr, tmp;

        addr = ADDRESS.get16BitAddressOperand();

        Value = CPU_MEMORY.read8Bit(addr);
        tmp = Value << 1;
        FLAG.CHECK_CARRY(Value);
        CPU_MEMORY.write8Bit(addr, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);

        CPU_REGISTER.PC += 3;
    }

    public static void LSR()
    {
        int Value, addr, tmp;

        addr = ADDRESS.get16BitAddressOperand();

        Value = CPU_MEMORY.read8Bit(addr);
        tmp = Value >> 1;
        if((Value & 1) == 1)
        {
            CPU_REGISTER.setCarryFlag();
        }
        else
        {
            CPU_REGISTER.clearCarryFlag();
        }
        CPU_MEMORY.write8Bit(addr, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);

        CPU_REGISTER.PC += 3;
    }

    public static void ROR()
    {
        int tmp, Value, addr, memValue;

        addr = ADDRESS.get16BitAddressOperand();
        memValue = CPU_MEMORY.read8Bit(addr);
        tmp = CPU_REGISTER.getCarryFlag() << 7;
        if((memValue & 1) == 1)
        {
            CPU_REGISTER.setCarryFlag();
        }
        else
        {
            CPU_REGISTER.clearCarryFlag();
        }
        Value = (memValue >> 1) | tmp;
        CPU_MEMORY.write8Bit(addr, Value);
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        CPU_REGISTER.PC += 3;
    }

    public static void INC()
    {
        int Value, addr;

        addr = ADDRESS.get16BitAddressOperand();
        Value = CPU_MEMORY.read8Bit(addr) + 1;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_MEMORY.write8Bit(addr, Value);

        CPU_REGISTER.PC += 3;
    }

    public static void DEC()
    {
        int addr, Value;

        addr = ADDRESS.get16BitAddressOperand();
        Value = CPU_MEMORY.read8Bit(addr) - 1;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_MEMORY.write8Bit(addr, Value);
        CPU_REGISTER.PC += 3;
    }

    public static void LDY()
    {
        int Value;

        Value = CPU_MEMORY.read8Bit(ADDRESS.get16BitAddressOperand());
        CPU_REGISTER.Y = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        CPU_REGISTER.PC += 3;
    }
}

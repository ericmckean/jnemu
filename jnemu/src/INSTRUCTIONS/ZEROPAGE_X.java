package INSTRUCTIONS;


import CPU.CPU_REGISTER;
import CPU.CPU_MEMORY;
import CPU.FLAG;

public class ZEROPAGE_X
{
    public static void ADC()
    {
        int tmp, Value;

        Value = CPU_MEMORY.read8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF);
        tmp = CPU_REGISTER.A + Value + CPU_REGISTER.getCarryFlag();
        FLAG.CHECK_OVERFLOW(CPU_REGISTER.A, Value, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY(tmp);
        CPU_REGISTER.A = tmp & 0xFF;
        
        CPU_REGISTER.PC += 2;
    }

    public static void SBC()
    {
        int tmp, Value;

        Value = CPU_MEMORY.read8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF);
        tmp = CPU_REGISTER.A - Value;
        FLAG.CHECK_OVERFLOW(CPU_REGISTER.A, Value, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY_SBC(tmp);
        CPU_REGISTER.A = tmp & 0xFF;
        
        CPU_REGISTER.PC += 2;
    }

    public static void STY()
    {
        CPU_MEMORY.write8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF, CPU_REGISTER.Y);
        CPU_REGISTER.PC += 2;
    }

    public static void ROL()
    {
        int Value, addr;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF;
        Value = (CPU_MEMORY.read8Bit(addr) << 1) | CPU_REGISTER.getCarryFlag();
        FLAG.CHECK_CARRY(CPU_MEMORY.read8Bit(addr));
        CPU_MEMORY.write8Bit(addr, Value);
        FLAG.CHECK_NEGATIVE(Value);
        FLAG.CHECK_ZERO(Value);
        CPU_REGISTER.PC += 2;
    }

    public static void STA()
    {
        CPU_MEMORY.write8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF, CPU_REGISTER.A);
        CPU_REGISTER.PC += 2;
    }

    public static void LDA()
    {
        int Value, addr;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF;
        Value =  CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        CPU_REGISTER.PC += 2;
    }

    public static void ASL()
    {
        int Value, addr, tmp;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF;

        Value = CPU_MEMORY.read8Bit(addr);
        tmp = Value << 1;
        FLAG.CHECK_CARRY(Value);
        CPU_MEMORY.write8Bit(addr, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);

        CPU_REGISTER.PC += 2;
    }

    public static void LSR()
    {
        int Value, addr, tmp;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF;

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

        CPU_REGISTER.PC += 2;
    }

    public static void ROR()
    {
        int tmp, Value, addr, memValue;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF;
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

        CPU_REGISTER.PC += 2;
    }

    public static void INC()
    {
        int Value, addr;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF;
        Value = CPU_MEMORY.read8Bit(addr) + 1;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_MEMORY.write8Bit(addr, Value);

        CPU_REGISTER.PC += 2;
    }

    public static void DEC()
    {
        int addr, Value;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF;
        Value = CPU_MEMORY.read8Bit(addr) - 1;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_MEMORY.write8Bit(addr, Value);
        CPU_REGISTER.PC += 2;
    }

    public static void LDY()
    {
        int Value, addr;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF;
        Value =  CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.Y = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        CPU_REGISTER.PC += 2;
    }

    public static void AND()
    {
        int Value, addr;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF;
        Value = CPU_REGISTER.A & CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.PC += 2;
    }

    public static void EOR()
    {
        int Value, addr;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF;
        Value = CPU_REGISTER.A ^ CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.PC += 2;
    }

    public static void ORA()
    {
        int Value, addr;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF;
        Value = CPU_REGISTER.A | CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.PC += 2;
    }

    public static void CMP()
    {
        int Value, addr, tmp;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xFF;
        Value = CPU_MEMORY.read8Bit(addr);
        tmp = CPU_REGISTER.A - Value;
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
        FLAG.CHECK_NEGATIVE(tmp);

        CPU_REGISTER.PC += 2;
    }
}

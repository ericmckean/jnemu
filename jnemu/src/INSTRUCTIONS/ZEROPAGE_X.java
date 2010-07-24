package INSTRUCTIONS;


import CPU.CPU_REGISTER;
import CPU.CPU_MEMORY;
import CPU.FLAG;

public class ZEROPAGE_X
{
    public static void ADC()
    {
        int tmp, Value;

        Value = CPU_MEMORY.read8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff);
        tmp = CPU_REGISTER.A + Value + CPU_REGISTER.getCarryFlag();
        FLAG.CHECK_OVERFLOW(CPU_REGISTER.A, Value, (tmp & 0xff));
        FLAG.CHECK_ZERO(tmp & 0xff);
        FLAG.CHECK_NEGATIVE(tmp & 0xff);
        FLAG.CHECK_CARRY(tmp);
        CPU_REGISTER.A = tmp & 0xff;
        
        CPU_REGISTER.PC += 2;
    }

    public static void SBC()
    {
        int tmp, Value;

        Value = CPU_MEMORY.read8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff);
        tmp = CPU_REGISTER.A - Value - ((CPU_REGISTER.getCarryFlag()==1) ? 0 : 1);
        FLAG.CHECK_OVERFLOW_SBC(CPU_REGISTER.A, Value);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY_SBC(CPU_REGISTER.A, Value);
        CPU_REGISTER.A = tmp & 0xff;
        
        CPU_REGISTER.PC += 2;
    }

    public static void STY()
    {
        CPU_MEMORY.write8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff, CPU_REGISTER.Y);
        CPU_REGISTER.PC += 2;
    }

    public static void ROL()
    {
        int Value, addr, tmp;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff;
        tmp = CPU_MEMORY.read8Bit(addr);
        Value = ((tmp << 1) | ((CPU_REGISTER.getCarryFlag()==1) ? 1:0)) & 0xff;
        //Carry Flag...
        if((tmp & 0x80) == 0x80)
        {
            CPU_REGISTER.setCarryFlag();
        }
        else
        {
            CPU_REGISTER.clearCarryFlag();
        }
        CPU_MEMORY.write8Bit(addr, Value);
        FLAG.CHECK_NEGATIVE(Value);
        FLAG.CHECK_ZERO(Value);
        CPU_REGISTER.PC += 2;
    }

    public static void STA()
    {
        CPU_MEMORY.write8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff, CPU_REGISTER.A);
        CPU_REGISTER.PC += 2;
    }

    public static void LDA()
    {
        int Value, addr;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff;
        Value =  CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        CPU_REGISTER.PC += 2;
    }

    public static void ASL()
    {
        int Value, addr, tmp;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff;

        Value = CPU_MEMORY.read8Bit(addr);
        tmp = (Value << 1) & 0xff;
        //Carry Flag...
        if((Value & 0x80) == 0x80)
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

    public static void LSR()
    {
        int Value, addr, tmp;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff;

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

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff;
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

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff;
        Value = CPU_MEMORY.read8Bit(addr) + 1;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_MEMORY.write8Bit(addr, Value);

        CPU_REGISTER.PC += 2;
    }

    public static void DEC()
    {
        int addr, Value;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff;
        Value = CPU_MEMORY.read8Bit(addr) - 1;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_MEMORY.write8Bit(addr, Value);
        CPU_REGISTER.PC += 2;
    }

    public static void LDY()
    {
        int Value, addr;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff;
        Value =  CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.Y = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        CPU_REGISTER.PC += 2;
    }

    public static void AND()
    {
        int Value, addr;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff;
        Value = CPU_REGISTER.A & CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.PC += 2;
    }

    public static void EOR()
    {
        int Value, addr;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff;
        Value = CPU_REGISTER.A ^ CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.PC += 2;
    }

    public static void ORA()
    {
        int Value, addr;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff;
        Value = CPU_REGISTER.A | CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.PC += 2;
    }

    public static void CMP()
    {
        int Value, addr, tmp;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff;
        Value = CPU_MEMORY.read8Bit(addr);
        //check for Carry Flag...
        if(CPU_REGISTER.A >= Value)
        {
            CPU_REGISTER.setCarryFlag();
        }
        else
        {
            CPU_REGISTER.clearCarryFlag();
        }
        //Check for ZERO Flag...
        if(CPU_REGISTER.A == Value)
        {
            CPU_REGISTER.setZeroFlag();
        }
        else
        {
            CPU_REGISTER.clearZeroFlag();
        }

        tmp = CPU_REGISTER.A - Value;
        //Check for Negative Flag...
        FLAG.CHECK_NEGATIVE(tmp);

        CPU_REGISTER.PC += 2;
    }
}

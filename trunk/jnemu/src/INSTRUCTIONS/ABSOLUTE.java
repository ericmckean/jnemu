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
        tmp = CPU_REGISTER.A + Value + CPU_REGISTER.getCarryFlag();
        FLAG.CHECK_OVERFLOW(CPU_REGISTER.A, Value, (tmp & 0xff));
        FLAG.CHECK_ZERO(tmp & 0xff);
        FLAG.CHECK_NEGATIVE(tmp & 0xff);
        FLAG.CHECK_CARRY(tmp);
        CPU_REGISTER.A = tmp & 0xff;

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
        int pc;

        pc = CPU_REGISTER.PC + 2;
        STACK.Push((pc >> 8) & 0xFF);   //MSB
        STACK.Push(pc & 0xFF);          //LSB
        CPU_REGISTER.PC = ADDRESS.get16BitAddressOperand();
    }

    public static void STY()
    {
        CPU_MEMORY.write8Bit(ADDRESS.get16BitAddressOperand(), CPU_REGISTER.Y);
        CPU_REGISTER.PC += 3;
    }

    public static void ROL()
    {
        int Value, addr, tmp;

        addr = ADDRESS.get16BitAddressOperand();
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
        tmp = CPU_REGISTER.A - Value - ((CPU_REGISTER.getCarryFlag()==1) ? 0 : 1);
        FLAG.CHECK_OVERFLOW_SBC(CPU_REGISTER.A, Value);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY_SBC(CPU_REGISTER.A, Value);
        CPU_REGISTER.A = tmp & 0xFF;

        CPU_REGISTER.PC += 3;
    }

    public static void ASL()
    {
        int Value, addr, tmp;

        addr = ADDRESS.get16BitAddressOperand();

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

    public static void AND()
    {
        int Value, addr;

        addr = ADDRESS.get16BitAddressOperand();
        Value = CPU_REGISTER.A & CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.PC += 3;
    }

    public static void EOR()
    {
        int Value, addr;

        addr = ADDRESS.get16BitAddressOperand();
        Value = CPU_REGISTER.A ^ CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.PC += 3;
    }

    public static void ORA()
    {
        int Value, addr;

        addr = ADDRESS.get16BitAddressOperand();
        Value = CPU_REGISTER.A | CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.PC += 3;
    }

    public static void CMP()
    {
        int Value, addr, tmp;

        addr = ADDRESS.get16BitAddressOperand();
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

        CPU_REGISTER.PC += 3;
    }

    public static void CPX()
    {
        int Value, addr, tmp;

        addr = ADDRESS.get16BitAddressOperand();
        Value = CPU_MEMORY.read8Bit(addr);
        //check for Carry Flag...
        if(CPU_REGISTER.X >= Value)
        {
            CPU_REGISTER.setCarryFlag();
        }
        else
        {
            CPU_REGISTER.clearCarryFlag();
        }
        //Check for ZERO Flag...
        if(CPU_REGISTER.X == Value)
        {
            CPU_REGISTER.setZeroFlag();
        }
        else
        {
            CPU_REGISTER.clearZeroFlag();
        }

        tmp = CPU_REGISTER.X - Value;
        //Check for Negative Flag...
        FLAG.CHECK_NEGATIVE(tmp);

        CPU_REGISTER.PC += 3;
    }

    public static void CPY()
    {
        int Value, addr, tmp;

        addr = ADDRESS.get16BitAddressOperand();
        Value = CPU_MEMORY.read8Bit(addr);
        //check for Carry Flag...
        if(CPU_REGISTER.Y >= Value)
        {
            CPU_REGISTER.setCarryFlag();
        }
        else
        {
            CPU_REGISTER.clearCarryFlag();
        }
        //Check for ZERO Flag...
        if(CPU_REGISTER.Y == Value)
        {
            CPU_REGISTER.setZeroFlag();
        }
        else
        {
            CPU_REGISTER.clearZeroFlag();
        }

        tmp = CPU_REGISTER.Y - Value;
        //Check for Negative Flag...
        FLAG.CHECK_NEGATIVE(tmp);

        CPU_REGISTER.PC += 3;
    }

    public static void BIT()
    {
        int Value, addr, mem;

        addr = ADDRESS.get16BitAddressOperand();
        mem = CPU_MEMORY.read8Bit(addr);
        Value = CPU_REGISTER.A & mem;
        FLAG.CHECK_ZERO(Value);
        //Overflow Flag
        if((mem & 0x40) == 0x40)
        {
            CPU_REGISTER.setOverflowFlag();
        }
        else
        {
            CPU_REGISTER.clearOverflowFlag();
        }
        //Negative Flag
        if((mem & 0x80) == 0x80)
        {
            CPU_REGISTER.setNegativeFlag();
        }
        else
        {
            CPU_REGISTER.clearNegativeFlag();
        }

        CPU_REGISTER.PC += 3;
    }
}

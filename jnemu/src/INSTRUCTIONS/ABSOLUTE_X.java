package INSTRUCTIONS;

import CPU.CPU_REGISTER;
import CPU.CPU_MEMORY;
import CPU.FLAG;

public class ABSOLUTE_X
{
    public static int ADC()
    {
        int tmp, Value, oldAddr, newAddr, retCycle;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr =  (oldAddr + CPU_REGISTER.X) & 0xFFFF;

        Value = CPU_MEMORY.read8Bit(newAddr);
        tmp = CPU_REGISTER.A + Value + CPU_REGISTER.getCarryFlag();
        FLAG.CHECK_OVERFLOW(CPU_REGISTER.A, Value, (tmp & 0xff));
        FLAG.CHECK_ZERO(tmp & 0xff);
        FLAG.CHECK_NEGATIVE(tmp & 0xff);
        FLAG.CHECK_CARRY(tmp);
        CPU_REGISTER.A = tmp & 0xff;

        if(CPU_MEMORY.getPage(oldAddr) != CPU_MEMORY.getPage(newAddr))
        {
            retCycle = 5;
        }
        else
        {
            retCycle = 4;
        }

        CPU_REGISTER.PC += 3;
        return retCycle;
    }

    public static int LDA()
    {
        int Value, cycle = 0, oldAddr, newAddr;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr = ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X;

        Value = CPU_MEMORY.read8Bit((ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X) & 0xFFFF);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        if(CPU_MEMORY.getPage(newAddr) != CPU_MEMORY.getPage(oldAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 4;
        }
        CPU_REGISTER.PC += 3;
        return cycle;
    }

    public static void ROL()
    {
        int Value, addr, tmp;

        addr = (ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X) & 0xFFFF;
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

    public static void STA()
    {
        CPU_MEMORY.write8Bit((ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X) & 0xFFFF, CPU_REGISTER.A);
        CPU_REGISTER.PC += 3;
    }

    public static int SBC()
    {
        int tmp, Value, oldAddr, newAddr, retCycle;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr =  (oldAddr + CPU_REGISTER.X) & 0xFFFF;

        Value = CPU_MEMORY.read8Bit(newAddr);
        tmp = CPU_REGISTER.A - Value - ((CPU_REGISTER.getCarryFlag()==1) ? 0 : 1);
        FLAG.CHECK_OVERFLOW_SBC(CPU_REGISTER.A, Value);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY_SBC(CPU_REGISTER.A, Value);
        CPU_REGISTER.A = tmp & 0xFF;

        if(CPU_MEMORY.getPage(oldAddr) != CPU_MEMORY.getPage(newAddr))
        {
            retCycle = 5;
        }
        else
        {
            retCycle = 4;
        }

        CPU_REGISTER.PC += 3;
        return retCycle;
    }

    public static void ASL()
    {
        int Value, addr, tmp;

        addr = (ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X) & 0xFFFF;

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

        addr = (ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X) & 0xFFFF;

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

        addr = (ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X) & 0xFFFF;
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

        addr = (ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X) & 0xFFFF;
        Value = (CPU_MEMORY.read8Bit(addr) + 1) & 0xff;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_MEMORY.write8Bit(addr, Value);

        CPU_REGISTER.PC += 3;
    }

    public static void DEC()
    {
        int addr, Value;

        addr = (ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X) & 0xFFFF;
        Value = CPU_MEMORY.read8Bit(addr) - 1;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_MEMORY.write8Bit(addr, Value);
        CPU_REGISTER.PC += 3;
    }

    public static int LDY()
    {
        int Value, cycle = 0, oldAddr, newAddr;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr = ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X;

        Value = CPU_MEMORY.read8Bit((ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X) & 0xFFFF);
        CPU_REGISTER.Y = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        if(CPU_MEMORY.getPage(newAddr) != CPU_MEMORY.getPage(oldAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 4;
        }
        CPU_REGISTER.PC += 3;
        return cycle;
    }

    public static int AND()
    {
        int Value, oldAddr, newAddr, cycle = 0;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr = ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X;

        Value = CPU_REGISTER.A & CPU_MEMORY.read8Bit(newAddr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        if(CPU_MEMORY.getPage(newAddr) != CPU_MEMORY.getPage(oldAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 4;
        }
        CPU_REGISTER.PC += 3;
        return cycle;
    }

    public static int EOR()
    {
        int Value, oldAddr, newAddr, cycle = 0;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr = ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X;

        Value = CPU_REGISTER.A ^ CPU_MEMORY.read8Bit(newAddr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        if(CPU_MEMORY.getPage(newAddr) != CPU_MEMORY.getPage(oldAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 4;
        }
        CPU_REGISTER.PC += 3;
        return cycle;
    }

    public static int ORA()
    {
        int Value, oldAddr, newAddr, cycle = 0;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr = ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X;

        Value = CPU_REGISTER.A | CPU_MEMORY.read8Bit(newAddr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        if(CPU_MEMORY.getPage(newAddr) != CPU_MEMORY.getPage(oldAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 4;
        }
        CPU_REGISTER.PC += 3;
        return cycle;
    }

    public static int CMP()
    {
        int Value, oldAddr, newAddr, cycle = 0, tmp;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr = ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X;

        Value = CPU_MEMORY.read8Bit(newAddr);
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

        if(CPU_MEMORY.getPage(newAddr) != CPU_MEMORY.getPage(oldAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 4;
        }
        CPU_REGISTER.PC += 3;
        return cycle;
    }
}

package INSTRUCTIONS;

import CPU.CPU_REGISTER;
import CPU.CPU_MEMORY;
import CPU.FLAG;

public class ABSOLUTE_Y
{
    public static int ADC()
    {
        int tmp, Value, oldAddr, newAddr, retCycle;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr =  (oldAddr + CPU_REGISTER.Y) & 0xFFFF;

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

    public static void STA()
    {
        CPU_MEMORY.write8Bit((ADDRESS.get16BitAddressOperand() + CPU_REGISTER.Y) & 0xFFFF, CPU_REGISTER.A);
        CPU_REGISTER.PC += 3;
    }

    public static int LDA()
    {
        int Value, cycle = 0, oldAddr, newAddr;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr = ADDRESS.get16BitAddressOperand() + CPU_REGISTER.Y;

        Value = CPU_MEMORY.read8Bit(newAddr & 0xFFFF);
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

    public static int SBC()
    {
        int tmp, Value, oldAddr, newAddr, retCycle;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr =  (oldAddr + CPU_REGISTER.Y) & 0xFFFF;

        Value = CPU_MEMORY.read8Bit(newAddr);
        tmp = CPU_REGISTER.A - Value- ((CPU_REGISTER.getCarryFlag()==1) ? 0 : 1);
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

    public static int LDX()
    {
        int Value, cycle = 0, oldAddr, newAddr;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr = ADDRESS.get16BitAddressOperand() + CPU_REGISTER.Y;

        Value = CPU_MEMORY.read8Bit(newAddr & 0xFFFF);
        CPU_REGISTER.X = Value;
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
        newAddr = ADDRESS.get16BitAddressOperand() + CPU_REGISTER.Y;

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
        newAddr = ADDRESS.get16BitAddressOperand() + CPU_REGISTER.Y;

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
        newAddr = ADDRESS.get16BitAddressOperand() + CPU_REGISTER.Y;

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
        newAddr = ADDRESS.get16BitAddressOperand() + CPU_REGISTER.Y;

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

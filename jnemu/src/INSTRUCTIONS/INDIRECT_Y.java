package INSTRUCTIONS;

import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;
import CPU.FLAG;

public class INDIRECT_Y
{
    public static void STA()
    {
        int addr, LSB, MSB;

        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        addr = (((MSB << 8) | LSB) + CPU_REGISTER.Y) & 0xffff;

        CPU_MEMORY.write8Bit(addr, CPU_REGISTER.A);
        CPU_REGISTER.PC += 2;
    }

    public static int ADC()
    {
        int tmp, Value, oldAddr, newAddr, MSB, LSB, cycle = 0;

        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        oldAddr = ((MSB << 8) | LSB);
        newAddr = (((MSB << 8) | LSB) + CPU_REGISTER.Y) & 0xffff;

        Value = CPU_MEMORY.read8Bit(newAddr);
        tmp = CPU_REGISTER.A + Value + CPU_REGISTER.getCarryFlag();
        FLAG.CHECK_OVERFLOW(CPU_REGISTER.A, Value, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY(tmp);
        CPU_REGISTER.A = tmp & 0xFF;

        if(CPU_MEMORY.getPage(oldAddr) == CPU_MEMORY.getPage(newAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 6;
        }

        CPU_REGISTER.PC += 2;
        return cycle;
    }

    public static int SBC()
    {
        int tmp, Value, oldAddr, newAddr, MSB, LSB, cycle = 0;

        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        oldAddr = ((MSB << 8) | LSB);
        newAddr = (((MSB << 8) | LSB) + CPU_REGISTER.Y) & 0xffff;

        Value = CPU_MEMORY.read8Bit(newAddr);
        tmp = CPU_REGISTER.A - Value;
        FLAG.CHECK_OVERFLOW(CPU_REGISTER.A, Value, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY_SBC(tmp);
        CPU_REGISTER.A = tmp & 0xFF;

        if(CPU_MEMORY.getPage(oldAddr) == CPU_MEMORY.getPage(newAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 6;
        }

        CPU_REGISTER.PC += 2;
        return cycle;
    }

    public static int LDA()
    {
        int Value, MSB, LSB, oldAddr, newAddr, cycle = 0;

        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        oldAddr = ((MSB << 8) | LSB);
        newAddr = (((MSB << 8) | LSB) + CPU_REGISTER.Y) & 0xffff;

        Value =  CPU_MEMORY.read8Bit(newAddr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        if(CPU_MEMORY.getPage(oldAddr) == CPU_MEMORY.getPage(newAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 6;
        }

        CPU_REGISTER.PC += 2;
        return cycle;
    }

    public static int AND()
    {
        int Value, oldAddr, newAddr, MSB, LSB, cycle = 0;

        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        oldAddr = ((MSB << 8) | LSB);
        newAddr = (((MSB << 8) | LSB) + CPU_REGISTER.Y) & 0xffff;

        Value = CPU_REGISTER.A & CPU_MEMORY.read8Bit(newAddr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        if(CPU_MEMORY.getPage(oldAddr) == CPU_MEMORY.getPage(newAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 6;
        }

        CPU_REGISTER.PC += 2;
        return cycle;
    }

    public static int EOR()
    {
        int Value, oldAddr, newAddr, MSB, LSB, cycle = 0;

        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        oldAddr = ((MSB << 8) | LSB);
        newAddr = (((MSB << 8) | LSB) + CPU_REGISTER.Y) & 0xffff;

        Value = CPU_REGISTER.A ^ CPU_MEMORY.read8Bit(newAddr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        if(CPU_MEMORY.getPage(oldAddr) == CPU_MEMORY.getPage(newAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 6;
        }

        CPU_REGISTER.PC += 2;
        return cycle;
    }

    public static int ORA()
    {
        int Value, oldAddr, newAddr, MSB, LSB, cycle = 0;

        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        oldAddr = ((MSB << 8) | LSB);
        newAddr = (((MSB << 8) | LSB) + CPU_REGISTER.Y) & 0xffff;

        Value = CPU_REGISTER.A | CPU_MEMORY.read8Bit(newAddr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        if(CPU_MEMORY.getPage(oldAddr) == CPU_MEMORY.getPage(newAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 6;
        }

        CPU_REGISTER.PC += 2;
        return cycle;
    }

    public static int CMP()
    {
        int Value, oldAddr, newAddr, MSB, LSB, cycle = 0, tmp;

        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        oldAddr = ((MSB << 8) | LSB);
        newAddr = (((MSB << 8) | LSB) + CPU_REGISTER.Y) & 0xffff;

        Value = CPU_MEMORY.read8Bit(newAddr);
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

        if(CPU_MEMORY.getPage(oldAddr) == CPU_MEMORY.getPage(newAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 6;
        }

        CPU_REGISTER.PC += 2;
        return cycle;
    }
}

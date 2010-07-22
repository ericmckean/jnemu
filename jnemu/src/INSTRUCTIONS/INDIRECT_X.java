package INSTRUCTIONS;

import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;
import CPU.FLAG;

public class INDIRECT_X
{
    public static void STA()
    {
        int addr, MSB, LSB;
        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        addr = (MSB << 8) | ((LSB + CPU_REGISTER.X) & 0xFF);
        
        CPU_MEMORY.write8Bit(addr, CPU_REGISTER.A);
        CPU_REGISTER.PC += 2;
    }

    public static void ADC()
    {
        int tmp, Value, addr, MSB, LSB;

        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        addr = (MSB << 8) | ((LSB + CPU_REGISTER.X) & 0xFF);

        Value = CPU_MEMORY.read8Bit(addr);
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
        int tmp, Value, addr, MSB, LSB;

        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        addr = (MSB << 8) | ((LSB + CPU_REGISTER.X) & 0xFF);

        Value = CPU_MEMORY.read8Bit(addr);
        tmp = CPU_REGISTER.A - Value;
        FLAG.CHECK_OVERFLOW(CPU_REGISTER.A, Value, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY_SBC(tmp);
        CPU_REGISTER.A = tmp & 0xFF;

        CPU_REGISTER.PC += 2;
    }

    public static void LDA()
    {
        int Value, addr, MSB, LSB;

        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        addr = (MSB << 8) | ((LSB + CPU_REGISTER.X) & 0xFF);

        Value =  CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        CPU_REGISTER.PC += 2;
    }

    public static void AND()
    {
        int Value, addr, MSB, LSB;

        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        addr = (MSB << 8) | ((LSB + CPU_REGISTER.X) & 0xFF);
        
        Value = CPU_REGISTER.A & CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.PC += 2;
    }

    public static void EOR()
    {
        int Value, addr, MSB, LSB;

        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        addr = (MSB << 8) | ((LSB + CPU_REGISTER.X) & 0xFF);

        Value = CPU_REGISTER.A ^ CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.PC += 2;
    }

    public static void ORA()
    {
        int Value, addr, MSB, LSB;

        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        addr = (MSB << 8) | ((LSB + CPU_REGISTER.X) & 0xFF);

        Value = CPU_REGISTER.A | CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.PC += 2;
    }

    public static void CMP()
    {
        int Value, addr, MSB, LSB, tmp;

        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        addr = (MSB << 8) | ((LSB + CPU_REGISTER.X) & 0xFF);
        
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

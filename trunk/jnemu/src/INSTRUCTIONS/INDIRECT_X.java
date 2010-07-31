package INSTRUCTIONS;

import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;
import CPU.FLAG;

public class INDIRECT_X
{
    public static void STA()
    {
        int addr, LSB, MSB;

        LSB = CPU_MEMORY.read8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff);
        MSB = CPU_MEMORY.read8Bit(((CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1) + CPU_REGISTER.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;
        
        CPU_MEMORY.write8Bit(addr, CPU_REGISTER.A);
        CPU_REGISTER.PC += 2;
    }

    public static void ADC()
    {
        int Value, addr, LSB, MSB, tmp;

        LSB = CPU_MEMORY.read8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff);
        MSB = CPU_MEMORY.read8Bit(((CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1) + CPU_REGISTER.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;

        Value = CPU_MEMORY.read8Bit(addr);
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
        int Value, addr, LSB, MSB, tmp;

        LSB = CPU_MEMORY.read8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff);
        MSB = CPU_MEMORY.read8Bit(((CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1) + CPU_REGISTER.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;

        Value = CPU_MEMORY.read8Bit(addr);
        tmp = CPU_REGISTER.A - Value - ((CPU_REGISTER.getCarryFlag()==1) ? 0 : 1);
        FLAG.CHECK_OVERFLOW_SBC(CPU_REGISTER.A, Value, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY_SBC(CPU_REGISTER.A, Value);
        CPU_REGISTER.A = tmp & 0xFF;

        CPU_REGISTER.PC += 2;
    }

    public static void LDA()
    {
        int Value, addr, LSB, MSB;

        LSB = CPU_MEMORY.read8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff);
        MSB = CPU_MEMORY.read8Bit(((CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1) + CPU_REGISTER.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;

        Value =  CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        CPU_REGISTER.PC += 2;
    }

    public static void AND()
    {
        int Value, addr, LSB, MSB;

        LSB = CPU_MEMORY.read8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff);
        MSB = CPU_MEMORY.read8Bit(((CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1) + CPU_REGISTER.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;
        
        Value = CPU_REGISTER.A & CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.PC += 2;
    }

    public static void EOR()
    {
        int Value, addr, LSB, MSB;

        LSB = CPU_MEMORY.read8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff);
        MSB = CPU_MEMORY.read8Bit(((CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1) + CPU_REGISTER.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;

        Value = CPU_REGISTER.A ^ CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.PC += 2;
    }

    public static void ORA()
    {
        int Value, addr, LSB, MSB;

        LSB = CPU_MEMORY.read8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff);
        MSB = CPU_MEMORY.read8Bit(((CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1) + CPU_REGISTER.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;

        Value = CPU_REGISTER.A | CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);
        CPU_REGISTER.PC += 2;
    }

    public static void CMP()
    {
        int Value, addr, LSB, MSB, tmp;

       LSB = CPU_MEMORY.read8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff);
        MSB = CPU_MEMORY.read8Bit(((CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1) + CPU_REGISTER.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;
        
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

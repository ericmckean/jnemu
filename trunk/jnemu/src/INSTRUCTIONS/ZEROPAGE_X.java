package INSTRUCTIONS;


import CPU.CpuRegister;
import CPU.CpuMemory;
import CPU.CpuFlag;

public class ZEROPAGE_X
{
    public static void ADC()
    {
        int tmp, Value;

        Value = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        tmp = CpuRegister.A + Value + CpuRegister.getCarryFlag();
        CpuFlag.checkOverflow(CpuRegister.A, Value, (tmp & 0xff));
        CpuFlag.checkZero(tmp & 0xff);
        CpuFlag.checkNegative(tmp & 0xff);
        CpuFlag.checkCarry(tmp);
        CpuRegister.A = tmp & 0xff;
        
        CpuRegister.PC += 2;
    }

    public static void SBC()
    {
        int tmp, Value;

        Value = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        tmp = CpuRegister.A - Value - ((CpuRegister.getCarryFlag()==1) ? 0 : 1);
        CpuFlag.checkOverflowSbc(CpuRegister.A, Value, tmp);
        CpuFlag.checkZero(tmp);
        CpuFlag.checkNegative(tmp);
        CpuFlag.checkCarrySbc(CpuRegister.A, Value);
        CpuRegister.A = tmp & 0xff;
        
        CpuRegister.PC += 2;
    }

    public static void STY()
    {
        CpuMemory.write8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff, CpuRegister.Y);
        CpuRegister.PC += 2;
    }

    public static void ROL()
    {
        int Value, addr, tmp;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;
        tmp = CpuMemory.read8Bit(addr);
        Value = ((tmp << 1) | ((CpuRegister.getCarryFlag()==1) ? 1:0)) & 0xff;
        //Carry Flag...
        if((tmp & 0x80) == 0x80)
        {
            CpuRegister.setCarryFlag();
        }
        else
        {
            CpuRegister.clearCarryFlag();
        }
        CpuMemory.write8Bit(addr, Value);
        CpuFlag.checkNegative(Value);
        CpuFlag.checkZero(Value);
        CpuRegister.PC += 2;
    }

    public static void STA()
    {
        CpuMemory.write8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff, CpuRegister.A);
        CpuRegister.PC += 2;
    }

    public static void LDA()
    {
        int Value, addr;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;
        Value =  CpuMemory.read8Bit(addr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        CpuRegister.PC += 2;
    }

    public static void ASL()
    {
        int Value, addr, tmp;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;

        Value = CpuMemory.read8Bit(addr);
        tmp = (Value << 1) & 0xff;
        //Carry Flag...
        if((Value & 0x80) == 0x80)
        {
            CpuRegister.setCarryFlag();
        }
        else
        {
            CpuRegister.clearCarryFlag();
        }
        CpuMemory.write8Bit(addr, tmp);
        CpuFlag.checkZero(tmp);
        CpuFlag.checkNegative(tmp);

        CpuRegister.PC += 2;
    }

    public static void LSR()
    {
        int Value, addr, tmp;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;

        Value = CpuMemory.read8Bit(addr);
        tmp = Value >> 1;
        if((Value & 1) == 1)
        {
            CpuRegister.setCarryFlag();
        }
        else
        {
            CpuRegister.clearCarryFlag();
        }
        CpuMemory.write8Bit(addr, tmp);
        CpuFlag.checkZero(tmp);
        CpuFlag.checkNegative(tmp);

        CpuRegister.PC += 2;
    }

    public static void ROR()
    {
        int tmp, Value, addr, memValue;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;
        memValue = CpuMemory.read8Bit(addr);
        tmp = CpuRegister.getCarryFlag() << 7;
        if((memValue & 1) == 1)
        {
            CpuRegister.setCarryFlag();
        }
        else
        {
            CpuRegister.clearCarryFlag();
        }
        Value = (memValue >> 1) | tmp;
        CpuMemory.write8Bit(addr, Value);
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        CpuRegister.PC += 2;
    }

    public static void INC()
    {
        int Value, addr;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;
        Value = (CpuMemory.read8Bit(addr) + 1) & 0xff;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuMemory.write8Bit(addr, Value);

        CpuRegister.PC += 2;
    }

    public static void DEC()
    {
        int addr, Value;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;
        Value = CpuMemory.read8Bit(addr) - 1;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuMemory.write8Bit(addr, Value);
        CpuRegister.PC += 2;
    }

    public static void LDY()
    {
        int Value, addr;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;
        Value =  CpuMemory.read8Bit(addr);
        CpuRegister.Y = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        CpuRegister.PC += 2;
    }

    public static void AND()
    {
        int Value, addr;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;
        Value = CpuRegister.A & CpuMemory.read8Bit(addr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuRegister.PC += 2;
    }

    public static void EOR()
    {
        int Value, addr;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;
        Value = CpuRegister.A ^ CpuMemory.read8Bit(addr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuRegister.PC += 2;
    }

    public static void ORA()
    {
        int Value, addr;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;
        Value = CpuRegister.A | CpuMemory.read8Bit(addr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuRegister.PC += 2;
    }

    public static void CMP()
    {
        int Value, addr, tmp;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;
        Value = CpuMemory.read8Bit(addr);
        //check for Carry Flag...
        if(CpuRegister.A >= Value)
        {
            CpuRegister.setCarryFlag();
        }
        else
        {
            CpuRegister.clearCarryFlag();
        }
        //Check for ZERO Flag...
        if(CpuRegister.A == Value)
        {
            CpuRegister.setZeroFlag();
        }
        else
        {
            CpuRegister.clearZeroFlag();
        }

        tmp = CpuRegister.A - Value;
        //Check for Negative Flag...
        CpuFlag.checkNegative(tmp);

        CpuRegister.PC += 2;
    }
}

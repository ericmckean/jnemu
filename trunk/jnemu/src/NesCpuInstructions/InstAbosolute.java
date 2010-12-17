package NesCpuInstructions;

import NesCpu.CpuRegister;
import NesCpu.CpuMemory;
import NesCpu.CpuFlag;
import NesCpu.CpuStack;

public class InstAbosolute
{
    public static void ADC()
    {
        int tmp, Value;
        
        Value = CpuMemory.read8Bit(InstAddress.get16BitAddressOperand());
        tmp = CpuRegister.A + Value + CpuRegister.getCarryFlag();
        CpuFlag.checkOverflow(CpuRegister.A, Value, (tmp & 0xff));
        CpuFlag.checkZero(tmp & 0xff);
        CpuFlag.checkNegative(tmp & 0xff);
        CpuFlag.checkCarry(tmp);
        CpuRegister.A = tmp & 0xff;

        CpuRegister.PC += 3;
    }

    public static void STA()
    {
        CpuMemory.write8Bit(InstAddress.get16BitAddressOperand(), CpuRegister.A);
        CpuRegister.PC += 3;
    }

    public static void LDA()
    {
        int Value;

        Value = CpuMemory.read8Bit(InstAddress.get16BitAddressOperand());
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        CpuRegister.PC += 3;
    }

    public static void JSR()
    {
        int pc;

        pc = CpuRegister.PC + 2;
        CpuStack.Push((pc >> 8) & 0xFF);   //MSB
        CpuStack.Push(pc & 0xFF);          //LSB
        CpuRegister.PC = InstAddress.get16BitAddressOperand();
    }

    public static void STY()
    {
        CpuMemory.write8Bit(InstAddress.get16BitAddressOperand(), CpuRegister.Y);
        CpuRegister.PC += 3;
    }

    public static void ROL()
    {
        int Value, addr, tmp;

        addr = InstAddress.get16BitAddressOperand();
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
        CpuRegister.PC += 3;
    }

    public static void JMP()
    {
        CpuRegister.PC = InstAddress.get16BitAddressOperand();
        //Does not increment PC.....
    }

    public static void LDX()
    {
        int Value;

        Value = CpuMemory.read8Bit(InstAddress.get16BitAddressOperand());
        CpuRegister.X = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        CpuRegister.PC += 3;
    }

    public static void STX()
    {
        CpuMemory.write8Bit(InstAddress.get16BitAddressOperand(), CpuRegister.X);
        CpuRegister.PC += 3;
    }

    public static void SBC()
    {
        int tmp, Value;

        Value = CpuMemory.read8Bit(InstAddress.get16BitAddressOperand());
        tmp = CpuRegister.A - Value - ((CpuRegister.getCarryFlag()==1) ? 0 : 1);
        CpuFlag.checkOverflowSbc(CpuRegister.A, Value, tmp);
        CpuFlag.checkZero(tmp);
        CpuFlag.checkNegative(tmp);
        CpuFlag.checkCarrySbc(CpuRegister.A, Value);
        CpuRegister.A = tmp & 0xFF;

        CpuRegister.PC += 3;
    }

    public static void ASL()
    {
        int Value, addr, tmp;

        addr = InstAddress.get16BitAddressOperand();

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

        CpuRegister.PC += 3;
    }

    public static void LSR()
    {
        int Value, addr, tmp;

        addr = InstAddress.get16BitAddressOperand();

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

        CpuRegister.PC += 3;
    }

    public static void ROR()
    {
        int tmp, Value, addr, memValue;

        addr = InstAddress.get16BitAddressOperand();
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

        CpuRegister.PC += 3;
    }

    public static void INC()
    {
        int Value, addr;

        addr = InstAddress.get16BitAddressOperand();
        Value = (CpuMemory.read8Bit(addr) + 1) & 0xff;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuMemory.write8Bit(addr, Value);

        CpuRegister.PC += 3;
    }

    public static void DEC()
    {
        int addr, Value;

        addr = InstAddress.get16BitAddressOperand();
        Value = CpuMemory.read8Bit(addr) - 1;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuMemory.write8Bit(addr, Value);
        CpuRegister.PC += 3;
    }

    public static void LDY()
    {
        int Value;

        Value = CpuMemory.read8Bit(InstAddress.get16BitAddressOperand());
        CpuRegister.Y = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        CpuRegister.PC += 3;
    }

    public static void AND()
    {
        int Value, addr;

        addr = InstAddress.get16BitAddressOperand();
        Value = CpuRegister.A & CpuMemory.read8Bit(addr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuRegister.PC += 3;
    }

    public static void EOR()
    {
        int Value, addr;

        addr = InstAddress.get16BitAddressOperand();
        Value = CpuRegister.A ^ CpuMemory.read8Bit(addr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuRegister.PC += 3;
    }

    public static void ORA()
    {
        int Value, addr;

        addr = InstAddress.get16BitAddressOperand();
        Value = CpuRegister.A | CpuMemory.read8Bit(addr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuRegister.PC += 3;
    }

    public static void CMP()
    {
        int Value, addr, tmp;

        addr = InstAddress.get16BitAddressOperand();
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

        CpuRegister.PC += 3;
    }

    public static void CPX()
    {
        int Value, addr, tmp;

        addr = InstAddress.get16BitAddressOperand();
        Value = CpuMemory.read8Bit(addr);
        //check for Carry Flag...
        if(CpuRegister.X >= Value)
        {
            CpuRegister.setCarryFlag();
        }
        else
        {
            CpuRegister.clearCarryFlag();
        }
        //Check for ZERO Flag...
        if(CpuRegister.X == Value)
        {
            CpuRegister.setZeroFlag();
        }
        else
        {
            CpuRegister.clearZeroFlag();
        }

        tmp = CpuRegister.X - Value;
        //Check for Negative Flag...
        CpuFlag.checkNegative(tmp);

        CpuRegister.PC += 3;
    }

    public static void CPY()
    {
        int Value, addr, tmp;

        addr = InstAddress.get16BitAddressOperand();
        Value = CpuMemory.read8Bit(addr);
        //check for Carry Flag...
        if(CpuRegister.Y >= Value)
        {
            CpuRegister.setCarryFlag();
        }
        else
        {
            CpuRegister.clearCarryFlag();
        }
        //Check for ZERO Flag...
        if(CpuRegister.Y == Value)
        {
            CpuRegister.setZeroFlag();
        }
        else
        {
            CpuRegister.clearZeroFlag();
        }

        tmp = CpuRegister.Y - Value;
        //Check for Negative Flag...
        CpuFlag.checkNegative(tmp);

        CpuRegister.PC += 3;
    }

    public static void BIT()
    {
        int Value, addr, mem;

        addr = InstAddress.get16BitAddressOperand();
        mem = CpuMemory.read8Bit(addr);
        Value = CpuRegister.A & mem;
        CpuFlag.checkZero(Value);
        //Overflow Flag
        if((mem & 0x40) == 0x40)
        {
            CpuRegister.setOverflowFlag();
        }
        else
        {
            CpuRegister.clearOverflowFlag();
        }
        //Negative Flag
        if((mem & 0x80) == 0x80)
        {
            CpuRegister.setNegativeFlag();
        }
        else
        {
            CpuRegister.clearNegativeFlag();
        }

        CpuRegister.PC += 3;
    }
}

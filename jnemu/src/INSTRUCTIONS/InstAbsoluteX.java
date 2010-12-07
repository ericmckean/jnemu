package INSTRUCTIONS;

import CPU.CpuRegister;
import CPU.CpuMemory;
import CPU.CpuFlag;

public class InstAbsoluteX
{
    public static int ADC()
    {
        int tmp, Value, oldAddr, newAddr, retCycle;

        oldAddr = InstAddress.get16BitAddressOperand();
        newAddr =  (oldAddr + CpuRegister.X) & 0xFFFF;

        Value = CpuMemory.read8Bit(newAddr);
        tmp = CpuRegister.A + Value + CpuRegister.getCarryFlag();
        CpuFlag.checkOverflow(CpuRegister.A, Value, (tmp & 0xff));
        CpuFlag.checkZero(tmp & 0xff);
        CpuFlag.checkNegative(tmp & 0xff);
        CpuFlag.checkCarry(tmp);
        CpuRegister.A = tmp & 0xff;

        if(CpuMemory.getPage(oldAddr) != CpuMemory.getPage(newAddr))
        {
            retCycle = 5;
        }
        else
        {
            retCycle = 4;
        }

        CpuRegister.PC += 3;
        return retCycle;
    }

    public static int LDA()
    {
        int Value, cycle = 0, oldAddr, newAddr;

        oldAddr = InstAddress.get16BitAddressOperand();
        newAddr = InstAddress.get16BitAddressOperand() + CpuRegister.X;

        Value = CpuMemory.read8Bit((InstAddress.get16BitAddressOperand() + CpuRegister.X) & 0xFFFF);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        if(CpuMemory.getPage(newAddr) != CpuMemory.getPage(oldAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 4;
        }
        CpuRegister.PC += 3;
        return cycle;
    }

    public static void ROL()
    {
        int Value, addr, tmp;

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.X) & 0xFFFF;
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

    public static void STA()
    {
        CpuMemory.write8Bit((InstAddress.get16BitAddressOperand() + CpuRegister.X) & 0xFFFF, CpuRegister.A);
        CpuRegister.PC += 3;
    }

    public static int SBC()
    {
        int tmp, Value, oldAddr, newAddr, retCycle;

        oldAddr = InstAddress.get16BitAddressOperand();
        newAddr =  (oldAddr + CpuRegister.X) & 0xFFFF;

        Value = CpuMemory.read8Bit(newAddr);
        tmp = CpuRegister.A - Value - ((CpuRegister.getCarryFlag()==1) ? 0 : 1);
        CpuFlag.checkOverflowSbc(CpuRegister.A, Value, tmp);
        CpuFlag.checkZero(tmp);
        CpuFlag.checkNegative(tmp);
        CpuFlag.checkCarrySbc(CpuRegister.A, Value);
        CpuRegister.A = tmp & 0xFF;

        if(CpuMemory.getPage(oldAddr) != CpuMemory.getPage(newAddr))
        {
            retCycle = 5;
        }
        else
        {
            retCycle = 4;
        }

        CpuRegister.PC += 3;
        return retCycle;
    }

    public static void ASL()
    {
        int Value, addr, tmp;

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.X) & 0xFFFF;

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

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.X) & 0xFFFF;

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

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.X) & 0xFFFF;
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

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.X) & 0xFFFF;
        Value = (CpuMemory.read8Bit(addr) + 1) & 0xff;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuMemory.write8Bit(addr, Value);

        CpuRegister.PC += 3;
    }

    public static void DEC()
    {
        int addr, Value;

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.X) & 0xFFFF;
        Value = CpuMemory.read8Bit(addr) - 1;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuMemory.write8Bit(addr, Value);
        CpuRegister.PC += 3;
    }

    public static int LDY()
    {
        int Value, cycle = 0, oldAddr, newAddr;

        oldAddr = InstAddress.get16BitAddressOperand();
        newAddr = InstAddress.get16BitAddressOperand() + CpuRegister.X;

        Value = CpuMemory.read8Bit((InstAddress.get16BitAddressOperand() + CpuRegister.X) & 0xFFFF);
        CpuRegister.Y = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        if(CpuMemory.getPage(newAddr) != CpuMemory.getPage(oldAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 4;
        }
        CpuRegister.PC += 3;
        return cycle;
    }

    public static int AND()
    {
        int Value, oldAddr, newAddr, cycle = 0;

        oldAddr = InstAddress.get16BitAddressOperand();
        newAddr = InstAddress.get16BitAddressOperand() + CpuRegister.X;

        Value = CpuRegister.A & CpuMemory.read8Bit(newAddr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        if(CpuMemory.getPage(newAddr) != CpuMemory.getPage(oldAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 4;
        }
        CpuRegister.PC += 3;
        return cycle;
    }

    public static int EOR()
    {
        int Value, oldAddr, newAddr, cycle = 0;

        oldAddr = InstAddress.get16BitAddressOperand();
        newAddr = InstAddress.get16BitAddressOperand() + CpuRegister.X;

        Value = CpuRegister.A ^ CpuMemory.read8Bit(newAddr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        if(CpuMemory.getPage(newAddr) != CpuMemory.getPage(oldAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 4;
        }
        CpuRegister.PC += 3;
        return cycle;
    }

    public static int ORA()
    {
        int Value, oldAddr, newAddr, cycle = 0;

        oldAddr = InstAddress.get16BitAddressOperand();
        newAddr = InstAddress.get16BitAddressOperand() + CpuRegister.X;

        Value = CpuRegister.A | CpuMemory.read8Bit(newAddr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        if(CpuMemory.getPage(newAddr) != CpuMemory.getPage(oldAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 4;
        }
        CpuRegister.PC += 3;
        return cycle;
    }

    public static int CMP()
    {
        int Value, oldAddr, newAddr, cycle = 0, tmp;

        oldAddr = InstAddress.get16BitAddressOperand();
        newAddr = InstAddress.get16BitAddressOperand() + CpuRegister.X;

        Value = CpuMemory.read8Bit(newAddr);
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

        if(CpuMemory.getPage(newAddr) != CpuMemory.getPage(oldAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 4;
        }
        CpuRegister.PC += 3;
        return cycle;
    }
}

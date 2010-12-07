package INSTRUCTIONS;

import CPU.CpuRegister;
import CPU.CpuMemory;
import CPU.CpuFlag;

public class InstAbsoluteY
{
    public static int ADC()
    {
        int tmp, Value, oldAddr, newAddr, retCycle;

        oldAddr = InstAddress.get16BitAddressOperand();
        newAddr =  (oldAddr + CpuRegister.Y) & 0xFFFF;

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

    public static void STA()
    {
        CpuMemory.write8Bit((InstAddress.get16BitAddressOperand() + CpuRegister.Y) & 0xFFFF, CpuRegister.A);
        CpuRegister.PC += 3;
    }

    public static int LDA()
    {
        int Value, cycle = 0, oldAddr, newAddr;

        oldAddr = InstAddress.get16BitAddressOperand();
        newAddr = InstAddress.get16BitAddressOperand() + CpuRegister.Y;

        Value = CpuMemory.read8Bit(newAddr & 0xFFFF);
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

    public static int SBC()
    {
        int tmp, Value, oldAddr, newAddr, retCycle;

        oldAddr = InstAddress.get16BitAddressOperand();
        newAddr =  (oldAddr + CpuRegister.Y) & 0xFFFF;

        Value = CpuMemory.read8Bit(newAddr);
        tmp = CpuRegister.A - Value- ((CpuRegister.getCarryFlag()==1) ? 0 : 1);
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

    public static int LDX()
    {
        int Value, cycle = 0, oldAddr, newAddr;

        oldAddr = InstAddress.get16BitAddressOperand();
        newAddr = InstAddress.get16BitAddressOperand() + CpuRegister.Y;

        Value = CpuMemory.read8Bit(newAddr & 0xFFFF);
        CpuRegister.X = Value;
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
        newAddr = InstAddress.get16BitAddressOperand() + CpuRegister.Y;

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
        newAddr = InstAddress.get16BitAddressOperand() + CpuRegister.Y;

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
        newAddr = InstAddress.get16BitAddressOperand() + CpuRegister.Y;

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
        newAddr = InstAddress.get16BitAddressOperand() + CpuRegister.Y;

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

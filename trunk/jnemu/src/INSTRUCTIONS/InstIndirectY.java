package INSTRUCTIONS;

import CPU.CpuMemory;
import CPU.CpuRegister;
import CPU.CpuFlag;

public class InstIndirectY
{
    public static void STA()
    {
        int addr, LSB, MSB;

        LSB = CpuMemory.fastRead8Bit(InstAddress.get8BitAddressOperand());
        MSB = CpuMemory.fastRead8Bit((InstAddress.get8BitAddressOperand() + 1) & 0xff);
        addr = (((MSB << 8) | LSB) + CpuRegister.Y) & 0xffff;

        CpuMemory.write8Bit(addr, CpuRegister.A);
        CpuRegister.PC += 2;
    }

    public static int ADC()
    {
        int tmp, Value, oldAddr, newAddr, MSB, LSB, cycle = 0;

        MSB = CpuMemory.fastRead8Bit((InstAddress.get8BitAddressOperand() + 1) & 0xff);
        LSB = CpuMemory.fastRead8Bit(InstAddress.get8BitAddressOperand());
        oldAddr = ((MSB << 8) | LSB);
        newAddr = (((MSB << 8) | LSB) + CpuRegister.Y) & 0xffff;

        Value = CpuMemory.read8Bit(newAddr);
        tmp = CpuRegister.A + Value + CpuRegister.getCarryFlag();
        CpuFlag.checkOverflow(CpuRegister.A, Value, (tmp & 0xff));
        CpuFlag.checkZero(tmp & 0xff);
        CpuFlag.checkNegative(tmp & 0xff);
        CpuFlag.checkCarry(tmp);
        CpuRegister.A = tmp & 0xff;

        if(CpuMemory.getPage(oldAddr) == CpuMemory.getPage(newAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 6;
        }

        CpuRegister.PC += 2;
        return cycle;
    }

    public static int SBC()
    {
        int tmp, Value, oldAddr, newAddr, MSB, LSB, cycle = 0;

        MSB = CpuMemory.fastRead8Bit((InstAddress.get8BitAddressOperand() + 1) & 0xff);
        LSB = CpuMemory.fastRead8Bit(InstAddress.get8BitAddressOperand());
        oldAddr = ((MSB << 8) | LSB);
        newAddr = (oldAddr + CpuRegister.Y) & 0xffff;

        Value = CpuMemory.read8Bit(newAddr);
        tmp = CpuRegister.A - Value - ((CpuRegister.getCarryFlag()==1) ? 0 : 1);
        CpuFlag.checkOverflowSbc(CpuRegister.A, Value, tmp);
        CpuFlag.checkZero(tmp);
        CpuFlag.checkNegative(tmp);
        CpuFlag.checkCarrySbc(CpuRegister.A, Value);
        CpuRegister.A = tmp & 0xFF;

        if(CpuMemory.getPage(oldAddr) == CpuMemory.getPage(newAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 6;
        }

        CpuRegister.PC += 2;
        return cycle;
    }

    public static int LDA()
    {
        int Value, MSB, LSB, oldAddr, newAddr, cycle = 0;

        MSB = CpuMemory.fastRead8Bit((InstAddress.get8BitAddressOperand() + 1) & 0xff);
        LSB = CpuMemory.fastRead8Bit(InstAddress.get8BitAddressOperand());
        oldAddr = ((MSB << 8) | LSB);
        newAddr = (oldAddr + CpuRegister.Y) & 0xffff;

        Value =  CpuMemory.read8Bit(newAddr);

        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        if(CpuMemory.getPage(oldAddr) == CpuMemory.getPage(newAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 6;
        }

        CpuRegister.PC += 2;
        return cycle;
    }

    public static int AND()
    {
        int Value, oldAddr, newAddr, MSB, LSB, cycle = 0;

        MSB = CpuMemory.fastRead8Bit((InstAddress.get8BitAddressOperand() + 1) & 0xff);
        LSB = CpuMemory.fastRead8Bit(InstAddress.get8BitAddressOperand());
        oldAddr = ((MSB << 8) | LSB);
        newAddr = (((MSB << 8) | LSB) + CpuRegister.Y) & 0xffff;

        Value = CpuRegister.A & CpuMemory.read8Bit(newAddr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        if(CpuMemory.getPage(oldAddr) == CpuMemory.getPage(newAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 6;
        }

        CpuRegister.PC += 2;
        return cycle;
    }

    public static int EOR()
    {
        int Value, oldAddr, newAddr, MSB, LSB, cycle = 0;

        MSB = CpuMemory.fastRead8Bit((InstAddress.get8BitAddressOperand() + 1) & 0xff);
        LSB = CpuMemory.fastRead8Bit(InstAddress.get8BitAddressOperand());
        oldAddr = ((MSB << 8) | LSB);
        newAddr = (((MSB << 8) | LSB) + CpuRegister.Y) & 0xffff;

        Value = CpuRegister.A ^ CpuMemory.read8Bit(newAddr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        if(CpuMemory.getPage(oldAddr) == CpuMemory.getPage(newAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 6;
        }

        CpuRegister.PC += 2;
        return cycle;
    }

    public static int ORA()
    {
        int Value, oldAddr, newAddr, MSB, LSB, cycle = 0;

        MSB = CpuMemory.fastRead8Bit((InstAddress.get8BitAddressOperand() + 1) & 0xff);
        LSB = CpuMemory.fastRead8Bit(InstAddress.get8BitAddressOperand());
        oldAddr = ((MSB << 8) | LSB);
        newAddr = (((MSB << 8) | LSB) + CpuRegister.Y) & 0xffff;

        Value = CpuRegister.A | CpuMemory.read8Bit(newAddr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        if(CpuMemory.getPage(oldAddr) == CpuMemory.getPage(newAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 6;
        }

        CpuRegister.PC += 2;
        return cycle;
    }

    public static int CMP()
    {
        int Value, oldAddr, newAddr, MSB, LSB, cycle = 0, tmp;

        MSB = CpuMemory.fastRead8Bit((InstAddress.get8BitAddressOperand() + 1) & 0xff);
        LSB = CpuMemory.fastRead8Bit(InstAddress.get8BitAddressOperand());
        oldAddr = ((MSB << 8) | LSB);
        newAddr = (((MSB << 8) | LSB) + CpuRegister.Y) & 0xffff;

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

        if(CpuMemory.getPage(oldAddr) == CpuMemory.getPage(newAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 6;
        }

        CpuRegister.PC += 2;
        return cycle;
    }
}

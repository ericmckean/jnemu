package INSTRUCTIONS;

import CPU.CpuMemory;
import CPU.CpuRegister;
import CPU.CpuFlag;

public class InstIndirectX
{
    public static void STA()
    {
        int addr, LSB, MSB;

        LSB = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        MSB = CpuMemory.read8Bit(((CpuMemory.read8Bit(CpuRegister.PC + 1) + CpuRegister.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;
        
        CpuMemory.write8Bit(addr, CpuRegister.A);
        CpuRegister.PC += 2;
    }

    public static void ADC()
    {
        int Value, addr, LSB, MSB, tmp;

        LSB = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        MSB = CpuMemory.read8Bit(((CpuMemory.read8Bit(CpuRegister.PC + 1) + CpuRegister.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;

        Value = CpuMemory.read8Bit(addr);
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
        int Value, addr, LSB, MSB, tmp;

        LSB = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        MSB = CpuMemory.read8Bit(((CpuMemory.read8Bit(CpuRegister.PC + 1) + CpuRegister.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;

        Value = CpuMemory.read8Bit(addr);
        tmp = CpuRegister.A - Value - ((CpuRegister.getCarryFlag()==1) ? 0 : 1);
        CpuFlag.checkOverflowSbc(CpuRegister.A, Value, tmp);
        CpuFlag.checkZero(tmp);
        CpuFlag.checkNegative(tmp);
        CpuFlag.checkCarrySbc(CpuRegister.A, Value);
        CpuRegister.A = tmp & 0xFF;

        CpuRegister.PC += 2;
    }

    public static void LDA()
    {
        int Value, addr, LSB, MSB;

        LSB = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        MSB = CpuMemory.read8Bit(((CpuMemory.read8Bit(CpuRegister.PC + 1) + CpuRegister.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;

        Value =  CpuMemory.read8Bit(addr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        CpuRegister.PC += 2;
    }

    public static void AND()
    {
        int Value, addr, LSB, MSB;

        LSB = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        MSB = CpuMemory.read8Bit(((CpuMemory.read8Bit(CpuRegister.PC + 1) + CpuRegister.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;
        
        Value = CpuRegister.A & CpuMemory.read8Bit(addr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuRegister.PC += 2;
    }

    public static void EOR()
    {
        int Value, addr, LSB, MSB;

        LSB = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        MSB = CpuMemory.read8Bit(((CpuMemory.read8Bit(CpuRegister.PC + 1) + CpuRegister.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;

        Value = CpuRegister.A ^ CpuMemory.read8Bit(addr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuRegister.PC += 2;
    }

    public static void ORA()
    {
        int Value, addr, LSB, MSB;

        LSB = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        MSB = CpuMemory.read8Bit(((CpuMemory.read8Bit(CpuRegister.PC + 1) + CpuRegister.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;

        Value = CpuRegister.A | CpuMemory.read8Bit(addr);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuRegister.PC += 2;
    }

    public static void CMP()
    {
        int Value, addr, LSB, MSB, tmp;

       LSB = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        MSB = CpuMemory.read8Bit(((CpuMemory.read8Bit(CpuRegister.PC + 1) + CpuRegister.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;
        
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

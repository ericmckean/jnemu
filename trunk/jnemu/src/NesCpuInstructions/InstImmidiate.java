package NesCpuInstructions;

import NesCpu.CpuRegister;
import NesCpu.CpuMemory;
import NesCpu.CpuFlag;

public class InstImmidiate
{
    public static void ADC()
    {
        int tmp, Value;

        Value =  CpuMemory.read8Bit(CpuRegister.PC + 1);
        tmp = CpuRegister.A + Value + CpuRegister.getCarryFlag();
        CpuFlag.checkOverflow(CpuRegister.A, Value, (tmp & 0xff));
        CpuFlag.checkZero(tmp & 0xff);
        CpuFlag.checkNegative(tmp & 0xff);
        CpuFlag.checkCarry(tmp);
        CpuRegister.A = tmp & 0xff;

        CpuRegister.PC += 2;
    }

    public static void LDA()
    {
        int Value;

        Value =  CpuMemory.read8Bit(CpuRegister.PC + 1);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        CpuRegister.PC += 2;
    }

    public static void LDX()
    {
        int Value;

        Value =  CpuMemory.read8Bit(CpuRegister.PC + 1);
        CpuRegister.X = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        CpuRegister.PC += 2;
    }

    public static void LDY()
    {
        int Value;

        Value =  CpuMemory.read8Bit(CpuRegister.PC + 1);
        CpuRegister.Y = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        CpuRegister.PC += 2;
    }

    public static void CMP()
    {
        int Value, tmp;
        
        Value = CpuMemory.read8Bit(CpuRegister.PC + 1);
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

    public static void AND()
    {
        int Value;

        Value = CpuRegister.A & CpuMemory.read8Bit(CpuRegister.PC + 1);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuRegister.PC += 2;
    }

    public static void SBC()
    {
        int tmp, Value;

        Value =  CpuMemory.read8Bit(CpuRegister.PC + 1);
        tmp = CpuRegister.A - Value - ((CpuRegister.getCarryFlag()==1) ? 0 : 1);
        CpuFlag.checkOverflowSbc(CpuRegister.A, Value, tmp);
        CpuFlag.checkZero(tmp);
        CpuFlag.checkNegative(tmp & 0xff);
        CpuFlag.checkCarrySbc(CpuRegister.A, Value);
        CpuRegister.A = tmp & 0xFF;

        CpuRegister.PC += 2;
    }

    public static void EOR()
    {
        int Value;

        Value = CpuRegister.A ^ CpuMemory.read8Bit(CpuRegister.PC + 1);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuRegister.PC += 2;
    }

    public static void ORA()
    {
        int Value;

        Value = CpuRegister.A | CpuMemory.read8Bit(CpuRegister.PC + 1);
        CpuRegister.A = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);
        CpuRegister.PC += 2;
    }

    public static void CPX()
    {
        int Value, tmp;

        Value = CpuMemory.read8Bit(CpuRegister.PC + 1);
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

        CpuRegister.PC += 2;
    }

    public static void CPY()
    {
        int Value, tmp;

        Value = CpuMemory.read8Bit(CpuRegister.PC + 1);
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

        CpuRegister.PC += 2;
    }

}

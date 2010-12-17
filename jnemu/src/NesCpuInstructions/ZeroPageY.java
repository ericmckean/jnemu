package NesCpuInstructions;

import NesCpu.CpuMemory;
import NesCpu.CpuRegister;
import NesCpu.CpuFlag;

public class ZeroPageY
{
    public static void STX()
    {
        CpuMemory.write8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.Y) & 0xFF, CpuRegister.X);
        CpuRegister.PC += 2;
    }

    public static void LDX()
    {
        int Value, addr;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.Y) & 0xFF;
        Value =  CpuMemory.read8Bit(addr);
        CpuRegister.X = Value;
        CpuFlag.checkZero(Value);
        CpuFlag.checkNegative(Value);

        CpuRegister.PC += 2;
    }
}

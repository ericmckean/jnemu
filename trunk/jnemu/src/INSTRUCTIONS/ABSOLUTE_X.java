package INSTRUCTIONS;

import CPU.CPU_REGISTER;
import CPU.CPU_MEMORY;
import CPU.FLAG;

public class ABSOLUTE_X
{
    public static void ADC()
    {
        int tmp, Value;

        Value = CPU_MEMORY.read8Bit((ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X) & 0xFFFF);
        tmp = CPU_REGISTER.A + Value;
        FLAG.CHECK_OVERFLOW(CPU_REGISTER.A, Value, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY(tmp);
        CPU_REGISTER.A = tmp & 0xFF;

        CPU_REGISTER.PC += 3;
    }

    public static int LDA()
    {
        int Value, cycle = 0, oldAddr, newAddr;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr = ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X;

        Value = CPU_MEMORY.read8Bit((ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X) & 0xFFFF);
        CPU_REGISTER.A = Value;
        FLAG.CHECK_ZERO(Value);
        FLAG.CHECK_NEGATIVE(Value);

        if(MEM_PAGER.getPage(newAddr) != MEM_PAGER.getPage(oldAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 4;
        }
        CPU_REGISTER.PC += 3;
        return cycle;
    }

    public static void ROL()
    {
        int Value, addr;

        addr = (ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X) & 0xFFFF;
        Value = (CPU_MEMORY.read8Bit(addr) << 1) | CPU_REGISTER.getCarryFlag();
        FLAG.CHECK_CARRY(CPU_MEMORY.read8Bit(addr));
        CPU_MEMORY.write8Bit(addr, Value);
        FLAG.CHECK_NEGATIVE(Value);
        FLAG.CHECK_ZERO(Value);
        CPU_REGISTER.PC += 3;
    }
}

package INSTRUCTIONS;

import CPU.CPU_REGISTER;
import CPU.CPU_MEMORY;
import CPU.FLAG;

public class ABSOLUTE_Y
{
    public static int ADC()
    {
        int tmp, Value, oldAddr, newAddr, retCycle;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr =  (oldAddr + CPU_REGISTER.Y) & 0xFFFF;

        Value = CPU_MEMORY.read8Bit(newAddr);
        tmp = CPU_REGISTER.A + Value;
        FLAG.CHECK_OVERFLOW(CPU_REGISTER.A, Value, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY(tmp);
        CPU_REGISTER.A = tmp & 0xFF;

        if(CPU_MEMORY.getPage(oldAddr) != CPU_MEMORY.getPage(newAddr))
        {
            retCycle = 5;
        }
        else
        {
            retCycle = 4;
        }

        CPU_REGISTER.PC += 3;
        return retCycle;
    }
}

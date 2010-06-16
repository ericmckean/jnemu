package INSTRUCTIONS;

import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;
import CPU.FLAG;

public class INDIRECT_Y
{
    public static void STA()
    {
        int addr, LSB, MSB;

        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        addr = ((MSB << 8) | LSB) + CPU_REGISTER.Y;

        CPU_MEMORY.write8Bit(addr, CPU_REGISTER.A);
        CPU_REGISTER.PC += 2;
    }

    public static int ADC()
    {
        int tmp, Value, oldAddr, newAddr, MSB, LSB, cycle = 0;

        MSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand() + 1);
        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        oldAddr = ((MSB << 8) | LSB);
        newAddr = ((MSB << 8) | LSB) + CPU_REGISTER.Y;

        Value = CPU_MEMORY.read8Bit(newAddr);
        tmp = CPU_REGISTER.A + Value;
        FLAG.CHECK_OVERFLOW(CPU_REGISTER.A, Value, tmp);
        FLAG.CHECK_ZERO(tmp);
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_CARRY(tmp);
        CPU_REGISTER.A = tmp & 0xFF;

        if(CPU_MEMORY.getPage(oldAddr) == CPU_MEMORY.getPage(newAddr))
        {
            cycle = 5;
        }
        else
        {
            cycle = 6;
        }

        CPU_REGISTER.PC += 2;
        return cycle;
    }
}

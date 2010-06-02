package INSTRUCTIONS;

import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;
import MISC.CONVERTER;

public class ADDRESS
{
    public static int get16BitAddressOperand()
    {
        StringBuilder addr = new StringBuilder(2);
        int tmp = 0;

        addr.append(MISC_FUNCTIONS.intTo8BitHexString(CPU_MEMORY.fastRead8Bit(CPU_REGISTER.PC + 2)));
        addr.append(MISC_FUNCTIONS.intTo8BitHexString(CPU_MEMORY.fastRead8Bit(CPU_REGISTER.PC + 1)));
        tmp = CONVERTER.stringHexToInt(addr.toString());

        return tmp;
    }

    public static int get8BitAddressOperand()
    {
        int tmp = 0;
        tmp = CONVERTER.stringHexToInt(MISC_FUNCTIONS.intTo8BitHexString(CPU_MEMORY.fastRead8Bit(CPU_REGISTER.PC + 1)));

        return tmp;
    }
}

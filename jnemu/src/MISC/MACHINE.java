package MISC;

import CPU.CPU_MEMORY;
import DEBUGGER.MISC_FUNCTIONS;

public class MACHINE
{
    public static String getCode(int size, int pc)
    {
        StringBuilder sb = new StringBuilder();

        switch(size)
        {
            case 1 :
                sb.append("     ");
                sb.append(MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.fastRead8Bit(pc)));
                sb.append("                         ");
                break;
            case 2 :
                sb.append("     ");
                sb.append(MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.fastRead8Bit(pc)));
                sb.append("  ");
                sb.append(MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.fastRead8Bit(pc + 1)));
                sb.append("                  ");
                break;
            case 3 :
                sb.append("     ");
                sb.append(MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.fastRead8Bit(pc)));
                sb.append("  ");
                sb.append(MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.fastRead8Bit(pc + 1)));
                sb.append("  ");
                sb.append(MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.fastRead8Bit(pc + 2)));
                sb.append("           ");
                break;
        }
        return sb.toString().toUpperCase();
    }
}

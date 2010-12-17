package EmuMisc;

import NesCpu.CpuMemory;
import EmuDebugger.MiscFunctions;

public class Machine
{
    public static String getCode(int size, int pc)
    {
        StringBuilder sb = new StringBuilder();

        switch(size)
        {
            case 1 :
                sb.append("     ");
                sb.append(MiscFunctions.forceTo8Bit(CpuMemory.fastRead8Bit(pc)));
                sb.append("                         ");
                break;
            case 2 :
                sb.append("     ");
                sb.append(MiscFunctions.forceTo8Bit(CpuMemory.fastRead8Bit(pc)));
                sb.append("  ");
                sb.append(MiscFunctions.forceTo8Bit(CpuMemory.fastRead8Bit(pc + 1)));
                sb.append("                  ");
                break;
            case 3 :
                sb.append("     ");
                sb.append(MiscFunctions.forceTo8Bit(CpuMemory.fastRead8Bit(pc)));
                sb.append("  ");
                sb.append(MiscFunctions.forceTo8Bit(CpuMemory.fastRead8Bit(pc + 1)));
                sb.append("  ");
                sb.append(MiscFunctions.forceTo8Bit(CpuMemory.fastRead8Bit(pc + 2)));
                sb.append("           ");
                break;
        }
        return sb.toString().toUpperCase();
    }
}

package INSTRUCTIONS;

import CPU.*;

public class RELATIVE
{
    public static int BPL()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(CPU_REGISTER.getNegativeFlag() == 0)
        {
            oldPC = CPU_REGISTER.PC; //get the old PC...
            BRANCH.execBranch();
            newPC = CPU_REGISTER.PC; //get the new PC...
            if(MEM_PAGER.getPage(oldPC) == MEM_PAGER.getPage(newPC))
            {
                i = 3;
            }
            else
            {
                i = 4;
            }
        }
        else
        {
            CPU_REGISTER.PC += 2;
            i = 2;
        }
        return i;
    }

    public static int BNE()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(CPU_REGISTER.getZeroFlag() == 0)
        {
            oldPC = CPU_REGISTER.PC; //get the old PC...
            BRANCH.execBranch();
            newPC = CPU_REGISTER.PC; //get the new PC...
            if(MEM_PAGER.getPage(oldPC) == MEM_PAGER.getPage(newPC))
            {
                i = 3;
            }
            else
            {
                i = 4;
            }
        }
        else
        {
            CPU_REGISTER.PC += 2;
            i = 2;
        }
        return i;
    }
}

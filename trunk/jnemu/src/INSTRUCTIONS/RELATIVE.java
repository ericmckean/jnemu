package INSTRUCTIONS;

import CPU.*;

public class RELATIVE
{
    public static int BPL()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(REGISTER.getNegativeFlag() == 0)
        {
            oldPC = REGISTER.PC; //get the old PC...
            BRANCH.execBranch();
            newPC = REGISTER.PC; //get the new PC...
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
            REGISTER.PC += 2;
            i = 2;
        }
        return i;
    }
}

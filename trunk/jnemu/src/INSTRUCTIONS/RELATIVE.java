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
            if(CPU_MEMORY.getPage(oldPC) == CPU_MEMORY.getPage(newPC))
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
            if(CPU_MEMORY.getPage(oldPC) == CPU_MEMORY.getPage(newPC))
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

    public static int BMI()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(CPU_REGISTER.getNegativeFlag() == 1)
        {
            oldPC = CPU_REGISTER.PC; //get the old PC...
            BRANCH.execBranch();
            newPC = CPU_REGISTER.PC; //get the new PC...
            if(CPU_MEMORY.getPage(oldPC) == CPU_MEMORY.getPage(newPC))
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

    public static int BEQ()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(CPU_REGISTER.getZeroFlag() == 1)
        {
            oldPC = CPU_REGISTER.PC; //get the old PC...
            BRANCH.execBranch();
            newPC = CPU_REGISTER.PC; //get the new PC...
            if(CPU_MEMORY.getPage(oldPC) == CPU_MEMORY.getPage(newPC))
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

    public static int BCS()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(CPU_REGISTER.getCarryFlag() == 1)
        {
            oldPC = CPU_REGISTER.PC; //get the old PC...
            BRANCH.execBranch();
            newPC = CPU_REGISTER.PC; //get the new PC...
            if(CPU_MEMORY.getPage(oldPC) == CPU_MEMORY.getPage(newPC))
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

    public static int BCC()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(CPU_REGISTER.getCarryFlag() == 0)
        {
            oldPC = CPU_REGISTER.PC; //get the old PC...
            BRANCH.execBranch();
            newPC = CPU_REGISTER.PC; //get the new PC...
            if(CPU_MEMORY.getPage(oldPC) == CPU_MEMORY.getPage(newPC))
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

    public static int BVC()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(CPU_REGISTER.getOverflowFlag() == 0)
        {
            oldPC = CPU_REGISTER.PC; //get the old PC...
            BRANCH.execBranch();
            newPC = CPU_REGISTER.PC; //get the new PC...
            if(CPU_MEMORY.getPage(oldPC) == CPU_MEMORY.getPage(newPC))
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

    public static int BVS()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(CPU_REGISTER.getOverflowFlag() == 1)
        {
            oldPC = CPU_REGISTER.PC; //get the old PC...
            BRANCH.execBranch();
            newPC = CPU_REGISTER.PC; //get the new PC...
            if(CPU_MEMORY.getPage(oldPC) == CPU_MEMORY.getPage(newPC))
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

package INSTRUCTIONS;

import CPU.*;

public class RELATIVE
{
    public static int BPL()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(CpuRegister.getNegativeFlag() == 0)
        {
            oldPC = CpuRegister.PC; //get the old PC...
            InstBranch.execBranch();
            newPC = CpuRegister.PC; //get the new PC...
            if(CpuMemory.getPage(oldPC) == CpuMemory.getPage(newPC))
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
            CpuRegister.PC += 2;
            i = 2;
        }
        return i;
    }

    public static int BNE()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(CpuRegister.getZeroFlag() == 0)
        {
            oldPC = CpuRegister.PC; //get the old PC...
            InstBranch.execBranch();
            newPC = CpuRegister.PC; //get the new PC...
            if(CpuMemory.getPage(oldPC) == CpuMemory.getPage(newPC))
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
            CpuRegister.PC += 2;
            i = 2;
        }
        return i;
    }

    public static int BMI()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(CpuRegister.getNegativeFlag() == 1)
        {
            oldPC = CpuRegister.PC; //get the old PC...
            InstBranch.execBranch();
            newPC = CpuRegister.PC; //get the new PC...
            if(CpuMemory.getPage(oldPC) == CpuMemory.getPage(newPC))
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
            CpuRegister.PC += 2;
            i = 2;
        }
        return i;
    }

    public static int BEQ()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(CpuRegister.getZeroFlag() == 1)
        {
            oldPC = CpuRegister.PC; //get the old PC...
            InstBranch.execBranch();
            newPC = CpuRegister.PC; //get the new PC...
            if(CpuMemory.getPage(oldPC) == CpuMemory.getPage(newPC))
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
            CpuRegister.PC += 2;
            i = 2;
        }
        return i;
    }

    public static int BCS()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(CpuRegister.getCarryFlag() == 1)
        {
            oldPC = CpuRegister.PC; //get the old PC...
            InstBranch.execBranch();
            newPC = CpuRegister.PC; //get the new PC...
            if(CpuMemory.getPage(oldPC) == CpuMemory.getPage(newPC))
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
            CpuRegister.PC += 2;
            i = 2;
        }
        return i;
    }

    public static int BCC()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(CpuRegister.getCarryFlag() == 0)
        {
            oldPC = CpuRegister.PC; //get the old PC...
            InstBranch.execBranch();
            newPC = CpuRegister.PC; //get the new PC...
            if(CpuMemory.getPage(oldPC) == CpuMemory.getPage(newPC))
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
            CpuRegister.PC += 2;
            i = 2;
        }
        return i;
    }

    public static int BVC()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(CpuRegister.getOverflowFlag() == 0)
        {
            oldPC = CpuRegister.PC; //get the old PC...
            InstBranch.execBranch();
            newPC = CpuRegister.PC; //get the new PC...
            if(CpuMemory.getPage(oldPC) == CpuMemory.getPage(newPC))
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
            CpuRegister.PC += 2;
            i = 2;
        }
        return i;
    }

    public static int BVS()
    {
        int i = 0; //opcode cycle...
        int oldPC, newPC;

        if(CpuRegister.getOverflowFlag() == 1)
        {
            oldPC = CpuRegister.PC; //get the old PC...
            InstBranch.execBranch();
            newPC = CpuRegister.PC; //get the new PC...
            if(CpuMemory.getPage(oldPC) == CpuMemory.getPage(newPC))
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
            CpuRegister.PC += 2;
            i = 2;
        }
        return i;
    }
}

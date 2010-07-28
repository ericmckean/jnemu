package INSTRUCTIONS;

import CPU.CPU_MEMORY;
import CPU.CPU_REGISTER;
import CPU.FLAG;

public class UNOFFICIAL
{
    //****************************************
    //                  NOP
    //****************************************
    public static void _NOP()
    {
        CPU_REGISTER.PC += 1;
    }

    //Double NOP...
    public static void DOP()
    {
        CPU_REGISTER.PC += 2;
    }

    //TRIPPLE NOP ABSOLUTE...
    public static void TOP()
    {
        CPU_REGISTER.PC += 3;
    }

    //TRIPPLE NOP ABSOLUTE, X...
    public static int _TOP()
    {
        int oldAddr, newAddr, retCycle;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr = (oldAddr + CPU_REGISTER.X) & 0xffff;

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

    //****************************************
    //                  AAC
    //****************************************

    public static void AAC() //Immidiate...
    {
        int tmp;

        tmp = CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1);
        CPU_REGISTER.A &= tmp;
        if((CPU_REGISTER.A & 0x80) == 0x80)
        {
            CPU_REGISTER.setCarryFlag();
            CPU_REGISTER.setNegativeFlag();
        }
        else
        {
            CPU_REGISTER.clearCarryFlag();
            CPU_REGISTER.clearNegativeFlag();
        }
        FLAG.CHECK_ZERO(CPU_REGISTER.A);
        CPU_REGISTER.PC += 2;
    }

    //****************************************
    //                  AAX
    //****************************************
    public static void ZP_AAX() //Zero Page
    {
        int addr, tmp;

        addr = ADDRESS.get8BitAddressOperand();
        tmp = (CPU_REGISTER.X & CPU_REGISTER.A) & 0xff;
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_ZERO(tmp);
        CPU_MEMORY.write8Bit(addr, tmp);
        CPU_REGISTER.PC += 2;
    }

    public static void ZP_Y_AAX() //Zero Page, Y
    {
        int addr, tmp;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.Y) & 0xff;
        tmp = (CPU_REGISTER.X & CPU_REGISTER.A) & 0xff;
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_ZERO(tmp);
        CPU_MEMORY.write8Bit(addr, tmp);
        CPU_REGISTER.PC += 2;
    }

    public static void IND_X_AAX() //Indirect X
    {
        int addr, tmp, MSB, LSB;

        LSB = CPU_MEMORY.read8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff);
        MSB = CPU_MEMORY.read8Bit(((CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1) + CPU_REGISTER.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;

        tmp = (CPU_REGISTER.X & CPU_REGISTER.A) & 0xff;
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_ZERO(tmp);
        CPU_MEMORY.write8Bit(addr, tmp);
        CPU_REGISTER.PC += 2;
    }

    public static void ABS_AAX() //Zero Page
    {
        int addr, tmp;

        addr = ADDRESS.get16BitAddressOperand();
        tmp = (CPU_REGISTER.X & CPU_REGISTER.A) & 0xff;
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_ZERO(tmp);
        CPU_MEMORY.write8Bit(addr, tmp);
        CPU_REGISTER.PC += 3;
    }

    //****************************************
    //                  ARR
    //****************************************

    public static void ARR() //Immidiate
    {
        int tmp;

        tmp = CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1);
        CPU_REGISTER.A &= tmp;
        CPU_REGISTER.A >>= 1;
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.A);
        FLAG.CHECK_ZERO(CPU_REGISTER.A);
        if((CPU_REGISTER.A & 0x40) == 0x40 && (CPU_REGISTER.A & 0x20) == 0x20)
        {
            CPU_REGISTER.setCarryFlag();
            CPU_REGISTER.clearOverflowFlag();
        }
        else if((CPU_REGISTER.A & 0x40) == 0 && (CPU_REGISTER.A & 0x20) == 0)
        {
            CPU_REGISTER.clearCarryFlag();
            CPU_REGISTER.clearOverflowFlag();
        }
        else if((CPU_REGISTER.A & 0x40) == 0 && (CPU_REGISTER.A & 0x20) == 0x20)
        {
            CPU_REGISTER.clearCarryFlag();
            CPU_REGISTER.setOverflowFlag();
        }
        else if((CPU_REGISTER.A & 0x40) == 0x40 && (CPU_REGISTER.A & 0x20) == 0)
        {
            CPU_REGISTER.setCarryFlag();
            CPU_REGISTER.setOverflowFlag();
        }
        CPU_REGISTER.PC += 2;
    }

    //****************************************
    //                  ASR
    //****************************************

    public static void ASR() //Immidiate
    {
        int tmp;

        tmp = CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1);
        CPU_REGISTER.A &= tmp;
        CPU_REGISTER.A >>= 1;
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.A);
        FLAG.CHECK_ZERO(CPU_REGISTER.A);
        FLAG.CHECK_CARRY(CPU_REGISTER.A); //FIXME: not sure with this one...
        CPU_REGISTER.PC += 2;
    }

    //****************************************
    //                  ATX
    //****************************************

    public static void ATX()//Immidiate
    {
        int tmp;

        tmp = CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1);
        CPU_REGISTER.A &= tmp;
        CPU_REGISTER.X = CPU_REGISTER.A;
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.X);
        FLAG.CHECK_ZERO(CPU_REGISTER.X);
        CPU_REGISTER.PC += 2;
    }

    //****************************************
    //                  AXA
    //****************************************

    public static void ABS_Y_AXA()//Absolute Y
    {
        int addr;

        addr = (ADDRESS.get16BitAddressOperand() + CPU_REGISTER.Y) & 0xff;
        CPU_REGISTER.X &= CPU_REGISTER.A;
        CPU_REGISTER.X &= 7;
        CPU_MEMORY.write8Bit(addr, CPU_REGISTER.X);
        CPU_REGISTER.PC += 3;
    }

    public static void IND_Y_AXA()//Indirect Y
    {
        int addr, LSB, MSB;

        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        MSB = CPU_MEMORY.fastRead8Bit((ADDRESS.get8BitAddressOperand() + 1) & 0xff);
        addr = (((MSB << 8) | LSB) + CPU_REGISTER.Y) & 0xffff;

        CPU_REGISTER.X &= CPU_REGISTER.A;
        CPU_REGISTER.X &= 7;
        CPU_MEMORY.write8Bit(addr, CPU_REGISTER.X);
        CPU_REGISTER.PC += 2;
    }

    //****************************************
    //                  AXS
    //****************************************

    public static void AXS()//Immidiate
    {
        int tmp;

        CPU_REGISTER.X &= CPU_REGISTER.A;
        tmp = CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1);
        CPU_REGISTER.X -= tmp;
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.X);
        FLAG.CHECK_ZERO(CPU_REGISTER.X);
        FLAG.CHECK_CARRY_SBC(CPU_REGISTER.X, tmp);
        CPU_REGISTER.PC += 2;
    }

    //****************************************
    //                  DCP
    //****************************************

    public static void ZP_DCP()//Zero page
    {
        int addr, tmp, Value;

        addr = ADDRESS.get8BitAddressOperand();
        Value = CPU_MEMORY.read8Bit(addr);
        tmp = Value--;
        CPU_MEMORY.write8Bit(addr, tmp);
        FLAG.CHECK_CARRY_SBC(Value, 1);
        CPU_REGISTER.PC += 2;
    }

    public static void ZP_X_DCP()//Zero page, X
    {
        int addr, tmp, Value;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff;
        Value = CPU_MEMORY.read8Bit(addr);
        tmp = Value--;
        CPU_MEMORY.write8Bit(addr, tmp);
        FLAG.CHECK_CARRY_SBC(Value, 1);
        CPU_REGISTER.PC += 2;
    }

    public static void ABS_DCP()//Absolute
    {
        int addr, tmp, Value;

        addr = ADDRESS.get16BitAddressOperand();
        Value = CPU_MEMORY.read8Bit(addr);
        tmp = Value--;
        CPU_MEMORY.write8Bit(addr, tmp);
        FLAG.CHECK_CARRY_SBC(Value, 1);
        CPU_REGISTER.PC += 3;
    }

    public static void ABS_X_DCP()//Absolute, X
    {
        int addr, tmp, Value;

        addr = (ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X) & 0xffff;
        Value = CPU_MEMORY.read8Bit(addr);
        tmp = Value--;
        CPU_MEMORY.write8Bit(addr, tmp);
        FLAG.CHECK_CARRY_SBC(Value, 1);
        CPU_REGISTER.PC += 3;
    }

    public static void ABS_Y_DCP()//Absolute, Y
    {
        int addr, tmp, Value;

        addr = (ADDRESS.get16BitAddressOperand() + CPU_REGISTER.Y) & 0xffff;
        Value = CPU_MEMORY.read8Bit(addr);
        tmp = Value--;
        CPU_MEMORY.write8Bit(addr, tmp);
        FLAG.CHECK_CARRY_SBC(Value, 1);
        CPU_REGISTER.PC += 3;
    }

    public static void IND_X_DCP()//Indirect X
    {
        int addr, tmp, Value, MSB, LSB;

        LSB = CPU_MEMORY.read8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff);
        MSB = CPU_MEMORY.read8Bit(((CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1) + CPU_REGISTER.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;

        Value = CPU_MEMORY.read8Bit(addr);
        tmp = Value--;
        CPU_MEMORY.write8Bit(addr, tmp);
        FLAG.CHECK_CARRY_SBC(Value, 1);
        CPU_REGISTER.PC += 2;
    }

    public static void IND_Y_DCP()//Indirect Y
    {
        int addr, tmp, Value, MSB, LSB;

        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        MSB = CPU_MEMORY.fastRead8Bit((ADDRESS.get8BitAddressOperand() + 1) & 0xff);
        addr = (((MSB << 8) | LSB) + CPU_REGISTER.Y) & 0xffff;

        Value = CPU_MEMORY.read8Bit(addr);
        tmp = Value--;
        CPU_MEMORY.write8Bit(addr, tmp);
        FLAG.CHECK_CARRY_SBC(Value, 1);
        CPU_REGISTER.PC += 2;
    }

    //****************************************
    //                  ISC
    //****************************************

    public static void ZP_ISC()//Zero page
    {
        int tmp, Value, addr;

        addr = ADDRESS.get8BitAddressOperand();
        Value = CPU_MEMORY.read8Bit(addr);
        Value++;
        tmp = CPU_REGISTER.A - Value - ((CPU_REGISTER.getCarryFlag()==1) ? 0 : 1);

        FLAG.CHECK_NEGATIVE(CPU_REGISTER.A);
        FLAG.CHECK_OVERFLOW(Value, tmp, CPU_REGISTER.A);
        FLAG.CHECK_ZERO(CPU_REGISTER.A);
        FLAG.CHECK_CARRY_SBC(CPU_REGISTER.A, Value);

        CPU_REGISTER.PC += 2;
    }

    public static void ZP_X_ISC()//Zero page, X
    {
        int tmp, Value, addr;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff;
        Value = CPU_MEMORY.read8Bit(addr);
        Value++;
        tmp = CPU_REGISTER.A - Value - ((CPU_REGISTER.getCarryFlag()==1) ? 0 : 1);

        FLAG.CHECK_NEGATIVE(CPU_REGISTER.A);
        FLAG.CHECK_OVERFLOW(Value, tmp, CPU_REGISTER.A);
        FLAG.CHECK_ZERO(CPU_REGISTER.A);
        FLAG.CHECK_CARRY_SBC(CPU_REGISTER.A, Value);

        CPU_REGISTER.PC += 2;
    }

    public static void ABS_ISC()//Absolute
    {
        int tmp, Value, addr;

        addr = ADDRESS.get16BitAddressOperand();
        Value = CPU_MEMORY.read8Bit(addr);
        Value++;
        tmp = CPU_REGISTER.A - Value - ((CPU_REGISTER.getCarryFlag()==1) ? 0 : 1);

        FLAG.CHECK_NEGATIVE(CPU_REGISTER.A);
        FLAG.CHECK_OVERFLOW(Value, tmp, CPU_REGISTER.A);
        FLAG.CHECK_ZERO(CPU_REGISTER.A);
        FLAG.CHECK_CARRY_SBC(CPU_REGISTER.A, Value);

        CPU_REGISTER.PC += 3;
    }

    public static void ABS_X_ISC()//Absolute X
    {
        int tmp, Value, addr;

        addr = (ADDRESS.get16BitAddressOperand() + CPU_REGISTER.X) & 0xffff;
        Value = CPU_MEMORY.read8Bit(addr);
        Value++;
        tmp = CPU_REGISTER.A - Value - ((CPU_REGISTER.getCarryFlag()==1) ? 0 : 1);

        FLAG.CHECK_NEGATIVE(CPU_REGISTER.A);
        FLAG.CHECK_OVERFLOW(Value, tmp, CPU_REGISTER.A);
        FLAG.CHECK_ZERO(CPU_REGISTER.A);
        FLAG.CHECK_CARRY_SBC(CPU_REGISTER.A, Value);

        CPU_REGISTER.PC += 3;
    }

    public static void ABS_Y_ISC()//Absolute Y
    {
        int tmp, Value, addr;

        addr = (ADDRESS.get16BitAddressOperand() + CPU_REGISTER.Y) & 0xffff;
        Value = CPU_MEMORY.read8Bit(addr);
        Value++;
        tmp = CPU_REGISTER.A - Value - ((CPU_REGISTER.getCarryFlag()==1) ? 0 : 1);

        FLAG.CHECK_NEGATIVE(CPU_REGISTER.A);
        FLAG.CHECK_OVERFLOW(Value, tmp, CPU_REGISTER.A);
        FLAG.CHECK_ZERO(CPU_REGISTER.A);
        FLAG.CHECK_CARRY_SBC(CPU_REGISTER.A, Value);

        CPU_REGISTER.PC += 3;
    }

    public static void IND_X_ISC()//Indirect X
    {
        int tmp, Value, addr, MSB, LSB;

        LSB = CPU_MEMORY.read8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff);
        MSB = CPU_MEMORY.read8Bit(((CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1) + CPU_REGISTER.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;
        
        Value = CPU_MEMORY.read8Bit(addr);
        Value++;
        tmp = CPU_REGISTER.A - Value - ((CPU_REGISTER.getCarryFlag()==1) ? 0 : 1);

        FLAG.CHECK_NEGATIVE(CPU_REGISTER.A);
        FLAG.CHECK_OVERFLOW(Value, tmp, CPU_REGISTER.A);
        FLAG.CHECK_ZERO(CPU_REGISTER.A);
        FLAG.CHECK_CARRY_SBC(CPU_REGISTER.A, Value);

        CPU_REGISTER.PC += 2;
    }

    public static void IND_Y_ISC()//Indirect Y
    {
        int tmp, Value, addr, MSB, LSB;

        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        MSB = CPU_MEMORY.fastRead8Bit((ADDRESS.get8BitAddressOperand() + 1) & 0xff);
        addr = (((MSB << 8) | LSB) + CPU_REGISTER.Y) & 0xffff;

        Value = CPU_MEMORY.read8Bit(addr);
        Value++;
        tmp = CPU_REGISTER.A - Value - ((CPU_REGISTER.getCarryFlag()==1) ? 0 : 1);

        FLAG.CHECK_NEGATIVE(CPU_REGISTER.A);
        FLAG.CHECK_OVERFLOW(Value, tmp, CPU_REGISTER.A);
        FLAG.CHECK_ZERO(CPU_REGISTER.A);
        FLAG.CHECK_CARRY_SBC(CPU_REGISTER.A, Value);

        CPU_REGISTER.PC += 2;
    }

    //****************************************
    //                  LAR
    //****************************************

    public static int LAR()// Absolute, Y
    {
        int oldAddr,newAddr, tmp, retCycle;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr = (oldAddr + CPU_REGISTER.Y) & 0xffff;

        tmp = CPU_MEMORY.read8Bit(newAddr);
        CPU_REGISTER.A = tmp & CPU_REGISTER.SP;
        FLAG.CHECK_NEGATIVE(CPU_REGISTER.A);
        FLAG.CHECK_ZERO(CPU_REGISTER.A);

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

    //****************************************
    //                  LAX
    //****************************************

    public static void ZP_LAX()//Zero page
    {
        int tmp, addr;

        addr = ADDRESS.get8BitAddressOperand();
        tmp = CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = tmp;
        CPU_REGISTER.X = tmp;
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_ZERO(tmp);
        CPU_REGISTER.PC += 2;
    }

    public static void ZP_Y_LAX()//Zero page, Y
    {
        int tmp, addr;

        addr = (ADDRESS.get8BitAddressOperand() + CPU_REGISTER.Y) & 0xff;
        tmp = CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = tmp;
        CPU_REGISTER.X = tmp;
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_ZERO(tmp);
        CPU_REGISTER.PC += 2;
    }

    public static void ABS_LAX()//Absolute
    {
        int tmp, addr;

        addr = ADDRESS.get16BitAddressOperand();
        tmp = CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = tmp;
        CPU_REGISTER.X = tmp;
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_ZERO(tmp);
        CPU_REGISTER.PC += 3;
    }

    public static int ABS_Y_LAX()//Absolute Y
    {
        int tmp, retCycle, oldAddr, newAddr;

        oldAddr = ADDRESS.get16BitAddressOperand();
        newAddr = (oldAddr + CPU_REGISTER.Y) & 0xffff;

        tmp = CPU_MEMORY.read8Bit(newAddr);
        CPU_REGISTER.A = tmp;
        CPU_REGISTER.X = tmp;
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_ZERO(tmp);

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

    public static void IND_X_LAX()//Indirect X
    {
        int tmp, addr, MSB, LSB;

        LSB = CPU_MEMORY.read8Bit((ADDRESS.get8BitAddressOperand() + CPU_REGISTER.X) & 0xff);
        MSB = CPU_MEMORY.read8Bit(((CPU_MEMORY.read8Bit(CPU_REGISTER.PC + 1) + CPU_REGISTER.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;
        
        tmp = CPU_MEMORY.read8Bit(addr);
        CPU_REGISTER.A = tmp;
        CPU_REGISTER.X = tmp;
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_ZERO(tmp);
        CPU_REGISTER.PC += 2;
    }

    public static int IND_Y_LAX()//Indirect Y
    {
        int tmp, oldAddr, newAddr, MSB, LSB, retCycle;

        LSB = CPU_MEMORY.fastRead8Bit(ADDRESS.get8BitAddressOperand());
        MSB = CPU_MEMORY.fastRead8Bit((ADDRESS.get8BitAddressOperand() + 1) & 0xff);
        oldAddr = ((MSB << 8) | LSB);
        newAddr = (oldAddr + CPU_REGISTER.Y) & 0xffff;

        tmp = CPU_MEMORY.read8Bit(newAddr);
        CPU_REGISTER.A = tmp;
        CPU_REGISTER.X = tmp;
        FLAG.CHECK_NEGATIVE(tmp);
        FLAG.CHECK_ZERO(tmp);

        if(CPU_MEMORY.getPage(oldAddr) != CPU_MEMORY.getPage(newAddr))
        {
            retCycle = 6;
        }
        else
        {
            retCycle = 5;
        }
        CPU_REGISTER.PC += 2;
        return retCycle;
    }
}

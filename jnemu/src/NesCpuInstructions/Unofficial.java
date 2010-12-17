package NesCpuInstructions;

import NesCpu.CpuMemory;
import NesCpu.CpuRegister;
import NesCpu.CpuFlag;

public class Unofficial
{
    //****************************************
    //                  NOP
    //****************************************
    public static void _NOP()
    {
        CpuRegister.PC += 1;
    }

    //Double NOP...
    public static void DOP()
    {
        CpuRegister.PC += 2;
    }

    //TRIPPLE NOP ABSOLUTE...
    public static void TOP()
    {
        CpuRegister.PC += 3;
    }

    //TRIPPLE NOP ABSOLUTE, X...
    public static int _TOP()
    {
        int oldAddr, newAddr, retCycle;

        oldAddr = InstAddress.get16BitAddressOperand();
        newAddr = (oldAddr + CpuRegister.X) & 0xffff;

        if(CpuMemory.getPage(oldAddr) != CpuMemory.getPage(newAddr))
        {
            retCycle = 5;
        }
        else
        {
            retCycle = 4;
        }
        CpuRegister.PC += 3;
        return retCycle;
    }

    //****************************************
    //                  AAC
    //****************************************

    public static void AAC() //Immidiate...
    {
        int tmp;

        tmp = CpuMemory.read8Bit(CpuRegister.PC + 1);
        CpuRegister.A &= tmp;
        if((CpuRegister.A & 0x80) == 0x80)
        {
            CpuRegister.setCarryFlag();
            CpuRegister.setNegativeFlag();
        }
        else
        {
            CpuRegister.clearCarryFlag();
            CpuRegister.clearNegativeFlag();
        }
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    //****************************************
    //                  AAX
    //****************************************
    public static void ZP_AAX() //Zero Page
    {
        int addr, tmp;

        addr = InstAddress.get8BitAddressOperand();
        tmp = (CpuRegister.X & CpuRegister.A) & 0xff;
        CpuMemory.write8Bit(addr, tmp);
        CpuRegister.PC += 2;
    }

    public static void ZP_Y_AAX() //Zero Page, Y
    {
        int addr, tmp;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.Y) & 0xff;
        tmp = (CpuRegister.X & CpuRegister.A) & 0xff;
        CpuMemory.write8Bit(addr, tmp);
        CpuRegister.PC += 2;
    }

    public static void IND_X_AAX() //Indirect X
    {
        int addr, tmp, MSB, LSB;

        LSB = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        MSB = CpuMemory.read8Bit(((CpuMemory.read8Bit(CpuRegister.PC + 1) + CpuRegister.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;

        tmp = (CpuRegister.X & CpuRegister.A) & 0xff;
        CpuMemory.write8Bit(addr, tmp);
        CpuRegister.PC += 2;
    }

    public static void ABS_AAX() //Zero Page
    {
        int addr, tmp;

        addr = InstAddress.get16BitAddressOperand();
        tmp = (CpuRegister.X & CpuRegister.A) & 0xff;
        CpuMemory.write8Bit(addr, tmp);
        CpuRegister.PC += 3;
    }

    //****************************************
    //                  ARR
    //****************************************

    public static void ARR() //Immidiate
    {
        int tmp;

        tmp = CpuMemory.read8Bit(CpuRegister.PC + 1);
        CpuRegister.A &= tmp;
        CpuRegister.A >>= 1;
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        if((CpuRegister.A & 0x40) == 0x40 && (CpuRegister.A & 0x20) == 0x20)
        {
            CpuRegister.setCarryFlag();
            CpuRegister.clearOverflowFlag();
        }
        else if((CpuRegister.A & 0x40) == 0 && (CpuRegister.A & 0x20) == 0)
        {
            CpuRegister.clearCarryFlag();
            CpuRegister.clearOverflowFlag();
        }
        else if((CpuRegister.A & 0x40) == 0 && (CpuRegister.A & 0x20) == 0x20)
        {
            CpuRegister.clearCarryFlag();
            CpuRegister.setOverflowFlag();
        }
        else if((CpuRegister.A & 0x40) == 0x40 && (CpuRegister.A & 0x20) == 0)
        {
            CpuRegister.setCarryFlag();
            CpuRegister.setOverflowFlag();
        }
        CpuRegister.PC += 2;
    }

    //****************************************
    //                  ASR
    //****************************************

    public static void ASR() //Immidiate
    {
        int tmp;

        tmp = CpuMemory.read8Bit(CpuRegister.PC + 1);
        CpuRegister.A &= tmp;
        CpuRegister.A >>= 1;
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuFlag.checkCarry(CpuRegister.A); //FIXME: not sure with this one...
        CpuRegister.PC += 2;
    }

    //****************************************
    //                  ATX
    //****************************************

    public static void ATX()//Immidiate
    {
        int tmp;

        tmp = CpuMemory.read8Bit(CpuRegister.PC + 1);
        CpuRegister.A &= tmp;
        CpuRegister.X = CpuRegister.A;
        CpuFlag.checkNegative(CpuRegister.X);
        CpuFlag.checkZero(CpuRegister.X);
        CpuRegister.PC += 2;
    }

    //****************************************
    //                  AXA
    //****************************************

    public static void ABS_Y_AXA()//Absolute Y
    {
        int addr;

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.Y) & 0xff;
        CpuRegister.X &= CpuRegister.A;
        CpuRegister.X &= 7;
        CpuMemory.write8Bit(addr, CpuRegister.X);
        CpuRegister.PC += 3;
    }

    public static void IND_Y_AXA()//Indirect Y
    {
        int addr, LSB, MSB;

        LSB = CpuMemory.fastRead8Bit(InstAddress.get8BitAddressOperand());
        MSB = CpuMemory.fastRead8Bit((InstAddress.get8BitAddressOperand() + 1) & 0xff);
        addr = (((MSB << 8) | LSB) + CpuRegister.Y) & 0xffff;

        CpuRegister.X &= CpuRegister.A;
        CpuRegister.X &= 7;
        CpuMemory.write8Bit(addr, CpuRegister.X);
        CpuRegister.PC += 2;
    }

    //****************************************
    //                  AXS
    //****************************************

    public static void AXS()//Immidiate
    {
        int tmp;

        CpuRegister.X &= CpuRegister.A;
        tmp = CpuMemory.read8Bit(CpuRegister.PC + 1);
        CpuRegister.X -= tmp;
        CpuFlag.checkNegative(CpuRegister.X);
        CpuFlag.checkZero(CpuRegister.X);
        CpuFlag.checkCarrySbc(CpuRegister.X, tmp);
        CpuRegister.PC += 2;
    }

    //****************************************
    //                  DCP
    //****************************************

    public static void ZP_DCP()//Zero page
    {
        int addr, tmp, Value;

        addr = InstAddress.get8BitAddressOperand();
        Value = CpuMemory.read8Bit(addr);
        tmp = Value--;
        CpuMemory.write8Bit(addr, tmp);
        CpuFlag.checkCarrySbc(Value, 1);
        CpuRegister.PC += 2;
    }

    public static void ZP_X_DCP()//Zero page, X
    {
        int addr, tmp, Value;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;
        Value = CpuMemory.read8Bit(addr);
        tmp = Value--;
        CpuMemory.write8Bit(addr, tmp);
        CpuFlag.checkCarrySbc(Value, 1);
        CpuRegister.PC += 2;
    }

    public static void ABS_DCP()//Absolute
    {
        int addr, tmp, Value;

        addr = InstAddress.get16BitAddressOperand();
        Value = CpuMemory.read8Bit(addr);
        tmp = Value--;
        CpuMemory.write8Bit(addr, tmp);
        CpuFlag.checkCarrySbc(Value, 1);
        CpuRegister.PC += 3;
    }

    public static void ABS_X_DCP()//Absolute, X
    {
        int addr, tmp, Value;

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.X) & 0xffff;
        Value = CpuMemory.read8Bit(addr);
        tmp = Value--;
        CpuMemory.write8Bit(addr, tmp);
        CpuFlag.checkCarrySbc(Value, 1);
        CpuRegister.PC += 3;
    }

    public static void ABS_Y_DCP()//Absolute, Y
    {
        int addr, tmp, Value;

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.Y) & 0xffff;
        Value = CpuMemory.read8Bit(addr);
        tmp = Value--;
        CpuMemory.write8Bit(addr, tmp);
        CpuFlag.checkCarrySbc(Value, 1);
        CpuRegister.PC += 3;
    }

    public static void IND_X_DCP()//Indirect X
    {
        int addr, tmp, Value, MSB, LSB;

        LSB = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        MSB = CpuMemory.read8Bit(((CpuMemory.read8Bit(CpuRegister.PC + 1) + CpuRegister.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;

        Value = CpuMemory.read8Bit(addr);
        tmp = Value--;
        CpuMemory.write8Bit(addr, tmp);
        CpuFlag.checkCarrySbc(Value, 1);
        CpuRegister.PC += 2;
    }

    public static void IND_Y_DCP()//Indirect Y
    {
        int addr, tmp, Value, MSB, LSB;

        LSB = CpuMemory.fastRead8Bit(InstAddress.get8BitAddressOperand());
        MSB = CpuMemory.fastRead8Bit((InstAddress.get8BitAddressOperand() + 1) & 0xff);
        addr = (((MSB << 8) | LSB) + CpuRegister.Y) & 0xffff;

        Value = CpuMemory.read8Bit(addr);
        tmp = Value--;
        CpuMemory.write8Bit(addr, tmp);
        CpuFlag.checkCarrySbc(Value, 1);
        CpuRegister.PC += 2;
    }

    //****************************************
    //                  ISC
    //****************************************

    public static void ZP_ISC()//Zero page
    {
        int tmp, Value, addr;

        addr = InstAddress.get8BitAddressOperand();
        Value = CpuMemory.read8Bit(addr);
        Value++;
        tmp = CpuRegister.A - Value - ((CpuRegister.getCarryFlag()==1) ? 0 : 1);

        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkOverflow(Value, tmp, CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuFlag.checkCarrySbc(CpuRegister.A, Value);

        CpuRegister.PC += 2;
    }

    public static void ZP_X_ISC()//Zero page, X
    {
        int tmp, Value, addr;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;
        Value = CpuMemory.read8Bit(addr);
        Value++;
        tmp = CpuRegister.A - Value - ((CpuRegister.getCarryFlag()==1) ? 0 : 1);

        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkOverflow(Value, tmp, CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuFlag.checkCarrySbc(CpuRegister.A, Value);

        CpuRegister.PC += 2;
    }

    public static void ABS_ISC()//Absolute
    {
        int tmp, Value, addr;

        addr = InstAddress.get16BitAddressOperand();
        Value = CpuMemory.read8Bit(addr);
        Value++;
        tmp = CpuRegister.A - Value - ((CpuRegister.getCarryFlag()==1) ? 0 : 1);

        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkOverflow(Value, tmp, CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuFlag.checkCarrySbc(CpuRegister.A, Value);

        CpuRegister.PC += 3;
    }

    public static void ABS_X_ISC()//Absolute X
    {
        int tmp, Value, addr;

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.X) & 0xffff;
        Value = CpuMemory.read8Bit(addr);
        Value++;
        tmp = CpuRegister.A - Value - ((CpuRegister.getCarryFlag()==1) ? 0 : 1);

        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkOverflow(Value, tmp, CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuFlag.checkCarrySbc(CpuRegister.A, Value);

        CpuRegister.PC += 3;
    }

    public static void ABS_Y_ISC()//Absolute Y
    {
        int tmp, Value, addr;

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.Y) & 0xffff;
        Value = CpuMemory.read8Bit(addr);
        Value++;
        tmp = CpuRegister.A - Value - ((CpuRegister.getCarryFlag()==1) ? 0 : 1);

        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkOverflow(Value, tmp, CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuFlag.checkCarrySbc(CpuRegister.A, Value);

        CpuRegister.PC += 3;
    }

    public static void IND_X_ISC()//Indirect X
    {
        int tmp, Value, addr, MSB, LSB;

        LSB = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        MSB = CpuMemory.read8Bit(((CpuMemory.read8Bit(CpuRegister.PC + 1) + CpuRegister.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;
        
        Value = CpuMemory.read8Bit(addr);
        Value++;
        tmp = CpuRegister.A - Value - ((CpuRegister.getCarryFlag()==1) ? 0 : 1);

        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkOverflow(Value, tmp, CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuFlag.checkCarrySbc(CpuRegister.A, Value);

        CpuRegister.PC += 2;
    }

    public static void IND_Y_ISC()//Indirect Y
    {
        int tmp, Value, addr, MSB, LSB;

        LSB = CpuMemory.fastRead8Bit(InstAddress.get8BitAddressOperand());
        MSB = CpuMemory.fastRead8Bit((InstAddress.get8BitAddressOperand() + 1) & 0xff);
        addr = (((MSB << 8) | LSB) + CpuRegister.Y) & 0xffff;

        Value = CpuMemory.read8Bit(addr);
        Value++;
        tmp = CpuRegister.A - Value - ((CpuRegister.getCarryFlag()==1) ? 0 : 1);

        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkOverflow(Value, tmp, CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuFlag.checkCarrySbc(CpuRegister.A, Value);

        CpuRegister.PC += 2;
    }

    //****************************************
    //                  LAR
    //****************************************

    public static int LAR()// Absolute, Y
    {
        int oldAddr,newAddr, tmp, retCycle;

        oldAddr = InstAddress.get16BitAddressOperand();
        newAddr = (oldAddr + CpuRegister.Y) & 0xffff;

        tmp = CpuMemory.read8Bit(newAddr);
        CpuRegister.A = tmp & CpuRegister.SP;
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);

        if(CpuMemory.getPage(oldAddr) != CpuMemory.getPage(newAddr))
        {
            retCycle = 5;
        }
        else
        {
            retCycle = 4;
        }
        CpuRegister.PC += 3;
        return retCycle;
    }

    //****************************************
    //                  LAX
    //****************************************

    public static void ZP_LAX()//Zero page
    {
        int tmp, addr;

        addr = InstAddress.get8BitAddressOperand();
        tmp = CpuMemory.read8Bit(addr);
        CpuRegister.A = tmp;
        CpuRegister.X = tmp;
        CpuFlag.checkNegative(tmp);
        CpuFlag.checkZero(tmp);
        CpuRegister.PC += 2;
    }

    public static void ZP_Y_LAX()//Zero page, Y
    {
        int tmp, addr;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.Y) & 0xff;
        tmp = CpuMemory.read8Bit(addr);
        CpuRegister.A = tmp;
        CpuRegister.X = tmp;
        CpuFlag.checkNegative(tmp);
        CpuFlag.checkZero(tmp);
        CpuRegister.PC += 2;
    }

    public static void ABS_LAX()//Absolute
    {
        int tmp, addr;

        addr = InstAddress.get16BitAddressOperand();
        tmp = CpuMemory.read8Bit(addr);
        CpuRegister.A = tmp;
        CpuRegister.X = tmp;
        CpuFlag.checkNegative(tmp);
        CpuFlag.checkZero(tmp);
        CpuRegister.PC += 3;
    }

    public static int ABS_Y_LAX()//Absolute Y
    {
        int tmp, retCycle, oldAddr, newAddr;

        oldAddr = InstAddress.get16BitAddressOperand();
        newAddr = (oldAddr + CpuRegister.Y) & 0xffff;

        tmp = CpuMemory.read8Bit(newAddr);
        CpuRegister.A = tmp;
        CpuRegister.X = tmp;
        CpuFlag.checkNegative(tmp);
        CpuFlag.checkZero(tmp);

        if(CpuMemory.getPage(oldAddr) != CpuMemory.getPage(newAddr))
        {
            retCycle = 5;
        }
        else
        {
            retCycle = 4;
        }
        CpuRegister.PC += 3;

        return retCycle;
    }

    public static void IND_X_LAX()//Indirect X
    {
        int tmp, addr, MSB, LSB;

        LSB = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        MSB = CpuMemory.read8Bit(((CpuMemory.read8Bit(CpuRegister.PC + 1) + CpuRegister.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;
        
        tmp = CpuMemory.read8Bit(addr);
        CpuRegister.A = tmp;
        CpuRegister.X = tmp;
        CpuFlag.checkNegative(tmp);
        CpuFlag.checkZero(tmp);
        CpuRegister.PC += 2;
    }

    public static int IND_Y_LAX()//Indirect Y
    {
        int tmp, oldAddr, newAddr, MSB, LSB, retCycle;

        LSB = CpuMemory.fastRead8Bit(InstAddress.get8BitAddressOperand());
        MSB = CpuMemory.fastRead8Bit((InstAddress.get8BitAddressOperand() + 1) & 0xff);
        oldAddr = ((MSB << 8) | LSB);
        newAddr = (oldAddr + CpuRegister.Y) & 0xffff;

        tmp = CpuMemory.read8Bit(newAddr);
        CpuRegister.A = tmp;
        CpuRegister.X = tmp;
        CpuFlag.checkNegative(tmp);
        CpuFlag.checkZero(tmp);

        if(CpuMemory.getPage(oldAddr) != CpuMemory.getPage(newAddr))
        {
            retCycle = 6;
        }
        else
        {
            retCycle = 5;
        }
        CpuRegister.PC += 2;
        return retCycle;
    }

    //****************************************
    //                  RLA
    //****************************************

    public static void ZP_RLA()//Zero page
    {
        int addr, tmp;

        addr = InstAddress.get8BitAddressOperand();
        tmp = CpuMemory.read8Bit(addr);
        tmp <<= 1;
        CpuRegister.A &= tmp;
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    public static void ZP_X_RLA()//Zero page, X
    {
        int addr, tmp;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;
        tmp = CpuMemory.read8Bit(addr);
        tmp <<= 1;
        CpuRegister.A &= tmp;
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    public static void ABS_RLA()//Absolute
    {
        int addr, tmp;

        addr = InstAddress.get16BitAddressOperand();
        tmp = CpuMemory.read8Bit(addr);
        tmp <<= 1;
        CpuRegister.A &= tmp;
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 3;
    }

    public static void ABS_X_RLA()//Absolute X
    {
        int addr, tmp;

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.X) & 0xffff;
        tmp = CpuMemory.read8Bit(addr);
        tmp <<= 1;
        CpuRegister.A &= tmp;
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 3;
    }

    public static void ABS_Y_RLA()//Absolute Y
    {
        int addr, tmp;

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.Y) & 0xffff;
        tmp = CpuMemory.read8Bit(addr);
        tmp <<= 1;
        CpuRegister.A &= tmp;
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 3;
    }

    public static void IND_X_RLA()//Indirect X
    {
        int addr, tmp, MSB, LSB;

        LSB = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        MSB = CpuMemory.read8Bit(((CpuMemory.read8Bit(CpuRegister.PC + 1) + CpuRegister.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;
        
        tmp = CpuMemory.read8Bit(addr);
        tmp <<= 1;
        CpuRegister.A &= tmp;
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    public static void IND_Y_RLA()//Indirect Y
    {
        int addr, tmp, MSB, LSB;

        LSB = CpuMemory.fastRead8Bit(InstAddress.get8BitAddressOperand());
        MSB = CpuMemory.fastRead8Bit((InstAddress.get8BitAddressOperand() + 1) & 0xff);
        addr = (((MSB << 8) | LSB) + CpuRegister.Y) & 0xffff;

        tmp = CpuMemory.read8Bit(addr);
        tmp <<= 1;
        CpuRegister.A &= tmp;
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    //****************************************
    //                  RRA
    //****************************************

    public static void ZP_RRA()//Zero page
    {
        int addr, tmp, tmp2;

        addr = InstAddress.get8BitAddressOperand();
        tmp = CpuMemory.read8Bit(addr);
        tmp >>= 1;
        tmp2 = (tmp + CpuRegister.getCarryFlag());
        CpuRegister.A += tmp2;
        CpuFlag.checkOverflow(tmp, tmp2, CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuFlag.checkCarry(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    public static void ZP_X_RRA()//Zero page, X
    {
        int addr, tmp, tmp2;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;
        tmp = CpuMemory.read8Bit(addr);
        tmp >>= 1;
        tmp2 = (tmp + CpuRegister.getCarryFlag());
        CpuRegister.A += tmp2;
        CpuFlag.checkOverflow(tmp, tmp2, CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuFlag.checkCarry(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    public static void ABS_RRA()//Absolute
    {
        int addr, tmp, tmp2;

        addr = InstAddress.get16BitAddressOperand();
        tmp = CpuMemory.read8Bit(addr);
        tmp >>= 1;
        tmp2 = (tmp + CpuRegister.getCarryFlag());
        CpuRegister.A += tmp2;
        CpuFlag.checkOverflow(tmp, tmp2, CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuFlag.checkCarry(CpuRegister.A);
        CpuRegister.PC += 3;
    }

    public static void ABS_X_RRA()//Absolute X
    {
        int addr, tmp, tmp2;

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.X) & 0xffff;
        tmp = CpuMemory.read8Bit(addr);
        tmp >>= 1;
        tmp2 = (tmp + CpuRegister.getCarryFlag());
        CpuRegister.A += tmp2;
        CpuFlag.checkOverflow(tmp, tmp2, CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuFlag.checkCarry(CpuRegister.A);
        CpuRegister.PC += 3;
    }

    public static void ABS_Y_RRA()//Absolute Y
    {
        int addr, tmp, tmp2;

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.Y) & 0xffff;
        tmp = CpuMemory.read8Bit(addr);
        tmp >>= 1;
        tmp2 = (tmp + CpuRegister.getCarryFlag());
        CpuRegister.A += tmp2;
        CpuFlag.checkOverflow(tmp, tmp2, CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuFlag.checkCarry(CpuRegister.A);
        CpuRegister.PC += 3;
    }

    public static void IND_X_RRA()//Indirect X
    {
        int addr, tmp, tmp2, MSB, LSB;

        LSB = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        MSB = CpuMemory.read8Bit(((CpuMemory.read8Bit(CpuRegister.PC + 1) + CpuRegister.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;
        
        tmp = CpuMemory.read8Bit(addr);
        tmp >>= 1;
        tmp2 = (tmp + CpuRegister.getCarryFlag());
        CpuRegister.A += tmp2;
        CpuFlag.checkOverflow(tmp, tmp2, CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuFlag.checkCarry(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    public static void IND_Y_RRA()//Indirect Y
    {
        int addr, tmp, tmp2, MSB, LSB;

       LSB = CpuMemory.fastRead8Bit(InstAddress.get8BitAddressOperand());
        MSB = CpuMemory.fastRead8Bit((InstAddress.get8BitAddressOperand() + 1) & 0xff);
        addr = (((MSB << 8) | LSB) + CpuRegister.Y) & 0xffff;

        tmp = CpuMemory.read8Bit(addr);
        tmp >>= 1;
        tmp2 = (tmp + CpuRegister.getCarryFlag());
        CpuRegister.A += tmp2;
        CpuFlag.checkOverflow(tmp, tmp2, CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuFlag.checkCarry(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    //****************************************
    //                  SLO
    //****************************************

    public static void ZP_SLO()//Zero page
    {
        int addr, tmp;

        addr = InstAddress.get8BitAddressOperand();
        tmp = CpuMemory.read8Bit(addr);
        tmp <<= 1;
        CpuRegister.A |= tmp;
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    public static void ZP_X_SLO()//Zero page, X
    {
        int addr, tmp;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;
        tmp = CpuMemory.read8Bit(addr);
        tmp <<= 1;
        CpuRegister.A |= tmp;
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    public static void ABS_SLO()//Absolute
    {
        int addr, tmp;

        addr = InstAddress.get16BitAddressOperand();
        tmp = CpuMemory.read8Bit(addr);
        tmp <<= 1;
        CpuRegister.A |= tmp;
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 3;
    }

    public static void ABS_X_SLO()//Absolute X
    {
        int addr, tmp;

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.X) & 0xffff;
        tmp = CpuMemory.read8Bit(addr);
        tmp <<= 1;
        CpuRegister.A |= tmp;
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 3;
    }

    public static void ABS_Y_SLO()//Absolute Y
    {
        int addr, tmp;

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.Y) & 0xffff;
        tmp = CpuMemory.read8Bit(addr);
        tmp <<= 1;
        CpuRegister.A |= tmp;
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 3;
    }

    public static void IND_X_SLO()//Indirect X
    {
        int addr, tmp, LSB, MSB;

        LSB = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        MSB = CpuMemory.read8Bit(((CpuMemory.read8Bit(CpuRegister.PC + 1) + CpuRegister.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;

        tmp = CpuMemory.read8Bit(addr);
        tmp <<= 1;
        CpuRegister.A |= tmp;
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    public static void IND_Y_SLO()//Indirect Y
    {
        int addr, tmp, LSB, MSB;

        LSB = CpuMemory.fastRead8Bit(InstAddress.get8BitAddressOperand());
        MSB = CpuMemory.fastRead8Bit((InstAddress.get8BitAddressOperand() + 1) & 0xff);
        addr = (((MSB << 8) | LSB) + CpuRegister.Y) & 0xffff;

        tmp = CpuMemory.read8Bit(addr);
        tmp <<= 1;
        CpuRegister.A |= tmp;
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    //****************************************
    //                  SRE
    //****************************************

    public static void ZP_SRE()//Zero page
    {
        int addr, tmp;

        addr = InstAddress.get8BitAddressOperand();
        tmp = CpuMemory.read8Bit(addr);
        tmp >>= 1;
        CpuRegister.A ^= tmp;
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    public static void ZP_X_SRE()//Zero page, X
    {
        int addr, tmp;

        addr = (InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff;
        tmp = CpuMemory.read8Bit(addr);
        tmp >>= 1;
        CpuRegister.A ^= tmp;
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    public static void ABS_SRE()//Absolute
    {
        int addr, tmp;

        addr = InstAddress.get16BitAddressOperand();
        tmp = CpuMemory.read8Bit(addr);
        tmp >>= 1;
        CpuRegister.A ^= tmp;
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 3;
    }

    public static void ABS_X_SRE()//Absolute X
    {
        int addr, tmp;

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.X) & 0xffff;
        tmp = CpuMemory.read8Bit(addr);
        tmp >>= 1;
        CpuRegister.A ^= tmp;
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 3;
    }

    public static void ABS_Y_SRE()//Absolute Y
    {
        int addr, tmp;

        addr = (InstAddress.get16BitAddressOperand() + CpuRegister.Y) & 0xffff;
        tmp = CpuMemory.read8Bit(addr);
        tmp >>= 1;
        CpuRegister.A ^= tmp;
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 3;
    }

    public static void IND_X_SRE()//Indirect X
    {
        int addr, tmp, MSB, LSB;

        LSB = CpuMemory.read8Bit((InstAddress.get8BitAddressOperand() + CpuRegister.X) & 0xff);
        MSB = CpuMemory.read8Bit(((CpuMemory.read8Bit(CpuRegister.PC + 1) + CpuRegister.X) + 1) & 0xff);
        addr = MSB * 0x100 + LSB;
        
        tmp = CpuMemory.read8Bit(addr);
        tmp >>= 1;
        CpuRegister.A ^= tmp;
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    public static void IND_Y_SRE()//Indirect Y
    {
        int addr, tmp, MSB, LSB;

        LSB = CpuMemory.fastRead8Bit(InstAddress.get8BitAddressOperand());
        MSB = CpuMemory.fastRead8Bit((InstAddress.get8BitAddressOperand() + 1) & 0xff);
        addr = (((MSB << 8) | LSB) + CpuRegister.Y) & 0xffff;

        tmp = CpuMemory.read8Bit(addr);
        tmp >>= 1;
        CpuRegister.A ^= tmp;
        CpuFlag.checkCarry(CpuRegister.A);
        CpuFlag.checkNegative(CpuRegister.A);
        CpuFlag.checkZero(CpuRegister.A);
        CpuRegister.PC += 2;
    }

    //****************************************
    //                  SXA
    //****************************************

    public static void SXA()//Absolute Y
    {
        int addr, tmpAddr, _arg, tmp;

        tmpAddr = InstAddress.get16BitAddressOperand();
        addr = (tmpAddr + CpuRegister.Y) & 0xffff;
        _arg = (tmpAddr >> 4) + 1;
        tmp = CpuRegister.X & _arg;
        CpuMemory.write8Bit(addr, tmp);
        CpuRegister.PC += 3;
    }

    //****************************************
    //                  SYA
    //****************************************

    public static void SYA()//Absolute X
    {
        int addr, tmpAddr, _arg, tmp;

        tmpAddr = InstAddress.get16BitAddressOperand();
        addr = (tmpAddr + CpuRegister.X) & 0xffff;
        _arg = (tmpAddr >> 4) + 1;
        tmp = CpuRegister.Y & _arg;
        CpuMemory.write8Bit(addr, tmp);
        CpuRegister.PC += 3;
    }

    //****************************************
    //                  XAS
    //****************************************

    public static void XAS()//Absolute Y
    {
        int addr, tmpAddr, tmp, _arg;

        tmpAddr = InstAddress.get16BitAddressOperand();
        addr = (tmpAddr + CpuRegister.Y) & 0xffff;
        _arg = (tmpAddr >> 4) + 1;
        CpuRegister.SP = CpuRegister.X & CpuRegister.A;
        tmp = CpuRegister.SP & _arg;
        CpuMemory.write8Bit(addr, tmp);
        CpuRegister.PC += 3;
    }
}

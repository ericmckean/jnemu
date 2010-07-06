package CPU;

public class CPU_REGISTER
{
    public static int X; //X register
    public static int Y; //Y register
    public static int A; //Accumulator
    public static int PC; //program counter
    public static int SP; //stack pointer
    public static int SR; //status register

    public static void init()
    {
        CPU_REGISTER.A = 0;
        CPU_REGISTER.SP = 0;
        CPU_REGISTER.PC = 0;
        CPU_REGISTER.X = 0;
        CPU_REGISTER.Y = 0;
        CPU_REGISTER.SR = 0;
        cpuCORE.CYCLE = 0;
    }

    //carry flag........................
    public static void setCarryFlag()
    {
        SR = SR | 0x01;
    }

    public static void clearCarryFlag()
    {
        SR = SR & 0xFE;
    }

    public static int getCarryFlag()
    {
        return SR & 0x01;
    }
    
    //zero flag.........................
    public static void setZeroFlag()
    {
        SR = SR | 0x02;
    }

    public static void clearZeroFlag()
    {
        SR = SR & 0xFD;
    }

    public static int getZeroFlag()
    {
        return (SR & 0x02) >> 0x01;
    }

    //interrupt flag.........................
    public static void setInterruptFlag()
    {
        SR = SR | 0x04;
    }

    public static void clearInterruptFlag()
    {
        SR = SR & 0xFB;
    }

    public static int getInterruptFlag()
    {
        return (SR & 0x04) >> 0x02;
    }

    //Decimal flag.........................
    public static void setDecimalFlag()
    {
        SR = SR | 0x08;
    }

    public static void clearDecimalFlag()
    {
        SR = SR & 0xF7;
    }

    public static int getDecimalFlag()
    {
        return (SR & 0x08) >> 0x03;
    }

    //BRK flag.........................
    public static void setBRKFlag()
    {
        SR = SR | 0x10;
    }

    public static void clearBRKFlag()
    {
        SR = SR & 0xEF;
    }

    public static int getBRKFlag()
    {
        return (SR & 0x10) >> 0x04;
    }

    //Overflow flag.........................
    public static void setOverflowFlag()
    {
        SR = SR | 0x40;
    }

    public static void clearOverflowFlag()
    {
        SR = SR & 0xBF;
    }

    public static int getOverflowFlag()
    {
        return (SR & 0x40) >> 0x06;
    }

    //Negative flag.........................
    public static void setNegativeFlag()
    {
        SR = SR | 0x80;
    }

    public static void clearNegativeFlag()
    {
        SR = SR & 0x7F;
    }

    public static int getNegativeFlag()
    {
        return (SR & 0x80) >> 0x07;
    }   
}

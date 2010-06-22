package CPU;

import java.util.Arrays;
import PPU.PPU_REGISTER;
import PPU.ppuCORE;
import jnemu.*;

public class CPU_MEMORY
{
    private static int[][] MEMORY_MAP;

    public static void init()
    {
        MEMORY_MAP = new int[0x100][0x100];
        //***********************************************
        //           Memory Power-Up state...
        //***********************************************
        for(int ctr=0x0000; ctr<=0x07FF; ctr++)
        {
            if(ctr == 0x0008)
            {
                write8Bit(ctr, 0xF7);
            }
            else if(ctr == 0x0009)
            {
                write8Bit(ctr, 0xEF);
            }
            else if(ctr == 0x000A)
            {
                write8Bit(ctr, 0xDF);
            }
            else if(ctr == 0x000F)
            {
                write8Bit(ctr, 0xBF);
            }
            else
            {
                write8Bit(ctr, 0xFF);
            }
        }
        
        write8Bit(0x4017, 0x00); //frame irq enabled...
        write8Bit(0x4015, 0x00); //all channels disabled...

        //FIXME: not sure about $4010-$4013...
        for(int ctr=0x4000; ctr<=0x400F; ctr++)
        {
            write8Bit(ctr, 0x00);
        }
    }

    public static int read8Bit(int address)
    {
        //***************************************************
        //      Read functions for Opcode Operand Only
        //***************************************************
        if(address == 0x2002 && PPU_REGISTER.getVBlankFlag() == 1)
        {
            //Force ending of VBlank when CPU reads at $2002 during VBlank state...
            ppuCORE.VBlankPremEnd = true;
        }
        return MEMORY_MAP[address >> 8][address & 0xFF] & 0xFF;
    }

    public static int fastRead8Bit(int address)
    {
        //***************************************************
        //            Multipurpose read functions
        //                  for performace
        //***************************************************
        return MEMORY_MAP[address >> 8][address & 0xFF] & 0xFF;
    }

    public static void write8Bit(int address, int value)
    {
        int page;
        page = address >> 8;
        MEMORY_MAP[page][address & 0xFF] = value;
        //***************************************************
        //                MEMORY Mirroring
        //***************************************************
        if(page >= 0 && page <= 7) //Mirror from page 0-7 (3x)
        {
            MEMORY_MAP[page + 8][address & 0xFF] = value;     //Mirror 1
            MEMORY_MAP[page + 16][address & 0xFF] = value;    //Mirror 2
            MEMORY_MAP[page + 24][address & 0xFF] = value;    //Mirror 3
        }
    }

    public static void clear()
    {
        Arrays.fill(MEMORY_MAP, 0);
    }

    public static int getResetVector()
    {
        //*********************************************
        //               RESET VECTOR
        //    This vector is located at $FFFC-$FFFD
        //*********************************************
        int MSB, LSB, tmp;

        MSB = fastRead8Bit(0xFFFD);
        LSB = fastRead8Bit(0xFFFC);
        tmp = (MSB << 8) | LSB;

        return tmp;
    }

    public static int getNMIVector()
    {
        //*********************************************
        //                 NMI VECTOR
        //    This vector is located at $FFFA-$FFFB
        //*********************************************
        int MSB, LSB, tmp;

        MSB = fastRead8Bit(0xFFFB);
        LSB = fastRead8Bit(0xFFFA);
        tmp = (MSB << 8) | LSB;

        return tmp;
    }

    public static int getIRQVector()
    {
        //*********************************************
        //               IRQ/BRK VECTOR
        //    This vector is located at $FFFE-$FFFF
        //*********************************************
        int MSB, LSB, tmp;

        MSB = fastRead8Bit(0xFFFF);
        LSB = fastRead8Bit(0xFFFE);
        tmp = (MSB << 8) | LSB;

        return tmp;
    }

    public static int getPage(int address)
    {
        //*********************************************
        //        This Function tells the Page
        //            of the Memory address
        //*********************************************
        return address >> 8;
    }
}

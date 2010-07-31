package CPU;

import MISC.CONVERTER;
import java.util.Arrays;
import PPU.PPU_REGISTER;
import PPU.ppuCORE;
import java.text.DecimalFormat;
import jnemu.Console;

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
        else if(address == 0x2007)
        {
            ppuCORE.isReadingPPUDATA = true;
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

        if(address == 0x2006)
        {
            ppuCORE.isAccessingPPUADDR = true;
        }
        else if(address == 0x2007)
        {
            ppuCORE.isWritingPPUDATA = true;
        }
        else if(address == 0x2003)
        {
            ppuCORE.isAccessingOAMADDR = true;
        }
        else if(address == 0x2004)
        {
            ppuCORE.isWritingOAMDATA = true;
        }
        MEMORY_MAP[page][address & 0xff] = value;
        //***************************************************
        //                MEMORY Mirroring
        //***************************************************
        if(page >= 0 && page <= 7) //Mirror from page 0-7 (3x)
        {
            MEMORY_MAP[page + 8][address & 0xff] = value;     //Mirror 1
            MEMORY_MAP[page + 16][address & 0xff] = value;    //Mirror 2
            MEMORY_MAP[page + 24][address & 0xff] = value;    //Mirror 3
        }
        else if(page == 0x20)
        {
            int ctr;
            if((address & 0xff) == 0x00)
            {
                for(ctr=0x2008; ctr<=0x3ff8; ctr+=8)
                {
                    MEMORY_MAP[ctr >> 8][ctr & 0xff] = value;
                }
            }
            else if((address & 0xff) == 0x01)
            {
                for(ctr=0x2009; ctr<=0x3ff9; ctr+=8)
                {
                    MEMORY_MAP[ctr >> 8][ctr & 0xff] = value;
                }
            }
            else if((address & 0xff) == 0x02)
            {
                for(ctr=0x200a; ctr<=0x3ffa; ctr+=8)
                {
                    MEMORY_MAP[ctr >> 8][ctr & 0xff] = value;
                }
            }
            else if((address & 0xff) == 0x03)
            {
                for(ctr=0x200b; ctr<=0x3ffb; ctr+=8)
                {
                    MEMORY_MAP[ctr >> 8][ctr & 0xff] = value;
                }
            }
            else if((address & 0xff) == 0x04)
            {
                for(ctr=0x200c; ctr<=0x3ffc; ctr+=8)
                {
                    MEMORY_MAP[ctr >> 8][ctr & 0xff] = value;
                }
            }
            else if((address & 0xff) == 0x05)
            {
                for(ctr=0x200d; ctr<=0x3ffd; ctr+=8)
                {
                    MEMORY_MAP[ctr >> 8][ctr & 0xff] = value;
                }
            }
            else if((address & 0xff) == 0x06)
            {
                for(ctr=0x200e; ctr<=0x3ffe; ctr+=8)
                {
                    MEMORY_MAP[ctr >> 8][ctr & 0xff] = value;
                }
            }
            else if((address & 0xff) == 0x07)
            {
                for(ctr=0x200f; ctr<=0x3fff; ctr+=8)
                {
                    MEMORY_MAP[ctr >> 8][ctr & 0xff] = value;
                }
            }
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

        MSB = fastRead8Bit(0xfffd);
        LSB = fastRead8Bit(0xfffc);
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

        MSB = fastRead8Bit(0xfffb);
        LSB = fastRead8Bit(0xfffa);
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

        MSB = fastRead8Bit(0xffff);
        LSB = fastRead8Bit(0xfffe);
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

    public static String getMemContent(int start, int end)
    {
        int ctr, ctr2, tmp;
        int BASE = 16;
        String space = " ";
        String space2 = " : ";
        String space3 = "   ";
        DecimalFormat dfUpper = new DecimalFormat("00");
        StringBuilder x = new StringBuilder();
        StringBuilder UTF8 = new StringBuilder(50);

        try
        {
            x.append("        ");
            for(ctr=0;ctr<BASE;ctr++)
            {
                x.append(dfUpper.format(ctr));
                x.append(space);
            }
            x.append("\n\n");
            x.append("$" + CONVERTER.intTo16BitStringHex(start));
            x.append(space2);

            ctr2 = 0;

            for(ctr=start; ctr<=end; ctr++)
            {
                ctr2 += 1;
                tmp = CPU_MEMORY.fastRead8Bit(ctr);
                if(ctr2 != BASE)
                {
                    if(tmp != 0)
                    {
                        x.append(CONVERTER.intTo8BitStringHex(tmp));
                        x.append(space);
                        UTF8.append(CONVERTER.intToChar(tmp));
                        UTF8.append(space);
                    }
                    else
                    {
                        x.append("00");
                        x.append(space);
                        UTF8.append(".");
                        UTF8.append(space);
                    }
                }
                else
                {
                    if(tmp != 0)
                    {
                        x.append(CONVERTER.intTo8BitStringHex(tmp));
                        UTF8.append(CONVERTER.intToChar(tmp));
                        UTF8.append(space);
                        x.append(space3);
                        x.append(UTF8.toString());
                        UTF8.delete(0, UTF8.length());
                        x.append("\n");
                        x.append("$" + CONVERTER.intTo16BitStringHex(ctr + 1));
                        x.append(space2);
                    }
                    else
                    {
                        x.append("00");
                        UTF8.append(".");
                        UTF8.append(space);
                        x.append(space3);
                        x.append(UTF8.toString());
                        UTF8.delete(0, UTF8.length());
                        x.append("\n");
                        x.append("$" + CONVERTER.intTo16BitStringHex(ctr + 1));
                        x.append(space2);
                    }
                    ctr2 = 0;
                }
            }
        }
        catch(Exception e)
        {
            Console.print("[ERROR] Load the Rom first.");
        }
        return x.toString();
    }
}

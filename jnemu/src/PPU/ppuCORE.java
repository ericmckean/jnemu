package PPU;

import CPU.cpuCORE;

public class ppuCORE
{
    public static int SCANLINE; //Scanline counter............
    public static boolean VBlankPremEnd;
    public static boolean isNMI;
    static int PPU_ADDR; //Address form PPUADDR ($2006)...
    static int MSB, LSB;
    public static boolean isFirstWrite;
    public static boolean isAccesingPPUADDR;
    public static boolean isWritingPPUDATA;
    
    public static void init()
    {
        SCANLINE = 0;
        VBlankPremEnd = false;
        isNMI = false;
        PPU_ADDR = 0;
        MSB = 0;
        LSB = 0;
        isFirstWrite = true;
        isAccesingPPUADDR = true;
        isWritingPPUDATA = false;
        PPU_MEMORY.init();
    }

    public static void execPPU()
    {
        SCANLINE = (int) ((double) cpuCORE.CYCLE / 113.6666666666667);
        if(SCANLINE >= 240 && SCANLINE < 261)
        {
            //*******************************************
            //              VBLANK State
            //*******************************************
            PPU_REGISTER.setVBlankFlag();
            //******************************************
            //        Read / Write During VBlank
            //              $2006 - $2007
            //******************************************
            if(isAccesingPPUADDR)
            {
                if(isFirstWrite)
                {
                    MSB = PPU_REGISTER.getPPUAddr();
                    isFirstWrite = false;
                }
                else
                {
                    LSB = PPU_REGISTER.getPPUAddr();
                    PPU_ADDR = (MSB << 8) | LSB; //get the actual PPU address..
                    //FIXME: put value of memory is PPUDATA according to
                    //PPUADDR's address content for reading purposes....
                    //Console.print("[PPU_ADDR] " + Integer.toHexString(PPU_ADDR));
                    PPU_REGISTER.setPPUData(PPU_MEMORY.readPPUMemory(PPU_ADDR));
                    isFirstWrite = true;
                    isAccesingPPUADDR = false;
                }
            }

            if(isWritingPPUDATA)
            {
                //FIXME: write the value of PPUDATA to PPU memory according to
                //PPUADDR's address content...
                //Console.print("[$2007] " + Integer.toHexString(PPU_REGISTER.getPPUData()));
                PPU_MEMORY.writePPUMemory(PPU_ADDR, PPU_REGISTER.getPPUData());
                if(PPU_REGISTER.getVramAddressInc() == 0)
                {
                    PPU_ADDR++;
                    PPU_ADDR &= 0x3fff;
                }
                else
                {
                    PPU_ADDR += 32;
                    PPU_ADDR &= 0x3fff;
                }
                isWritingPPUDATA = false;
            }
            //******************************************
            //               NMI Section
            //******************************************
            if(PPU_REGISTER.getNMIFlag() == 1)
            {
                //Generate NMI...............
                isNMI = true;
            }
            else if(VBlankPremEnd) //check for VBlank Premature termination...
            {
                PPU_REGISTER.clearVBlankFlag();
                //Don't forget to set VBlankPremEnd to false after VBlank is
                //prematurely terminated as it will cause an infinite loop..
                VBlankPremEnd = false;
                isNMI = false;
            }
        }
        else if(SCANLINE == 262)
        {
            PPU_REGISTER.clearVBlankFlag();
            cpuCORE.CYCLE -= 29780; //262 scanline...
            SCANLINE = 0;
        }
        else
        {
            //*******************************************
            //             Rendering State
            //*******************************************
            
            //FIXME: needs more routine here...
        }
    }
}

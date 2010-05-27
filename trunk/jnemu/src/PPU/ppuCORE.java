package PPU;

import CPU.cpuCORE;

public class ppuCORE
{
    public static int SCANLINE; //scanline counter............
    public static boolean VBlankPremEnd;

    public static void init()
    {
        SCANLINE = 0;
        VBlankPremEnd = false;
        PPU_MEMORY.init();
    }

    public static void execPPU()
    {
        SCANLINE = (int) ((double) cpuCORE.CYCLE / 113.6666666666667);
        if(SCANLINE >= 240 && SCANLINE < 261)
        {
            PPU_REGISTER.setVBlankFlag();
            //check for NMI here............
            if(PPU_REGISTER.getNMIFlag() == 1)
            {
                //Generate NMI...............
            }
            else if(VBlankPremEnd) //check for VBlank Premature termination...
            {
                PPU_REGISTER.clearVBlankFlag();
                //Don't forget to set VBlankPremEnd to false after VBlank is
                //prematurely terminated as it will cause an infinite loop..
                VBlankPremEnd = false;
                //FIXME: needs more routine here...
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
            //FIXME: needs more routine here...
        }
    }
}

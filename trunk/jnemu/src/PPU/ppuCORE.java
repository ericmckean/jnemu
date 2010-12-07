/*
 *
 *  +------------------------------------------------------------------------+
 *  |    PPU Cycle   |                    PPU is doing???                    |
 *  +----------------+-------------------------------------------------------+
 *  |    256-319:    |  Grab pattern slivers for the eight frontmost sprites |
 *  |                |  in range.                                            |
 *  +----------------+-------------------------------------------------------+
 *  |    320-335:    |  Grab map entries and pattern slivers for the first   |
 *  |                |  two columns of the next scanline.                    |
 *  +----------------+-------------------------------------------------------+
 *  |    336-340:    |  Freeze, but this is still considered rendering.      |
 *  +------------------------------------------------------------------------+
 */


package PPU;

import CPU.CpuCore;

public class PpuCore
{
    public static int SCANLINE; //Scanline counter............
    public static boolean VBlankPremEnd;
    public static boolean isNMI;
    public static int PPU_ADDR_LATCH; //Address form PPUADDR ($2006)...
    public static boolean isFirstWrite;
    public static boolean isAccessingPPUADDR;
    public static boolean isWritingPPUDATA;
    public static boolean isReadingPPUDATA;
    public static boolean isAccessingOAMADDR;
    public static boolean isWritingOAMDATA;
    static int OAM_ADDR;
    public static int PpuCycle;
    public static int InternalBuffer;
    
    public static void init()
    {
        SCANLINE = 241;
        VBlankPremEnd = false;
        isNMI = false;
        PPU_ADDR_LATCH = 0;
        isFirstWrite = true;
        isAccessingPPUADDR = true;
        isWritingPPUDATA = false;
        isReadingPPUDATA = false;
        isAccessingOAMADDR = false;
        isWritingOAMDATA = false;
        PpuCycle = 0;
        InternalBuffer = 0;
        PpuMemory.init();
        OamMemory.init();
    }

    public static void execPPU()
    {
        //**********************************************
        //         1 CPU Cycle = 3 PPU Cycle
        //**********************************************
        PpuCycle = CpuCore.cpuCycle * 3; //Get the actual PPU Cycle...
        VRam.checkForSetAddr();
        VRam.checkForRead();
        VRam.checkForWrite();
        OamCore.checkForRead();
        OamCore.checkForWrite();
        if(PpuCycle >= 341)
        {
            SCANLINE++;
            PpuCycle = 1;
            CpuCore.cpuCycle = 0;
            if(SCANLINE >= 240 && SCANLINE <= 259)
            {
                //*******************************************
                //              VBlank Period
                //*******************************************
                PpuRegister.setVBlankFlag();
                //******************************************
                //               NMI Section
                //******************************************
                if(PpuRegister.getNMIFlag() == 1)
                {
                    //Generate NMI...............
                    isNMI = true;
                }
                else if(VBlankPremEnd) //check for VBlank Premature termination...
                {
                    PpuRegister.clearVBlankFlag();
                    //Don't forget to set VBlankPremEnd to false after VBlank is
                    //prematurely terminated as it will cause an infinite loop..
                    VBlankPremEnd = false;
                    isNMI = false;
                    //Clear the PPUSCROLL and PPUADDR address latch...
                    PPU_ADDR_LATCH = 0;
                }
            }
            else if(SCANLINE == 261)
            {
                PpuRegister.clearVBlankFlag();
                PpuCycle = 0;
                SCANLINE = -1;
            }
        }
        else if(PpuCycle >= 256 && PpuCycle <= 340)
        {
            //********************************
            //         HBlank Period
            //********************************
        }
    }
}
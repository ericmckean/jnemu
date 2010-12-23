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


package NesPpu;

import NesCpu.CpuCore;

public class PpuCore
{
    public static int SCANLINE; //Scanline counter............
    public static boolean vBlankPremEnd;
    public static boolean isNmi;
    public static int PPU_ADDR_LATCH; //Address form PPUADDR ($2006)...
    public static boolean isFirstWrite;
    public static boolean isAccessingPpuAddr;
    public static boolean isWritingPpuData;
    public static boolean isReadingPpuData;
    public static boolean isAccessingOamAddr;
    public static boolean isWritingOamData;
    static int OAM_ADDR;
    public static int PpuCycle;
    public static int InternalBuffer;
    
    public static void init()
    {
        SCANLINE = 241;
        vBlankPremEnd = false;
        isNmi = false;
        PPU_ADDR_LATCH = 0;
        isFirstWrite = true;
        isAccessingPpuAddr = true;
        isWritingPpuData = false;
        isReadingPpuData = false;
        isAccessingOamAddr = false;
        isWritingOamData = false;
        PpuCycle = 0;
        InternalBuffer = 0;
        PpuMemory.init();
        OamMemory.init();
    }

    public static void exec()
    {
        //**********************************************
        //         1 CPU Cycle = 3 PPU Cycle
        //**********************************************
        PpuCycle++;
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
                PpuRegister.setVblankFlag();
                //******************************************
                //               NMI Section
                //******************************************
                if(PpuRegister.getNmiFlag() == 1)
                {
                    //Generate NMI...............
                    isNmi = true;
                }
                else if(vBlankPremEnd) //check for VBlank Premature termination...
                {
                    PpuRegister.clearVblankFlag();
                    //Don't forget to set VBlankPremEnd to false after VBlank is
                    //prematurely terminated as it will cause an infinite loop..
                    vBlankPremEnd = false;
                    isNmi = false;
                    //Clear the PPUSCROLL and PPUADDR address latch...
                    PPU_ADDR_LATCH = 0;
                }
            }
            else if(SCANLINE == 261)
            {
                PpuRegister.clearVblankFlag();
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

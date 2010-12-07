package INSTRUCTIONS;

import CPU.*;

public class InstInterrupt
{
    public static void NMI()
    {
        CpuStack.Push(CpuRegister.PC & 0xFF); //LSB
        CpuStack.Push(CpuRegister.PC >> 8);   //MSB
        CpuRegister.setInterruptFlag();
        CpuStack.Push((CpuRegister.SR & 0xef));

        CpuRegister.PC = CpuMemory.getNMIVector();
    }

    public static void IRQ()
    {
        CpuStack.Push(CpuRegister.PC & 0xFF); //LSB
        CpuStack.Push(CpuRegister.PC >> 8);   //MSB
        CpuRegister.setInterruptFlag();
        CpuStack.Push((CpuRegister.SR & 0xef));

        CpuRegister.PC = CpuMemory.getIRQVector();
    }
}

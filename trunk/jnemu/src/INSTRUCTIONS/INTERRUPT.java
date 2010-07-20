package INSTRUCTIONS;

import CPU.*;

public class INTERRUPT
{
    public static void NMI()
    {
        STACK.Push(CPU_REGISTER.PC & 0xFF); //LSB
        STACK.Push(CPU_REGISTER.PC >> 8);   //MSB
        CPU_REGISTER.setInterruptFlag();
        STACK.Push((CPU_REGISTER.SR & 0xef));

        CPU_REGISTER.PC = CPU_MEMORY.getNMIVector();
    }

    public static void IRQ()
    {
        STACK.Push(CPU_REGISTER.PC & 0xFF); //LSB
        STACK.Push(CPU_REGISTER.PC >> 8);   //MSB
        CPU_REGISTER.setInterruptFlag();
        STACK.Push((CPU_REGISTER.SR & 0xef));

        CPU_REGISTER.PC = CPU_MEMORY.getIRQVector();
    }
}

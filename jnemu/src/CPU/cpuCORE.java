/*      TODO:
 *
 *      1.)All the opcode should be listed here before implementation.
 *      2.)Implement the opcode.
 */

package CPU;

import jnemu.Console;
import INSTRUCTIONS.*;
import jnemu.emuCORE;

public class cpuCORE
{
    public static int CYCLE; //cycle counter..................
    
    public static void init()
    {
        CPU_REGISTER.A = 0;
        CPU_REGISTER.SP = 0;
        CPU_REGISTER.PC = CPU_MEMORY.getResetVector();
        CPU_REGISTER.X = 0;
        CPU_REGISTER.Y = 0;
        CPU_REGISTER.SR = 0;
        cpuCORE.CYCLE = 0;
    }

    public static int exec(int opcode)
    {
        int cycle = 0;
        int tmp = 0;

        switch(opcode)
        {
            /*       >>Math Instructions<<
             *          Add / Subtract
             */
            /************** ADC **************/
            case 0x69 : //Immediate
                IMMIDIATE.ADC();
                cycle = 2;
                break;
            case 0x65 : //Zero Page
                ZEROPAGE.ADC();
                cycle = 3;
                break;
            case 0x75 : //Zero Page, X
                ZEROPAGE_X.ADC();
                cycle = 4;
                break;
            case 0x6D : //Absolute
                ABSOLUTE.ADC();
                cycle = 4;
                break;
            case 0x7D : //Absolute, X
                cycle = ABSOLUTE_X.ADC();
                break;
            case 0x79 : //Absolute, Y
                cycle = ABSOLUTE_Y.ADC();
                break;
            case 0x61 : //(Indirect, X)
                Console.print("Unimplemented Opcode ADC (Indirect, X)");
                emuCORE.isRunning = false;
                break;
            case 0x71 : //(Indirect), Y
                Console.print("Unimplemented Opcode ADC (Indirect), Y");
                emuCORE.isRunning = false;
                break;
            /************** SBC **************/
            case 0xE9 : //immidiate
                Console.print("Unimplemented Opcode SBC immidiate");
                emuCORE.isRunning = false;
                break;
            case 0xE5 : //zeropage
                Console.print("Unimplemented Opcode SBC zeropage");
                emuCORE.isRunning = false;
                break;
            case 0xF5 : //zeropage,X
                ZEROPAGE_X.SBC();
                cycle = 4;
                break;
            case 0xED : //absolute
                Console.print("Unimplemented Opcode SBC absolute");
                emuCORE.isRunning = false;
                break;
            case 0xFD : //absolute,X
                Console.print("Unimplemented Opcode SBC absolute,X");
                emuCORE.isRunning = false;
                break;
            case 0xF9 : //absolute,Y
                Console.print("Unimplemented Opcode SBC absolute,Y");
                emuCORE.isRunning = false;
                break;
            case 0xE1 : //(indirect,X)
                Console.print("Unimplemented Opcode SBC (indirect,X)");
                emuCORE.isRunning = false;
                break;
            case 0xF1 : //(indirect),Y
                Console.print("Unimplemented Opcode SBC (indirect),Y");
                emuCORE.isRunning = false;
                break;
            /*
             *        Shift / Rotate
             */
            /************** ASL **************/
            case 0x0A : //(Accumulator)
                Console.print("Unimplemented Opcode ASL (Accumulator)");
                emuCORE.isRunning = false;
                break;
            case 0x06 : //Zero Page
                Console.print("Unimplemented Opcode ASL Zero Page");
                emuCORE.isRunning = false;
                break;
            case 0x16 : //Zero Page, X
                Console.print("Unimplemented Opcode ASL Zero Page, X");
                emuCORE.isRunning = false;
                break;
            case 0x0E : //Absolute
                Console.print("Unimplemented Opcode ASL Absolute");
                emuCORE.isRunning = false;
                break;
            case 0x1E : //Absolute, X
                Console.print("Unimplemented Opcode ASL Absolute, X");
                emuCORE.isRunning = false;
                break;
            /************** LSR **************/
            case 0x4A : //accumulator
                Console.print("Unimplemented Opcode LSR accumulator");
                emuCORE.isRunning = false;
                break;
            case 0x46 : //zeropage
                Console.print("Unimplemented Opcode LSR zeropage");
                emuCORE.isRunning = false;
                break;
            case 0x56 : //zeropage,X
                Console.print("Unimplemented Opcode LSR zeropage,X");
                emuCORE.isRunning = false;
                break;
            case 0x4E : //absolute
                Console.print("Unimplemented Opcode LSR absolute");
                emuCORE.isRunning = false;
                break;
            case 0x5E : //absolute,X
                Console.print("Unimplemented Opcode LSR absolute,X");
                emuCORE.isRunning = false;
                break;
            /************** ROL **************/
            case 0x2A : //accumulator
                ACCUMULATOR.ROL();
                cycle = 2;
                break;
            case 0x26 : //zeropage
                ZEROPAGE.ROL();
                cycle = 5;
                break;
            case 0x36 : //zeropage,X
                ZEROPAGE_X.ROL();
                cycle = 6;
                break;
            case 0x2E : //absolute
                ABSOLUTE.ROL();
                cycle = 6;
                break;
            case 0x3E : //absolute,X
                ABSOLUTE_X.ROL();
                cycle = 7;
                break;
            /************** ROR **************/
            case 0x6A : //accumulator
                Console.print("Unimplemented Opcode ROR accumulator");
                emuCORE.isRunning = false;
                break;
            case 0x66 : //zeropage
                Console.print("Unimplemented Opcode ROR zeropage");
                emuCORE.isRunning = false;
                break;
            case 0x76 : //zeropage,X
                Console.print("Unimplemented Opcode ROR zeropage,X");
                emuCORE.isRunning = false;
                break;
            case 0x6E : //absolute
                Console.print("Unimplemented Opcode ROR absolute");
                emuCORE.isRunning = false;
                break;
            case 0x7E : //absolute,X
                Console.print("Unimplemented Opcode ROR absolute,X");
                emuCORE.isRunning = false;
                break;
            /*
             *        Increment / Decrement
             */
            /************** INC **************/
            case 0xE6 : //Zero Page
                Console.print("Unimplemented Opcode INC Zero Page");
                emuCORE.isRunning = false;
                break;
            case 0xF6 : //Zero Page, X
                Console.print("Unimplemented Opcode INC Zero Page, X");
                emuCORE.isRunning = false;
                break;
            case 0xEE : //Absolute
                Console.print("Unimplemented Opcode INC Absolute");
                emuCORE.isRunning = false;
                break;
            case 0xFE : //Absolute, X
                Console.print("Unimplemented Opcode INC Absolute, X");
                emuCORE.isRunning = false;
                break;
            /************** INX **************/
            case 0xE8 : //Implied
                IMPLIED.INX();
                cycle = 2;
                break;
            /************** INY **************/
            case 0xC8 : //Implied
                Console.print("Unimplemented Opcode INY Implied");
                emuCORE.isRunning = false;
                break;
            /************** DEC **************/
            case 0xC6 : //Zero Page
                ZEROPAGE.DEC();
                cycle = 5;
                break;
            case 0xD6 : //Zero Page, X
                Console.print("Unimplemented Opcode DEC Zero Page, X");
                emuCORE.isRunning = false;
                break;
            case 0xCE : //Absolute
                Console.print("Unimplemented Opcode DEC Absolute");
                emuCORE.isRunning = false;
                break;
            case 0xDE : //Absolute, X
            /************** DEX **************/
            case 0xCA : //Implied
                IMPLIED.DEX();
                cycle = 2;
                break;
            /************** DEY **************/
            case 0x88 : //Implied
                IMPLIED.DEY();
                cycle = 2;
                break;
            /*
             *        Load and Store Instructions
             */
            /************** LDA **************/
            case 0xA9 : //Immidiate
                IMMIDIATE.LDA();
                cycle = 2;
                break;
            case 0xA5 : //Zero Page
                ZEROPAGE.LDA();
                cycle = 3;
                break;
            case 0xB5 : //Zero Page, X
                Console.print("Unimplemented Opcode LDA Zero Page, X");
                emuCORE.isRunning = false;
                break;
            case 0xAD : //Absolute
                ABSOLUTE.LDA();
                cycle = 4;
                break;
            case 0xBD : //Absolute, X
                cycle = ABSOLUTE_X.LDA();
                break;
            case 0xB9 : //Absolute, Y
                Console.print("Unimplemented Opcode LDA Absolute, Y");
                emuCORE.isRunning = false;
                break;
            case 0xA1 : //(Indirect, X)
                Console.print("Unimplemented Opcode LDA (Indirect, X)");
                emuCORE.isRunning = false;
                break;
            case 0xB1 : //(Indirect), Y
                Console.print("Unimplemented Opcode LDA (Indirect), Y");
                emuCORE.isRunning = false;
                break;
            /************** LDX **************/
            case 0xA2 : //Immediate
                IMMIDIATE.LDX();
                cycle = 2;
                break;
            case 0xA6 : //Zero Page
                Console.print("Unimplemented Opcode LDX Zero Page");
                emuCORE.isRunning = false;
                break;
            case 0xB6 : //Zero Page, Y
                Console.print("Unimplemented Opcode LDX Zero Page, Y");
                emuCORE.isRunning = false;
                break;
            case 0xAE : //Absolute
                Console.print("Unimplemented Opcode LDX Absolute");
                emuCORE.isRunning = false;
                break;
            case 0xBE : //Absolute, Y
                Console.print("Unimplemented Opcode LDX Absolute, Y");
                emuCORE.isRunning = false;
                break;
            /************** LDY **************/
            case 0xA0 : //Immediate
                IMMIDIATE.LDY();
                cycle = 2;
                break;
            case 0xA4 : //Zero Page
                Console.print("Unimplemented Opcode LDY Zero Page");
                emuCORE.isRunning = false;
                break;
            case 0xB4 : //Zero Page, X
                Console.print("Unimplemented Opcode LDY Zero Page, X");
                emuCORE.isRunning = false;
                break;
            case 0xAC : //Absolute
                Console.print("Unimplemented Opcode LDY Absolute");
                emuCORE.isRunning = false;
                break;
            case 0xBC : //Absolute, X
                Console.print("Unimplemented Opcode LDY Absolute, X");
                emuCORE.isRunning = false;
                break;
            /************** STA **************/
            case 0x85 : //Zero Page
                ZEROPAGE.STA();
                cycle = 3;
                break;
            case 0x95 : //Zero Page,X
                Console.print("Unimplemented Opcode STA Zero Page,X");
                emuCORE.isRunning = false;
                break;
            case 0x8D : //Absolute
                ABSOLUTE.STA();
                cycle = 3;
                break;
            case 0x9D : //Absolute,X
                Console.print("Unimplemented Opcode STA Absolute,X");
                emuCORE.isRunning = false;
                break;
            case 0x99 : //Absolute,Y
                Console.print("Unimplemented Opcode STA Absolute,Y");
                emuCORE.isRunning = false;
                break;
            case 0x81 : //(Indirect,X)
                Console.print("Unimplemented Opcode STA (Indirect,X)");
                emuCORE.isRunning = false;
                break;
            case 0x91 : //(Indirect),Y
                INDIRECT_Y.STA();
                cycle = 6;
                break;
            /************** STX **************/
            case 0x86 : //Zero Page
                Console.print("Unimplemented Opcode STX Zero Page");
                emuCORE.isRunning = false;
                break;
            case 0x96 : //Zero Page,Y
                Console.print("Unimplemented Opcode STX Zero Page,Y");
                emuCORE.isRunning = false;
                break;
            case 0x8E : //Absolute
                Console.print("Unimplemented Opcode STX Absolute");
                emuCORE.isRunning = false;
                break;
            /************** STY **************/
            case 0x84 : //Zero Page
                ZEROPAGE.STY();
                cycle = 3;
                break;
            case 0x94 : //Zero Page,X
                ZEROPAGE_X.STY();
                cycle = 4;
                break;
            case 0x8C : //Absolute
                ABSOLUTE.STY();
                cycle = 4;
                break;
            /*
             *        Logical Instructions
             */
            /************** AND **************/
            case 0x29 : //Immediate
                IMMIDIATE.AND();
                cycle = 2;
                break;
            case 0x25 : //Zero Page
                ZEROPAGE.AND();
                cycle = 3;
                break;
            case 0x35 : //Zero Page, X
                Console.print("Unimplemented Opcode AND Zero Page, X");
                emuCORE.isRunning = false;
                break;
            case 0x2D : //Absolute
                Console.print("Unimplemented Opcode AND Absolute");
                emuCORE.isRunning = false;
                break;
            case 0x3D : //Absolute, X
                Console.print("Unimplemented Opcode AND Absolute, X");
                emuCORE.isRunning = false;
                break;
            case 0x39 : //Absolute, Y
                Console.print("Unimplemented Opcode AND Absolute, Y");
                emuCORE.isRunning = false;
                break;
            case 0x21 : //(Indirect, X)
                Console.print("Unimplemented Opcode AND (Indirect, X)");
                emuCORE.isRunning = false;
                break;
            case 0x31 : //(Indirect), Y
                Console.print("Unimplemented Opcode AND (Indirect), Y");
                emuCORE.isRunning = false;
                break;
            /************** EOR **************/
            case 0x49 : //Immediate
                Console.print("Unimplemented Opcode EOR Immediate");
                emuCORE.isRunning = false;
                break;
            case 0x45 : //Zero Page
                Console.print("Unimplemented Opcode EOR Zero Page");
                emuCORE.isRunning = false;
                break;
            case 0x55 : //Zero Page, X
                Console.print("Unimplemented Opcode EOR Zero Page, X");
                emuCORE.isRunning = false;
                break;
            case 0x4D : //Absolute
                Console.print("Unimplemented Opcode EOR Absolute");
                emuCORE.isRunning = false;
                break;
            case 0x5D : //Absolute, X
                Console.print("Unimplemented Opcode EOR Absolute, X");
                emuCORE.isRunning = false;
                break;
            case 0x59 : //Absolute, Y
                Console.print("Unimplemented Opcode EOR Absolute, Y");
                emuCORE.isRunning = false;
                break;
            case 0x41 : //(Indirect, X)
                Console.print("Unimplemented Opcode EOR (Indirect, X)");
                emuCORE.isRunning = false;
                break;
            case 0x51 : //(Indirect), Y
                Console.print("Unimplemented Opcode EOR (Indirect), Y");
                emuCORE.isRunning = false;
                break;
            /************** ORA **************/
            case 0x09 : //Immediate
                Console.print("Unimplemented Opcode ORA Immediate");
                emuCORE.isRunning = false;
                break;
            case 0x05 : //Zero Page
                Console.print("Unimplemented Opcode ORA Zero Page");
                emuCORE.isRunning = false;
                break;
            case 0x15 : //Zero Page,X
                Console.print("Unimplemented Opcode ORA Zero Page,X");
                emuCORE.isRunning = false;
                break;
            case 0x0D : //Absolute
                Console.print("Unimplemented Opcode ORA Absolute");
                emuCORE.isRunning = false;
                break;
            case 0x1D : //Absolute,X
                Console.print("Unimplemented Opcode ORA Absolute,X");
                emuCORE.isRunning = false;
                break;
            case 0x19 : //Absolute,Y
                Console.print("Unimplemented Opcode ORA Absolute,Y");
                emuCORE.isRunning = false;
                break;
            case 0x01 : //(Indirect,X)
                Console.print("Unimplemented Opcode ORA (Indirect,X)");
                emuCORE.isRunning = false;
                break;
            case 0x11 : //(Indirect),Y
                Console.print("Unimplemented Opcode ORA (Indirect),Y");
                emuCORE.isRunning = false;
                break;
            /*
             *        Jump, Branching, Subroutine Instructions
             */
            /************** JMP **************/
            case 0x4C : //Absolute
                ABSOLUTE.JMP();
                cycle = 3;
                break;
            case 0x6C : //Indirect
                INDIRECT.JMP();
                cycle = 5;
                break;
            /************** BCC **************/
            case 0x90 : //Relative
                cycle = RELATIVE.BCC();
                break;
            /************** BCS **************/
            case 0xB0 : //Relative
                cycle = RELATIVE.BCS();
                break;
            /************** BEQ **************/
            case 0xF0 : //Relative
                cycle = RELATIVE.BEQ();
                break;
            /************** BMI **************/
            case 0x30 : //Relative
                cycle = RELATIVE.BMI();
                break;
            /************** BNE **************/
            case 0xD0 : //Relative
                cycle = RELATIVE.BNE();
                break;
            /************** BPL **************/
            case 0x10 : //Relative
                cycle = RELATIVE.BPL();
                break;
            /************** BVC **************/
            case 0x50 : //Relative
                cycle = RELATIVE.BVC();
                break;
            /************** BVS **************/
            case 0x70 : //Relative
                cycle = RELATIVE.BVS();
                break;
            /************** JSR **************/
            case 0x20 : //Absolute
                ABSOLUTE.JSR();
                cycle = 6;
                break;
            /************** RTI **************/
            case 0x40 : //implied
                Console.print("Unimplemented Opcode RTI implied");
                emuCORE.isRunning = false;
                break;
            /************** RTS **************/
            case 0x60 : //implied
                IMPLIED.RTS();
                cycle = 6;
                break;
            /*
             *        Compare / Test Bits Instructions
             */
            /************** CMP **************/
            case 0xC9 : //Immediate
                IMMIDIATE.CMP();
                cycle = 2;
                break;
            case 0xC5 : //Zero Page
                Console.print("Unimplemented Opcode CMP Zero Page");
                emuCORE.isRunning = false;
                break;
            case 0xD5 : //Zero Page, X
                Console.print("Unimplemented Opcode CMP Zero Page, X");
                emuCORE.isRunning = false;
                break;
            case 0xCD : //Absolute
                Console.print("Unimplemented Opcode CMP Absolute");
                emuCORE.isRunning = false;
                break;
            case 0xDD : //Absolute, X
                Console.print("Unimplemented Opcode CMP Absolute, X");
                emuCORE.isRunning = false;
                break;
            case 0xD9 : //Absolute, Y
                Console.print("Unimplemented Opcode CMP Absolute, Y");
                emuCORE.isRunning = false;
                break;
            case 0xC1 : //(Indirect, X)
                Console.print("Unimplemented Opcode CMP (Indirect, X)");
                emuCORE.isRunning = false;
                break;
            case 0xD1 : //(Indirect), Y
                Console.print("Unimplemented Opcode CMP (Indirect), Y");
                emuCORE.isRunning = false;
                break;
            /************** CPX **************/
            case 0xE0 : //Immediate
                Console.print("Unimplemented Opcode CPX Immediate");
                emuCORE.isRunning = false;
                break;
            case 0xE4 : //Zero Page
                Console.print("Unimplemented Opcode CPX Zero Page");
                emuCORE.isRunning = false;
                break;
            case 0xEC : //Absolute
                Console.print("Unimplemented Opcode CPX Absolute");
                emuCORE.isRunning = false;
                break;
            /************** CPY **************/
            case 0xC0 : //immidiate
                Console.print("Unimplemented Opcode CPY immidiate");
                emuCORE.isRunning = false;
                break;
            case 0xC4 : //zeropage
                Console.print("Unimplemented Opcode CPY zeropage");
                emuCORE.isRunning = false;
                break;
            case 0xCC : //absolute
                Console.print("Unimplemented Opcode CPY absolute");
                emuCORE.isRunning = false;
                break;
            /************** BIT **************/
            case 0x24 : //zeropage
                Console.print("Unimplemented Opcode BIT zeropage");
                emuCORE.isRunning = false;
                break;
            case 0x2C : //absolute
                Console.print("Unimplemented Opcode BIT absolute");
                emuCORE.isRunning = false;
                break;
            /*
             *        Uncategorized Instructions
             */
            /************** BRL **************/ //<< FIXME: needs attention.
            /************** CLC **************/
            case 0x18 : //Implied
                IMPLIED.CLC();
                cycle = 2;
                break;
            /************** CLD **************/
            case 0xD8 : //Implied
                IMPLIED.CLD();
                cycle = 2;
                break;
            /************** CLI **************/
            case 0x58 : //Implied
                IMPLIED.CLI();
                cycle = 2;
                break;
            /************** CLV **************/
            case 0xB8 : //Implied
                IMPLIED.CLV();
                cycle = 2;
                break;
            /************** NOP **************/
            case 0xEA : //implied
                IMPLIED.NOP();
                cycle = 2;
                break;
            /************** PHA **************/
            case 0x48 : //implied
                IMPLIED.PHA();
                cycle = 3;
                break;
            /************** PHP **************/
            case 0x08 : //implied
                IMPLIED.PHP();
                cycle = 3;
                break;
            /************** PLA **************/
            case 0x68 : //implied
                IMPLIED.PLA();
                cycle = 3;
                break;
            /************** PLP **************/
            case 0x28 : //implied
                IMPLIED.PLP();
                cycle = 4;
                break;
            /************** SEC **************/
            case 0x38 : //implied
                IMPLIED.SEC();
                cycle = 2;
                break;
            /************** SED **************/
            case 0xF8 : //implied
                IMPLIED.SED();
                cycle = 2;
                break;
            /************** SEI **************/
            case 0x78 : //implied
                IMPLIED.SEI();
                cycle = 2;
                break;
            /************** TAX **************/
            case 0xAA : //implied
                IMPLIED.TAX();
                cycle = 2;
                break;
            /************** TAY **************/
            case 0xA8 : //implied
                IMPLIED.TAY();
                cycle = 2;
                break;
            /************** TSX **************/
            case 0xBA : //implied
                IMPLIED.TSX();
                cycle = 2;
                break;
            /************** TXA **************/
            case 0x8A : //implied
                IMPLIED.TXA();
                cycle = 2;
                break;
            /************** TYA **************/
            case 0x98 : //implied
                IMPLIED.TYA();
                cycle = 2;
                break;
            /************** TXS **************/
            case 0x9A : //implied
                IMPLIED.TXS();
                cycle = 2;
                break;
            /************** BRK **************/
            case 0x00 :
                Console.print("Unimplemented Opcode BRK");
                emuCORE.isRunning = false;
                break;
            /************** Unknown Opcode **************/
            default :
                Console.print("[WARNING] Unknown Opcode [" + Integer.toHexString(opcode) + "] at " + Integer.toHexString(CPU_REGISTER.PC));
                Console.print("Executing NOP...");
                IMPLIED.NOP();
                cycle = 2;
                break;
        }
        return cycle;
    }
}

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
    public static void init()
    {
        REGISTER.A = 0;
        REGISTER.SP = 0;
        REGISTER.PC = MEMORY.getInitialPC();
        REGISTER.X = 0;
        REGISTER.Y = 0;
        REGISTER.SR = 0;
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
                Console.print("Unimplemented Opcode ADC");
                emuCORE.isRunning = false;
                break;
            case 0x75 : //Zero Page, X
                Console.print("Unimplemented Opcode ADC");
                emuCORE.isRunning = false;
                break;
            case 0x6D : //Absolute
                Console.print("Unimplemented Opcode ADC");
                emuCORE.isRunning = false;
                break;
            case 0x7D : //Absolute, X
                Console.print("Unimplemented Opcode ADC");
                emuCORE.isRunning = false;
                break;
            case 0x79 : //Absolute, Y
                Console.print("Unimplemented Opcode ADC");
                emuCORE.isRunning = false;
                break;
            case 0x61 : //(Indirect, X)
                Console.print("Unimplemented Opcode ADC");
                emuCORE.isRunning = false;
                break;
            case 0x71 : //(Indirect), Y
                Console.print("Unimplemented Opcode ADC");
                emuCORE.isRunning = false;
                break;
            /************** SBC **************/
            case 0xE9 : //immidiate
                Console.print("Unimplemented Opcode SBC");
                emuCORE.isRunning = false;
                break;
            case 0xE5 : //zeropage
                Console.print("Unimplemented Opcode SBC");
                emuCORE.isRunning = false;
                break;
            case 0xF5 : //zeropage,X
                Console.print("Unimplemented Opcode SBC");
                emuCORE.isRunning = false;
                break;
            case 0xED : //absolute
                Console.print("Unimplemented Opcode SBC");
                emuCORE.isRunning = false;
                break;
            case 0xFD : //absolute,X
                Console.print("Unimplemented Opcode SBC");
                emuCORE.isRunning = false;
                break;
            case 0xF9 : //absolute,Y
                Console.print("Unimplemented Opcode SBC");
                emuCORE.isRunning = false;
                break;
            case 0xE1 : //(indirect,X)
                Console.print("Unimplemented Opcode SBC");
                emuCORE.isRunning = false;
                break;
            case 0xF1 : //(indirect),Y
                Console.print("Unimplemented Opcode SBC");
                emuCORE.isRunning = false;
                break;
            /*
             *        Shift / Rotate
             */
            /************** ASL **************/
            case 0x0A : //(Accumulator)
                Console.print("Unimplemented Opcode ASL");
                emuCORE.isRunning = false;
                break;
            case 0x06 : //Zero Page
                Console.print("Unimplemented Opcode ASL");
                emuCORE.isRunning = false;
                break;
            case 0x16 : //Zero Page, X
                Console.print("Unimplemented Opcode ASL");
                emuCORE.isRunning = false;
                break;
            case 0x0E : //Absolute
                Console.print("Unimplemented Opcode ASL");
                emuCORE.isRunning = false;
                break;
            case 0x1E : //Absolute, X
                Console.print("Unimplemented Opcode ASL");
                emuCORE.isRunning = false;
                break;
            /************** LSR **************/
            case 0x4A : //accumulator
                Console.print("Unimplemented Opcode LSR");
                emuCORE.isRunning = false;
                break;
            case 0x46 : //zeropage
                Console.print("Unimplemented Opcode LSR");
                emuCORE.isRunning = false;
                break;
            case 0x56 : //zeropage,X
                Console.print("Unimplemented Opcode LSR");
                emuCORE.isRunning = false;
                break;
            case 0x4E : //absolute
                Console.print("Unimplemented Opcode LSR");
                emuCORE.isRunning = false;
                break;
            case 0x5E : //absolute,X
                Console.print("Unimplemented Opcode LSR");
                emuCORE.isRunning = false;
                break;
            /************** ROL **************/
            case 0x2A : //accumulator
                Console.print("Unimplemented Opcode ROL");
                emuCORE.isRunning = false;
                break;
            case 0x26 : //zeropage
                Console.print("Unimplemented Opcode ROL");
                emuCORE.isRunning = false;
                break;
            case 0x36 : //zeropage,X
                Console.print("Unimplemented Opcode ROL");
                emuCORE.isRunning = false;
                break;
            case 0x2E : //absolute
                Console.print("Unimplemented Opcode ROL");
                emuCORE.isRunning = false;
                break;
            case 0x3E : //absolute,X
                Console.print("Unimplemented Opcode ROL");
                emuCORE.isRunning = false;
                break;
            /************** ROR **************/
            case 0x6A : //accumulator
                Console.print("Unimplemented Opcode ROR");
                emuCORE.isRunning = false;
                break;
            case 0x66 : //zeropage
                Console.print("Unimplemented Opcode ROR");
                emuCORE.isRunning = false;
                break;
            case 0x76 : //zeropage,X
                Console.print("Unimplemented Opcode ROR");
                emuCORE.isRunning = false;
                break;
            case 0x6E : //absolute
                Console.print("Unimplemented Opcode ROR");
                emuCORE.isRunning = false;
                break;
            case 0x7E : //absolute,X
                Console.print("Unimplemented Opcode ROR");
                emuCORE.isRunning = false;
                break;
            /*
             *        Increment / Decrement
             */
            /************** INC **************/
            case 0xE6 : //Zero Page
                Console.print("Unimplemented Opcode INC");
                emuCORE.isRunning = false;
                break;
            case 0xF6 : //Zero Page, X
                Console.print("Unimplemented Opcode INC");
                emuCORE.isRunning = false;
                break;
            case 0xEE : //Absolute
                Console.print("Unimplemented Opcode INC");
                emuCORE.isRunning = false;
                break;
            case 0xFE : //Absolute, X
                Console.print("Unimplemented Opcode INC");
                emuCORE.isRunning = false;
                break;
            /************** INX **************/
            case 0xE8 : //Implied
                Console.print("Unimplemented Opcode INX");
                emuCORE.isRunning = false;
                break;
            /************** INY **************/
            case 0xC8 : //Implied
                Console.print("Unimplemented Opcode INY");
                emuCORE.isRunning = false;
                break;
            /************** DEC **************/
            case 0xC6 : //Zero Page
                Console.print("Unimplemented Opcode DEC");
                emuCORE.isRunning = false;
                break;
            case 0xD6 : //Zero Page, X
                Console.print("Unimplemented Opcode DEC");
                emuCORE.isRunning = false;
                break;
            case 0xCE : //Absolute
                Console.print("Unimplemented Opcode DEC");
                emuCORE.isRunning = false;
                break;
            case 0xDE : //Absolute, X
            /************** DEX **************/
            case 0xCA : //Implied
                Console.print("Unimplemented Opcode DEX");
                emuCORE.isRunning = false;
                break;
            /************** DEY **************/
            case 0x88 : //Implied
                Console.print("Unimplemented Opcode DEY");
                emuCORE.isRunning = false;
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
                Console.print("Unimplemented Opcode LDA");
                emuCORE.isRunning = false;
                break;
            case 0xB5 : //Zero Page, X
                Console.print("Unimplemented Opcode LDA");
                emuCORE.isRunning = false;
                break;
            case 0xAD : //Absolute
                ABSOLUTE.LDA();
                cycle = 4;
                break;
            case 0xBD : //Absolute, X
                Console.print("Unimplemented Opcode LDA");
                emuCORE.isRunning = false;
                break;
            case 0xB9 : //Absolute, Y
                Console.print("Unimplemented Opcode LDA");
                emuCORE.isRunning = false;
                break;
            case 0xA1 : //(Indirect, X)
                Console.print("Unimplemented Opcode LDA");
                emuCORE.isRunning = false;
                break;
            case 0xB1 : //(Indirect), Y
                Console.print("Unimplemented Opcode LDA");
                emuCORE.isRunning = false;
                break;
            /************** LDX **************/
            case 0xA2 : //Immediate
                IMMIDIATE.LDX();
                cycle = 2;
                break;
            case 0xA6 : //Zero Page
                Console.print("Unimplemented Opcode LDX");
                emuCORE.isRunning = false;
                break;
            case 0xB6 : //Zero Page, Y
                Console.print("Unimplemented Opcode LDX");
                emuCORE.isRunning = false;
                break;
            case 0xAE : //Absolute
                Console.print("Unimplemented Opcode LDX");
                emuCORE.isRunning = false;
                break;
            case 0xBE : //Absolute, Y
                Console.print("Unimplemented Opcode LDX");
                emuCORE.isRunning = false;
                break;
            /************** LDY **************/
            case 0xA0 : //Immediate
                Console.print("Unimplemented Opcode LDY");
                emuCORE.isRunning = false;
                break;
            case 0xA4 : //Zero Page
                Console.print("Unimplemented Opcode LDY");
                emuCORE.isRunning = false;
                break;
            case 0xB4 : //Zero Page, X
                Console.print("Unimplemented Opcode LDY");
                emuCORE.isRunning = false;
                break;
            case 0xAC : //Absolute
                Console.print("Unimplemented Opcode LDY");
                emuCORE.isRunning = false;
                break;
            case 0xBC : //Absolute, X
                Console.print("Unimplemented Opcode LDY");
                emuCORE.isRunning = false;
                break;
            /************** STA **************/
            case 0x85 : //Zero Page
                Console.print("Unimplemented Opcode STA");
                emuCORE.isRunning = false;
                break;
            case 0x95 : //Zero Page,X
                Console.print("Unimplemented Opcode STA");
                emuCORE.isRunning = false;
                break;
            case 0x8D : //Absolute
                ABSOLUTE.STA();
                cycle = 3;
                break;
            case 0x9D : //Absolute,X
                Console.print("Unimplemented Opcode STA");
                emuCORE.isRunning = false;
                break;
            case 0x99 : //Absolute,Y
                Console.print("Unimplemented Opcode STA");
                emuCORE.isRunning = false;
                break;
            case 0x81 : //(Indirect,X)
                Console.print("Unimplemented Opcode STA");
                emuCORE.isRunning = false;
                break;
            case 0x91 : //(Indirect),Y
                Console.print("Unimplemented Opcode STA");
                emuCORE.isRunning = false;
                break;
            /************** STX **************/
            case 0x86 : //Zero Page
                Console.print("Unimplemented Opcode STX");
                emuCORE.isRunning = false;
                break;
            case 0x96 : //Zero Page,Y
                Console.print("Unimplemented Opcode STX");
                emuCORE.isRunning = false;
                break;
            case 0x8E : //Absolute
                Console.print("Unimplemented Opcode STX");
                emuCORE.isRunning = false;
                break;
            /************** STY **************/
            case 0x84 : //Zero Page
                Console.print("Unimplemented Opcode STY");
                emuCORE.isRunning = false;
                break;
            case 0x94 : //Zero Page,X
                Console.print("Unimplemented Opcode STY");
                emuCORE.isRunning = false;
                break;
            case 0x8C : //Absolute
                Console.print("Unimplemented Opcode STY");
                emuCORE.isRunning = false;
                break;
            /*
             *        Logical Instructions
             */
            /************** AND **************/
            case 0x29 : //Immediate
                Console.print("Unimplemented Opcode AND");
                emuCORE.isRunning = false;
                break;
            case 0x25 : //Zero Page
                Console.print("Unimplemented Opcode AND");
                emuCORE.isRunning = false;
                break;
            case 0x35 : //Zero Page, X
                Console.print("Unimplemented Opcode AND");
                emuCORE.isRunning = false;
                break;
            case 0x2D : //Absolute
                Console.print("Unimplemented Opcode AND");
                emuCORE.isRunning = false;
                break;
            case 0x3D : //Absolute, X
                Console.print("Unimplemented Opcode AND");
                emuCORE.isRunning = false;
                break;
            case 0x39 : //Absolute, Y
                Console.print("Unimplemented Opcode AND");
                emuCORE.isRunning = false;
                break;
            case 0x21 : //(Indirect, X)
                Console.print("Unimplemented Opcode AND");
                emuCORE.isRunning = false;
                break;
            case 0x31 : //(Indirect), Y
                Console.print("Unimplemented Opcode AND");
                emuCORE.isRunning = false;
                break;
            /************** EOR **************/
            case 0x49 : //Immediate
                Console.print("Unimplemented Opcode EOR");
                emuCORE.isRunning = false;
                break;
            case 0x45 : //Zero Page
                Console.print("Unimplemented Opcode EOR");
                emuCORE.isRunning = false;
                break;
            case 0x55 : //Zero Page, X
                Console.print("Unimplemented Opcode EOR");
                emuCORE.isRunning = false;
                break;
            case 0x4D : //Absolute
                Console.print("Unimplemented Opcode EOR");
                emuCORE.isRunning = false;
                break;
            case 0x5D : //Absolute, X
                Console.print("Unimplemented Opcode EOR");
                emuCORE.isRunning = false;
                break;
            case 0x59 : //Absolute, Y
                Console.print("Unimplemented Opcode EOR");
                emuCORE.isRunning = false;
                break;
            case 0x41 : //(Indirect, X)
                Console.print("Unimplemented Opcode EOR");
                emuCORE.isRunning = false;
                break;
            case 0x51 : //(Indirect), Y
                Console.print("Unimplemented Opcode EOR");
                emuCORE.isRunning = false;
                break;
            /************** ORA **************/
            case 0x09 : //Immediate
                Console.print("Unimplemented Opcode ORA");
                emuCORE.isRunning = false;
                break;
            case 0x05 : //Zero Page
                Console.print("Unimplemented Opcode ORA");
                emuCORE.isRunning = false;
                break;
            case 0x15 : //Zero Page,X
                Console.print("Unimplemented Opcode ORA");
                emuCORE.isRunning = false;
                break;
            case 0x0D : //Absolute
                Console.print("Unimplemented Opcode ORA");
                emuCORE.isRunning = false;
                break;
            case 0x1D : //Absolute,X
                Console.print("Unimplemented Opcode ORA");
                emuCORE.isRunning = false;
                break;
            case 0x19 : //Absolute,Y
                Console.print("Unimplemented Opcode ORA");
                emuCORE.isRunning = false;
                break;
            case 0x01 : //(Indirect,X)
                Console.print("Unimplemented Opcode ORA");
                emuCORE.isRunning = false;
                break;
            case 0x11 : //(Indirect),Y
                Console.print("Unimplemented Opcode ORA");
                emuCORE.isRunning = false;
                break;
            /*
             *        Jump, Branching, Subroutine Instructions
             */
            /************** JMP **************/
            case 0x4C : //Absolute
                Console.print("Unimplemented Opcode JMP");
                emuCORE.isRunning = false;
                break;
            case 0x6C : //Indirect
                Console.print("Unimplemented Opcode JMP");
                emuCORE.isRunning = false;
                break;
            /************** BCC **************/
            case 0x90 : //Relative
                Console.print("Unimplemented Opcode BCC");
                emuCORE.isRunning = false;
                break;
            /************** BCS **************/
            case 0xB0 : //Relative
                Console.print("Unimplemented Opcode BCS");
                emuCORE.isRunning = false;
                break;
            /************** BEQ **************/
            case 0xF0 : //Relative
                Console.print("Unimplemented Opcode BEQ");
                emuCORE.isRunning = false;
                break;
            /************** BMI **************/
            case 0x30 : //Relative
                Console.print("Unimplemented Opcode BMI");
                emuCORE.isRunning = false;
                break;
            /************** BNE **************/
            case 0xD0 : //Relative
                Console.print("Unimplemented Opcode BNE");
                emuCORE.isRunning = false;
                break;
            /************** BPL **************/
            case 0x10 : //Relative
                tmp = RELATIVE.BPL();
                cycle = tmp;
                tmp = 0;
                break;
            /************** BVC **************/
            case 0x50 : //Relative
                Console.print("Unimplemented Opcode BVC");
                emuCORE.isRunning = false;
                break;
            /************** BVS **************/
            case 0x70 : //Relative
                Console.print("Unimplemented Opcode BVS");
                emuCORE.isRunning = false;
                break;
            /************** JSR **************/
            case 0x20 : //Absolute
                Console.print("Unimplemented Opcode JRS");
                emuCORE.isRunning = false;
                break;
            /************** RTI **************/
            case 0x40 : //implied
                Console.print("Unimplemented Opcode RTI");
                emuCORE.isRunning = false;
                break;
            /************** RTS **************/
            case 0x60 : //implied
                Console.print("Unimplemented Opcode RTS");
                emuCORE.isRunning = false;
                break;
            /*
             *        Compare / Test Bits Instructions
             */
            /************** CMP **************/
            case 0xC9 : //Immediate
                Console.print("Unimplemented Opcode CMP");
                emuCORE.isRunning = false;
                break;
            case 0xC5 : //Zero Page
                Console.print("Unimplemented Opcode CMP");
                emuCORE.isRunning = false;
                break;
            case 0xD5 : //Zero Page, X
                Console.print("Unimplemented Opcode CMP");
                emuCORE.isRunning = false;
                break;
            case 0xCD : //Absolute
                Console.print("Unimplemented Opcode CMP");
                emuCORE.isRunning = false;
                break;
            case 0xDD : //Absolute, X
                Console.print("Unimplemented Opcode CMP");
                emuCORE.isRunning = false;
                break;
            case 0xD9 : //Absolute, Y
                Console.print("Unimplemented Opcode CMP");
                emuCORE.isRunning = false;
                break;
            case 0xC1 : //(Indirect, X)
                Console.print("Unimplemented Opcode CMP");
                emuCORE.isRunning = false;
                break;
            case 0xD1 : //(Indirect), Y
                Console.print("Unimplemented Opcode CMP");
                emuCORE.isRunning = false;
                break;
            /************** CPX **************/
            case 0xE0 : //Immediate
                Console.print("Unimplemented Opcode CPX");
                emuCORE.isRunning = false;
                break;
            case 0xE4 : //Zero Page
                Console.print("Unimplemented Opcode CPX");
                emuCORE.isRunning = false;
                break;
            case 0xEC : //Absolute
                Console.print("Unimplemented Opcode CPX");
                emuCORE.isRunning = false;
                break;
            /************** CPY **************/
            case 0xC0 : //immidiate
                Console.print("Unimplemented Opcode CPY");
                emuCORE.isRunning = false;
                break;
            case 0xC4 : //zeropage
                Console.print("Unimplemented Opcode CPY");
                emuCORE.isRunning = false;
                break;
            case 0xCC : //absolute
                Console.print("Unimplemented Opcode CPY");
                emuCORE.isRunning = false;
                break;
            /************** BIT **************/
            case 0x24 : //zeropage
                Console.print("Unimplemented Opcode BIT");
                emuCORE.isRunning = false;
                break;
            case 0x2C : //absolute
                Console.print("Unimplemented Opcode BIT");
                emuCORE.isRunning = false;
                break;
            /*
             *        Uncategorized Instructions
             */
            /************** BRL **************/ //<< FIXME: needs attention.
            /************** CLC **************/
            case 0x18 : //Implied
                Console.print("Unimplemented Opcode CLC");
                emuCORE.isRunning = false;
                break;
            /************** CLD **************/
            case 0xD8 : //Implied
                IMPLIED.CLD();
                cycle = 2;
                break;
            /************** CLI **************/
            case 0x58 : //Implied
                Console.print("Unimplemented Opcode CLI");
                emuCORE.isRunning = false;
                break;
            /************** CLV **************/
            case 0xB8 : //Implied
                Console.print("Unimplemented Opcode CLV");
                emuCORE.isRunning = false;
                break;
            /************** NOP **************/
            case 0xEA : //implied
                Console.print("Unimplemented Opcode NOP");
                emuCORE.isRunning = false;
                break;
            /************** PHA **************/
            case 0x48 : //implied
                Console.print("Unimplemented Opcode PHA");
                emuCORE.isRunning = false;
                break;
            /************** PHP **************/
            case 0x08 : //implied
                Console.print("Unimplemented Opcode PHP");
                emuCORE.isRunning = false;
                break;
            /************** PLA **************/
            case 0x68 : //implied
                Console.print("Unimplemented Opcode PLA");
                emuCORE.isRunning = false;
                break;
            /************** PLP **************/
            case 0x28 : //implied
                Console.print("Unimplemented Opcode PLP");
                emuCORE.isRunning = false;
                break;
            /************** SEC **************/
            case 0x38 : //implied
                Console.print("Unimplemented Opcode SEC");
                emuCORE.isRunning = false;
                break;
            /************** SED **************/
            case 0xF8 : //implied
                Console.print("Unimplemented Opcode SED");
                emuCORE.isRunning = false;
                break;
            /************** SEI **************/
            case 0x78 : //implied
                IMPLIED.SEI();
                cycle = 2;
                break;
            /************** TAX **************/
            case 0xAA : //implied
                Console.print("Unimplemented Opcode TAX");
                emuCORE.isRunning = false;
                break;
            /************** TAY **************/
            case 0xA8 : //implied
                Console.print("Unimplemented Opcode TAY");
                emuCORE.isRunning = false;
                break;
            /************** TSX **************/
            case 0xBA : //implied
                Console.print("Unimplemented Opcode TSX");
                emuCORE.isRunning = false;
                break;
            /************** TXA **************/
            case 0x8A : //implied
                Console.print("Unimplemented Opcode TXA");
                emuCORE.isRunning = false;
                break;
            /************** TYA **************/
            case 0x98 : //implied
                Console.print("Unimplemented Opcode TYA");
                emuCORE.isRunning = false;
                break;
            /************** TXS **************/
            case 0x9A : //implied
                Console.print("Unimplemented Opcode TXS");
                emuCORE.isRunning = false;
                break;
            /************** BRK **************/
            case 0x00 :
                Console.print("Unimplemented Opcode BRK");
                emuCORE.isRunning = false;
                break;
            /************** Unknown Opcode **************/
            default :
                Console.print("[ERROR] Unknown Opcode [" + Integer.toHexString(opcode) + "] at " + Integer.toHexString(REGISTER.PC));
                emuCORE.isRunning = false;
                break;
        }
        return cycle;
    }
}

/*      TODO:
 *
 *      1.)All the opcode should be listed here before implementation.
 *      2.)Implement the opcode.
 */

package CPU;

import jnemu.Console;

public class cpuCORE
{
    public static void init()
    {
        REGISTER.A = 0;
        REGISTER.P = 0;
        REGISTER.PC = (byte)0xFFFC; //start at RESTART VECTOR.
        REGISTER.S = 0;
        REGISTER.X = 0;
        REGISTER.Y = 0;
        
        CYCLE.init();
    }

    public static void exec(int opcode)
    {
        switch(opcode)
        {
            /*       >>Math Instructions<<
             *          Add / Subtract
             */
            /************** ADC **************/
            case 0x69 : //Immediate
                INSTRUCTIONS.immediateADC();
                break;
            case 0x65 : //Zero Page
                INSTRUCTIONS.zeroPageADC();
                break;
            case 0x75 : //Zero Page, X
                INSTRUCTIONS.zeroPageXADC();
                break;
            case 0x6D : //Absolute
                INSTRUCTIONS.absoluteADC();
                break;
            case 0x7D : //Absolute, X
                INSTRUCTIONS.absoluteXADC();
                break;
            case 0x79 : //Absolute, Y
                INSTRUCTIONS.absoluteYADC();
                break;
            case 0x61 : //(Indirect, X)
                INSTRUCTIONS.indirectXADC();
                break;
            case 0x71 : //(Indirect), Y
                INSTRUCTIONS.indirectYADC();
                break;
            /************** SBC **************/
            case 0xE9 : //immidiate
                break;
            case 0xE5 : //zeropage
                break;
            case 0xF5 : //zeropage,X
                break;
            case 0xED : //absolute
                break;
            case 0xFD : //absolute,X
                break;
            case 0xF9 : //absolute,Y
                break;
            case 0xE1 : //(indirect,X)
                break;
            case 0xF1 : //(indirect),Y
                break;
            /*
             *        Shift / Rotate
             */
            /************** ASL **************/
            case 0x0A : //(Accumulator)
                break;
            case 0x06 : //Zero Page
                break;
            case 0x16 : //Zero Page, X
                break;
            case 0x0E : //Absolute
                break;
            case 0x1E : //Absolute, X
                break;
            /************** LSR **************/
            case 0x4A : //accumulator
                break;
            case 0x46 : //zeropage
                break;
            case 0x56 : //zeropage,X
                break;
            case 0x4E : //absolute
                break;
            case 0x5E : //absolute,X
                break;
            /************** ROL **************/
            case 0x2A : //accumulator
                break;
            case 0x26 : //zeropage
                break;
            case 0x36 : //zeropage,X
                break;
            case 0x2E : //absolute
                break;
            case 0x3E : //absolute,X
                break;
            /************** ROR **************/
            case 0x6A : //accumulator
                break;
            case 0x66 : //zeropage
                break;
            case 0x76 : //zeropage,X
                break;
            case 0x6E : //absolute
                break;
            case 0x7E : //absolute,X
                break;
            /*
             *        Increment / Decrement
             */
            /************** INC **************/
            case 0xE6 : //Zero Page
                break;
            case 0xF6 : //Zero Page, X
                break;
            case 0xEE : //Absolute
                break;
            case 0xFE : //Absolute, X
                break;
            /************** INX **************/
            case 0xE8 : //Implied
                break;
            /************** INY **************/
            case 0xC8 : //Implied
                break;
            /************** DEC **************/
            case 0xC6 : //Zero Page
                break;
            case 0xD6 : //Zero Page, X
                break;
            case 0xCE : //Absolute
                break;
            case 0xDE : //Absolute, X
            /************** DEX **************/
            case 0xCA : //Implied
                break;
            /************** DEY **************/
            case 0x88 : //Implied
                break;
            /*
             *        Load and Store Instructions
             */
            /************** LDA **************/
            case 0xA9 : //Immediate
                break;
            case 0xA5 : //Zero Page
                break;
            case 0xB5 : //Zero Page, X
                break;
            case 0xAD : //Absolute
                break;
            case 0xBD : //Absolute, X
                break;
            case 0xB9 : //Absolute, Y
                break;
            case 0xA1 : //(Indirect, X)
                break;
            case 0xB1 : //(Indirect), Y
                break;
            /************** LDX **************/
            case 0xA2 : //Immediate
                break;
            case 0xA6 : //Zero Page
                break;
            case 0xB6 : //Zero Page, Y
                break;
            case 0xAE : //Absolute
                break;
            case 0xBE : //Absolute, Y
                break;
            /************** LDY **************/
            case 0xA0 : //Immediate
                break;
            case 0xA4 : //Zero Page
                break;
            case 0xB4 : //Zero Page, X
                break;
            case 0xAC : //Absolute
                break;
            case 0xBC : //Absolute, X
                break;
            /************** STA **************/
            case 0x85 : //Zero Page
                break;
            case 0x95 : //Zero Page,X
                break;
            case 0x80 : //Absolute
                break;
            case 0x9D : //Absolute,X
                break;
            case 0x99 : //Absolute,Y
                break;
            case 0x81 : //(Indirect,X)
                break;
            case 0x91 : //(Indirect),Y
                break;
            /************** STX **************/
            case 0x86 : //Zero Page
                break;
            case 0x96 : //Zero Page,Y
                break;
            case 0x8E : //Absolute
                break;
            /************** STY **************/
            case 0x84 : //Zero Page
                break;
            case 0x94 : //Zero Page,X
                break;
            case 0x8C : //Absolute
                break;
            /*
             *        Logical Instructions
             */
            /************** AND **************/
            case 0x29 : //Immediate
                break;
            case 0x25 : //Zero Page
                break;
            case 0x35 : //Zero Page, X
                break;
            case 0x2D : //Absolute
                break;
            case 0x3D : //Absolute, X
                break;
            case 0x39 : //Absolute, Y
                break;
            case 0x21 : //(Indirect, X)
                break;
            case 0x31 : //(Indirect), Y
                break;
            /************** EOR **************/
            case 0x49 : //Immediate
                break;
            case 0x45 : //Zero Page
                break;
            case 0x55 : //Zero Page, X
                break;
            case 0x4D : //Absolute
                break;
            case 0x5D : //Absolute, X
                break;
            case 0x59 : //Absolute, Y
                break;
            case 0x41 : //(Indirect, X)
                break;
            case 0x51 : //(Indirect), Y
                break;
            /************** ORA **************/
            case 0x09 : //Immediate
                break;
            case 0x05 : //Zero Page
                break;
            case 0x15 : //Zero Page,X
                break;
            case 0x0D : //Absolute
                break;
            case 0x1D : //Absolute,X
                break;
            case 0x19 : //Absolute,Y
                break;
            case 0x01 : //(Indirect,X)
                break;
            case 0x11 : //(Indirect),Y
                break;
            /*
             *        Jump, Branching, Subroutine Instructions
             */
            /************** JMP **************/
            case 0x4C : //Absolute
                break;
            case 0x6C : //Indirect
                break;
            /************** BCC **************/
            case 0x90 : //Relative
                break;
            /************** BCS **************/
            case 0xB0 : //Relative
                break;
            /************** BEQ **************/
            case 0xF0 : //Relative
                break;
            /************** BMI **************/
            case 0x30 : //Relative
                break;
            /************** BNE **************/
            case 0xD0 : //Relative
                break;
            /************** BPL **************/
            case 0x10 : //Relative
                break;
            /************** BVC **************/
            case 0x50 : //Relative
                break;
            /************** BVS **************/
            case 0x70 : //Relative
                break;
            /************** JSR **************/
            case 0x20 : //Absolute
                break;
            /************** RTI **************/
            case 0x40 : //implied
                break;
            /************** RTS **************/
            case 0x60 : //implied
                break;
            /*
             *        Compare / Test Bits Instructions
             */
            /************** CMP **************/
            case 0xC9 : //Immediate
                break;
            case 0xC5 : //Zero Page
                break;
            case 0xD5 : //Zero Page, X
                break;
            case 0xCD : //Absolute
                break;
            case 0xDD : //Absolute, X
                break;
            case 0xD9 : //Absolute, Y
                break;
            case 0xC1 : //(Indirect, X)
                break;
            case 0xD1 : //(Indirect), Y
                break;
            /************** CPX **************/
            case 0xE0 : //Immediate
                break;
            case 0xE4 : //Zero Page
                break;
            case 0xEC : //Absolute
                break;
            /************** CPY **************/
            case 0xC0 : //immidiate
                break;
            case 0xC4 : //zeropage
                break;
            case 0xCC : //absolute
                break;
            /************** BIT **************/
            case 0x24 : //zeropage
                break;
            case 0x2C : //absolute
                break;
            /*
             *        Uncategorized Instructions
             */
            /************** BRL **************/ //<< FIXME: needs attention.
            /************** CLC **************/
            case 0x18 : //Implied
                break;
            /************** CLD **************/
            case 0xD8 : //Implied
                break;
            /************** CLI **************/
            case 0x58 : //Implied
                break;
            /************** CLV **************/
            case 0xB8 : //Implied
                break;
            /************** NOP **************/
            case 0xEA : //implied
                break;
            /************** PHA **************/
            case 0x48 : //implied
                break;
            /************** PHP **************/
            case 0x08 : //implied
                break;
            /************** PLA **************/
            case 0x68 : //implied
                break;
            /************** PLP **************/
            case 0x28 : //implied
                break;
            /************** SEC **************/
            case 0x38 : //implied
                break;
            /************** SED **************/
            case 0xF8 : //implied
                break;
            /************** SEI **************/
            case 0x78 : //implied
                break;
            /************** TAX **************/
            case 0xAA : //implied
                break;
            /************** TAY **************/
            case 0xA8 : //implied
                break;
            /************** TSX **************/
            case 0xBA : //implied
                break;
            /************** TXA **************/
            case 0x8A : //implied
                break;
            /************** TYA **************/
            case 0x98 : //implied
                break;
            /************** TXS **************/
            case 0x9A : //implied 
                break;
            /************** Unknown Opcode **************/
            default :
                Console.print("[ERROR] Unknown Opcode '" + opcode + "'");
        }
    }
}

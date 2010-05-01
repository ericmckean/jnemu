/*      TODO:
 *
 *      1.)All the opcode should be listed here before implementation.
 *      2.)Implement the opcode.
 */

package PROC;

public class RP2A0x
{
    public static void exec(int opcode)
    {
        switch(opcode)
        {
            /*       >>Math Instructions<<
             *          Add / Subtract
             */
            /************** ADC **************/
            case 0x69 : //Immediate
                Instructions.immediateADC();
                break;
            case 0x65 : //Zero Page
                Instructions.zeroPageADC();
                break;
            case 0x75 : //Zero Page, X
                Instructions.zeroPageXADC();
                break;
            case 0x6D : //Absolute
                Instructions.absoluteADC();
                break;
            case 0x7D : //Absolute, X
                Instructions.absoluteXADC();
                break;
            case 0x79 : //Absolute, Y
                Instructions.absoluteYADC();
                break;
            case 0x61 : //(Indirect, X)
                Instructions.indirectXADC();
                break;
            case 0x71 : //(Indirect), Y
                Instructions.indirectYADC();
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
            case 0x90 : //Absolute,X
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

        }
    }
}

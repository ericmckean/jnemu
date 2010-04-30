/*      TODO:
 *
 *      1.)All the opcode should be listed here before implementation.
 *      2.)Implement the opcode.
 */

package jnemu;

public class Ricoh_RP2A0x
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
                immediateADC();
                break;
            case 0x65 : //Zero Page
                zeroPageADC();
                break;
            case 0x75 : //Zero Page, X
                zeroPageXADC();
                break;
            case 0x6D : //Absolute
                absoluteADC();
                break;
            case 0x7D : //Absolute, X
                absoluteXADC();
                break;
            case 0x79 : //Absolute, Y
                absoluteYADC();
                break;
            case 0x61 : //(Indirect, X)
                indirectXADC();
                break;
            case 0x71 : //(Indirect), Y
                indirectYADC();
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

        }
    }

    /**************************** ADC ****************************/
    static void immediateADC()
    {
        //FIXME: needs a routine to execute the opcode.
    }

    static void zeroPageADC()
    {
        //FIXME: needs a routine to execute the opcode.
    }

    static void zeroPageXADC()
    {
        //FIXME: needs a routine to execute the opcode.
    }

    static void absoluteADC()
    {
        //FIXME: needs a routine to execute the opcode.
    }

    static void absoluteXADC()
    {
        //FIXME: needs a routine to execute the opcode.
    }

    static void absoluteYADC()
    {
        //FIXME: needs a routine to execute the opcode.
    }

    static void indirectXADC()
    {
        //FIXME: needs a routine to execute the opcode.
    }

    static void indirectYADC()
    {
        //FIXME: needs a routine to execute the opcode.
    }
    /**************************** SBC ****************************/
    /**************************** ASL ****************************/
    /**************************** LSR ****************************/
    /**************************** ROL ****************************/
    /**************************** ROR ****************************/

}

package DEBUGGER;

import MISC.MACHINE;
import CPU.*;

public class OpcodeFetcher 
{
    public static void loadOpcode(int address)
    {
        int ctr, i;
        int pc = address;

        //clear the listbox...
        NesDebugger.REG_Viewer.removeAll();

        for(ctr=0; ctr<=100; ctr++)
        {
            i = getOpcode(pc);
            pc += i;
        }
    }

    private static void addData(int Address, String value)
    {
        StringBuilder tmp = new StringBuilder();

        switch(Integer.toHexString(Address).length())
        {
            case 3 :
                tmp.append("0");
                tmp.append(Integer.toHexString(Address));
                break;
            case 2 :
                tmp.append("00");
                tmp.append(Integer.toHexString(Address));
                break;
            case 1 :
                tmp.append("000");
                tmp.append(Integer.toHexString(Address));
                break;
            default :
                tmp.append(Integer.toHexString(Address));
                break;
        }
        NesDebugger.REG_Viewer.add("$" + tmp.toString().toUpperCase() + " :  " + value);
    }

    public static int getOpcode(int pc)
    {
        int size = 0;

        switch(CPU_MEMORY.read8BitForOtherFunctions(pc))
        {
            /*       >>Math Instructions<<
             *          Add / Subtract
             */
            /************** ADC **************/
            case 0x69 : //Immediate
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "ADC #$" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x65 : //Zero Page
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "ADC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x75 : //Zero Page, X
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "ADC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0x6D : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "ADC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x7D : //Absolute, X
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "ADC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0x79 : //Absolute, Y
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "ADC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", Y");
                break;
            case 0x61 : //(Indirect, X)
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "ADC ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X)");
                break;
            case 0x71 : //(Indirect), Y
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "ADC ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + "), Y");
                break;
            /************** SBC **************/
            case 0xE9 : //immidiate
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "SBC #$" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xE5 : //zeropage
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "SBC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xF5 : //zeropage,X
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "SBC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0xED : //absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "SBC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xFD : //absolute,X
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "SBC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0xF9 : //absolute,Y
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "SBC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", Y");
                break;
            case 0xE1 : //(indirect,X)
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "SBC ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X)");
                break;
            case 0xF1 : //(indirect),Y
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "SBC ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + "), Y");
                break;
            /*
             *        Shift / Rotate
             */
            /************** ASL **************/
            case 0x0A : //(Accumulator)
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "ASL A");
                break;
            case 0x06 : //Zero Page
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "ASL $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x16 : //Zero Page, X
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "ASL $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0x0E : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "ASL $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x1E : //Absolute, X
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "ASL $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            /************** LSR **************/
            case 0x4A : //accumulator
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "LSR A");
                break;
            case 0x46 : //zeropage
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "LSR $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x56 : //zeropage,X
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "LSR $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0x4E : //absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "LSR $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x5E : //absolute,X
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "LSR $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            /************** ROL **************/
            case 0x2A : //accumulator
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "ROL A");
                break;
            case 0x26 : //zeropage
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "ROL $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x36 : //zeropage,X
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "ROL $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0x2E : //absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "ROL $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x3E : //absolute,X
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "ROL $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            /************** ROR **************/
            case 0x6A : //accumulator
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "ROR A");
                break;
            case 0x66 : //zeropage
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "ROR $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x76 : //zeropage,X
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "ROR $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0x6E : //absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "ROR $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x7E : //absolute,X
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "ROR $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            /*
             *        Increment / Decrement
             */
            /************** INC **************/
            case 0xE6 : //Zero Page
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "INC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xF6 : //Zero Page, X
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "INC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0xEE : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "INC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xFE : //Absolute, X
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "INC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            /************** INX **************/
            case 0xE8 : //Implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "INX");
                break;
            /************** INY **************/
            case 0xC8 : //Implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "INY");
                break;
            /************** DEC **************/
            case 0xC6 : //Zero Page
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "DEC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xD6 : //Zero Page, X
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "DEC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0xCE : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "DEC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xDE : //Absolute, X
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "DEC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            /************** DEX **************/
            case 0xCA : //Implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "DEX");
                break;
            /************** DEY **************/
            case 0x88 : //Implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "DEY");
                break;
            /*
             *        Load and Store Instructions
             */
            /************** LDA **************/
            case 0xA9 : //Immediate
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "LDA #$" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xA5 : //Zero Page
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "LDA $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xB5 : //Zero Page, X
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "LDA $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0xAD : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "LDA $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xBD : //Absolute, X
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "LDA $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0xB9 : //Absolute, Y
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "LDA $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", Y");
                break;
            case 0xA1 : //(Indirect, X)
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "LDA ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X)");
                break;
            case 0xB1 : //(Indirect), Y
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "LDA ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + "), Y");
                break;
            /************** LDX **************/
            case 0xA2 : //Immediate
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "LDX #$" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xA6 : //Zero Page
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "LDX $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xB6 : //Zero Page, Y
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "LDX $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", Y");
                break;
            case 0xAE : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "LDX $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xBE : //Absolute, Y
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "LDX $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", Y");
                break;
            /************** LDY **************/
            case 0xA0 : //Immediate
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "LDY #$" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xA4 : //Zero Page
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "LDY $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xB4 : //Zero Page, X
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "LDY $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0xAC : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "LDY $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xBC : //Absolute, X
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "LDY $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            /************** STA **************/
            case 0x85 : //Zero Page
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "STA $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x95 : //Zero Page,X
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "STA $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0x8D : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "STA $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x9D : //Absolute,X
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "STA $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0x99 : //Absolute,Y
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "STA $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", Y");
                break;
            case 0x81 : //(Indirect,X)
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "STA ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X)");
                break;
            case 0x91 : //(Indirect),Y
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "STA ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + "), Y");
                break;
            /************** STX **************/
            case 0x86 : //Zero Page
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "STX $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x96 : //Zero Page,Y
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "STX $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", Y");
                break;
            case 0x8E : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "STX $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            /************** STY **************/
            case 0x84 : //Zero Page
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "STY $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x94 : //Zero Page, X
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "STY $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0x8C : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "STY $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            /*
             *        Logical Instructions
             */
            /************** AND **************/
            case 0x29 : //Immediate
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "AND #$" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x25 : //Zero Page
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "AND $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x35 : //Zero Page, X
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "AND $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0x2D : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "AND $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x3D : //Absolute, X
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "AND $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0x39 : //Absolute, Y
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "AND $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", Y");
                break;
            case 0x21 : //(Indirect, X)
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "AND ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X)");
                break;
            case 0x31 : //(Indirect), Y
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "AND ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + "), Y");
                break;
            /************** EOR **************/
            case 0x49 : //Immediate
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "EOR #$" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x45 : //Zero Page
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "EOR $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x55 : //Zero Page, X
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "EOR $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0x4D : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "EOR $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x5D : //Absolute, X
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "EOR $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0x59 : //Absolute, Y
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "EOR $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", Y");
                break;
            case 0x41 : //(Indirect, X)
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "EOR ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X)");
                break;
            case 0x51 : //(Indirect), Y
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "EOR ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + "), Y");
                break;
            /************** ORA **************/
            case 0x09 : //Immediate
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "ORA #$" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x05 : //Zero Page
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "ORA $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x15 : //Zero Page,X
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "ORA $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0x0D : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "ORA $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x1D : //Absolute,X
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "ORA $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0x19 : //Absolute,Y
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "ORA $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", Y");
                break;
            case 0x01 : //(Indirect,X)
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "ORA ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X)");
                break;
            case 0x11 : //(Indirect),Y
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "ORA ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + "), Y");
                break;
            /*
             *        Jump, Branching, Subroutine Instructions
             */
            /************** JMP **************/
            case 0x4C : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "JMP $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x6C : //Indirect
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "JMP ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ")");
                break;
            /************** BCC **************/
            case 0x90 : //Relative
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "BCC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            /************** BCS **************/
            case 0xB0 : //Relative
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "BCS $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            /************** BEQ **************/
            case 0xF0 : //Relative
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "BEQ $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            /************** BMI **************/
            case 0x30 : //Relative
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "BMI $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            /************** BNE **************/
            case 0xD0 : //Relative
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "BNE $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            /************** BPL **************/
            case 0x10 : //Relative
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "BPL $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            /************** BVC **************/
            case 0x50 : //Relative
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "BVC $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            /************** BVS **************/
            case 0x70 : //Relative
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "BVS $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            /************** JSR **************/
            case 0x20 : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "JSR $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            /************** RTI **************/
            case 0x40 : //implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "RTI");
                break;
            /************** RTS **************/
            case 0x60 : //implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "RTS");
                break;
            /*
             *        Compare / Test Bits Instructions
             */
            /************** CMP **************/
            case 0xC9 : //Immediate
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "CMP #$" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xC5 : //Zero Page
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "CMP $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xD5 : //Zero Page, X
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "CMP $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0xCD : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "CMP $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xDD : //Absolute, X
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "CMP $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X");
                break;
            case 0xD9 : //Absolute, Y
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "CMP $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", Y");
                break;
            case 0xC1 : //(Indirect, X)
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "CMP ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + ", X)");
                break;
            case 0xD1 : //(Indirect), Y
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "CMP ($" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)) + "), Y");
                break;
            /************** CPX **************/
            case 0xE0 : //Immediate
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "CPX #$" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xE4 : //Zero Page
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "CPX $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xEC : //Absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "CPX $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            /************** CPY **************/
            case 0xC0 : //immidiate
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "CPY #$" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xC4 : //zeropage
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "CPY $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0xCC : //absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "CPY $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            /************** BIT **************/
            case 0x24 : //zeropage
                size = 2;
                addData(pc, MACHINE.getCode(size, pc) + "BIT $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            case 0x2C : //absolute
                size = 3;
                addData(pc, MACHINE.getCode(size, pc) + "BIT $" + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 2)) + MISC_FUNCTIONS.forceTo8Bit(CPU_MEMORY.read8BitForOtherFunctions(pc + 1)));
                break;
            /*
             *        Uncategorized Instructions
             */
            /************** BRL **************/ //<< FIXME: needs attention.
            /************** CLC **************/
            case 0x18 : //Implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "CLC");
                break;
            /************** CLD **************/
            case 0xD8 : //Implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "CLD");
                break;
            /************** CLI **************/
            case 0x58 : //Implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "CLI");
                break;
            /************** CLV **************/
            case 0xB8 : //Implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "CLV");
                break;
            /************** NOP **************/
            case 0xEA : //implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "NOP");
                break;
            /************** PHA **************/
            case 0x48 : //implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "PHA");
                break;
            /************** PHP **************/
            case 0x08 : //implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "PHP");
                break;
            /************** PLA **************/
            case 0x68 : //implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "PLA");
                break;
            /************** PLP **************/
            case 0x28 : //implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "PLP");
                break;
            /************** SEC **************/
            case 0x38 : //implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "SEC");
                break;
            /************** SED **************/
            case 0xF8 : //implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "SED");
                break;
            /************** SEI **************/
            case 0x78 : //implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "SEI");
                break;
            /************** TAX **************/
            case 0xAA : //implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "TAX");
                break;
            /************** TAY **************/
            case 0xA8 : //implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "TAY");
                break;
            /************** TSX **************/
            case 0xBA : //implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "TSX");
                break;
            /************** TXA **************/
            case 0x8A : //implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "TXA");
                break;
            /************** TYA **************/
            case 0x98 : //implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "TYA");
                break;
            /************** TXS **************/
            case 0x9A : //implied
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "TXS");
                break;
            default :
                size = 1;
                addData(pc, MACHINE.getCode(size, pc) + "????");
                break;
        }
        return size;
    }

}

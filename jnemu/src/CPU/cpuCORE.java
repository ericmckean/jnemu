/*      TODO:
 *
 *      1.)All the opcode should be listed here before implementation.
 *      2.)Implement the opcode.
 */

package CPU;

import INSTRUCTIONS.*;

public class cpuCORE
{
    public static int CYCLE; //cycle counter..................

    public static int exec(int opcode)
    {
        int cycle = 0;

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
                INDIRECT_X.ADC();
                cycle = 6;
                break;
            case 0x71 : //(Indirect), Y
                cycle = INDIRECT_Y.ADC();
                break;
            /************** SBC **************/
            case 0xE9 : //immidiate
                IMMIDIATE.SBC();
                cycle = 2;
                break;
            case 0xE5 : //zeropage
                ZEROPAGE.SBC();
                cycle = 3;
                break;
            case 0xF5 : //zeropage,X
                ZEROPAGE_X.SBC();
                cycle = 4;
                break;
            case 0xED : //absolute
                ABSOLUTE.SBC();
                cycle = 4;
                break;
            case 0xFD : //absolute,X
                cycle = ABSOLUTE_X.SBC();
                break;
            case 0xF9 : //absolute,Y
                cycle = ABSOLUTE_Y.SBC();
                break;
            case 0xE1 : //(indirect,X)
                INDIRECT_X.SBC();
                cycle = 6;
                break;
            case 0xF1 : //(indirect),Y
                cycle = INDIRECT_Y.SBC();
                break;
            /*
             *        Shift / Rotate
             */
            /************** ASL **************/
            case 0x0A : //(Accumulator)
                ACCUMULATOR.ASL();
                cycle = 2;
                break;
            case 0x06 : //Zero Page
                ZEROPAGE.ASL();
                cycle = 5;
                break;
            case 0x16 : //Zero Page, X
                ZEROPAGE_X.ASL();
                cycle = 6;
                break;
            case 0x0E : //Absolute
                ABSOLUTE.ASL();
                cycle = 6;
                break;
            case 0x1E : //Absolute, X
                ABSOLUTE_X.ASL();
                cycle = 7;
                break;
            /************** LSR **************/
            case 0x4A : //accumulator
                ACCUMULATOR.LSR();
                cycle = 2;
                break;
            case 0x46 : //zeropage
                ZEROPAGE.LSR();
                cycle = 5;
                break;
            case 0x56 : //zeropage,X
                ZEROPAGE_X.LSR();
                cycle = 6;
                break;
            case 0x4E : //absolute
                ABSOLUTE.LSR();
                cycle = 6;
                break;
            case 0x5E : //absolute,X
                ABSOLUTE_X.LSR();
                cycle = 7;
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
                ACCUMULATOR.ROR();
                cycle = 2;
                break;
            case 0x66 : //zeropage
                ZEROPAGE.ROR();
                cycle = 5;
                break;
            case 0x76 : //zeropage,X
                ZEROPAGE_X.ROR();
                cycle = 6;
                break;
            case 0x6E : //absolute
                ABSOLUTE.ROR();
                cycle = 6;
                break;
            case 0x7E : //absolute,X
                ABSOLUTE_X.ROR();
                cycle = 7;
                break;
            /*
             *        Increment / Decrement
             */
            /************** INC **************/
            case 0xE6 : //Zero Page
                ZEROPAGE.INC();
                cycle = 5;
                break;
            case 0xF6 : //Zero Page, X
                ZEROPAGE_X.INC();
                cycle = 6;
                break;
            case 0xEE : //Absolute
                ABSOLUTE.INC();
                cycle = 6;
                break;
            case 0xFE : //Absolute, X
                ABSOLUTE_X.INC();
                cycle = 7;
                break;
            /************** INX **************/
            case 0xE8 : //Implied
                IMPLIED.INX();
                cycle = 2;
                break;
            /************** INY **************/
            case 0xC8 : //Implied
                IMPLIED.INY();
                cycle = 2;
                break;
            /************** DEC **************/
            case 0xC6 : //Zero Page
                ZEROPAGE.DEC();
                cycle = 5;
                break;
            case 0xD6 : //Zero Page, X
                ZEROPAGE_X.DEC();
                cycle = 6;
                break;
            case 0xCE : //Absolute
                ABSOLUTE.DEC();
                cycle = 6;
                break;
            case 0xDE : //Absolute, X
                ABSOLUTE_X.DEC();
                cycle = 7;
                break;
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
                ZEROPAGE_X.LDA();
                cycle = 4;
                break;
            case 0xAD : //Absolute
                ABSOLUTE.LDA();
                cycle = 4;
                break;
            case 0xBD : //Absolute, X
                cycle = ABSOLUTE_X.LDA();
                break;
            case 0xB9 : //Absolute, Y
                cycle = ABSOLUTE_Y.LDA();
                break;
            case 0xA1 : //(Indirect, X)
                INDIRECT_X.LDA();
                cycle = 6;
                break;
            case 0xB1 : //(Indirect), Y
                cycle = INDIRECT_Y.LDA();
                break;
            /************** LDX **************/
            case 0xA2 : //Immediate
                IMMIDIATE.LDX();
                cycle = 2;
                break;
            case 0xA6 : //Zero Page
                ZEROPAGE.LDX();
                cycle = 3;
                break;
            case 0xB6 : //Zero Page, Y
                ZEROPAGE_Y.LDX();
                cycle = 4;
                break;
            case 0xAE : //Absolute
                ABSOLUTE.LDX();
                cycle = 4;
                break;
            case 0xBE : //Absolute, Y
                cycle = ABSOLUTE_Y.LDX();
                break;
            /************** LDY **************/
            case 0xA0 : //Immediate
                IMMIDIATE.LDY();
                cycle = 2;
                break;
            case 0xA4 : //Zero Page
                ZEROPAGE.LDY();
                cycle = 3;
                break;
            case 0xB4 : //Zero Page, X
                ZEROPAGE_X.LDY();
                cycle = 4;
                break;
            case 0xAC : //Absolute
                ABSOLUTE.LDY();
                cycle = 4;
                break;
            case 0xBC : //Absolute, X
                cycle = ABSOLUTE_X.LDY();
                break;
            /************** STA **************/
            case 0x85 : //Zero Page
                ZEROPAGE.STA();
                cycle = 3;
                break;
            case 0x95 : //Zero Page,X
                ZEROPAGE_X.STA();
                cycle = 4;
                break;
            case 0x8D : //Absolute
                ABSOLUTE.STA();
                cycle = 3;
                break;
            case 0x9D : //Absolute,X
                ABSOLUTE_X.STA();
                cycle = 5;
                break;
            case 0x99 : //Absolute,Y
                ABSOLUTE_Y.STA();
                cycle = 5;
                break;
            case 0x81 : //(Indirect,X)
                INDIRECT_X.STA();
                cycle = 6;
                break;
            case 0x91 : //(Indirect),Y
                INDIRECT_Y.STA();
                cycle = 6;
                break;
            /************** STX **************/
            case 0x86 : //Zero Page
                ZEROPAGE.STX();
                cycle = 3;
                break;
            case 0x96 : //Zero Page,Y
                ZEROPAGE_Y.STX();
                cycle = 4;
                break;
            case 0x8E : //Absolute
                ABSOLUTE.STX();
                cycle = 4;
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
                ZEROPAGE_X.AND();
                cycle = 4;
                break;
            case 0x2D : //Absolute
                ABSOLUTE.AND();
                cycle = 4;
                break;
            case 0x3D : //Absolute, X
                cycle = ABSOLUTE_X.AND();
                break;
            case 0x39 : //Absolute, Y
                cycle = ABSOLUTE_Y.AND();
                break;
            case 0x21 : //(Indirect, X)
                INDIRECT_X.AND();
                cycle = 6;
                break;
            case 0x31 : //(Indirect), Y
                cycle = INDIRECT_Y.AND();
                break;
            /************** EOR **************/
            case 0x49 : //Immediate
                IMMIDIATE.EOR();
                cycle = 2;
                break;
            case 0x45 : //Zero Page
                ZEROPAGE.EOR();
                cycle = 3;
                break;
            case 0x55 : //Zero Page, X
                ZEROPAGE_X.EOR();
                cycle = 4;
                break;
            case 0x4D : //Absolute
                ABSOLUTE.EOR();
                cycle = 4;
                break;
            case 0x5D : //Absolute, X
                cycle = ABSOLUTE_X.EOR();
                break;
            case 0x59 : //Absolute, Y
                cycle = ABSOLUTE_Y.EOR();
                break;
            case 0x41 : //(Indirect, X)
                INDIRECT_X.EOR();
                cycle = 6;
                break;
            case 0x51 : //(Indirect), Y
                cycle = INDIRECT_Y.EOR();
                break;
            /************** ORA **************/
            case 0x09 : //Immediate
                IMMIDIATE.ORA();
                cycle = 2;
                break;
            case 0x05 : //Zero Page
                ZEROPAGE.ORA();
                cycle = 3;
                break;
            case 0x15 : //Zero Page,X
                ZEROPAGE_X.ORA();
                cycle = 4;
                break;
            case 0x0D : //Absolute
                ABSOLUTE.ORA();
                cycle = 4;
                break;
            case 0x1D : //Absolute,X
                cycle = ABSOLUTE_X.ORA();
                break;
            case 0x19 : //Absolute,Y
                cycle = ABSOLUTE_Y.ORA();
                break;
            case 0x01 : //(Indirect,X)
                INDIRECT_X.ORA();
                cycle = 6;
                break;
            case 0x11 : //(Indirect),Y
                cycle = INDIRECT_Y.ORA();
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
                IMPLIED.RTI();
                cycle = 6;
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
                ZEROPAGE.CMP();
                cycle = 3;
                break;
            case 0xD5 : //Zero Page, X
                ZEROPAGE_X.CMP();
                cycle = 4;
                break;
            case 0xCD : //Absolute
                ABSOLUTE.CMP();
                cycle = 4;
                break;
            case 0xDD : //Absolute, X
                cycle = ABSOLUTE_X.CMP();
                break;
            case 0xD9 : //Absolute, Y
                cycle = ABSOLUTE_Y.CMP();
                break;
            case 0xC1 : //(Indirect, X)
                INDIRECT_X.CMP();
                cycle = 6;
                break;
            case 0xD1 : //(Indirect), Y
                cycle = INDIRECT_Y.CMP();
                break;
            /************** CPX **************/
            case 0xE0 : //Immediate
                IMMIDIATE.CPX();
                cycle = 2;
                break;
            case 0xE4 : //Zero Page
                ZEROPAGE.CPX();
                cycle = 3;
                break;
            case 0xEC : //Absolute
                ABSOLUTE.CPX();
                cycle = 4;
                break;
            /************** CPY **************/
            case 0xC0 : //immidiate
                IMMIDIATE.CPY();
                cycle = 2;
                break;
            case 0xC4 : //zeropage
                ZEROPAGE.CPY();
                cycle = 3;
                break;
            case 0xCC : //absolute
                ABSOLUTE.CPY();
                cycle = 4;
                break;
            /************** BIT **************/
            case 0x24 : //zeropage
                ZEROPAGE.BIT();
                cycle = 3;
                break;
            case 0x2C : //absolute
                ABSOLUTE.BIT();
                cycle = 4;
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
                IMPLIED.BRK();
                cycle = 7;
                break;
            /*
             *               Unofficial Opcode
             */
            /************** DOP (Double NOP) **************/
            case 0x04 :
                UNOFFICIAL.DOP();
                cycle = 3;
                break;
            case 0x14 :
                UNOFFICIAL.DOP();
                cycle = 4;
                break;
            case 0x34 :
                UNOFFICIAL.DOP();
                cycle = 4;
                break;
            case 0x44 :
                UNOFFICIAL.DOP();
                cycle = 3;
                break;
            case 0x54 :
                UNOFFICIAL.DOP();
                cycle = 4;
                break;
            case 0x64 :
                UNOFFICIAL.DOP();
                cycle = 3;
                break;
            case 0x74 :
                UNOFFICIAL.DOP();
                cycle = 4;
                break;
            case 0x80 :
                UNOFFICIAL.DOP();
                cycle = 2;
                break;
            case 0x82 :
                UNOFFICIAL.DOP();
                cycle = 2;
                break;
            case 0x89 :
                UNOFFICIAL.DOP();
                cycle = 2;
                break;
            case 0xC2 :
                UNOFFICIAL.DOP();
                cycle = 2;
                break;
            case 0xD4 :
                UNOFFICIAL.DOP();
                cycle = 4;
                break;
            case 0xE2 :
                UNOFFICIAL.DOP();
                cycle = 2;
                break;
            case 0xF4 :
                UNOFFICIAL.DOP();
                cycle = 4;
                break;
            /************** *NOP **************/
            case 0x1A :
                UNOFFICIAL._NOP();
                cycle = 2;
                break;
            case 0x3A :
                UNOFFICIAL._NOP();
                cycle = 2;
                break;
            case 0x5A :
                UNOFFICIAL._NOP();
                cycle = 2;
                break;
            case 0x7A :
                UNOFFICIAL._NOP();
                cycle = 2;
                break;
            case 0xDA :
                UNOFFICIAL._NOP();
                cycle = 2;
                break;
            case 0xFA :
                UNOFFICIAL._NOP();
                cycle = 2;
                break;
            /************** TRIPPLE NOP (TOP) **************/
            case 0x0C :
                UNOFFICIAL.TOP();
                cycle = 4;
                break;
            case 0x1C :
                cycle = UNOFFICIAL._TOP();
                break;
            case 0x3C :
                cycle = UNOFFICIAL._TOP();
                break;
            case 0x5C :
                cycle = UNOFFICIAL._TOP();
                break;
            case 0x7C :
                cycle = UNOFFICIAL._TOP();
                break;
            case 0xDC :
                cycle = UNOFFICIAL._TOP();
                break;
            case 0xFC :
                cycle = UNOFFICIAL._TOP();
                break;
            /************** AAC **************/
            case 0x0B :
                UNOFFICIAL.AAC();
                cycle = 2;
                break;
            case 0x2B :
                UNOFFICIAL.AAC();
                cycle = 2;
                break;
            /************** AAX **************/
            case 0x87 :
                UNOFFICIAL.ZP_AAX();
                cycle = 3;
                break;
            case 0x97 :
                UNOFFICIAL.ZP_Y_AAX();
                cycle = 4;
                break;
            case 0x83 :
                UNOFFICIAL.IND_X_AAX();
                cycle = 6;
                break;
            case 0x8F :
                UNOFFICIAL.ABS_AAX();
                cycle = 4;
                break;
            /************** ARR **************/
            case 0x6B :
                UNOFFICIAL.ARR();
                cycle = 2;
                break;
            /************** ASR **************/
            case 0x4B :
                UNOFFICIAL.ASR();
                cycle = 2;
                break;
            /************** ATX **************/
            case 0xAB :
                UNOFFICIAL.ATX();
                cycle = 2;
                break;
            /************** AXA **************/
            case 0x9F :
                UNOFFICIAL.ABS_Y_AXA();
                cycle = 5;
                break;
            case 0x93 :
                UNOFFICIAL.IND_Y_AXA();
                cycle = 6;
                break;
            /************** AXS **************/
            case 0xCB :
                UNOFFICIAL.AXS();
                cycle = 2;
                break;
            /************** DCP **************/
            case 0xC7 :
                UNOFFICIAL.ZP_DCP();
                cycle = 5;
                break;
            case 0xD7 :
                UNOFFICIAL.ZP_X_DCP();
                cycle = 6;
                break;
            case 0xCF :
                UNOFFICIAL.ABS_DCP();
                cycle = 6;
                break;
            case 0xDF :
                UNOFFICIAL.ABS_X_DCP();
                cycle = 7;
                break;
            case 0xDB :
                UNOFFICIAL.ABS_Y_DCP();
                cycle = 7;
                break;
            case 0xC3 :
                UNOFFICIAL.IND_X_DCP();
                cycle = 8;
                break;
            case 0xD3 :
                UNOFFICIAL.IND_Y_DCP();
                cycle = 8;
                break;
            /************** ISC **************/
            case 0xE7 :
                UNOFFICIAL.ZP_ISC();
                cycle = 5;
                break;
            case 0xF7 :
                UNOFFICIAL.ZP_X_ISC();
                cycle = 6;
                break;
            case 0xEF :
                UNOFFICIAL.ABS_ISC();
                cycle = 6;
                break;
            case 0xFF :
                UNOFFICIAL.ABS_X_ISC();
                cycle = 7;
                break;
            case 0xFB :
                UNOFFICIAL.ABS_Y_ISC();
                cycle = 7;
                break;
            case 0xE3 :
                UNOFFICIAL.IND_X_ISC();
                cycle = 8;
                break;
            case 0xF3 :
                UNOFFICIAL.IND_Y_ISC();
                cycle = 8;
                break;
            /*************** KIL ***************/
            //       PROCESSOR LOCK UP
            // NOTE: Stops the Program Counter
            /***********************************/
            case 0x02 :
                break;
            case 0x12 :
                break;
            case 0x22 :
                break;
            case 0x32 :
                break;
            case 0x42 :
                break;
            case 0x52 :
                break;
            case 0x62 :
                break;
            case 0x72 :
                break;
            case 0x92 :
                break;
            case 0xB2 :
                break;
            case 0xD2 :
                break;
            case 0xF2 :
                break;
            /*************** LAR ***************/
            case 0xBB :
                cycle = UNOFFICIAL.LAR();
                break;
            /*************** LAX ***************/
            case 0xA7 :
                UNOFFICIAL.ZP_LAX();
                cycle = 3;
                break;
            case 0xB7 :
                UNOFFICIAL.ZP_Y_LAX();
                cycle = 4;
                break;
            case 0xAF :
                UNOFFICIAL.ABS_LAX();
                cycle = 4;
                break;
            case 0xBF :
                cycle = UNOFFICIAL.ABS_Y_LAX();
                break;
            case 0xA3 :
                UNOFFICIAL.IND_X_LAX();
                cycle = 6;
                break;
            case 0xB3 :
                cycle = UNOFFICIAL.IND_Y_LAX();
                break;
            /*************** SBC ***************/
            case 0xEB :
                IMMIDIATE.SBC();
                cycle = 2;
                break;
            /*************** RLA ***************/
            case 0x27 :
                UNOFFICIAL.ZP_RLA();
                cycle = 5;
                break;
            case 0x37 :
                UNOFFICIAL.ZP_X_RLA();
                cycle = 6;
                break;
            case 0x2F :
                UNOFFICIAL.ABS_RLA();
                cycle = 6;
                break;
            case 0x3F :
                UNOFFICIAL.ABS_X_RLA();
                cycle = 7;
                break;
            case 0x3B :
                UNOFFICIAL.ABS_Y_RLA();
                cycle = 7;
                break;
            case 0x23 :
                UNOFFICIAL.IND_X_RLA();
                cycle = 8;
                break;
            case 0x33 :
                UNOFFICIAL.IND_Y_RLA();
                cycle = 8;
                break;
            /*************** RRA ***************/
            case 0x67 :
                UNOFFICIAL.ZP_RRA();
                cycle = 5;
                break;
            case 0x77 :
                UNOFFICIAL.ZP_X_RRA();
                cycle = 6;
                break;
            case 0x6F :
                UNOFFICIAL.ABS_RRA();
                cycle = 6;
                break;
            case 0x7F :
                UNOFFICIAL.ABS_X_RRA();
                cycle = 7;
                break;
            case 0x7B :
                UNOFFICIAL.ABS_Y_RRA();
                cycle = 7;
                break;
            case 0x63 :
                UNOFFICIAL.IND_X_RRA();
                cycle = 8;
                break;
            case 0x73 :
                UNOFFICIAL.IND_Y_RRA();
                cycle = 8;
                break;
            /*************** SLO ***************/
            case 0x07 :
                UNOFFICIAL.ZP_SLO();
                cycle = 5;
                break;
            case 0x17 :
                UNOFFICIAL.ZP_X_SLO();
                cycle = 6;
                break;
            case 0x0F :
                UNOFFICIAL.ABS_SLO();
                cycle = 6;
                break;
            case 0x1F :
                UNOFFICIAL.ABS_X_SLO();
                cycle = 7;
                break;
            case 0x1B :
                UNOFFICIAL.ABS_Y_SLO();
                cycle = 7;
                break;
            case 0x03 :
                UNOFFICIAL.IND_X_SLO();
                cycle = 8;
                break;
            case 0x13 :
                UNOFFICIAL.IND_Y_SLO();
                cycle = 8;
                break;
            /*************** SRE ***************/
            case 0x47 :
                UNOFFICIAL.ZP_SRE();
                cycle = 5;
                break;
            case 0x57 :
                UNOFFICIAL.ZP_X_SRE();
                cycle = 6;
                break;
            case 0x4F :
                UNOFFICIAL.ABS_SRE();
                cycle = 6;
                break;
            case 0x5F :
                UNOFFICIAL.ABS_X_SRE();
                cycle = 7;
                break;
            case 0x5B :
                UNOFFICIAL.ABS_Y_SRE();
                cycle = 7;
                break;
            case 0x43 :
                UNOFFICIAL.IND_X_SRE();
                cycle = 8;
                break;
            case 0x53 :
                UNOFFICIAL.IND_Y_SRE();
                cycle = 8;
                break;
            /*************** SXA ***************/
            case 0x9E :
                UNOFFICIAL.SXA();
                cycle = 5;
                break;
            /*************** SYA ***************/
            case 0x9C :
                UNOFFICIAL.SYA();
                cycle = 5;
                break;
            /*************** XAA ***************/
            //FIXME: Exact operation unknown. Read the referenced documents
            //       for more information and observations.

            case 0x8B :
                CPU_REGISTER.PC += 2;
                cycle = 2;
                break;
            /*************** XAS ***************/
            case 0x9B :
                UNOFFICIAL.XAS();
                cycle = 5;
                break;
            /************** Unknown Opcode **************/
            default :
                System.out.println("[WARNING] Unknown Opcode " + Integer.toHexString(opcode) + " at " + Integer.toHexString(CPU_REGISTER.PC) + ". Executing NOP.");
                IMPLIED.NOP();
                cycle = 2;
                break;
        }
        return cycle;
    }
}

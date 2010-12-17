/*      TODO:
 *
 *      1.)All the opcode should be listed here before implementation.
 *      2.)Implement the opcode.
 */

package NesCpu;

import NesCpuInstructions.*;

public class CpuCore
{
    public static int cpuCycle; //cycle counter..................

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
                InstImmidiate.ADC();
                cycle = 2;
                break;
            case 0x65 : //Zero Page
                ZeroPage.ADC();
                cycle = 3;
                break;
            case 0x75 : //Zero Page, X
                ZeroPageX.ADC();
                cycle = 4;
                break;
            case 0x6D : //Absolute
                InstAbosolute.ADC();
                cycle = 4;
                break;
            case 0x7D : //Absolute, X
                cycle = InstAbsoluteX.ADC();
                break;
            case 0x79 : //Absolute, Y
                cycle = InstAbsoluteY.ADC();
                break;
            case 0x61 : //(Indirect, X)
                InstIndirectX.ADC();
                cycle = 6;
                break;
            case 0x71 : //(Indirect), Y
                cycle = InstIndirectY.ADC();
                break;
            /************** SBC **************/
            case 0xE9 : //immidiate
                InstImmidiate.SBC();
                cycle = 2;
                break;
            case 0xE5 : //zeropage
                ZeroPage.SBC();
                cycle = 3;
                break;
            case 0xF5 : //zeropage,X
                ZeroPageX.SBC();
                cycle = 4;
                break;
            case 0xED : //absolute
                InstAbosolute.SBC();
                cycle = 4;
                break;
            case 0xFD : //absolute,X
                cycle = InstAbsoluteX.SBC();
                break;
            case 0xF9 : //absolute,Y
                cycle = InstAbsoluteY.SBC();
                break;
            case 0xE1 : //(indirect,X)
                InstIndirectX.SBC();
                cycle = 6;
                break;
            case 0xF1 : //(indirect),Y
                cycle = InstIndirectY.SBC();
                break;
            /*
             *        Shift / Rotate
             */
            /************** ASL **************/
            case 0x0A : //(Accumulator)
                InstAccumulator.ASL();
                cycle = 2;
                break;
            case 0x06 : //Zero Page
                ZeroPage.ASL();
                cycle = 5;
                break;
            case 0x16 : //Zero Page, X
                ZeroPageX.ASL();
                cycle = 6;
                break;
            case 0x0E : //Absolute
                InstAbosolute.ASL();
                cycle = 6;
                break;
            case 0x1E : //Absolute, X
                InstAbsoluteX.ASL();
                cycle = 7;
                break;
            /************** LSR **************/
            case 0x4A : //accumulator
                InstAccumulator.LSR();
                cycle = 2;
                break;
            case 0x46 : //zeropage
                ZeroPage.LSR();
                cycle = 5;
                break;
            case 0x56 : //zeropage,X
                ZeroPageX.LSR();
                cycle = 6;
                break;
            case 0x4E : //absolute
                InstAbosolute.LSR();
                cycle = 6;
                break;
            case 0x5E : //absolute,X
                InstAbsoluteX.LSR();
                cycle = 7;
                break;
            /************** ROL **************/
            case 0x2A : //accumulator
                InstAccumulator.ROL();
                cycle = 2;
                break;
            case 0x26 : //zeropage
                ZeroPage.ROL();
                cycle = 5;
                break;
            case 0x36 : //zeropage,X
                ZeroPageX.ROL();
                cycle = 6;
                break;
            case 0x2E : //absolute
                InstAbosolute.ROL();
                cycle = 6;
                break;
            case 0x3E : //absolute,X
                InstAbsoluteX.ROL();
                cycle = 7;
                break;
            /************** ROR **************/
            case 0x6A : //accumulator
                InstAccumulator.ROR();
                cycle = 2;
                break;
            case 0x66 : //zeropage
                ZeroPage.ROR();
                cycle = 5;
                break;
            case 0x76 : //zeropage,X
                ZeroPageX.ROR();
                cycle = 6;
                break;
            case 0x6E : //absolute
                InstAbosolute.ROR();
                cycle = 6;
                break;
            case 0x7E : //absolute,X
                InstAbsoluteX.ROR();
                cycle = 7;
                break;
            /*
             *        Increment / Decrement
             */
            /************** INC **************/
            case 0xE6 : //Zero Page
                ZeroPage.INC();
                cycle = 5;
                break;
            case 0xF6 : //Zero Page, X
                ZeroPageX.INC();
                cycle = 6;
                break;
            case 0xEE : //Absolute
                InstAbosolute.INC();
                cycle = 6;
                break;
            case 0xFE : //Absolute, X
                InstAbsoluteX.INC();
                cycle = 7;
                break;
            /************** INX **************/
            case 0xE8 : //Implied
                InstImplied.INX();
                cycle = 2;
                break;
            /************** INY **************/
            case 0xC8 : //Implied
                InstImplied.INY();
                cycle = 2;
                break;
            /************** DEC **************/
            case 0xC6 : //Zero Page
                ZeroPage.DEC();
                cycle = 5;
                break;
            case 0xD6 : //Zero Page, X
                ZeroPageX.DEC();
                cycle = 6;
                break;
            case 0xCE : //Absolute
                InstAbosolute.DEC();
                cycle = 6;
                break;
            case 0xDE : //Absolute, X
                InstAbsoluteX.DEC();
                cycle = 7;
                break;
            /************** DEX **************/
            case 0xCA : //Implied
                InstImplied.DEX();
                cycle = 2;
                break;
            /************** DEY **************/
            case 0x88 : //Implied
                InstImplied.DEY();
                cycle = 2;
                break;
            /*
             *        Load and Store Instructions
             */
            /************** LDA **************/
            case 0xA9 : //Immidiate
                InstImmidiate.LDA();
                cycle = 2;
                break;
            case 0xA5 : //Zero Page
                ZeroPage.LDA();
                cycle = 3;
                break;
            case 0xB5 : //Zero Page, X
                ZeroPageX.LDA();
                cycle = 4;
                break;
            case 0xAD : //Absolute
                InstAbosolute.LDA();
                cycle = 4;
                break;
            case 0xBD : //Absolute, X
                cycle = InstAbsoluteX.LDA();
                break;
            case 0xB9 : //Absolute, Y
                cycle = InstAbsoluteY.LDA();
                break;
            case 0xA1 : //(Indirect, X)
                InstIndirectX.LDA();
                cycle = 6;
                break;
            case 0xB1 : //(Indirect), Y
                cycle = InstIndirectY.LDA();
                break;
            /************** LDX **************/
            case 0xA2 : //Immediate
                InstImmidiate.LDX();
                cycle = 2;
                break;
            case 0xA6 : //Zero Page
                ZeroPage.LDX();
                cycle = 3;
                break;
            case 0xB6 : //Zero Page, Y
                ZeroPageY.LDX();
                cycle = 4;
                break;
            case 0xAE : //Absolute
                InstAbosolute.LDX();
                cycle = 4;
                break;
            case 0xBE : //Absolute, Y
                cycle = InstAbsoluteY.LDX();
                break;
            /************** LDY **************/
            case 0xA0 : //Immediate
                InstImmidiate.LDY();
                cycle = 2;
                break;
            case 0xA4 : //Zero Page
                ZeroPage.LDY();
                cycle = 3;
                break;
            case 0xB4 : //Zero Page, X
                ZeroPageX.LDY();
                cycle = 4;
                break;
            case 0xAC : //Absolute
                InstAbosolute.LDY();
                cycle = 4;
                break;
            case 0xBC : //Absolute, X
                cycle = InstAbsoluteX.LDY();
                break;
            /************** STA **************/
            case 0x85 : //Zero Page
                ZeroPage.STA();
                cycle = 3;
                break;
            case 0x95 : //Zero Page,X
                ZeroPageX.STA();
                cycle = 4;
                break;
            case 0x8D : //Absolute
                InstAbosolute.STA();
                cycle = 3;
                break;
            case 0x9D : //Absolute,X
                InstAbsoluteX.STA();
                cycle = 5;
                break;
            case 0x99 : //Absolute,Y
                InstAbsoluteY.STA();
                cycle = 5;
                break;
            case 0x81 : //(Indirect,X)
                InstIndirectX.STA();
                cycle = 6;
                break;
            case 0x91 : //(Indirect),Y
                InstIndirectY.STA();
                cycle = 6;
                break;
            /************** STX **************/
            case 0x86 : //Zero Page
                ZeroPage.STX();
                cycle = 3;
                break;
            case 0x96 : //Zero Page,Y
                ZeroPageY.STX();
                cycle = 4;
                break;
            case 0x8E : //Absolute
                InstAbosolute.STX();
                cycle = 4;
                break;
            /************** STY **************/
            case 0x84 : //Zero Page
                ZeroPage.STY();
                cycle = 3;
                break;
            case 0x94 : //Zero Page,X
                ZeroPageX.STY();
                cycle = 4;
                break;
            case 0x8C : //Absolute
                InstAbosolute.STY();
                cycle = 4;
                break;
            /*
             *        Logical Instructions
             */
            /************** AND **************/
            case 0x29 : //Immediate
                InstImmidiate.AND();
                cycle = 2;
                break;
            case 0x25 : //Zero Page
                ZeroPage.AND();
                cycle = 3;
                break;
            case 0x35 : //Zero Page, X
                ZeroPageX.AND();
                cycle = 4;
                break;
            case 0x2D : //Absolute
                InstAbosolute.AND();
                cycle = 4;
                break;
            case 0x3D : //Absolute, X
                cycle = InstAbsoluteX.AND();
                break;
            case 0x39 : //Absolute, Y
                cycle = InstAbsoluteY.AND();
                break;
            case 0x21 : //(Indirect, X)
                InstIndirectX.AND();
                cycle = 6;
                break;
            case 0x31 : //(Indirect), Y
                cycle = InstIndirectY.AND();
                break;
            /************** EOR **************/
            case 0x49 : //Immediate
                InstImmidiate.EOR();
                cycle = 2;
                break;
            case 0x45 : //Zero Page
                ZeroPage.EOR();
                cycle = 3;
                break;
            case 0x55 : //Zero Page, X
                ZeroPageX.EOR();
                cycle = 4;
                break;
            case 0x4D : //Absolute
                InstAbosolute.EOR();
                cycle = 4;
                break;
            case 0x5D : //Absolute, X
                cycle = InstAbsoluteX.EOR();
                break;
            case 0x59 : //Absolute, Y
                cycle = InstAbsoluteY.EOR();
                break;
            case 0x41 : //(Indirect, X)
                InstIndirectX.EOR();
                cycle = 6;
                break;
            case 0x51 : //(Indirect), Y
                cycle = InstIndirectY.EOR();
                break;
            /************** ORA **************/
            case 0x09 : //Immediate
                InstImmidiate.ORA();
                cycle = 2;
                break;
            case 0x05 : //Zero Page
                ZeroPage.ORA();
                cycle = 3;
                break;
            case 0x15 : //Zero Page,X
                ZeroPageX.ORA();
                cycle = 4;
                break;
            case 0x0D : //Absolute
                InstAbosolute.ORA();
                cycle = 4;
                break;
            case 0x1D : //Absolute,X
                cycle = InstAbsoluteX.ORA();
                break;
            case 0x19 : //Absolute,Y
                cycle = InstAbsoluteY.ORA();
                break;
            case 0x01 : //(Indirect,X)
                InstIndirectX.ORA();
                cycle = 6;
                break;
            case 0x11 : //(Indirect),Y
                cycle = InstIndirectY.ORA();
                break;
            /*
             *        Jump, Branching, Subroutine Instructions
             */
            /************** JMP **************/
            case 0x4C : //Absolute
                InstAbosolute.JMP();
                cycle = 3;
                break;
            case 0x6C : //Indirect
                InstIndirect.JMP();
                cycle = 5;
                break;
            /************** BCC **************/
            case 0x90 : //Relative
                cycle = Relative.BCC();
                break;
            /************** BCS **************/
            case 0xB0 : //Relative
                cycle = Relative.BCS();
                break;
            /************** BEQ **************/
            case 0xF0 : //Relative
                cycle = Relative.BEQ();
                break;
            /************** BMI **************/
            case 0x30 : //Relative
                cycle = Relative.BMI();
                break;
            /************** BNE **************/
            case 0xD0 : //Relative
                cycle = Relative.BNE();
                break;
            /************** BPL **************/
            case 0x10 : //Relative
                cycle = Relative.BPL();
                break;
            /************** BVC **************/
            case 0x50 : //Relative
                cycle = Relative.BVC();
                break;
            /************** BVS **************/
            case 0x70 : //Relative
                cycle = Relative.BVS();
                break;
            /************** JSR **************/
            case 0x20 : //Absolute
                InstAbosolute.JSR();
                cycle = 6;
                break;
            /************** RTI **************/
            case 0x40 : //implied
                InstImplied.RTI();
                cycle = 6;
                break;
            /************** RTS **************/
            case 0x60 : //implied
                InstImplied.RTS();
                cycle = 6;
                break;
            /*
             *        Compare / Test Bits Instructions
             */
            /************** CMP **************/
            case 0xC9 : //Immediate
                InstImmidiate.CMP();
                cycle = 2;
                break;
            case 0xC5 : //Zero Page
                ZeroPage.CMP();
                cycle = 3;
                break;
            case 0xD5 : //Zero Page, X
                ZeroPageX.CMP();
                cycle = 4;
                break;
            case 0xCD : //Absolute
                InstAbosolute.CMP();
                cycle = 4;
                break;
            case 0xDD : //Absolute, X
                cycle = InstAbsoluteX.CMP();
                break;
            case 0xD9 : //Absolute, Y
                cycle = InstAbsoluteY.CMP();
                break;
            case 0xC1 : //(Indirect, X)
                InstIndirectX.CMP();
                cycle = 6;
                break;
            case 0xD1 : //(Indirect), Y
                cycle = InstIndirectY.CMP();
                break;
            /************** CPX **************/
            case 0xE0 : //Immediate
                InstImmidiate.CPX();
                cycle = 2;
                break;
            case 0xE4 : //Zero Page
                ZeroPage.CPX();
                cycle = 3;
                break;
            case 0xEC : //Absolute
                InstAbosolute.CPX();
                cycle = 4;
                break;
            /************** CPY **************/
            case 0xC0 : //immidiate
                InstImmidiate.CPY();
                cycle = 2;
                break;
            case 0xC4 : //zeropage
                ZeroPage.CPY();
                cycle = 3;
                break;
            case 0xCC : //absolute
                InstAbosolute.CPY();
                cycle = 4;
                break;
            /************** BIT **************/
            case 0x24 : //zeropage
                ZeroPage.BIT();
                cycle = 3;
                break;
            case 0x2C : //absolute
                InstAbosolute.BIT();
                cycle = 4;
                break;
            /*
             *        Uncategorized Instructions
             */
            /************** BRL **************/ //<< FIXME: needs attention.
            /************** CLC **************/
            case 0x18 : //Implied
                InstImplied.CLC();
                cycle = 2;
                break;
            /************** CLD **************/
            case 0xD8 : //Implied
                InstImplied.CLD();
                cycle = 2;
                break;
            /************** CLI **************/
            case 0x58 : //Implied
                InstImplied.CLI();
                cycle = 2;
                break;
            /************** CLV **************/
            case 0xB8 : //Implied
                InstImplied.CLV();
                cycle = 2;
                break;
            /************** NOP **************/
            case 0xEA : //implied
                InstImplied.NOP();
                cycle = 2;
                break;
            /************** PHA **************/
            case 0x48 : //implied
                InstImplied.PHA();
                cycle = 3;
                break;
            /************** PHP **************/
            case 0x08 : //implied
                InstImplied.PHP();
                cycle = 3;
                break;
            /************** PLA **************/
            case 0x68 : //implied
                InstImplied.PLA();
                cycle = 3;
                break;
            /************** PLP **************/
            case 0x28 : //implied
                InstImplied.PLP();
                cycle = 4;
                break;
            /************** SEC **************/
            case 0x38 : //implied
                InstImplied.SEC();
                cycle = 2;
                break;
            /************** SED **************/
            case 0xF8 : //implied
                InstImplied.SED();
                cycle = 2;
                break;
            /************** SEI **************/
            case 0x78 : //implied
                InstImplied.SEI();
                cycle = 2;
                break;
            /************** TAX **************/
            case 0xAA : //implied
                InstImplied.TAX();
                cycle = 2;
                break;
            /************** TAY **************/
            case 0xA8 : //implied
                InstImplied.TAY();
                cycle = 2;
                break;
            /************** TSX **************/
            case 0xBA : //implied
                InstImplied.TSX();
                cycle = 2;
                break;
            /************** TXA **************/
            case 0x8A : //implied
                InstImplied.TXA();
                cycle = 2;
                break;
            /************** TYA **************/
            case 0x98 : //implied
                InstImplied.TYA();
                cycle = 2;
                break;
            /************** TXS **************/
            case 0x9A : //implied
                InstImplied.TXS();
                cycle = 2;
                break;
            /************** BRK **************/
            case 0x00 :
                InstImplied.BRK();
                cycle = 7;
                break;
            /*
             *               Unofficial Opcode
             */
            /************** DOP (Double NOP) **************/
            case 0x04 :
                Unofficial.DOP();
                cycle = 3;
                break;
            case 0x14 :
                Unofficial.DOP();
                cycle = 4;
                break;
            case 0x34 :
                Unofficial.DOP();
                cycle = 4;
                break;
            case 0x44 :
                Unofficial.DOP();
                cycle = 3;
                break;
            case 0x54 :
                Unofficial.DOP();
                cycle = 4;
                break;
            case 0x64 :
                Unofficial.DOP();
                cycle = 3;
                break;
            case 0x74 :
                Unofficial.DOP();
                cycle = 4;
                break;
            case 0x80 :
                Unofficial.DOP();
                cycle = 2;
                break;
            case 0x82 :
                Unofficial.DOP();
                cycle = 2;
                break;
            case 0x89 :
                Unofficial.DOP();
                cycle = 2;
                break;
            case 0xC2 :
                Unofficial.DOP();
                cycle = 2;
                break;
            case 0xD4 :
                Unofficial.DOP();
                cycle = 4;
                break;
            case 0xE2 :
                Unofficial.DOP();
                cycle = 2;
                break;
            case 0xF4 :
                Unofficial.DOP();
                cycle = 4;
                break;
            /************** *NOP **************/
            case 0x1A :
                Unofficial._NOP();
                cycle = 2;
                break;
            case 0x3A :
                Unofficial._NOP();
                cycle = 2;
                break;
            case 0x5A :
                Unofficial._NOP();
                cycle = 2;
                break;
            case 0x7A :
                Unofficial._NOP();
                cycle = 2;
                break;
            case 0xDA :
                Unofficial._NOP();
                cycle = 2;
                break;
            case 0xFA :
                Unofficial._NOP();
                cycle = 2;
                break;
            /************** TRIPPLE NOP (TOP) **************/
            case 0x0C :
                Unofficial.TOP();
                cycle = 4;
                break;
            case 0x1C :
                cycle = Unofficial._TOP();
                break;
            case 0x3C :
                cycle = Unofficial._TOP();
                break;
            case 0x5C :
                cycle = Unofficial._TOP();
                break;
            case 0x7C :
                cycle = Unofficial._TOP();
                break;
            case 0xDC :
                cycle = Unofficial._TOP();
                break;
            case 0xFC :
                cycle = Unofficial._TOP();
                break;
            /************** AAC **************/
            case 0x0B :
                Unofficial.AAC();
                cycle = 2;
                break;
            case 0x2B :
                Unofficial.AAC();
                cycle = 2;
                break;
            /************** AAX **************/
            case 0x87 :
                Unofficial.ZP_AAX();
                cycle = 3;
                break;
            case 0x97 :
                Unofficial.ZP_Y_AAX();
                cycle = 4;
                break;
            case 0x83 :
                Unofficial.IND_X_AAX();
                cycle = 6;
                break;
            case 0x8F :
                Unofficial.ABS_AAX();
                cycle = 4;
                break;
            /************** ARR **************/
            case 0x6B :
                Unofficial.ARR();
                cycle = 2;
                break;
            /************** ASR **************/
            case 0x4B :
                Unofficial.ASR();
                cycle = 2;
                break;
            /************** ATX **************/
            case 0xAB :
                Unofficial.ATX();
                cycle = 2;
                break;
            /************** AXA **************/
            case 0x9F :
                Unofficial.ABS_Y_AXA();
                cycle = 5;
                break;
            case 0x93 :
                Unofficial.IND_Y_AXA();
                cycle = 6;
                break;
            /************** AXS **************/
            case 0xCB :
                Unofficial.AXS();
                cycle = 2;
                break;
            /************** DCP **************/
            case 0xC7 :
                Unofficial.ZP_DCP();
                cycle = 5;
                break;
            case 0xD7 :
                Unofficial.ZP_X_DCP();
                cycle = 6;
                break;
            case 0xCF :
                Unofficial.ABS_DCP();
                cycle = 6;
                break;
            case 0xDF :
                Unofficial.ABS_X_DCP();
                cycle = 7;
                break;
            case 0xDB :
                Unofficial.ABS_Y_DCP();
                cycle = 7;
                break;
            case 0xC3 :
                Unofficial.IND_X_DCP();
                cycle = 8;
                break;
            case 0xD3 :
                Unofficial.IND_Y_DCP();
                cycle = 8;
                break;
            /************** ISC **************/
            case 0xE7 :
                Unofficial.ZP_ISC();
                cycle = 5;
                break;
            case 0xF7 :
                Unofficial.ZP_X_ISC();
                cycle = 6;
                break;
            case 0xEF :
                Unofficial.ABS_ISC();
                cycle = 6;
                break;
            case 0xFF :
                Unofficial.ABS_X_ISC();
                cycle = 7;
                break;
            case 0xFB :
                Unofficial.ABS_Y_ISC();
                cycle = 7;
                break;
            case 0xE3 :
                Unofficial.IND_X_ISC();
                cycle = 8;
                break;
            case 0xF3 :
                Unofficial.IND_Y_ISC();
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
                cycle = Unofficial.LAR();
                break;
            /*************** LAX ***************/
            case 0xA7 :
                Unofficial.ZP_LAX();
                cycle = 3;
                break;
            case 0xB7 :
                Unofficial.ZP_Y_LAX();
                cycle = 4;
                break;
            case 0xAF :
                Unofficial.ABS_LAX();
                cycle = 4;
                break;
            case 0xBF :
                cycle = Unofficial.ABS_Y_LAX();
                break;
            case 0xA3 :
                Unofficial.IND_X_LAX();
                cycle = 6;
                break;
            case 0xB3 :
                cycle = Unofficial.IND_Y_LAX();
                break;
            /*************** SBC ***************/
            case 0xEB :
                InstImmidiate.SBC();
                cycle = 2;
                break;
            /*************** RLA ***************/
            case 0x27 :
                Unofficial.ZP_RLA();
                cycle = 5;
                break;
            case 0x37 :
                Unofficial.ZP_X_RLA();
                cycle = 6;
                break;
            case 0x2F :
                Unofficial.ABS_RLA();
                cycle = 6;
                break;
            case 0x3F :
                Unofficial.ABS_X_RLA();
                cycle = 7;
                break;
            case 0x3B :
                Unofficial.ABS_Y_RLA();
                cycle = 7;
                break;
            case 0x23 :
                Unofficial.IND_X_RLA();
                cycle = 8;
                break;
            case 0x33 :
                Unofficial.IND_Y_RLA();
                cycle = 8;
                break;
            /*************** RRA ***************/
            case 0x67 :
                Unofficial.ZP_RRA();
                cycle = 5;
                break;
            case 0x77 :
                Unofficial.ZP_X_RRA();
                cycle = 6;
                break;
            case 0x6F :
                Unofficial.ABS_RRA();
                cycle = 6;
                break;
            case 0x7F :
                Unofficial.ABS_X_RRA();
                cycle = 7;
                break;
            case 0x7B :
                Unofficial.ABS_Y_RRA();
                cycle = 7;
                break;
            case 0x63 :
                Unofficial.IND_X_RRA();
                cycle = 8;
                break;
            case 0x73 :
                Unofficial.IND_Y_RRA();
                cycle = 8;
                break;
            /*************** SLO ***************/
            case 0x07 :
                Unofficial.ZP_SLO();
                cycle = 5;
                break;
            case 0x17 :
                Unofficial.ZP_X_SLO();
                cycle = 6;
                break;
            case 0x0F :
                Unofficial.ABS_SLO();
                cycle = 6;
                break;
            case 0x1F :
                Unofficial.ABS_X_SLO();
                cycle = 7;
                break;
            case 0x1B :
                Unofficial.ABS_Y_SLO();
                cycle = 7;
                break;
            case 0x03 :
                Unofficial.IND_X_SLO();
                cycle = 8;
                break;
            case 0x13 :
                Unofficial.IND_Y_SLO();
                cycle = 8;
                break;
            /*************** SRE ***************/
            case 0x47 :
                Unofficial.ZP_SRE();
                cycle = 5;
                break;
            case 0x57 :
                Unofficial.ZP_X_SRE();
                cycle = 6;
                break;
            case 0x4F :
                Unofficial.ABS_SRE();
                cycle = 6;
                break;
            case 0x5F :
                Unofficial.ABS_X_SRE();
                cycle = 7;
                break;
            case 0x5B :
                Unofficial.ABS_Y_SRE();
                cycle = 7;
                break;
            case 0x43 :
                Unofficial.IND_X_SRE();
                cycle = 8;
                break;
            case 0x53 :
                Unofficial.IND_Y_SRE();
                cycle = 8;
                break;
            /*************** SXA ***************/
            case 0x9E :
                Unofficial.SXA();
                cycle = 5;
                break;
            /*************** SYA ***************/
            case 0x9C :
                Unofficial.SYA();
                cycle = 5;
                break;
            /*************** XAA ***************/
            //FIXME: Exact operation unknown. Read the referenced documents
            //       for more information and observations.

            case 0x8B :
                CpuRegister.PC += 2;
                cycle = 2;
                break;
            /*************** XAS ***************/
            case 0x9B :
                Unofficial.XAS();
                cycle = 5;
                break;
            /************** Unknown Opcode **************/
            default :
                System.out.println("[WARNING] Unknown Opcode " + Integer.toHexString(opcode) + " at " + Integer.toHexString(CpuRegister.PC) + ". Executing NOP.");
                InstImplied.NOP();
                cycle = 2;
                break;
        }
        return cycle;
    }
}

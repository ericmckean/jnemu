package jnemu;

import CPU.MEMORY;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import MISC.NES_DEBUGGER;

public class NesDebugger extends JDialog
{
    int scWidth = 600 - 30;
    int scHeight = 400;

    public static JTextPane jt;
    public static List REG_Viewer;

    public static JLabel REG_X;
    public static JLabel REG_Y;
    public static JLabel REG_A;

    public static JLabel REG_PC;

    public static JLabel F_N;
    public static JLabel F_Z;
    public static JLabel F_C;
    public static JLabel F_I;
    public static JLabel F_D; 
    public static JLabel F_V;

    public static JLabel MEM_2000;
    public static JLabel MEM_2001;
    public static JLabel MEM_2002;

    NesDebugger()
    {
        /********************** Main Window ***********************/
        setTitle("Debugger");
        setSize(scWidth,scHeight);
        setResizable(false);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dim = t.getScreenSize();
        setLocation((dim.width / 2) - (scWidth / 2), (dim.height / 2) - (scHeight / 2));
        setAlwaysOnTop(true);

        /*************************** TAB ***************************/

        JTabbedPane tab = new JTabbedPane();
        add(tab);

        //Assembly............................
        //JPanel wASM = new JPanel();
        //tab.add("Assembly",wASM);

        //Memory............................
        JPanel wMEM = new JPanel();
        tab.add("Disassembly",wMEM);
        wMEM.setLayout(null);

        REG_Viewer = new List();
        REG_Viewer.setBackground(Color.white);

        JScrollPane mem_sp = new JScrollPane(REG_Viewer);
        mem_sp.setBounds(0, 0, scWidth - 250, scHeight - 55);
        wMEM.add(mem_sp);

        JButton stepOverButton = new JButton("Step Over");
        stepOverButton.setBounds(350, 310, 90, 20);
        wMEM.add(stepOverButton);

        JButton stepIntoButton = new JButton("Step Into");
        stepIntoButton.setBounds(450, 310, 90, 20);
        stepIntoButton.addActionListener(new ActionHandler());
        wMEM.add(stepIntoButton);

        //X register..
        JLabel REG_X_LABEL = new JLabel("X :");
        REG_X_LABEL.setBounds(330, 10, 60, 20);
        wMEM.add(REG_X_LABEL);

        REG_X = new JLabel("00");
        REG_X.setBounds(330 + 20, 10, 60, 20);
        wMEM.add(REG_X);

        //Y Register..
        JLabel REG_Y_LABEL = new JLabel("Y :");
        REG_Y_LABEL.setBounds(380, 10, 60, 20);
        wMEM.add(REG_Y_LABEL);

        REG_Y = new JLabel("00");
        REG_Y.setBounds(380 + 20, 10, 60, 20);
        wMEM.add(REG_Y);

        //Accumulator..
        JLabel REG_A_LABEL = new JLabel("A :");
        REG_A_LABEL.setBounds(435, 10, 60, 20);
        wMEM.add(REG_A_LABEL);

        REG_A = new JLabel("00");
        REG_A.setBounds(435 + 20, 10, 60, 20);
        wMEM.add(REG_A);

        //Program Counter..
        JLabel REG_PC_LABEL = new JLabel("PC :");
        REG_PC_LABEL.setBounds(485, 10, 60, 20);
        wMEM.add(REG_PC_LABEL);

        REG_PC = new JLabel("0000");
        REG_PC.setBounds(485 + 30, 10, 60, 20);
        wMEM.add(REG_PC);

        //Status Register..
        JLabel REG_STATUS_LABEL = new JLabel("N Z C I D V");
        REG_STATUS_LABEL.setBounds(435 + 20, 40, 60, 20);
        wMEM.add(REG_STATUS_LABEL);

        F_N = new JLabel("0");
        F_N.setBounds(435 + 20, 60, 60, 20);
        wMEM.add(F_N);

        F_Z = new JLabel("0");
        F_Z.setBounds(435 + 30, 60, 60, 20);
        wMEM.add(F_Z);

        F_C = new JLabel("0");
        F_C.setBounds(435 + 40, 60, 60, 20);
        wMEM.add(F_C);

        F_I = new JLabel("0");
        F_I.setBounds(435 + 50, 60, 60, 20);
        wMEM.add(F_I);

        F_D = new JLabel("0");
        F_D.setBounds(435 + 60, 60, 60, 20);
        wMEM.add(F_D);

        F_V = new JLabel("0");
        F_V.setBounds(435 + 70, 60, 60, 20);
        wMEM.add(F_V);


        //Memory $2000..
        JLabel MEM_2000_LABEL = new JLabel("$2000 :");
        MEM_2000_LABEL.setBounds(350, 40, 60, 20);
        wMEM.add(MEM_2000_LABEL);

        MEM_2000 = new JLabel("00");
        MEM_2000.setBounds(350 + 50, 40, 60, 20);
        wMEM.add(MEM_2000);

        //Memory $2001..
        JLabel MEM_2001_LABEL = new JLabel("$2001 :");
        MEM_2001_LABEL.setBounds(350, 60, 60, 20);
        wMEM.add(MEM_2001_LABEL);

        MEM_2001 = new JLabel("00");
        MEM_2001.setBounds(350 + 50, 60, 60, 20);
        wMEM.add(MEM_2001);

        //Memory $2002..
        JLabel MEM_2002_LABEL = new JLabel("$2002 :");
        MEM_2002_LABEL.setBounds(350, 80, 60, 20);
        wMEM.add(MEM_2002_LABEL);

        MEM_2002 = new JLabel("00");
        MEM_2002.setBounds(350 + 50, 80, 60, 20);
        wMEM.add(MEM_2002);

        //Assembly............................
        JPanel wPPU = new JPanel();
        tab.add("PPU",wPPU);

        //PRG............................
        JPanel wPRG = new JPanel();
        tab.add("PRG",wPRG);

        //CHR............................
        JPanel wCHR = new JPanel();
        tab.add("CHR",wCHR);
        
        //NES ROM............................
        JPanel wROM = new JPanel();
        tab.add("NES ROM",wROM);
        wROM.setLayout(null);

        jt = new JTextPane();
        jt.setEditable(false);
        jt.setBackground(Color.lightGray);
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(0, 0, scWidth - 10, scHeight - 55);
        wROM.add(sp);

        SimpleAttributeSet sa = new SimpleAttributeSet();
        StyleConstants.setAlignment(sa, StyleConstants.ALIGN_JUSTIFIED);
        jt.setParagraphAttributes(sa, true);
    }

    public static void loadOpcode()
    {
        int ctr, i;
        int pc = MEMORY.getInitialPC();

        //clear the listbox...
        REG_Viewer.removeAll();

        for(ctr=0; ctr<=255; ctr++)
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
        REG_Viewer.add("[" + tmp.toString().toUpperCase() + "]  " + value);
    }

    private static int getOpcode(int pc)
    {
        int size = 0;

        switch(MEMORY.read8Bit(pc))
        {
            /*       >>Math Instructions<<
             *          Add / Subtract
             */
            /************** ADC **************/
            case 0x69 : //Immediate
                addData(pc, "ADC #$" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0x65 : //Zero Page
                addData(pc, "ADC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0x75 : //Zero Page, X
                addData(pc, "ADC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 2;
                break;
            case 0x6D : //Absolute
                addData(pc, "ADC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0x7D : //Absolute, X
                addData(pc, "ADC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 3;
                break;
            case 0x79 : //Absolute, Y
                addData(pc, "ADC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", Y");
                size = 3;
                break;
            case 0x61 : //(Indirect, X)
                addData(pc, "ADC ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X)");
                size = 2;
                break;
            case 0x71 : //(Indirect), Y
                addData(pc, "ADC ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + "), Y");
                size = 2;
                break;
            /************** SBC **************/
            case 0xE9 : //immidiate
                addData(pc, "SBC #$" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0xE5 : //zeropage
                addData(pc, "SBC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0xF5 : //zeropage,X
                addData(pc, "SBC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 2;
                break;
            case 0xED : //absolute
                addData(pc, "SBC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0xFD : //absolute,X
                addData(pc, "SBC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 3;
                break;
            case 0xF9 : //absolute,Y
                addData(pc, "SBC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", Y");
                size = 3;
                break;
            case 0xE1 : //(indirect,X)
                addData(pc, "SBC ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X)");
                size = 3;
                break;
            case 0xF1 : //(indirect),Y
                addData(pc, "SBC ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + "), Y");
                size = 3;
                break;
            /*
             *        Shift / Rotate
             */
            /************** ASL **************/
            case 0x0A : //(Accumulator)
                addData(pc, "ASL A");
                size = 1;
                break;
            case 0x06 : //Zero Page
                addData(pc, "ASL $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0x16 : //Zero Page, X
                addData(pc, "ASL $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 2;
                break;
            case 0x0E : //Absolute
                addData(pc, "ASL $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0x1E : //Absolute, X
                addData(pc, "ASL $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 3;
                break;
            /************** LSR **************/
            case 0x4A : //accumulator
                addData(pc, "LSR A");
                size = 1;
                break;
            case 0x46 : //zeropage
                addData(pc, "LSR $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0x56 : //zeropage,X
                addData(pc, "LSR $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 2;
                break;
            case 0x4E : //absolute
                addData(pc, "LSR $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0x5E : //absolute,X
                addData(pc, "LSR $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 3;
                break;
            /************** ROL **************/
            case 0x2A : //accumulator
                addData(pc, "ROL A");
                size = 1;
                break;
            case 0x26 : //zeropage
                addData(pc, "ROL $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0x36 : //zeropage,X
                addData(pc, "ROL $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 2;
                break;
            case 0x2E : //absolute
                addData(pc, "ROL $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0x3E : //absolute,X
                addData(pc, "ROL $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 3;
                break;
            /************** ROR **************/
            case 0x6A : //accumulator
                addData(pc, "ROR A");
                size = 1;
                break;
            case 0x66 : //zeropage
                addData(pc, "ROR $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0x76 : //zeropage,X
                addData(pc, "ROR $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 2;
                break;
            case 0x6E : //absolute
                addData(pc, "ROR $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0x7E : //absolute,X
                addData(pc, "ROR $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 3;
                break;
            /*
             *        Increment / Decrement
             */
            /************** INC **************/
            case 0xE6 : //Zero Page
                addData(pc, "INC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0xF6 : //Zero Page, X
                addData(pc, "INC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 2;
                break;
            case 0xEE : //Absolute
                addData(pc, "INC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0xFE : //Absolute, X
                addData(pc, "INC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 3;
                break;
            /************** INX **************/
            case 0xE8 : //Implied
                addData(pc, "INX");
                size = 1;
                break;
            /************** INY **************/
            case 0xC8 : //Implied
                addData(pc, "INY");
                size = 1;
                break;
            /************** DEC **************/
            case 0xC6 : //Zero Page
                addData(pc, "DEC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0xD6 : //Zero Page, X
                addData(pc, "DEC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 2;
                break;
            case 0xCE : //Absolute
                addData(pc, "DEC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0xDE : //Absolute, X
                addData(pc, "DEC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 3;
                break;
            /************** DEX **************/
            case 0xCA : //Implied
                addData(pc, "DEX");
                size = 1;
                break;
            /************** DEY **************/
            case 0x88 : //Implied
                addData(pc, "DEY");
                size = 1;
                break;
            /*
             *        Load and Store Instructions
             */
            /************** LDA **************/
            case 0xA9 : //Immediate
                addData(pc, "LDA #$" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0xA5 : //Zero Page
                addData(pc, "LDA $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0xB5 : //Zero Page, X
                addData(pc, "LDA $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 2;
                break;
            case 0xAD : //Absolute
                addData(pc, "LDA $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0xBD : //Absolute, X
                addData(pc, "LDA $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 3;
                break;
            case 0xB9 : //Absolute, Y
                addData(pc, "LDA $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", Y");
                size = 3;
                break;
            case 0xA1 : //(Indirect, X)
                addData(pc, "LDA ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X)");
                size = 2;
                break;
            case 0xB1 : //(Indirect), Y
                addData(pc, "LDA ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + "), Y");
                size = 2;
                break;
            /************** LDX **************/
            case 0xA2 : //Immediate
                addData(pc, "LDX #$" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0xA6 : //Zero Page
                addData(pc, "LDX $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0xB6 : //Zero Page, Y
                addData(pc, "LDX $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", Y");
                size = 2;
                break;
            case 0xAE : //Absolute
                addData(pc, "LDX $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0xBE : //Absolute, Y
                addData(pc, "LDX $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", Y");
                size = 3;
                break;
            /************** LDY **************/
            case 0xA0 : //Immediate
                addData(pc, "LDY #$" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0xA4 : //Zero Page
                addData(pc, "LDY $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0xB4 : //Zero Page, X
                addData(pc, "LDY $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 2;
                break;
            case 0xAC : //Absolute
                addData(pc, "LDY $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0xBC : //Absolute, X
                addData(pc, "LDY $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 3;
                break;
            /************** STA **************/
            case 0x85 : //Zero Page
                addData(pc, "STA $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0x95 : //Zero Page,X
                addData(pc, "STA $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 2;
                break;
            case 0x8D : //Absolute
                addData(pc, "STA $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0x9D : //Absolute,X
                addData(pc, "STA $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 3;
                break;
            case 0x99 : //Absolute,Y
                addData(pc, "STA $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", Y");
                size = 3;
                break;
            case 0x81 : //(Indirect,X)
                addData(pc, "STA ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X)");
                size = 3;
                break;
            case 0x91 : //(Indirect),Y
                addData(pc, "STA ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + "), Y");
                size = 3;
                break;
            /************** STX **************/
            case 0x86 : //Zero Page
                addData(pc, "STX $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0x96 : //Zero Page,Y
                addData(pc, "STX $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", Y");
                size = 2;
                break;
            case 0x8E : //Absolute
                addData(pc, "STX $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            /************** STY **************/
            case 0x84 : //Zero Page
                addData(pc, "STY $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0x94 : //Zero Page, X
                addData(pc, "STY $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 2;
                break;
            case 0x8C : //Absolute
                addData(pc, "STY $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            /*
             *        Logical Instructions
             */
            /************** AND **************/
            case 0x29 : //Immediate
                addData(pc, "AND #$" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0x25 : //Zero Page
                addData(pc, "AND $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0x35 : //Zero Page, X
                addData(pc, "AND $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 2;
                break;
            case 0x2D : //Absolute
                addData(pc, "AND $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0x3D : //Absolute, X
                addData(pc, "AND $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 3;
                break;
            case 0x39 : //Absolute, Y
                addData(pc, "AND $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", Y");
                size = 3;
                break;
            case 0x21 : //(Indirect, X)
                addData(pc, "AND ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X)");
                size = 2;
                break;
            case 0x31 : //(Indirect), Y
                addData(pc, "AND ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + "), Y");
                size = 2;
                break;
            /************** EOR **************/
            case 0x49 : //Immediate
                addData(pc, "EOR #$" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0x45 : //Zero Page
                addData(pc, "EOR $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0x55 : //Zero Page, X
                addData(pc, "EOR $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 2;
                break;
            case 0x4D : //Absolute
                addData(pc, "EOR $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0x5D : //Absolute, X
                addData(pc, "EOR $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 3;
                break;
            case 0x59 : //Absolute, Y
                addData(pc, "EOR $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", Y");
                size = 3;
                break;
            case 0x41 : //(Indirect, X)
                addData(pc, "EOR ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X)");
                size = 2;
                break;
            case 0x51 : //(Indirect), Y
                addData(pc, "EOR ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + "), Y");
                size = 2;
                break;
            /************** ORA **************/
            case 0x09 : //Immediate
                addData(pc, "ORA #$" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0x05 : //Zero Page
                addData(pc, "ORA $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0x15 : //Zero Page,X
                addData(pc, "ORA $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 2;
                break;
            case 0x0D : //Absolute
                addData(pc, "ORA $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0x1D : //Absolute,X
                addData(pc, "ORA $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 3;
                break;
            case 0x19 : //Absolute,Y
                addData(pc, "ORA $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", Y");
                size = 3;
                break;
            case 0x01 : //(Indirect,X)
                addData(pc, "ORA ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X)");
                size = 2;
                break;
            case 0x11 : //(Indirect),Y
                addData(pc, "ORA ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + "), Y");
                size = 2;
                break;
            /*
             *        Jump, Branching, Subroutine Instructions
             */
            /************** JMP **************/
            case 0x4C : //Absolute
                addData(pc, "JMP $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0x6C : //Indirect
                addData(pc, "JMP ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ")");
                size = 3;
                break;
            /************** BCC **************/
            case 0x90 : //Relative
                addData(pc, "BCC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            /************** BCS **************/
            case 0xB0 : //Relative
                addData(pc, "BCS $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            /************** BEQ **************/
            case 0xF0 : //Relative
                addData(pc, "BEQ $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            /************** BMI **************/
            case 0x30 : //Relative
                addData(pc, "BMI $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            /************** BNE **************/
            case 0xD0 : //Relative
                addData(pc, "BNE $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            /************** BPL **************/
            case 0x10 : //Relative
                addData(pc, "BPL $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            /************** BVC **************/
            case 0x50 : //Relative
                addData(pc, "BVC $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            /************** BVS **************/
            case 0x70 : //Relative
                addData(pc, "BVS $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            /************** JSR **************/
            case 0x20 : //Absolute
                addData(pc, "JSR $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            /************** RTI **************/
            case 0x40 : //implied
                addData(pc, "RTI");
                size = 1;
                break;
            /************** RTS **************/
            case 0x60 : //implied
                addData(pc, "RTS");
                size = 1;
                break;
            /*
             *        Compare / Test Bits Instructions
             */
            /************** CMP **************/
            case 0xC9 : //Immediate
                addData(pc, "CMP #$" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0xC5 : //Zero Page
                addData(pc, "CMP $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0xD5 : //Zero Page, X
                addData(pc, "CMP $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 2;
                break;
            case 0xCD : //Absolute
                addData(pc, "CMP $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            case 0xDD : //Absolute, X
                addData(pc, "CMP $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X");
                size = 3;
                break;
            case 0xD9 : //Absolute, Y
                addData(pc, "CMP $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", Y");
                size = 3;
                break;
            case 0xC1 : //(Indirect, X)
                addData(pc, "CMP ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + ", X)");
                size = 2;
                break;
            case 0xD1 : //(Indirect), Y
                addData(pc, "CMP ($" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)) + "), Y");
                size = 2;
                break;
            /************** CPX **************/
            case 0xE0 : //Immediate
                addData(pc, "CPX #$" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0xE4 : //Zero Page
                addData(pc, "CPX $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0xEC : //Absolute
                addData(pc, "CPX $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            /************** CPY **************/
            case 0xC0 : //immidiate
                addData(pc, "CPY #$" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0xC4 : //zeropage
                addData(pc, "CPY $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0xCC : //absolute
                addData(pc, "CPY $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            /************** BIT **************/
            case 0x24 : //zeropage
                addData(pc, "BIT $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 2;
                break;
            case 0x2C : //absolute
                addData(pc, "BIT $" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 2)) + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc + 1)));
                size = 3;
                break;
            /*
             *        Uncategorized Instructions
             */
            /************** BRL **************/ //<< FIXME: needs attention.
            /************** CLC **************/
            case 0x18 : //Implied
                addData(pc, "CLC");
                size = 1;
                break;
            /************** CLD **************/
            case 0xD8 : //Implied
                addData(pc, "CLD");
                size = 1;
                break;
            /************** CLI **************/
            case 0x58 : //Implied
                addData(pc, "CLI");
                size = 1;
                break;
            /************** CLV **************/
            case 0xB8 : //Implied
                addData(pc, "CLV");
                size = 1;
                break;
            /************** NOP **************/
            case 0xEA : //implied
                addData(pc, "NOP");
                size = 1;
                break;
            /************** PHA **************/
            case 0x48 : //implied
                addData(pc, "PHA");
                size = 1;
                break;
            /************** PHP **************/
            case 0x08 : //implied
                addData(pc, "PHP");
                size = 1;
                break;
            /************** PLA **************/
            case 0x68 : //implied
                addData(pc, "PLA");
                size = 1;
                break;
            /************** PLP **************/
            case 0x28 : //implied
                addData(pc, "PLP");
                size = 1;
                break;
            /************** SEC **************/
            case 0x38 : //implied
                addData(pc, "SEC");
                size = 1;
                break;
            /************** SED **************/
            case 0xF8 : //implied
                addData(pc, "SED");
                size = 1;
                break;
            /************** SEI **************/
            case 0x78 : //implied
                addData(pc, "SEI");
                size = 1;
                break;
            /************** TAX **************/
            case 0xAA : //implied
                addData(pc, "TAX");
                size = 1;
                break;
            /************** TAY **************/
            case 0xA8 : //implied
                addData(pc, "TAY");
                size = 1;
                break;
            /************** TSX **************/
            case 0xBA : //implied
                addData(pc, "TSX");
                size = 1;
                break;
            /************** TXA **************/
            case 0x8A : //implied
                addData(pc, "TXA");
                size = 1;
                break;
            /************** TYA **************/
            case 0x98 : //implied
                addData(pc, "TYA");
                size = 1;
                break;
            /************** TXS **************/
            case 0x9A : //implied
                addData(pc, "TXS");
                size = 1;
                break;
            default :
                addData(pc, ">>>>>>>>Illegal Opcode [" + NES_DEBUGGER.forceTo8Bit(MEMORY.read8Bit(pc)) + "]<<<<<<<<");
                size = 1;
                break;
        }
        return size;
    }
}

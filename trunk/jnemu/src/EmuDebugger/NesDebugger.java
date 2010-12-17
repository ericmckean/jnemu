package EmuDebugger;


import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import jnemu.KeyListener;
import jnemu.ActionHandler;

public class NesDebugger extends JDialog
{
    int scWidth = 700 - 30;
    int scHeight = 400;

    public static JTextPane jt;
    public static List REG_Viewer;

    public static JLabel REG_X;
    public static JLabel REG_Y;
    public static JLabel REG_A;
    public static JLabel REG_SP;

    public static JLabel REG_PC;

    public static JLabel F_N;
    public static JLabel F_Z;
    public static JLabel F_C;
    public static JLabel F_I;
    public static JLabel F_D; 
    public static JLabel F_V;
    public static JLabel F_B;

    public static JLabel MEM_2000;
    public static JLabel MEM_2001;
    public static JLabel MEM_2002;
    public static JLabel MEM_2003;
    public static JLabel MEM_2004;
    public static JLabel MEM_2005;
    public static JLabel MEM_2006;
    public static JLabel MEM_2007;

    public static JLabel CPU_CYCLE;
    public static JLabel PPU_SCANLINE;

    public static JTextPane mv;
    public static JButton mv_btn;
    public static JComboBox mv_txt;

    public static JTextField codeBreak;
    public static JCheckBox codeCheck;

    public NesDebugger()
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
        tab.addKeyListener(new KeyListener());
        add(tab);

        //Assembly............................
        //JPanel wASM = new JPanel();
        //tab.add("Assembly",wASM);

        //******************************************************
        //
        //                  Disassembly TAB
        //
        //******************************************************
        JPanel wMEM = new JPanel();
        tab.add("Disassembly",wMEM);
        wMEM.setLayout(null);

        REG_Viewer = new List();
        REG_Viewer.setBackground(Color.white);
        
        JScrollPane mem_sp = new JScrollPane(REG_Viewer);
        mem_sp.setBounds(0, 0, 320, 345);
        wMEM.add(mem_sp);

        JButton DISASM_REFRESH = new JButton("Refresh");
        DISASM_REFRESH.setBounds(350, 310, 90, 20);
        DISASM_REFRESH.addActionListener(new ActionHandler());
        wMEM.add(DISASM_REFRESH);

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
        JLabel REG_STATUS_LABEL = new JLabel("N V  B D I Z C");
        REG_STATUS_LABEL.setBounds(435 + 20, 40, 80, 20);
        wMEM.add(REG_STATUS_LABEL);

        JLabel LABEL_CPU_CYCLE = new JLabel("Cycle :");
        LABEL_CPU_CYCLE.setBounds(435 + 20, 90, 80, 20);
        wMEM.add(LABEL_CPU_CYCLE);

        CPU_CYCLE = new JLabel("0");
        CPU_CYCLE.setBounds(435 + 70, 90, 60, 20);
        wMEM.add(CPU_CYCLE);

        //Scanline...........
        JLabel LABEL_CPU_SCANLINE = new JLabel("Scanline :");
        LABEL_CPU_SCANLINE.setBounds(435 + 20, 110, 80, 20);
        wMEM.add(LABEL_CPU_SCANLINE);

        PPU_SCANLINE = new JLabel("0");
        PPU_SCANLINE.setBounds(435 + 80, 110, 60, 20);
        wMEM.add(PPU_SCANLINE);

        //Stack Pointer...........
        JLabel LABEL_CPU_SP = new JLabel("SP :");
        LABEL_CPU_SP.setBounds(435 + 20, 140, 80, 20);
        wMEM.add(LABEL_CPU_SP);

        REG_SP = new JLabel("0");
        REG_SP.setBounds(435 + 50, 140, 60, 20);
        wMEM.add(REG_SP);

        //Status register content.........
        F_C = new JLabel("0");
        F_C.setBounds(435 + 84, 60, 60, 20);
        wMEM.add(F_C);

        F_Z = new JLabel("0");
        F_Z.setBounds(435 + 74, 60, 60, 20);
        wMEM.add(F_Z);

        F_I = new JLabel("0");
        F_I.setBounds(435 + 65, 60, 60, 20);
        wMEM.add(F_I);

        F_D = new JLabel("0");
        F_D.setBounds(435 + 56, 60, 60, 20);
        wMEM.add(F_D);

        F_B = new JLabel("0");
        F_B.setBounds(435 + 46, 60, 60, 20);
        wMEM.add(F_B);

        F_V = new JLabel("0");
        F_V.setBounds(435 + 32, 60, 60, 20);
        wMEM.add(F_V);

        F_N = new JLabel("0");
        F_N.setBounds(435 + 22, 60, 60, 20);
        wMEM.add(F_N);


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

        //Memory $2003..
        JLabel MEM_2003_LABEL = new JLabel("$2003 :");
        MEM_2003_LABEL.setBounds(350, 100, 60, 20);
        wMEM.add(MEM_2003_LABEL);

        MEM_2003 = new JLabel("00");
        MEM_2003.setBounds(350 + 50, 100, 60, 20);
        wMEM.add(MEM_2003);

        //Memory $2004..
        JLabel MEM_2004_LABEL = new JLabel("$2004 :");
        MEM_2004_LABEL.setBounds(350, 120, 60, 20);
        wMEM.add(MEM_2004_LABEL);

        MEM_2004 = new JLabel("00");
        MEM_2004.setBounds(350 + 50, 120, 60, 20);
        wMEM.add(MEM_2004);

        //Memory $2005..
        JLabel MEM_2005_LABEL = new JLabel("$2005 :");
        MEM_2005_LABEL.setBounds(350, 140, 60, 20);
        wMEM.add(MEM_2005_LABEL);

        MEM_2005 = new JLabel("00");
        MEM_2005.setBounds(350 + 50, 140, 60, 20);
        wMEM.add(MEM_2005);

        //Memory $2006..
        JLabel MEM_2006_LABEL = new JLabel("$2006 :");
        MEM_2006_LABEL.setBounds(350, 160, 60, 20);
        wMEM.add(MEM_2006_LABEL);

        MEM_2006 = new JLabel("00");
        MEM_2006.setBounds(350 + 50, 160, 60, 20);
        wMEM.add(MEM_2006);

        //Memory $2007..
        JLabel MEM_2007_LABEL = new JLabel("$2007 :");
        MEM_2007_LABEL.setBounds(350, 180, 60, 20);
        wMEM.add(MEM_2007_LABEL);

        MEM_2007 = new JLabel("00");
        MEM_2007.setBounds(350 + 50, 180, 60, 20);
        wMEM.add(MEM_2007);

        codeBreak = new JTextField();
        codeBreak.setBounds(370, 280, 50, 20);
        wMEM.add(codeBreak);

        codeCheck = new JCheckBox();
        codeCheck.setBounds(350, 280, 20, 20);
        wMEM.add(codeCheck);

        JLabel breakLabel = new JLabel("Break Point");
        breakLabel.setBounds(430, 280, 120, 20);
        wMEM.add(breakLabel);

        //******************************************************
        //
        //                  Memory Viewer TAB
        //
        //******************************************************
        JPanel wMemViewer = new JPanel();
        tab.add("CPU / PPU Memory",wMemViewer);
        wMemViewer.setLayout(null);

        mv = new JTextPane();
        mv.setEditable(false);
        mv.setBackground(Color.white);
        JScrollPane _mv = new JScrollPane(mv);
        _mv.setBounds(0, 40, scWidth - 10, (scHeight - 55) - 40);
        wMemViewer.add(_mv);

        String[] mv_cont = new String[14];
        mv_cont[0] = "Zero Page $0000-$00FF";
        mv_cont[1] = "Stack $0100-$01FF";
        mv_cont[2] = "RAM $02FF-$07FF";
        mv_cont[3] = "Mirror of $2000-$2007";
        mv_cont[4] = "APU and I/O Register $4000-$4019";
        mv_cont[5] = "Expansion ROM $4020-$5FFF";
        mv_cont[6] = "SRAM $6000-$7FFF";
        mv_cont[7] = "PRG-ROM Bank 1 $8000-$BFFF";
        mv_cont[8] = "PRG-ROM Bank 2 $C000-$FFFF";
        mv_cont[9] = "Pattern Table 0";
        mv_cont[10] = "Pattern Table 1";
        mv_cont[11] = "Name Tables";
        mv_cont[12] = "Palette";
        mv_cont[13] = "OAM";
        

        mv_txt = new JComboBox(mv_cont);
        mv_txt.setBounds(80, 10, 400, 20);
        wMemViewer.add(mv_txt);

        mv_btn = new JButton("Show");
        mv_btn.setBounds(490, 10, 70, 20);
        mv_btn.addActionListener(new ActionHandler());
        wMemViewer.add(mv_btn);

        SimpleAttributeSet mv_sa = new SimpleAttributeSet();
        StyleConstants.setAlignment(mv_sa, StyleConstants.ALIGN_JUSTIFIED);
        mv.setParagraphAttributes(mv_sa, true);


        //******************************************************
        //
        //                    NES ROM TAB
        //
        //******************************************************
        JPanel wROM = new JPanel();
        tab.add("NES ROM",wROM);
        wROM.setLayout(null);

        jt = new JTextPane();
        jt.setEditable(false);
        jt.setBackground(Color.white);
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(0, 0, scWidth - 10, scHeight - 55);
        wROM.add(sp);

        SimpleAttributeSet sa = new SimpleAttributeSet();
        StyleConstants.setAlignment(sa, StyleConstants.ALIGN_JUSTIFIED);
        jt.setParagraphAttributes(sa, true);
    }
}

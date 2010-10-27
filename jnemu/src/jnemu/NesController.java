package jnemu;

import java.awt.*;
import javax.swing.*;

public class NesController extends JDialog
{
    int scWidth = 200;
    int scHeight = 300;

    int X = 20;
    int Y = 10;

    NesController()
    {
        /********************** Main Window ***********************/
        setTitle("Controller");
        setSize(scWidth,scHeight);
        setResizable(false);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dim = t.getScreenSize();
        setLocation((dim.width / 2) - (scWidth / 2), (dim.height / 2) - (scHeight / 2));

        /****************************** TAB ****************************/
        JTabbedPane tab = new JTabbedPane();
        add(tab);

        JPanel con1 = new JPanel();
        tab.add("PAD 1",con1);
        con1.setLayout(null);

        JPanel con2 = new JPanel();
        tab.add("PAD 2",con2);
        con2.setLayout(null);

        /********************************** Controls 1 ******************************/
        //UP.................
        JLabel l_up = new JLabel("UP :");
        l_up.setBounds(X, Y, 60, 20);
        con1.add(l_up);

        JTextField t_up = new JTextField();
        t_up.setBounds(X + 65, Y, 60, 20);
        con1.add(t_up);

        //DOWN.................
        JLabel l_down = new JLabel("DOWN :");
        l_down.setBounds(X, Y + 23, 60, 20);
        con1.add(l_down);

        JTextField t_down = new JTextField();
        t_down.setBounds(X + 65, Y + 23, 60, 20);
        con1.add(t_down);

        //LEFT.................
        JLabel l_left = new JLabel("LEFT :");
        l_left.setBounds(X, Y + (23 * 2), 60, 20);
        con1.add(l_left);

        JTextField t_left = new JTextField();
        t_left.setBounds(X + 65, Y + (23 * 2), 60, 20);
        con1.add(t_left);

        //RIGHT.................
        JLabel l_right = new JLabel("RIGHT :");
        l_right.setBounds(X, Y + (23 * 3), 60, 20);
        con1.add(l_right);

        JTextField t_right = new JTextField();
        t_right.setBounds(X + 65, Y + (23 * 3), 60, 20);
        con1.add(t_right);

        //SELECT.................
        JLabel l_select = new JLabel("SELECT :");
        l_select.setBounds(X, Y + (23 * 4), 60, 20);
        con1.add(l_select);

        JTextField t_select = new JTextField();
        t_select.setBounds(X + 65, Y + (23 * 4), 60, 20);
        con1.add(t_select);

        //START.................
        JLabel l_start = new JLabel("START :");
        l_start.setBounds(X, Y + (23 * 5), 60, 20);
        con1.add(l_start);

        JTextField t_start = new JTextField();
        t_start.setBounds(X + 65, Y + (23 * 5), 60, 20);
        con1.add(t_start);

        //B.................
        JLabel l_b = new JLabel("B :");
        l_b.setBounds(X, Y + (23 * 6), 60, 20);
        con1.add(l_b);

        JTextField t_b = new JTextField();
        t_b.setBounds(X + 65, Y + (23 * 6), 60, 20);
        con1.add(t_b);

        //A.................
        JLabel l_a = new JLabel("A :");
        l_a.setBounds(X, Y + (23 * 7), 60, 20);
        con1.add(l_a);

        JTextField t_a = new JTextField();
        t_a.setBounds(X + 65, Y + (23 * 7), 60, 20);
        con1.add(t_a);

        //SAVE.............
        JButton save = new JButton("Save");
        save.setBounds(X + 80, Y + (23 * 9), 80, 20);
        con1.add(save);

        /********************************** Controls 2 ******************************/
        //UP.................
        JLabel l_up2 = new JLabel("UP :");
        l_up2.setBounds(X, Y, 60, 20);
        con2.add(l_up2);

        JTextField t_up2 = new JTextField();
        t_up2.setBounds(X + 65, Y, 60, 20);
        con2.add(t_up2);

        //DOWN.................
        JLabel l_down2 = new JLabel("DOWN :");
        l_down2.setBounds(X, Y + 23, 60, 20);
        con2.add(l_down2);

        JTextField t_down2 = new JTextField();
        t_down2.setBounds(X + 65, Y + 23, 60, 20);
        con2.add(t_down2);

        //LEFT.................
        JLabel l_left2 = new JLabel("LEFT :");
        l_left2.setBounds(X, Y + (23 * 2), 60, 20);
        con2.add(l_left2);

        JTextField t_left2 = new JTextField();
        t_left2.setBounds(X + 65, Y + (23 * 2), 60, 20);
        con2.add(t_left2);

        //RIGHT.................
        JLabel l_right2 = new JLabel("RIGHT :");
        l_right2.setBounds(X, Y + (23 * 3), 60, 20);
        con2.add(l_right2);

        JTextField t_right2 = new JTextField();
        t_right2.setBounds(X + 65, Y + (23 * 3), 60, 20);
        con2.add(t_right2);

        //SELECT.................
        JLabel l_select2 = new JLabel("SELECT :");
        l_select2.setBounds(X, Y + (23 * 4), 60, 20);
        con2.add(l_select2);

        JTextField t_select2 = new JTextField();
        t_select2.setBounds(X + 65, Y + (23 * 4), 60, 20);
        con2.add(t_select2);

        //START.................
        JLabel l_start2 = new JLabel("START :");
        l_start2.setBounds(X, Y + (23 * 5), 60, 20);
        con2.add(l_start2);

        JTextField t_start2 = new JTextField();
        t_start2.setBounds(X + 65, Y + (23 * 5), 60, 20);
        con2.add(t_start2);

        //B.................
        JLabel l_b2 = new JLabel("B :");
        l_b2.setBounds(X, Y + (23 * 6), 60, 20);
        con2.add(l_b2);

        JTextField t_b2 = new JTextField();
        t_b2.setBounds(X + 65, Y + (23 * 6), 60, 20);
        con2.add(t_b2);

        //A.................
        JLabel l_a2 = new JLabel("A :");
        l_a2.setBounds(X, Y + (23 * 7), 60, 20);
        con2.add(l_a2);

        JTextField t_a2 = new JTextField();
        t_a2.setBounds(X + 65, Y + (23 * 7), 60, 20);
        con2.add(t_a2);

        //SAVE.............
        JButton save2 = new JButton("Save");
        save2.setBounds(X + 80, Y + (23 * 9), 80, 20);
        con2.add(save2);

    }

}

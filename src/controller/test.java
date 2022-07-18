package controller;

import javax.swing.*;

public class test extends JFrame {

    public test() {
        JLabel d = new JLabel();
        d.setBounds(0, 0, 100, 100);
        System.out.println(d.getX() + " " + d.getY() + " " + d.getWidth() + " " + d.getHeight());
    }
}

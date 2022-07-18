package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.*;

public class PreviewGUI extends JFrame {

    private JButton backButton = new JButton("Back to main menu");

    public PreviewGUI(int w, int h) {

        setSize(w, h);
        setLayout(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        backButton.setBounds(w / 2, h / 2, 150, 30);

        this.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {

            }
        });

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainMenu();
            }

        });

        add(backButton);
        setVisible(true);
    }

}

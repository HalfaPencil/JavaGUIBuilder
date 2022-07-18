package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SizeFieldPanel extends JPanel implements ActionListener {

    private JComboBox<String> sizeComboBox = new JComboBox<String>();
    private JLabel textLabel = new JLabel("Select the screen size you want to use");
    private JLabel enterLabel = new JLabel("Or enter a custom screen size (w x h) ");

    private JTextField sizeField = new JTextField();
    private String curSize;
    boolean custom = false;

    public SizeFieldPanel() {

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(null);

        textLabel.setBounds(10, 10, 225, 25);
        enterLabel.setBounds(10, 80, 225, 25);
        sizeField.setBounds(10, 100, 225, 25);

        sizeComboBox.setBounds(10, 50, 150, 25);

        try {
            Scanner size = new Scanner(new File("src\\Files\\ScreenSizes.txt"));
            while (size.hasNextLine()) {
                sizeComboBox.addItem(size.nextLine());

            }
            sizeComboBox.setSelectedIndex(1);
            curSize = sizeComboBox.getSelectedItem().toString();

            sizeComboBox.addItem("Custom size");
            sizeComboBox.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    JComboBox cb = (JComboBox) e.getSource();

                    if (cb.getSelectedItem().equals("Custom size") && !custom) {
                        add(sizeField);
                        add(enterLabel);
                        custom = true;

                    } else if (!cb.getSelectedItem().equals("Custom size") && custom) {
                        remove(sizeField);
                        remove(enterLabel);
                        custom = false;
                    }

                    repaint();
                    curSize = (String) cb.getSelectedItem();
                }

            });
            size.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        add(sizeComboBox);
        add(textLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

    public String getCurSize() {
        if (curSize.equals("Custom size"))
            return sizeField.getText();
        else
            return curSize;
    }

    public void setCurSize(String curSize) {
        this.curSize = curSize;
    }

    public JTextField getSizeField() {
        return sizeField;
    }

    public void setSizeField(JTextField sizeField) {
        this.sizeField = sizeField;
    }

}

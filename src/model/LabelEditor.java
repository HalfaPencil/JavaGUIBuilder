package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.DesignGUI;

public class LabelEditor extends JPanel {
    JTextField editText = new JTextField();
    JCheckBox boldText = new JCheckBox("Bold");
    JCheckBox italicText = new JCheckBox("Italics");
    JComboBox<Integer> textSize = new JComboBox<Integer>();

    JButton deleteButton = new JButton("Delete");

    public LabelEditor(String curString, int self, String type) {
        setPreferredSize(new Dimension(200, 200));
        setLayout(null);
        editText.setText(curString);
        editText.setBounds(0, 0, 200, 25);
        boldText.setBounds(0, 50, 100, 25);
        italicText.setBounds(0, 100, 100, 25);
        deleteButton.setBounds(0, 150, 100, 25);
        deleteButton.setForeground(Color.RED);

        deleteButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                DesignGUI.delete(self,type);
                
            }

        });
        add(deleteButton);
        add(boldText);
        add(editText);
        add(italicText);

        textSize.setBounds(100, 50, 100, 25);
        for (int i = 1; i <= 50; i++) {
            textSize.addItem(i);
        }
        textSize.setSelectedIndex(13);
        add(textSize);
    }

    public JCheckBox getBoldText() {
        return boldText;
    }

    public JCheckBox getItalicText() {
        return italicText;
    }

    public JComboBox<Integer> getTextSize() {
        return textSize;
    }

    public JTextField getEditText() {
        return editText;
    }

}

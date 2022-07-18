package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NamePanel extends JPanel {

    private JTextField nameField = new JTextField();
    private JLabel nameLabel = new JLabel("Enter the title of your GUI");

    public NamePanel() {

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(null);

        nameField.setBounds(5, 50, 180, 25);
        add(nameField);

        nameLabel.setBounds(5, 20, 150, 25);
        add(nameLabel);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }


}

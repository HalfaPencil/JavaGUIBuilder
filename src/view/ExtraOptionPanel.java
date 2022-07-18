package view;

import java.awt.Color;

import javax.swing.*;

public class ExtraOptionPanel extends JPanel {

    private JLabel extraOptionLabel = new JLabel("Select the features you would like to include");

    private JCheckBox menuCheckBox = new JCheckBox("Menu bar");

    private JComboBox<String> typeCombo = new JComboBox<String>();

    private JLabel packageLabel = new JLabel("Enter package name (Empty for default)");
    private JTextField packageField = new JTextField();

    public ExtraOptionPanel() {
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        placeOptions();

        add(packageLabel);
        add(packageField);
        add(extraOptionLabel);
        add(menuCheckBox);
        add(typeCombo);
    }

    private void placeOptions() {

        extraOptionLabel.setBounds(10, 10, 200, 25);
        packageLabel.setBounds(10, 65, 250, 25);
        packageField.setBounds(10, 90, 200, 25);
        menuCheckBox.setBounds(10, 30, 200, 25);

        typeCombo.setBounds(10, 120, 250, 25);
        typeCombo.addItem("Choose the type of file you want");
        typeCombo.addItem("JFrame");
        typeCombo.addItem("JPanel");
    }

    public JCheckBox getMenuCheckBox() {
        return menuCheckBox;
    }

    public void setMenuCheckBox(JCheckBox menuCheckBox) {
        this.menuCheckBox = menuCheckBox;
    }

    public JTextField getPackageField() {
        return packageField;
    }

    public JComboBox<String> getTypeCombo() {
        return typeCombo;
    }

}

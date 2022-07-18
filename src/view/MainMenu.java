package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener {

    private JLabel titleLabel = new JLabel("Java GUI Builder");

    private JButton newGUIButton = new JButton("Create a new GUI");
    private JButton previewGUIButton = new JButton("Preview");
    private JButton openPrevGUI = new JButton("Open saved GUI");

    private SizeFieldPanel sizeFieldPanel = new SizeFieldPanel();
    private NamePanel namePanel = new NamePanel();
    private ExtraOptionPanel extraOptionPanel = new ExtraOptionPanel();

    public MainMenu() {

        setSize(800, 600);
        setLayout(null);
        setTitle("Java GUI Builder");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        sizeFieldPanel.setBounds(10, 100, 250, 200);
        add(sizeFieldPanel);

        namePanel.setBounds(270, 100, 250, 200);
        add(namePanel);

        extraOptionPanel.setBounds(10, 310, 350, 250);
        add(extraOptionPanel);

        setupLabels();
        setupButtons();

        setVisible(true);

    }

    private void setupButtons() {

        newGUIButton.setBounds(575, 450, 150, 25);
        newGUIButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String[] size = sizeFieldPanel.getCurSize().split(" ");
                int w = Integer.parseInt(size[0]);
                int h = Integer.parseInt(size[2]);
                boolean menu = extraOptionPanel.getMenuCheckBox().isSelected();
                String title = namePanel.getNameField().getText();
                String type = extraOptionPanel.getTypeCombo().getSelectedItem().toString();
                String packageName = extraOptionPanel.getPackageField().getText();

                // new DesignGUI(w, h, menu, title);
                if (title.length() == 0) {
                    noTitle();
                } else {
                    dispose();
                    if (w < 1920 && h < 1080)
                        new DesignBar(w, h, new DesignGUI(w, h, menu, title, packageName, type));
                    else
                        new DesignBar(0, 0, new DesignGUI(w, h, menu, title, packageName, type));

                }
            }

        });
        add(newGUIButton);

        previewGUIButton.setBounds(600, 500, 100, 25);
        previewGUIButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String[] size = sizeFieldPanel.getCurSize().split(" ");

                int w = Integer.parseInt(size[0]);
                int h = Integer.parseInt(size[2]);

                dispose();
                new PreviewGUI(w, h);
            }

        });
        add(previewGUIButton);

        openPrevGUI.setBounds(600,400,100,25);
        openPrevGUI.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                
            }
        
        });

    }

    public void noTitle() {
        namePanel.getNameField().setBorder(BorderFactory.createLineBorder(Color.RED));
        JOptionPane.showMessageDialog(this, "Enter a Title");
    }

    private void setupLabels() {
        titleLabel.setBounds(325, 20, 150, 30);
        titleLabel.setFont(new Font(null, 0, 20));
        add(titleLabel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
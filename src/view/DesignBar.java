package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import controller.GUIReader;

public class DesignBar extends JFrame {

    public static ArrayList<JToggleButton> elementButtons = new ArrayList<JToggleButton>();
    private JButton test = new JButton();
    private JButton save = new JButton("Transfer to Java file");

    private DesignGUI designGUI;

    public DesignBar(int w, int h, DesignGUI designGUI) {
        this.designGUI = designGUI;

        setSize(1000, 200);
        setTitle("Add components");
        setLayout(null);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocation(0, h);

        setupElements();
        setVisible(true);

    }

    private void setupElements() {
        makeCreateLabel();
        makeCreateButton();
        makeSaveButton();
    }

    private void makeSaveButton() {

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        save.setBounds(800, 0, 100, 50);
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(DesignBar.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {

                        File f = new File(fc.getSelectedFile().getAbsolutePath() + "\\"
                                + designGUI.getTitle() + ".java");
                        File f2 = new File(
                                fc.getSelectedFile().getAbsolutePath() + "\\" + designGUI.getTitle() + ".txt");
                        if (f.createNewFile()) {
                            new GUIReader(f, f2, designGUI);
                        }
                    } catch (IOException e1) {

                    }
                }
            }

        });

        add(save);
    }

    private void makeCreateButton() {
        elementButtons.add(new JToggleButton("Create Button"));
        elementButtons.get(1).setBounds(120, 10, 100, 200);
        elementButtons.get(1).setBorder(BorderFactory.createLineBorder(Color.black));
        elementButtons.get(1).setHorizontalAlignment(SwingConstants.CENTER);
        elementButtons.get(1).setVerticalAlignment(SwingConstants.CENTER);

        elementButtons.get(1).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                designGUI.setGUIType(GUIType.BUTTON);
            }

        });
        add(elementButtons.get(1));
    }

    private void makeCreateLabel() {
        elementButtons.add(new JToggleButton("Text Label"));
        elementButtons.get(0).setBounds(10, 10, 100, 200);
        elementButtons.get(0).setBorder(BorderFactory.createLineBorder(Color.BLACK));
        elementButtons.get(0).setHorizontalAlignment(SwingConstants.CENTER);
        elementButtons.get(0).setVerticalAlignment(SwingConstants.CENTER);
        elementButtons.get(0).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                designGUI.setGUIType(GUIType.LABEL);

            }

        });

        add(elementButtons.get(0));
    }
    private void createCheckBox(){
        
    }
}

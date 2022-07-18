package view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.*;

import model.GUIObjects;

public class DesignGUI extends JFrame implements MouseListener {
    GUIType type;
    private Map<Integer, GUIObjects> buttonMap = new HashMap<Integer, GUIObjects>();
    private int buttonCount = 0;
    private Map<Integer, GUIObjects> labelMap = new HashMap<Integer, GUIObjects>();
    private int labelCount = 0;
    private String title;
    private String typeString;
    private String packageName;

    public DesignGUI(int width, int height, boolean menu, String title, String packageName, String type) {
        this.packageName = packageName;
        this.title = title;
        this.typeString = type;

        setLayout(null);
        setSize(width, height);
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addMouseListener(this);

        if (menu) {
            JMenu menu2 = new JMenu("Menu");
            JMenuBar menuBar = new JMenuBar();
            JMenuItem test = new JMenuItem("test");

            menu2.add(test);
            menuBar.add(menu2);
            setJMenuBar(menuBar);
        }

        setVisible(true);
    }

    public DesignGUI(File f) {
        loadFile(f);
    }

    private void loadFile(File f) {
        try {
            Scanner in = new Scanner(f);

            while (in.hasNextLine()) {

            }

        } catch (IOException e) {
        }

    }

    public static void delete(int self, String type) {
        switch (type) {
            case "label":
                remove(labelMap.get(self));
                labelMap.remove(self);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (type != null)
            switch (type.toString()) {
                case "LABEL":

                    labelMap.put(labelCount, new GUIObjects(new JLabel("Enter text here"), "label", labelCount));
                    labelMap.get(labelCount).setBounds(e.getX(), e.getY() - 25, 150, 25);
                    add(labelMap.get(labelCount));
                    System.out.println(labelMap.get(labelCount).getBounds());
                    labelCount++;
                    revalidate();
                    repaint();
                    setGUIType(null);
                    DesignBar.elementButtons.get(0).setSelected(false);

                    break;

                case "BUTTON":
                    buttonMap.put(buttonCount, new GUIObjects(new JButton("Enter text here"), "button", buttonCount));
                    buttonMap.get(buttonCount).setBounds(e.getX(), e.getY() - 25, 150, 50);
                    add(buttonMap.get(buttonCount));
                    System.out.println(buttonMap.get(buttonCount).getBounds());
                    buttonCount++;
                    revalidate();
                    repaint();
                    setGUIType(null);
                    DesignBar.elementButtons.get(1).setSelected(false);
                    break;
                default:
                    break;

            }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public String getTitle() {
        return title;
    }

    public int getLabelCount() {
        return labelCount;
    }

    public Map<Integer, GUIObjects> getLabelMap() {
        return labelMap;
    }

    public void setLabelMap(Map<Integer, GUIObjects> labelMap) {
        this.labelMap = labelMap;
    }

    public void setGUIType(GUIType type) {
        this.type = type;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getTypeString() {
        return typeString;
    }

    public Map<Integer, GUIObjects> getButtonMap() {
        return buttonMap;
    }

    public int getButtonCount() {
        return buttonCount;
    }

}

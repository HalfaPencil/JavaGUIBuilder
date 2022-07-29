package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import model.GUIObjects;
import view.DesignGUI;

public class GUIReader {

    BufferedWriter out;
    BufferedWriter out2;

    private DesignGUI dGui;
    private File fileLocation;
    private File textLocation;

    public GUIReader(File fileLocation, File textLocation, DesignGUI dGui) {
        this.fileLocation = fileLocation;
        this.textLocation = textLocation;
        this.dGui = dGui;
        createGUIFile();
        textReader();
        System.out.println("Done");
    }

    // public GUIReader(File fileLocation) {
    // this.fileLocation = fileLocation;
    // createGUIFileTest();
    // textReader();
    // System.out.println("Done");
    // }

    private void textReader() {
        try {
            out2 = new BufferedWriter(new FileWriter(textLocation));
            
        } catch (IOException e) {

        }
    }

    public void writeToFile() {

    }

    public void createGUIFile() {
        try {
            out = new BufferedWriter(new FileWriter(fileLocation));
            writeStart();

            if (dGui.getLabelCount() > 0)
                writeLabels();

            if (dGui.getButtonCount() > 0)
                writeButtons();

            out.write("}");
            out.close();
        } catch (IOException e) {
        }
    }

    private void writeButtons() {
        try {

            out.write("public void createButtons() { \n");
            ArrayList<Integer> keys = new ArrayList<Integer>(dGui.getButtonMap().keySet());

            for (int i = 0; i < dGui.getButtonMap().size(); i++) {
                String name = "button" + Integer.toString(i);
                GUIObjects temp = dGui.getButtonMap().get(keys.get(i));

                if (temp.getComp() instanceof JButton) {
                    JButton tempButton = (JButton) temp.getComp();
                    String text = tempButton.getText();
                    String r = tempButton.getBounds().toString();
                    System.out.println(temp.getX());

                    out.write("JButton " + name + "= new JButton(\"" + text + "\");\n ");
                    out.write(name + ".setBounds(" + temp.getX() + "," + temp.getY() + ","
                            + temp.getWidth() + "," + temp.getHeight() + ");\n");
                    out.write(name + ".setFont(new Font (\"" + tempButton.getFont().getFontName() + "\","
                            + tempButton.getFont().getStyle() + ", " + tempButton.getFont().getSize() + "));\n");
                    out.write(name + ".addActionListener(new ActionListener(){\n\n");
                    out.write("@Override \n ");
                    out.write("public void actionPerformed(ActionEvent e) { \n\n");
                    out.write("}\n\n");
                    out.write("});\n");

                    out.write("add(" + name + ");\n");
                }

            }
            out.write("}\n");
        } catch (IOException e) {
        }
    }

    private void writeLabels() {
        try {
            out.write("public void createLabels() { \n");
            ArrayList<Integer> keys = new ArrayList<Integer>(dGui.getLabelMap().keySet());
            for (int i = 0; i < dGui.getLabelMap().size(); i++) {
                String name = "label" + Integer.toString(i);
                GUIObjects temp = dGui.getLabelMap().get(keys.get(i));

                if (temp.getComp() instanceof JLabel) {
                    JLabel tempLabel = (JLabel) temp.getComp();
                    String text = tempLabel.getText();

                    out.write("JLabel " + name + "= new JLabel(\"" + text + "\");\n ");
                    out.write(name + ".setBounds(" + temp.getX() + "," + temp.getY() + ","
                            + temp.getWidth() + "," + temp.getHeight() + ");\n");
                    out.write(name + ".setFont(new Font (\"" + tempLabel.getFont().getFontName() + "\","
                            + tempLabel.getFont().getStyle() + ", " + tempLabel.getFont().getSize() + "));\n");
                    out.write("add(" + name + ");\n");
                }

            }

            out.write("\n}");
        } catch (IOException e) {

        }
    }

    private void writeStart() {

        try {
            if (dGui.getPackageName().length() > 0) {
                out.write("package " + dGui.getPackageName() + ";\n\n");
            }
            out.write("import javax.swing.*;\n");
            if (dGui.getButtonCount() > 0) {
                out.write("import java.awt.event.ActionListener;\n");
                out.write("import java.awt.event.ActionEvent;\n");
            }

            out.write("public class " + dGui.getTitle() + " extends " + dGui.getTypeString() + "{ \n\n");
            out.write("public " + dGui.getTitle() + "() { \n\n");

            if (dGui.getTypeString().equals("JFrame"))
                out.write("\t\tsetTitle(\"" + dGui.getTitle() + "\");");
            out.write("\t\tsetLayout(null); \n");
            out.write("\t\tsetSize(" + dGui.getWidth() + "," + dGui.getHeight() + ");\n");
            out.write("\t\tcreateLabels();\n");
            out.write("\t\tcreateButtons();\n");
            out.write("\t\tsetVisible(true);\n");
            out.write("}\n\n");

        } catch (IOException e) {

        }

    }

    public void createGUIFileTest() {
        try {
            out = new BufferedWriter(new FileWriter(fileLocation));

            out.close();

        } catch (IOException e) {

        }

    }
}

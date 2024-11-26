package io.sahil.assignment3;

import acm.program.Program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * This program demonstrates the Editor problem
 *
 * @author Sahil Saini
 */
public class Editor extends Program {

    private final String open = "Open";
    private final String save = "Save";

    private JTextArea inputText;
    private JTextField filename;
    private JButton saveButton;
    private JButton openButton;

    private StringBuilder fileContent;

    public static void main(String[] args) {
        Editor program = new Editor();
        program.start();
    }

    /**
     * Initialize UI consisting of Text Area, Text Field, and Buttons
     */
    public void init() {
        inputText = new JTextArea();
        inputText.setFont(new Font("Monospaced", Font.BOLD, 20));
        add(inputText, CENTER);

        filename = new JTextField(20);
        filename.setFont(new Font("Monospaced", Font.PLAIN, 16));
        add(filename, SOUTH);

        openButton = new JButton(open);
        add(openButton, SOUTH);

        saveButton = new JButton(save);
        add(saveButton, SOUTH);

        fileContent = new StringBuilder();

        addActionListeners();
    }

    /**
     * Observe registered button events
     *
     * @param actionEvent
     */
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        if (command.equals(open)) {
            try {
                openFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals(save)) {
            try {
                writeFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void writeFile() throws Exception{
        FileWriter fileWriter = new FileWriter(filename.getText(), false);
        String inputTextString = inputText.getText();
        fileWriter.write(inputTextString);
        fileWriter.close();
    }

    private void openFile() throws Exception {
        FileReader fileReader = new FileReader(filename.getText());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            fileContent.append(line);
            fileContent.append("\n");
        }
        bufferedReader.close();
        fileReader.close();
        String fileText = fileContent.toString();
        inputText.setText(fileText);
    }
}

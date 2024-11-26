package io.sahil.assignment3;

import acm.program.ConsoleProgram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * This class represents State Lookup problem
 *
 * @author Sahil Saini
 */
public class StateLookup extends ConsoleProgram {

    HashMap<String, String> states;

    public static void main(String[] args) {
        StateLookup stateLookup = new StateLookup();
        stateLookup.start();
    }

    public void init() {
        setSize(500, 500);
        setFont("Courier-20");
        states = new HashMap<>();
        try {
            readStatesFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            String input = readLine("Enter state initials: ");
            println(states.get(input));
        }
    }

    /**
     * Read states from local file  and store in HashMap
     * @throws IOException
     */
    private void readStatesFromFile() throws IOException {
        FileReader fileReader = new FileReader("states.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            while (tokenizer.hasMoreTokens()) {
                states.put(tokenizer.nextToken(), tokenizer.nextToken());
            }
        }
        bufferedReader.close();
        fileReader.close();
    }


}

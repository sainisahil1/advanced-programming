package io.sahil.assignment3;

import acm.program.ConsoleProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * This class represents trains timetable problem
 *
 * @author Sahil Saini
 */
public class Trains extends ConsoleProgram {

    HashMap<String, ArrayList<String>> timetable;

    public static void main(String[] args) {
        Trains program = new Trains();
        program.start();
    }

    public void init() {
        setSize(500, 500);
        setFont("Courier-16");
        try {
            readTimetable();
            println(timetable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prompt user for stops
     */
    public void run() {
        ArrayList<String> userStops = new ArrayList<>();
        println("Departure stations: " + timetable.keySet());
        String departureStation = readLine("Enter departure station: ");
        userStops.add(departureStation);
        println("Next possible Stops: " + timetable.get(departureStation));
        while (true) {
            String newStop = readLine("Enter stop/Empty String to finish");
            if (!newStop.equals("")) {
                userStops.add(newStop);
                println("Next possible Stops: " + timetable.get(newStop));
            } else {
                break;
            }
        }
        println("Your route is: " + userStops);
    }

    /**
     * Load timetable from text file
     *
     * @throws IOException
     */
    private void readTimetable() throws IOException {
        timetable = new HashMap<>();
        FileReader fileReader = new FileReader("timetable.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            handleStringTokenization(line);
        }
        bufferedReader.close();
        fileReader.close();
    }

    /**
     * Store cities departure and arrival in HashMap
     *
     * @param line input line from file read
     */
    private void handleStringTokenization(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line, ">");
        String departure = tokenizer.nextToken().trim();
        String arrival = tokenizer.nextToken().trim();
        ArrayList<String> arrivalsList = timetable.get(departure);
        if (arrivalsList == null) {
            arrivalsList = new ArrayList<>();
        }
        arrivalsList.add(arrival);
        timetable.put(departure, arrivalsList);
    }
}

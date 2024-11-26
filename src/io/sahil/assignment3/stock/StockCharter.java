package io.sahil.assignment3.stock;

import acm.program.GraphicsProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * This class demonstrates a stock charter program
 *
 * @author Sahil Saini
 */
public class StockCharter extends GraphicsProgram {

    private JButton graphButton;
    private JLabel symbolLabel;
    private JComboBox<String> stocksDropdown;
    private JTextArea stocksArea;
    private JScrollPane stocksScrollPane;

    private ArrayList<String> dates;
    private HashMap<String, Stock> stocks;

    public static void main(String[] args) {
        StockCharter stockCharter = new StockCharter();
        stockCharter.start();
    }

    public void init() {
        setupUI();
        dates = new ArrayList<>();
        stocks = new HashMap<>();
    }

    /**
     * Main program loop
     */
    public void run() {
        try {
            readData();
            populateUI();
            addActionListeners();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Setup UI
     */
    private void setupUI(){
        setSize(400, 600);
        setupSouthUI();
        setupCenterUI();
    }

    /**
     * Setup Symbol label, dropdown and Graph Button
     */
    private void setupSouthUI() {
        symbolLabel = new JLabel("Symbol:");
        stocksDropdown = new JComboBox<>();
        graphButton = new JButton("Graph");
        add(symbolLabel, SOUTH);
        add(stocksDropdown, SOUTH);
        add(graphButton, SOUTH);
    }

    /**
     * Setup TextArea inside a ScrollPane
     */
    private void setupCenterUI() {
        stocksArea = new JTextArea();
        stocksArea.setEditable(false);
        stocksScrollPane = new JScrollPane(stocksArea);
        add(stocksScrollPane, CENTER);
    }

    /**
     * Populate the dropdown with stock symbols
     */
    private void populateUI(){
        Set<String> symbols = stocks.keySet();
        for (String symbol : symbols) {
            stocksDropdown.addItem(symbol);
        }
    }

    /**
     * Read the data from CSV file
     * @throws IOException
     */
    private void readData() throws IOException {
        FileReader fileReader = new FileReader("SP500_HistoricalStockDataMonthly.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String dates = bufferedReader.readLine();
        parseDates(dates);
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            parseStockData(line);
        }
        bufferedReader.close();
        fileReader.close();
    }

    /**
     * Format date raw date input "yyyyMMdd" to parsed "dd/MM/yyyy"
     * @param dateInput date input from csv file
     * @return formatted date
     */
    private String formatDate(String dateInput){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(dateInput, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(outputFormatter);
        return formattedDate;
    }

    /**
     * Parse the stock data from csv file. Fetch both symbols and prices
     * @param line
     */
    private void parseStockData(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line, ",");
        String symbol = tokenizer.nextToken();
        Stock stock = createStock(symbol, tokenizer);
        stocks.put(symbol, stock);
    }

    /**
     * Create a {@link Stock} object
     * @param symbol Symbol String
     * @param tokenizer StringTokenizer for raw data
     * @return {@link Stock} object
     */
    private Stock createStock(String symbol, StringTokenizer tokenizer) {
        ArrayList<PriceMap> priceList = new ArrayList<>();
        int dateIndex = 0;
        while (tokenizer.hasMoreTokens()) {
            extractStockPriceData(tokenizer, dateIndex, priceList);
            dateIndex++;
        }
        Stock stock = new Stock(symbol, priceList);
        return stock;
    }

    /**
     * Extract Stock Price Data
     * @param tokenizer
     * @param dateIndex
     * @param priceList
     */
    private void extractStockPriceData(StringTokenizer tokenizer, int dateIndex, ArrayList<PriceMap> priceList) {
        String priceString = tokenizer.nextToken();
        try {
            double price = Double.parseDouble(priceString);
            String date = dates.get(dateIndex);
            PriceMap priceMap = new PriceMap(date, price);
            priceList.add(priceMap);
        }catch (NumberFormatException e) {
            e.getMessage();
        }
    }

    private void parseDates(String datesString) {
        StringTokenizer tokenizer = new StringTokenizer(datesString, ",");
        while (tokenizer.hasMoreTokens()) {
            String date = tokenizer.nextToken();
            String formattedDate = formatDate(date);
            dates.add(formattedDate);
        }
    }

    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        if (action.equals("Graph")) {
            String symbol = (String) stocksDropdown.getSelectedItem();
            updatePriceDisplay(symbol);
        }
    }

    private void updatePriceDisplay(String symbol) {
        StringBuilder builder = new StringBuilder();
        Stock stock = stocks.get(symbol);
        ArrayList<PriceMap> priceMapArrayList = stock.getPrices();
        for (PriceMap priceMap : priceMapArrayList) {
            updateStringBuilder(priceMap, builder);
        }
        String displayText = builder.toString();
        stocksArea.setText(displayText);
    }

    private static void updateStringBuilder(PriceMap priceMap, StringBuilder builder) {
        String date = priceMap.getDate();
        double price = priceMap.getPrice();
        builder.append(date);
        builder.append(" - ");
        builder.append(price);
        builder.append("\n");
    }
}

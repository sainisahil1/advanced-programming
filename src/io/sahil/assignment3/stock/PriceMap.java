package io.sahil.assignment3.stock;

/**
 * This class is used store Stock Price with respect to date
 * @author Sahil Saini
 */
public class PriceMap {
    private String date;
    private double price;

    public PriceMap(String date, double price) {
        this.date = date;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }
}

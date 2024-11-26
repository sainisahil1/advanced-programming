package io.sahil.assignment3.stock;

import java.util.ArrayList;

/**
 * This class defines a Stock object
 *
 * @author Sahil Saini
 */
public class Stock {

    private final String symbol;
    private final ArrayList<PriceMap> prices;

    public Stock(String symbol, ArrayList<PriceMap> prices) {
        this.symbol = symbol;
        this.prices = prices;
    }

    public String getSymbol() {
        return symbol;
    }

    public ArrayList<PriceMap> getPrices() {
        return prices;
    }
}

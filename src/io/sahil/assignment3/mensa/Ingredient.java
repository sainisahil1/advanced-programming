package io.sahil.assignment3.mensa;

/**
 * This class defines an Ingredient object
 *
 * @author Sahil Saini
 */
public class Ingredient {

    private String name;
    private double price;
    private int calories;

    public Ingredient(String name, double price, int calories) {
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", calories=" + calories +
                '}';
    }
}

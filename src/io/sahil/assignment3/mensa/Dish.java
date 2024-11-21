package io.sahil.assignment3.mensa;

import java.util.ArrayList;

/**
 * This class defines a Dish object
 *
 * @author Sahil Saini
 */
public class Dish {

    private String name;
    private double price;
    private ArrayList<Ingredient> ingredients;

    public Dish(String name, double price, ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", ingredients=" + ingredients +
                '}';
    }
}

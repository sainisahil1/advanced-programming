package io.sahil.assignment3.mensa;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

import java.util.ArrayList;

/**
 * This class represents University Mensa
 *
 * @author Sahil Saini
 */
public class Mensa extends ConsoleProgram {

    private ArrayList<Dish> dishes;
    private ArrayList<Ingredient> ingredients;
    private RandomGenerator random = new RandomGenerator();

    public static void main(String[] args) {
        Mensa mensa = new Mensa();
        mensa.start();
    }

    public void init() {
        setSize(400, 600);
        setFont("Courier-14");
        addSampleIngredients();
        addSampleDishes();
        println("Welcome to the Library!");
        println("0: Create new Dish\n" +
                "1: Create new Ingredient\n" +
                "2: Display All Dish\n" +
                "3: Display All Ingredient\n" +
                "4: Delete a dish");
    }

    public void run() {
        while (true) {
            int choice = readInt("Your choice: ");
            switch (choice) {
                case 0:
                    createNewDish();
                    break;
                case 1:
                    createNewIngredient();
                    break;
                case 2:
                    displayAllDishes();
                    break;
                case 3:
                    displayAllIngredients();
                    break;
                case 4:
                    deleteARandomDish();
                    break;
            }
        }
    }

    private void deleteARandomDish() {
        int index = random.nextInt(dishes.size());
        dishes.remove(index);
    }

    private void displayAllIngredients() {
        println("All Ingredients: "+ingredients);
    }

    private void displayAllDishes() {
        println("Dishes: " + dishes);
    }

    private void createNewIngredient() {
        String name = readLine("Enter name of ingredient: ");
        double price = readDouble("Enter price of ingredient: ");
        int calories = readInt("Enter calories: ");
        Ingredient ingredient = new Ingredient(name, price, calories);
        ingredients.add(ingredient);
    }

    private void createNewDish() {
        String name = readLine("Enter the name of the dish: ");
        double price = readDouble("Enter the price of the dish: ");
        ArrayList<Ingredient> newDishIngredients = new ArrayList<>();
        while (true){
            int ingredientNumber = readInt("Enter the index of the ingredient from 0 to " + (ingredients.size() - 1) + ". Enter -1 if done.");
            if (ingredientNumber == -1) {
                break;
            }
            if (ingredientNumber >= ingredients.size()) {
                println("Enter a valid index");
            } else {
            Ingredient ingredient = ingredients.get(ingredientNumber);
            newDishIngredients.add(ingredient);
            }
        }
        Dish newDish = new Dish(name, price, newDishIngredients);
        dishes.add(newDish);
    }

    private void addSampleDishes() {
        dishes = new ArrayList<>();
        Dish chickenSalad = createChickenSalad();
        dishes.add(chickenSalad);
        Dish cheeseSandwich = createCheeseSandwich();
        dishes.add(cheeseSandwich);
    }

    private Dish createChickenSalad() {
        ArrayList<Ingredient> chickenSaladIngredients = new ArrayList<>();
        Ingredient tomato = ingredients.get(0);
        Ingredient lettuce = ingredients.get(2);
        Ingredient chicken = ingredients.get(3);
        chickenSaladIngredients.add(tomato);
        chickenSaladIngredients.add(lettuce);
        chickenSaladIngredients.add(chicken);
        Dish chickenSalad = new Dish("Chicken Salad", 5.0, chickenSaladIngredients);
        return chickenSalad;
    }

    private Dish createCheeseSandwich() {
        ArrayList<Ingredient> cheeseSandwichIngredients = new ArrayList<>();
        Ingredient tomato = ingredients.get(0);
        Ingredient cheese = ingredients.get(1);
        cheeseSandwichIngredients.add(tomato);
        cheeseSandwichIngredients.add(cheese);
        Dish cheeseSandwich = new Dish("Cheese Sandwich", 3.5, cheeseSandwichIngredients);
        return cheeseSandwich;
    }

    private void addSampleIngredients() {
        ingredients = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient("Tomato", 0.5, 20);
        Ingredient ingredient2 = new Ingredient("Cheese", 1.0, 80);
        Ingredient ingredient3 = new Ingredient("Lettuce", 0.3, 5);
        Ingredient ingredient4 = new Ingredient("Chicken", 2.5, 150);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);
        ingredients.add(ingredient4);
    }


}

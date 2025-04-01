package io.sahil.assignment4;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * This class represents a graphical representation of random generator
 *
 * @author Sahil Saini
 */
public class Randomness extends GraphicsProgram {

    private int SIZE = 400;

    public static void main(String[] args) {
        new Randomness().start();
    }

    @Override
    public void init() {
        setSize(SIZE, SIZE);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            int x = giveMeRandomNumber(SIZE);
            pause(giveMeRandomNumber(5));
            int y = giveMeRandomNumber(SIZE);
            setPixel(x, y, Color.RED);
            pause(giveMeRandomNumber(5));
        }
    }

    private void setPixel(int x, int y, Color color) {
        GRect rect = new GRect(x, y, 1, 1);
        rect.setFilled(true);
        rect.setColor(color);
        rect.setFillColor(color);
        add(rect);
    }

    private int giveMeRandomNumber(int m){
        int a = 7*7;
        long x = System.currentTimeMillis();
        return (int) ((a * x) % m);
    }

}

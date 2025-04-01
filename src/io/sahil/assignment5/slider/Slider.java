package io.sahil.assignment5.slider;

import acm.graphics.GRect;

import java.awt.*;

/**
 * @author Sahil Saini
 */
public class Slider extends GRect implements Runnable {

    private final int DELAY = 40;
    private final int STEP = 5;

    public Slider(int size, Color color) {
        super(size / 2, size);
        setFilled(true);
        setFillColor(color);
    }

    @Override
    public void run() {
        while (true) {
            pause(DELAY);
            move(STEP, 0);
        }
    }
}

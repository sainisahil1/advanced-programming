package io.sahil.assignment5.slider;

import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.event.MouseEvent;

/**
 * @author Sahil Saini
 */
public class SliderThreadingActivity extends GraphicsProgram {

    private final int SLIDER_SIZE = 80;

    private RandomGenerator rgen = new RandomGenerator();

    public static void main(String[] args) {
        new SliderThreadingActivity().start();
    }

    @Override
    public void init() {
        super.init();
        addMouseListeners();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Slider slider = new Slider(SLIDER_SIZE, rgen.nextColor());
        add(slider, 0, rgen.nextDouble(0, getHeight()));
        Thread sliderThread = new Thread(slider);
        sliderThread.start();
    }
}

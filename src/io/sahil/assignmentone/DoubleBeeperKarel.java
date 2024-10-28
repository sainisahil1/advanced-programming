package io.sahil.assignmentone;

import stanford.karel.Karel;

/**
 * @author Sahil Saini
 */
public class DoubleBeeperKarel extends Karel {

    public static void main(String[] args) {
        new DoubleBeeperKarel().start();
    }

    public void run() {
        move();
        while (beepersPresent()) {
            pickBeeper();
            move();
            putBeeper();
            putBeeper();
            turnAround();
            move();
            turnAround();
        }
    }

    private void turnAround() {
        turnLeft();
        turnLeft();
    }
}

package io.sahil.assignmentone;

import stanford.karel.SuperKarel;

/**
 * @author Sahil Saini
 */
public class PyramidKarel extends SuperKarel {

    public static void main(String[] args) {
        new PyramidKarel().start();
    }

    public void run() {
        putFirstLineOfBeepers();
        putSecondLineOfBeepers();
        while (beepersPresent()){
            putNthLineOfBeepers();
        }
    }

    private void putFirstLineOfBeepers() {
        while (frontIsClear()){
            putBeeper();
            move();
        }
        putBeeper();
    }

    private void putSecondLineOfBeepers() {
        turnLeft();
        move();
        turnLeft();
        move();
        putFirstLineOfBeepers();
        pickBeeper();
        turnAround();
        move();
    }

    private void putNthLineOfBeepers() {
        putOneBeeperAbove();
        removeExtraBeeper();
        moveToFront();
    }

    private void putOneBeeperAbove() {
        move();
        while (beepersPresent()) {
            turnLeft();
            move();
            putBeeper();
            turnAround();
            move();
            turnLeft();
            move();
        }
    }

    private void removeExtraBeeper(){
        turnAround();
        move();
        turnRight();
        move();
        if (beepersPresent()) {
            pickBeeper();
        }
    }

    private void moveToFront(){
        turnLeft();
        do {
            move();
        } while (beepersPresent());
        turnAround();
        move();
    }




}

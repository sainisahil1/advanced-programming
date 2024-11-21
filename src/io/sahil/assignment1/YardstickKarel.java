package io.sahil.assignment1;

import stanford.karel.SuperKarel;

/**
 * @author Sahil Saini
 */
public class YardstickKarel extends SuperKarel {

    public static void main(String[] args) {
        new YardstickKarel().start();
    }

    public void run() {
        putBeepersOnYardstick();
        moveToEnd();
        turnAround();
        while (frontIsClear()) {
            moveToNearestBeeper();
            putBeeperAtTheEnd();
        }
        moveToEndOfStick();
    }

    private void putBeepersOnYardstick() {
        move();
        while (rightIsBlocked()){
            putBeeper();
            move();
        }
    }

    private void moveToEnd(){
        while (frontIsClear()){
            move();
        }
    }

    private void moveToNearestBeeper(){
        do {
            move();
        }while (noBeepersPresent() && frontIsClear());
    }

    private void putBeeperAtTheEnd(){
        if (beepersPresent()){
            pickBeeper();
            turnAround();
            moveToEnd();
            putBeeper();
            turnAround();
        }
    }

    private void moveToEndOfStick(){
        turnAround();
        while(rightIsClear()){
            move();
        }
    }

}

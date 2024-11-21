package io.sahil.assignment1;

import stanford.karel.SuperKarel;

/**
 * @author Sahil Saini
 */
public class SteepleChaserKarel extends SuperKarel {

    public static void main(String[] args) {
        new SteepleChaserKarel().start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 8; i++) {
            if (frontIsClear()){
                move();
            } else{
                turnLeft();
                crawl();
                jump();
                crawl();
                turnLeft();
            }
        }
    }

    private void crawl(){
        while (rightIsBlocked() && frontIsClear()){
            move();
        }
    }

    private void jump(){
        turnRight();
        move();
        turnRight();
        move();
    }


}
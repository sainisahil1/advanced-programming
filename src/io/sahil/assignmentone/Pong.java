package io.sahil.assignmentone;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * This class demonstrates Pong game animation using ACM library.
 * Left paddle controls: 'q' for up, 'a' for down
 * Right paddle controls: 'p' for up, 'l' for down
 *
 * @author Sahil Saini
 */
public class Pong extends GraphicsProgram {
    private final int SCREEN_WIDTH = 600;
    private final int SCREEN_HEIGHT = 500;
    private final int PADDLE_WIDTH = 10;
    private final int PADDLE_HEIGHT = 70;
    private final int PADDLE_PADDING = 30;
    private final int BALL_SIZE = 15;
    private final int PADDLE_VY = 10;
    private boolean moving = false;
    private double vx = 7.5;
    private double vy = 1.5;
    private int Y_MARGIN_MULTIPLIER = 3;
    private int PADDLE_Y_OFFSET = 10;

    private GRect leftPaddle;
    private GRect rightPaddle;
    private GOval ball;
    private GLabel pressToStart;

    public static void main(String[] args) {
        new Pong().start();
    }

    public void run() {
        setup();
        gameLoop();
    }

    /**
     * Initial setup of the game
     */
    private void setup(){
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setBackground(Color.BLACK);
        setupLeftPaddle();
        setupRightPaddle();
        setupBall();
        setupStartLabel();
        addKeyListeners();
    }

    /**
     * Game loop that moves ball and detects collision
     */
    private void gameLoop() {
        while(true) {
            if (moving) {
                checkForCollision();
                ball.move(vx, vy);
            }
            pause(40);
        }
    }

    /**
     * Collision check of ball with paddle and walls
     */
    private void checkForCollision() {
        checkForCollisionWithWalls();
        checkForCollisionWithPaddle();
    }

    /**
     * Collision check with paddles
     * <p>
     * NOTE: Any collision from lateral sides of paddle will be registered.
     * Which traps the ball inside paddle due to multiple consecutive collisions.
     * This particular condition avoids collisions from the sides
     */
    private void checkForCollisionWithPaddle() {
        GObject object = getElementAt(ball.getX(), ball.getY());
        if (object != null) {
            if (
                    (object == leftPaddle
                    && ball.getY() >= leftPaddle.getY()
                    && ball.getY() <= leftPaddle.getY()+PADDLE_HEIGHT)
                    ||
                    (object == rightPaddle
                    && ball.getY() >= rightPaddle.getY()
                    && ball.getY() <= rightPaddle.getY()+PADDLE_HEIGHT)
            ){
                vx = -vx;
            }
        }
    }

    /**
     * Collision check with walls
     * <p>
     * Y_MARGIN_MULTIPLIER avoids hidden Y-axis area as collision condition
     */
    private void checkForCollisionWithWalls() {
        double currentX = ball.getX();
        double currentY = ball.getY();
        if(currentX <= 0 || currentX >= SCREEN_WIDTH-ball.getWidth()) {
            toggleMoving();
            resetGame();
        }
        if(currentY <= 0 || currentY >= SCREEN_HEIGHT-(Y_MARGIN_MULTIPLIER *ball.getHeight())) {
            vy = -vy;
        }
    }

    /**
     * Left paddle initial setup
     */
    private void setupLeftPaddle(){
        leftPaddle = new GRect(PADDLE_PADDING, (double) (SCREEN_HEIGHT /2)-PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
        leftPaddle.setFilled(true);
        leftPaddle.setFillColor(Color.WHITE);
        add(leftPaddle);
    }

    /**
     * Right paddle initial setup
     */
    private void setupRightPaddle(){
        rightPaddle = new GRect(SCREEN_WIDTH-PADDLE_PADDING-PADDLE_WIDTH, (double) (SCREEN_HEIGHT /2)-PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
        rightPaddle.setFilled(true);
        rightPaddle.setFillColor(Color.WHITE);
        add(rightPaddle);
    }

    /**
     * Ball initial setup
     */
    private void setupBall(){
        ball = new GOval((double) SCREEN_WIDTH /2, (double) SCREEN_HEIGHT /2, BALL_SIZE, BALL_SIZE);
        ball.setFilled(true);
        ball.setFillColor(Color.WHITE);
        add(ball);
    }

    /**
     * Reusable setup for "Start Label"
     */
    private void setupStartLabel(){
        pressToStart = new GLabel("Press \"Space\" to start");
        pressToStart.setColor(Color.WHITE);
        add(pressToStart, (double) SCREEN_WIDTH /2 - pressToStart.getWidth()/2, 100);

    }

    /**
     * toggle main while loop in game loop
     */
    private void toggleMoving() {
        moving = !moving;
        if (moving) {
            removeStartLabel();
        }
    }

    /**
     * Remove start label
     */
    private void removeStartLabel(){
        remove(pressToStart);
    }

    /**
     * Resets the game when it is over
     */
    private void resetGame(){
        ball.setLocation((double) SCREEN_WIDTH /2, (double) SCREEN_HEIGHT /2);
        leftPaddle.setLocation(PADDLE_PADDING, (double) (SCREEN_HEIGHT /2)-PADDLE_Y_OFFSET);
        rightPaddle.setLocation(SCREEN_WIDTH-PADDLE_PADDING-PADDLE_WIDTH, (double) (SCREEN_HEIGHT /2)-PADDLE_Y_OFFSET);
        setupStartLabel();
    }

    public void keyPressed(KeyEvent keyEvent) {
        char c = keyEvent.getKeyChar();
        switch (c){
            case 'q':
                if (moving) {
                    leftPaddle.move(0, -PADDLE_VY);
                }
                break;
            case 'a':
                if (moving) {
                    leftPaddle.move(0, PADDLE_VY);
                }
                break;
            case 'p':
                if (moving) {
                    rightPaddle.move(0, -PADDLE_VY);
                }
                break;
            case 'l':
                if (moving) {
                    rightPaddle.move(0, PADDLE_VY);
                }
                break;
            case ' ':
                if (!moving){
                    toggleMoving();
                }
                break;
        }
    }
}

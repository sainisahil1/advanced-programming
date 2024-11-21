package io.sahil.assignment1;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * This class demonstrates the Brick Breaker animation using the ACM library
 * Controls: 'a' to move paddle to left and 'd' to move right
 *
 * @author Sahil Saini
 */
public class BrickBreaker extends GraphicsProgram {

    private final int SCREEN_WIDTH = 600;
    private final int SCREEN_HEIGHT = 700;
    private final int PADDLE_WIDTH = 100;
    private final int PADDLE_HEIGHT = 15;
    private final int BRICK_HEIGHT = 20;
    private final int BRICK_WIDTH = 50;
    private final int PADDLE_Y_MARGIN_MULTIPLIER = 4;
    private final int BALL_SIZE = 20;
    private double BALL_VX = 6;
    private double BALL_VY = 2;
    private final double PADDLE_VX = 10;
    private boolean moving = false;
    private final int START_LABEL_OFFSET = 100;

    private GOval ball;
    private GRect paddle;
    private GLabel pressToStart;
    private ArrayList<GRect> bricks = new ArrayList<>();

    public static void main(String[] args) {
        new BrickBreaker().start();
    }

    public void run() {
        setup();
        gameLoop();
    }

    /**
     * Main game loop
     */
    private void gameLoop() {
        while(true) {
            if (moving) {
                checkForCollision();
                ball.move(BALL_VX, BALL_VY);
            }
            pause(40);
        }
    }

    /**
     * Initiate collision check
     */
    private void checkForCollision() {
        checkForCollisionWithWalls();
        checkForCollisionWithBrickOrPaddle();
    }

    /**
     * Collision check with brick or paddle
     *
     * NOTE: Any collision from lateral sides of paddle will be registered.
     * Which traps the ball inside paddle due to multiple consecutive collisions.
     * This particular condition avoids collisions from the sides
     */
    private void checkForCollisionWithBrickOrPaddle(){
        GObject object = getElementAt(ball.getX(), ball.getY());
        if (object != null) {
            if (object != paddle){
                remove(object);
                BALL_VY = -BALL_VY;
            } else if (
                    ball.getX() >= paddle.getX()
                    && ball.getX() <= paddle.getX() + PADDLE_WIDTH
            ) {
                BALL_VY = -BALL_VY;
            }
        }
    }

    /**
     * Collision check with the walls
     */
    private void checkForCollisionWithWalls() {
        double ballCurrentX = ball.getX();
        double ballCurrentY = ball.getY();
        if (ballCurrentY >= paddle.getY() + PADDLE_HEIGHT) {
            resetGame();
        }
        if (ballCurrentY <= 0){
            BALL_VY = -BALL_VY;
        }
        if (ballCurrentX >= SCREEN_WIDTH-BALL_SIZE || ballCurrentX <= 0){
            BALL_VX = -BALL_VX;
        }
    }

    /**
     * Reset the main game loop
     */
    private void resetGame(){
        toggleMoving();
        remakeBricks();
        ball.setLocation((double) SCREEN_WIDTH /2, (double) SCREEN_HEIGHT /2);
        paddle.setLocation((double) (SCREEN_WIDTH - PADDLE_WIDTH) /2, SCREEN_HEIGHT - (PADDLE_Y_MARGIN_MULTIPLIER * PADDLE_HEIGHT));
        setupStartLabel();
    }

    /**
     * Delete the bricks and create new bricks
     */
    private void remakeBricks() {
        for (GRect rect : bricks) {
            remove(rect);
        }
        bricks.clear();
        setupBricks();
    }

    /**
     * Initial game setup
     */
    private void setup(){
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setBackground(Color.WHITE);
        setupPaddle();
        setupBall();
        setupBricks();
        setupStartLabel();
        addKeyListeners();
    }

    /**
     * Setup bricks by printing bricks in a loop
     */
    private void setupBricks(){
        RandomGenerator generator = new RandomGenerator();
        int rowY = 100;
        for (int i = 0; i < 5; i++) {
            int columnX = 50;
            for (int j = 0; j < 10; j++) {
                GRect brick = new GRect(columnX, rowY, BRICK_WIDTH, BRICK_HEIGHT);
                brick.setFilled(true);
                brick.setFillColor(generator.nextColor());
                brick.setColor(Color.BLACK);
                add(brick);
                bricks.add(brick);
                columnX += BRICK_WIDTH;
            }
            rowY += BRICK_HEIGHT;
        }
    }

    /**
     * Paddle setup
     * PADDLE_Y_MARGIN_MULTIPLIER avoids paddle to be hidden in hidden y-axis area
     */
    private void setupPaddle() {
        paddle = new GRect((double) (SCREEN_WIDTH - PADDLE_WIDTH) /2, SCREEN_HEIGHT - (PADDLE_Y_MARGIN_MULTIPLIER * PADDLE_HEIGHT), PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        paddle.setFillColor(Color.BLACK);
        add(paddle);
    }

    /**
     * setup game ball
     */
    private void setupBall(){
        ball = new GOval((double) SCREEN_WIDTH /2, (double) SCREEN_HEIGHT /2, BALL_SIZE, BALL_SIZE);
        ball.setFilled(true);
        ball.setFillColor(Color.BLACK);
        add(ball);
    }

    /**
     * Start label for user info
     */
    private void setupStartLabel(){
        pressToStart = new GLabel("Press \"Space\" to start");
        pressToStart.setColor(Color.BLACK);
        add(pressToStart, (double) SCREEN_WIDTH /2 - pressToStart.getWidth()/2,(double) SCREEN_HEIGHT /2 + START_LABEL_OFFSET);
    }

    public void keyPressed(KeyEvent keyEvent) {
        char c = keyEvent.getKeyChar();
        switch (c){
            case 'a':
                if (moving) {
                    paddle.move(-PADDLE_VX, 0);
                }
                break;
            case 'd':
                if (moving) {
                    paddle.move(PADDLE_VX, 0);
                }
                break;
            case ' ':
                if (!moving){
                    toggleMoving();
                }
                break;
        }
    }

    /**
     * Set moving state of the ball
     */
    private void toggleMoving() {
        moving = !moving;
        if (moving) {
            removeStartLabel();
        }
    }

    /**
     * Removes the start label when game is running
     */
    private void removeStartLabel(){
        remove(pressToStart);
    }

}

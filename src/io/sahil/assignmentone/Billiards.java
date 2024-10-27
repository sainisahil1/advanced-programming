package io.sahil.assignmentone;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;

/**
 * This class demonstrates Billiards game animation using ACM library.
 * Mouse click direction determines ball direction
 * Distance of mouse click from ball determines it's speed
 * Click Again to pause the ball
 *
 * 
 * @author Sahil Saini
 */
public class Billiards extends GraphicsProgram{
	
	private final int SCREEN_WIDTH = 400;
	private final int SCREEN_HEIGHT = 500;
	private final int BALL_SIZE = 20;
	private double vx = 0;
	private double vy = 0;
	private boolean moving = false;
	private int GRID_SIZE = 20;
	private int Y_MARGIN_MULTIPLIER = 2;

	private GOval ball;
	private GLabel ballNumber;

	/**
	* Using main method since the Project is build in IntelliJ Idea
	* */
	public static void main(String[] args) {
		new Billiards().start();
	}

	public void run() {
		setup();
		gameLoop();
	}

	/**
	 * Main loop running the animation
	 */
	private void gameLoop() {
		while(true) {
			if (moving) {
				checkForCollision();
				moveBall();
			}
			pause(40);
		}
	}

	/**
	 * Initial setup of animation and mouse listeners
	 */
	private void setup() {
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setBackground(Color.GREEN);
		ballSetup();
		labelSetup();
		addMouseListeners();
	}

	/**
	 * @param mouseEvent Default override parameter from ACM. Provides mouse click event.
	 */
	public void mouseClicked(MouseEvent mouseEvent) {
		if (!moving) {
			checkMouseClickDirection(mouseEvent);
		}
		toggleMoving();
	}

	/**
	 * Move ball and ball number together
	 */
	private void moveBall() {
		ball.move(vx, vy);
		ballNumber.move(vx, vy);
	}

	/**
	 * Checking for collision at the edges
	 * Added some margin to prevent ball from exiting frame in +y direction
	 */
	private void checkForCollision() {
		double currentX = ball.getX();
		double currentY = ball.getY();
		if(currentX < 0 || currentX >= SCREEN_WIDTH-ball.getWidth()) {
			vx = -vx;
		}
		if(currentY < 0 || currentY >= SCREEN_HEIGHT-(Y_MARGIN_MULTIPLIER *ball.getHeight())) {
			vy = -vy;
		}
	}

	/**
	 * Toggle move animation
	 */
	private void toggleMoving() {
		moving = !moving;
	}

	/**
	 * Get direction of ball by normalizing the vector
	 */
	private void checkMouseClickDirection(MouseEvent mouseEvent) {
		double deltaX = mouseEvent.getX()-ball.getX();
		double deltaY = mouseEvent.getY()-ball.getY();
		double length = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
		vx = deltaX/length;
		vy = deltaY/length;
		addSpeedToBall(deltaX, deltaY);
	}

	/**
	 * Add speed to the ball using a multiplier based on distance of ball from the mouse click
	 * @param deltaX delta vector in x direction
	 * @param deltaY delta vector in y direction
	 */
	private void addSpeedToBall(double deltaX, double deltaY) {
		double multiplierX = Math.abs(deltaX/ GRID_SIZE);
		double multiplierY = Math.abs(deltaY/ GRID_SIZE);
		vx = vx * multiplierX;
		vy = vy * multiplierY;
	}

	/**
	 * Ball setup
	 */
	private void ballSetup() {
		ball = new GOval(BALL_SIZE, BALL_SIZE);
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
		add(ball, SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
	}

	/**
	 * 8 Ball Label setup
	 */
	private void labelSetup() {
		ballNumber = new GLabel("8");
		ballNumber.setColor(Color.WHITE);
		ballNumber.setFont("Arial-Bold-20");
		add(ballNumber, getLabelPositionX(), getLabelPositionY());
	}

	/**
	 * Center the label in ball on x-axis
	 * @return Label x-position
	 */
	private double getLabelPositionX() {
        return ball.getX() + (ball.getWidth()-ballNumber.getWidth())/2;
	}

	/**
	 * Center the label in ball on y-axis
	 * Multiplier manually added based on static size
	 * @return Label y-position
	 */
	private double getLabelPositionY() {
        return ball.getY() + (0.9 * ball.getHeight());
	}

}

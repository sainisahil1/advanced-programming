package io.sahil.assignment2;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.program.Program;
import acm.util.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * This class contains all the assignment 2 question with the ability to choose the program to run.
 * Initial input requires int 1, 2, 3 and so on.
 * <p>
 * 1: Random Text problem.
 * Create Random Sentences with word length from 3 to 15.
 * <p>
 * 2: Abjad.
 * Remove vowels from a given text.
 * <p>
 * 3: Pig Latin.
 * Convert a given English String to Pig Latin.
 * <p>
 * 4: Yoda Talk.
 * Convert a given English String to Yoda Talk.
 *
 * @author Sahil Saini
 */
public class AssignmentTwo extends Program {

    public static void main(String[] args) {
        new AssignmentTwo().start();
    }

    /**
     * main run loop for the program.
     * Choose the problem with int input.
     */
    public void run() {
        println("Choose program:" +
                "\nRandom Text:    1" +
                "\nAbjad:          2" +
                "\nPig Latin:      3" +
                "\nYoda Talk:      4" +
                "\nAngry Cannon:   5" +
                "\nAsteroids:      6" +
                "\nCalculator:     7" +
                "\nCountdown:      8" +
                "\nDrawing Editor: 9" +
                "\nEditor:         10");
        String input = readLine();
        try {
            int number = Integer.parseInt(input);
            if (number < 1 || number > 10) {
                println("Enter valid number");
            } else {
                selectProgram(number);
            }
        } catch (Exception e) {
            println("Enter valid number");
        }
    }

    /**
     * Select problem based on number input.
     *
     * @param number input for problem selection of type int.
     */
    private void selectProgram(int number) {
        switch (number) {
            case 1:
                println(createRandomSentence());
                break;
            case 2:
                String text = readLine("Enter text: ");
                println(removeVowels(text));
                break;
            case 3:
                String text2 = readLine("Enter a english text: ");
                println(translateToPigLatin(text2));
                break;
            case 4:
                String text3 = readLine("Enter english text: ");
                println(translateToYodish(text3));
                break;
            case 5:
                new AngryCannon().start();
                break;
            case 6:
                new Asteroids().start();
                break;
            case 7:
                new Calculator().start();
                break;
            case 8:
                new CountDown().start();
                break;
            case 9:
                new DrawingEditor().start();
                break;
            case 10:
                new Editor().start();
        }
    }

    //Random Text

    /**
     * Create random sentences of words between 3 and 15, inclusive.
     * Number of words decided by {@link RandomGenerator}.
     *
     * @return Random sentence of type {@link String}
     */
    private String createRandomSentence() {
        StringBuilder builder = new StringBuilder();
        RandomGenerator rg = new RandomGenerator();
        int numberOfWords = rg.nextInt(3, 15);
        for (int i = 0; i < numberOfWords; i++) {
            builder.append(createRandomWord()).append(" ");
        }
        builder.append(".");
        return builder.toString();
    }

    /**
     * Create random word of length between 3 and 8, inclusive.
     * Length of the word decided by {@link RandomGenerator}.
     *
     * @return Random word of type {@link String}
     */
    private String createRandomWord() {
        StringBuilder builder = new StringBuilder();
        RandomGenerator rg = new RandomGenerator();
        int numberOfCharacters = rg.nextInt(3, 8);
        for (int i = 0; i < numberOfCharacters; i++) {
            builder.append((char) rg.nextInt(97, 122));
        }
        return builder.toString();
    }

    //Abjad

    /**
     * A method that removes vowels from the input string.
     *
     * @param str input of type {@link String}
     * @return String with removed vowels
     */
    private String removeVowels(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (!isVowel(str.charAt(i))) {
                stringBuilder.append(str.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Checks whether the input char is vowel.
     *
     * @param ch input vowel.
     * @return true if input is vowel.
     */
    private boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o';
    }

    //Pig Latin

    /**
     * Translates a given string to Pig Latin.
     *
     * @param s input of type {@link String}
     * @return Pig Latin translation of type {@link String}
     */
    private String translateToPigLatin(String s) {
        StringTokenizer tokenizer = new StringTokenizer(s);
        StringBuilder builder = new StringBuilder();
        while (tokenizer.hasMoreTokens()) {
            builder.append(transformWord(tokenizer.nextToken())).append(" ");
        }
        builder.replace(builder.length() - 1, builder.length(), ""); //OBOB
        return builder.toString().trim();
    }

    /**
     * Transform input word to Pig Latin word.
     *
     * @param word input of type {@link String}
     * @return Pig Latin translated word of type {@link String}
     */
    private String transformWord(String word) {
        String suffix = "ay";
        StringBuilder builder = new StringBuilder();
        if (!isVowel(word.charAt(0))) {
            builder.append(word.substring(1)).append(word.charAt(0));
        } else {
            builder.append(word);
        }
        builder.append(suffix);
        return builder.toString();
    }

    //Yoda Talk

    /**
     * Translates an input String to Yodish
     *
     * @param text input of type {@link String}
     * @return Yodish translated word of type {@link String}
     */
    private String translateToYodish(String text) {
        StringTokenizer tokenizer = new StringTokenizer(text);
        if (tokenizer.countTokens() != 3) {
            throw new IllegalArgumentException("Invalid tet for Yodish translation");
        }
        StringBuilder builder = new StringBuilder();
        ArrayList<String> tokens = extractTokens(tokenizer);
        builder.append(tokens.get(2)).append(", ").append(tokens.get(0)).append(" ").append(tokens.get(1));
        return builder.toString();
    }

    /**
     * Extract tokens from input {@link StringTokenizer}
     *
     * @param tokenizer input tokenizer of type {@link StringTokenizer}
     * @return {@link ArrayList} of {@link String} type tokens
     */
    private static ArrayList<String> extractTokens(StringTokenizer tokenizer) {
        ArrayList<String> tokens = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
    }


}

/**
 * This class describes the Angry Cannon game
 *
 * @author Sahil Saini
 */
class AngryCannon extends GraphicsProgram implements AngryCannonConstants {

    private Cannon cannon;
    private Bullet bullet;

    public static void main(String[] args) {
        new AngryCannon().start();
    }

    /**
     * Initialize UI.<br>
     * Set the APP SIZE.<br>
     * Create cannon.<br>
     * Create Blocks.<br>
     */
    public void init() {
        setSize((int) APP_WIDTH, (int) APP_HEIGHT);
        createCannon();
        createBlocks();
        addKeyListeners();
    }

    /**
     * Create Connon object and add to frame
     */
    private void createCannon() {
        cannon = new Cannon(100, APP_WIDTH / 2);
        add(cannon);
    }

    /**
     * Create blocks and add to frame
     */
    private void createBlocks() {
        int startX = getWidth() - 600;
        int startY = 200;
        int numberOfRows = 10;
        int numberOfCols = 22;
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfCols; j++) {
                Block block = new Block(startX + (j * BLOCK_SIZE), startY + (i * BLOCK_SIZE));
                add(block);
            }
        }
    }

    /**
     * If bullet is null, create new and add to frame
     */
    private void createBullet() {
        if (bullet != null) {
            return;
        }
        bullet = new Bullet(cannon.getX(), cannon.getY());
        bullet.setVelocityVectors(cannon.getBearing());
        add(bullet);
    }

    /**
     * Main game loop with 25Hz frequency
     */
    public void run() {
        while (true) {
            try {
                if (bullet != null) {
                    moveBullet();
                    checkForCollisions();
                    addDropToBullet();
                }
                pause(40);
            } catch (Exception e) {

            }
        }
    }

    /**
     * Move the bullet
     */
    private void moveBullet() {
        bullet.move();
    }

    /**
     * Add gravity drop to bullet
     */
    private void addDropToBullet() {
        bullet.addDropToBullet();
    }

    /**
     * Check for bullet collisions
     */
    private void checkForCollisions() {
        checkCollisionOfBulletWithBlock();
        checkCollisionOfBulletWithWall();
    }

    /**
     * Check for collision of Bullet with block
     */
    private void checkCollisionOfBulletWithBlock() {
        double x = bullet.getX();
        double y = bullet.getY();
        GObject object = getElementAt(x, y);
        if (object != null && object != cannon && object != bullet) {
            remove(object);
            remove(bullet);
            bullet = null;
        }
    }

    /**
     * Check for collision of bullet with wall
     */
    private void checkCollisionOfBulletWithWall() {
        double x = bullet.getX();
        double y = bullet.getY();
        if (x < -BULLET_SIZE || x > getWidth() || y < -BULLET_SIZE || y > getHeight()) {
            remove(bullet);
            bullet = null;
        }
    }

    /**
     * Rotates the cannon
     *
     * @param angle input angle in degrees
     */
    private void rotateCannon(double angle) {
        cannon.rotate(angle);
    }

    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                rotateCannon(CANNON_ROTATION);
                break;
            case KeyEvent.VK_RIGHT:
                rotateCannon(-CANNON_ROTATION);
                break;
            case KeyEvent.VK_SPACE:
                createBullet();
        }
    }
}


/**
 * Interface to contain all constants
 *
 * @author Sahil Saini
 */
interface AngryCannonConstants {
    double CANNON_SIZE = 60;
    double CANNON_ROTATION = 5;

    double APP_WIDTH = 1000;
    double APP_HEIGHT = 600;

    double BULLET_SIZE = 18;
    double BULLET_DROP = 0.5;
    double BULLET_SPEED = 20;

    double BLOCK_SIZE = 25;
}

/**
 * This class defines the cannon object
 *
 * @author Sahil Saini
 */
class Cannon extends GPolygon implements AngryCannonConstants {

    private double bearing;

    public Cannon(double x, double y) {
        super(x, y);
        this.addVertex(-CANNON_SIZE / 4, -CANNON_SIZE / 2);
        this.addVertex(CANNON_SIZE / 4, -CANNON_SIZE / 2);
        this.addVertex(CANNON_SIZE / 4, 0);
        this.addVertex(CANNON_SIZE / 2, 0);
        this.addVertex(CANNON_SIZE / 2, CANNON_SIZE / 4);
        this.addVertex(-CANNON_SIZE / 2, CANNON_SIZE / 4);
        this.addVertex(-CANNON_SIZE / 2, 0);
        this.addVertex(-CANNON_SIZE / 4, 0);
        this.setFilled(true);
        this.setFillColor(Color.BLACK);
    }

    /**
     * Get the bearing angle of cannon
     *
     * @return angle in degrees
     */
    public double getBearing() {
        return bearing;
    }


    /**
     * Rotate the cannon
     *
     * @param angle angle in degrees
     */
    public void rotate(double angle) {
        super.rotate(angle);
        bearing += angle;
    }
}


/**
 * This class defines the Block object
 *
 * @author Sahil Saini
 */
class Block extends GRect implements AngryCannonConstants {

    public Block(double x, double y) {
        super(x, y, BLOCK_SIZE, BLOCK_SIZE);
        this.setFilled(true);
        this.setFillColor(Color.BLUE);
        this.setColor(Color.BLACK);
    }

}

/**
 * This class defines the bullet object
 *
 * @author Sahil Saini
 */
class Bullet extends GOval implements AngryCannonConstants {

    private double vx = 0;
    private double vy = 0;
    private double bearing = 0;

    public Bullet(double x, double y) {
        super(x, y, BULLET_SIZE, BULLET_SIZE);
        this.setFilled(true);
        this.setFillColor(Color.RED);
        this.setColor(Color.BLACK);
    }

    /**
     * Set the velocity vectors of bullet
     *
     * @param angle input angle in degrees
     */
    public void setVelocityVectors(double angle) {
        bearing = angle;
        vx = -Math.sin(Math.toRadians(angle)) * BULLET_SPEED;
        vy = -Math.cos(Math.toRadians(angle)) * BULLET_SPEED;
    }

    /**
     * Add gravity drop to the bullet based on bearing angle
     */
    public void addDropToBullet() {
        vy += Math.cos(Math.toRadians(bearing)) * BULLET_DROP;
    }

    /**
     * Move the bullet
     */
    public void move() {
        super.move(vx, vy);
    }

}

/**
 * This class demonstrates the implementation of Asteroids game.
 *
 * @author Sahil Saini
 */
class Asteroids extends GraphicsProgram implements AsteroidConstants {

    private GSpaceship spaceship;
    private GBullet bullet;
    private RandomGenerator randomGenerator = new RandomGenerator();
    private GAsteroid[] asteroidArray = new GAsteroid[NUMBER_OF_ASTEROIDS];

    public static void main(String[] args) {
        new Asteroids().start();
    }

    /**
     * Initialize UI.<br>
     * Setup App window size.<br>
     */
    public void init() {
        setSize(APP_SIZE, APP_SIZE);
        createSpaceShip();
        createAsteroids();
        addKeyListeners();
    }

    /**
     * Main game loop
     */
    public void run() {
        waitForClick();
        while (spaceship != null) {
            moveSpaceShip();
            moveAsteroid();
            moveBullet();
            checkForCollisions();
            pause(40);
        }
    }

    /**
     * Checks for all collisions
     */
    private void checkForCollisions() {
        checkCollisionAsteroidWithWall();
        checkCollisionBulletWithWall();
        checkForCollisionSpaceshipWithWall();
        checkCollisionAsteroidWithSpaceship();
        checkCollisionAsteroidWithBullet();
    }

    /**
     * Check collision of bullet with asteroid when bullet is non-null.<br>
     * If collision occurs, remove asteroid and bullet from the frame, and set bullet as null.
     */
    private void checkCollisionAsteroidWithBullet() {
        if (bullet == null) {
            return;
        }
        double x = bullet.getX();
        double y = bullet.getY();
        GObject object = getElementAt(x, y);
        if (object != null && object != spaceship && object != bullet) {
            remove(object);
            remove(bullet);
            bullet = null;
        }
    }

    /**
     * Check collision of asteroid with spaceship.<br>
     * If collision occurs, remove asteroid and spaceship from frame and set spaceship as null.
     */
    private void checkCollisionAsteroidWithSpaceship() {
        double x = spaceship.getX();
        double y = spaceship.getY();
        GObject object = getElementAt(x, y);
        if (object != null && object != spaceship && object != bullet) {
            remove(object);
            remove(spaceship);
            spaceship = null;
        }
    }

    /**
     * Check collision of spaceship with wall of frame.<br>
     * If collision occurs, move the spaceship to opposite side of frame.
     */
    private void checkForCollisionSpaceshipWithWall() {
        double x = spaceship.getX();
        double y = spaceship.getY();
        if (x < -SPACESHIP_SIZE) {
            spaceship.setLocation(getWidth(), y);
        } else if (x > getWidth()) {
            spaceship.setLocation(0, y);
        }
        if (y < -SPACESHIP_SIZE) {
            spaceship.setLocation(x, getHeight());
        } else if (y > getHeight()) {
            spaceship.setLocation(x, 0);
        }
    }

    /**
     * Check collision of bullet with wall of frame.<br>
     * If collision occurs, remove the bullet from frame and set bullet as null.
     */
    private void checkCollisionBulletWithWall() {
        if (bullet == null) {
            return;
        }
        double x = bullet.getX();
        double y = bullet.getY();
        if (x < -BULLET_SIZE || x > getWidth() || y < -BULLET_SIZE || y > getHeight()) {
            remove(bullet);
            bullet = null;
        }
    }

    /**
     * Check collision of asteroids with wall of frame.<br>
     * If collision occurs, move the asteroids to opposite side of frame.
     */
    private void checkCollisionAsteroidWithWall() {
        for (int i = 0; i < NUMBER_OF_ASTEROIDS; i++) {
            double x = asteroidArray[i].getX();
            double y = asteroidArray[i].getY();
            if (x < -ASTEROID_SIZE) {
                asteroidArray[i].setLocation(getWidth(), y);
            } else if (x > getWidth()) {
                asteroidArray[i].setLocation(0, y);
            }
            if (y < -ASTEROID_SIZE) {
                asteroidArray[i].setLocation(x, getHeight());
            } else if (y > getHeight()) {
                asteroidArray[i].setLocation(x, 0);
            }
        }
    }

    /**
     * Move all asteroids
     */
    private void moveAsteroid() {
        for (GAsteroid asteroid : asteroidArray) {
            asteroid.move();
        }
    }

    /**
     * Move the spaceship
     */
    private void moveSpaceShip() {
        spaceship.move();
    }

    /**
     * Move the bullet
     */
    private void moveBullet() {
        if (bullet != null) {
            bullet.move();
        }
    }

    /**
     * Create the spaceship
     */
    private void createSpaceShip() {
        spaceship = new GSpaceship((double) APP_SIZE / 2, (double) APP_SIZE / 2);
        add(spaceship);
    }

    /**
     * Create the asteroids using a loop
     */
    private void createAsteroids() {
        for (int i = 0; i < NUMBER_OF_ASTEROIDS; i++) {
            asteroidArray[i] = new GAsteroid(randomGenerator.nextInt(getWidth()), randomGenerator.nextInt(getHeight()));
            asteroidArray[i].setVelocityVectors(randomGenerator.nextInt(-ASTEROID_SPEED, ASTEROID_SPEED),
                    randomGenerator.nextInt(-ASTEROID_SPEED, ASTEROID_SPEED));
            add(asteroidArray[i]);
        }
    }

    /**
     * Fire the bullet.<br>
     * Create a new bullet object and set the velocity vectors
     */
    private void fireBullet() {
        if (bullet != null) {
            return;
        }
        bullet = new GBullet(spaceship.getX(), spaceship.getY());
        bullet.setVelocityVectors(spaceship.getBearing());
        add(bullet);
    }

    /**
     * Observer method listening to key events
     *
     * @param keyEvent
     */
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                spaceship.setVelocityVectors();
                break;
            case KeyEvent.VK_LEFT:
                spaceship.rotate(10);
                break;
            case KeyEvent.VK_RIGHT:
                spaceship.rotate(-10);
                break;
            case KeyEvent.VK_SPACE:
                fireBullet();
                break;
        }
    }
}

/**
 * This interface contains all constants for the app
 *
 * @author Sahil Saini
 */
interface AsteroidConstants {
    int APP_SIZE = 800;

    int BULLET_SIZE = 10;
    double BULLET_SPEED = 15;

    int NUMBER_OF_ASTEROIDS = 10;
    int ASTEROID_SIZE = 30;
    int ASTEROID_SPEED = 2;

    int SPACESHIP_SIZE = 60;
    double SPACESHIP_SPEED = 1;
}

/**
 * This class defines the Bullet object
 *
 * @author Sahil Saini
 */
class GBullet extends GOval implements AsteroidConstants {


    private double vx = 0;
    private double vy = 0;

    /**
     * Constructor to create a new bullet
     *
     * @param x initial x-position of bullet
     * @param y initial y-position of bullet
     */
    public GBullet(double x, double y) {
        super(x, y, BULLET_SIZE, BULLET_SIZE);
        this.setFilled(true);
        this.setFillColor(Color.RED);
    }

    /**
     * Set velocity vectors of the bullet
     *
     * @param angle angle in degrees
     */
    public void setVelocityVectors(double angle) {
        vx = -Math.sin(Math.toRadians(angle)) * BULLET_SPEED;
        vy = -Math.cos(Math.toRadians(angle)) * BULLET_SPEED;
    }

    /**
     * Move the bullet
     */
    public void move() {
        this.move(vx, vy);
    }

}

/**
 * This class defines the Spaceship object
 *
 * @author Sahil Saini
 */
class GSpaceship extends GPolygon implements AsteroidConstants {

    private double vx = 0;
    private double vy = 0;
    private double bearing = 0;

    /**
     * Constructor to create new spaceship
     *
     * @param x initial x-position of spaceship
     * @param y initial y-position of spaceship
     */
    public GSpaceship(double x, double y) {
        super(x, y);
        this.addVertex(0, (double) -SPACESHIP_SIZE / 2);
        this.addVertex(SPACESHIP_SIZE * 0.3, (double) SPACESHIP_SIZE / 2);
        this.addVertex(0, (double) SPACESHIP_SIZE / 3);
        this.addVertex(-SPACESHIP_SIZE * 0.3, (double) SPACESHIP_SIZE / 2);
        this.setFilled(true);
        this.setFillColor(Color.BLUE);
        this.setColor(Color.BLACK);
        this.sendToFront();
    }

    /**
     * Get bearing of the spaceship
     *
     * @return bearing in degrees
     */
    public double getBearing() {
        return bearing;
    }

    /**
     * Rotate the spaceship
     *
     * @param angle angle in degrees
     */
    public void rotate(double angle) {
        super.rotate(angle);
        bearing += angle;
    }

    /**
     * Set the velocity vectors of spaceship based on bearing
     */
    public void setVelocityVectors() {
        vx += -Math.sin(Math.toRadians(bearing)) * SPACESHIP_SPEED;
        vy += -Math.cos(Math.toRadians(bearing)) * SPACESHIP_SPEED;
    }

    /**
     * Move the spaceship
     */
    public void move() {
        this.move(vx, vy);
    }

}

/**
 * This class defines an Asteroid object
 *
 * @author Sahil Saini
 */
class GAsteroid extends GRect implements AsteroidConstants {

    private double vx = 0;
    private double vy = 0;

    /**
     * Create a new asteroid
     *
     * @param x initial x-position of asteroid
     * @param y initial y-position of asteroid
     */
    public GAsteroid(double x, double y) {
        super(x, y, ASTEROID_SIZE, ASTEROID_SIZE);
        this.setFilled(true);
        this.setFillColor(Color.LIGHT_GRAY);
    }

    /**
     * Move the asteroid
     */
    public void move() {
        this.move(vx, vy);
    }

    /**
     * Set velocity vectors of asteroid
     *
     * @param vx velocity vector in x-direction
     * @param vy velocity vector in y-direction
     */
    public void setVelocityVectors(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

}

/**
 * This class demonstrates the implementation of a Calculator.
 * However, delete functionality is not implemented.
 *
 * @author Sahil Saini
 */
class Calculator extends Program {

    JTextField display;
    JPanel buttonPanel;

    private final int WIDTH = 400;
    private final int HEIGHT = 500;

    private StringBuilder firstNumber;
    private StringBuilder secondNumber;
    private Operator operator = null;
    private StringBuilder displayText;

    /**
     * Enum to define type of operator
     */
    enum Operator {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE;

        /**
         * Give {@link String} value of {@link Operator}
         *
         * @return operator value as String
         */
        public String toString() {
            switch (this) {
                case PLUS:
                    return "+";
                case MINUS:
                    return "-";
                case MULTIPLY:
                    return "*";
                case DIVIDE:
                    return "/";
                default:
                    return null;
            }
        }
    }

    public static void main(String[] args) {
        new Calculator().start();
    }

    /**
     * Set window size ({@link Calculator#WIDTH}, {@link Calculator#HEIGHT}).
     * Initialize the display area.
     * Initialize button panel.
     * Add listener to observe mouse click
     */
    public void init() {
        setSize(WIDTH, HEIGHT);
        initDisplay();
        initButtonPanel();
        addActionListeners();
    }

    /**
     * Set display with a frequency of 25Hz
     */
    public void run() {
        while (true) {
            setDisplayText();
            pause(40);
        }
    }

    /**
     * Initialize the button panel with a {@link GridLayout}
     */
    private void initButtonPanel() {
        String[] buttons = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", ".", "0", "=", "+"};
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));
        add(buttonPanel);
        for (String s : buttons) {
            JButton button = new JButton(s);
            button.setSize(100, 100);
            buttonPanel.add(button);
        }
    }

    /**
     * Initialize display with a {@link JTextField}
     */
    private void initDisplay() {
        firstNumber = new StringBuilder();
        secondNumber = new StringBuilder();
        display = new JTextField("0", 10);
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        add(display, NORTH);
    }

    /**
     * Set the text for the display
     */
    private void setDisplayText() {
        displayText = new StringBuilder();
        if (firstNumber.toString().isEmpty()) {
            displayText.append("0");
        } else {
            displayText.append(firstNumber);
        }
        if (operator != null) {
            addSpaces(displayText);
            displayText.append(operator.toString());
            if (!secondNumber.toString().isEmpty()) {
                addSpaces(displayText);
                displayText.append(secondNumber.toString());
            }
        }
        display.setText(displayText.toString());
    }

    /**
     * Add spaces to a string builder
     *
     * @param text {@link StringBuilder} non-null object
     */
    private void addSpaces(StringBuilder text) {
        text.append(" ");
    }

    /**
     * Set the {@link Calculator#firstNumber} and {@link Calculator#secondNumber} of type {@link StringBuilder}, based on condition if {@link Calculator#operator} is null
     *
     * @param number input number from user/mouse click
     */
    private void setNumbers(char number) {
        if (operator == null) {
            firstNumber.append(number);
        } else {
            secondNumber.append(number);
        }
    }

    /**
     * Set the new operator input by user.<br>
     * Checks for trailing points to add 0.<br>
     * If {@link Calculator#secondNumber} is not empty, prepare the execution of previous stored operation.<br>
     * Followed by assigning input to {@link Calculator#operator}
     *
     * @param newOperator new operator input from user/mouse click
     */
    private void setOperation(Operator newOperator) {
        checkForTrailingPoint();
        if (!secondNumber.toString().isEmpty()) {
            prepareExecution();
        }
        operator = newOperator;
    }

    /**
     * Prepares the execution of stored numbers and operator
     */
    private void prepareExecution() {
        double number1;
        double number2;

        if (firstNumber.toString().isEmpty()) {
            number1 = 0;
        } else {
            number1 = Double.parseDouble(firstNumber.toString());
        }
        number2 = Double.parseDouble(secondNumber.toString());

        executeOperation(number1, number2);
    }

    /**
     * Checks for trailing points.<br>
     * If exists, add 0 at the end.
     */
    private void checkForTrailingPoint() {
        try {
            if (firstNumber.toString().charAt(firstNumber.toString().length() - 1) == '.'
                    || secondNumber.toString().charAt(secondNumber.toString().length() - 1) == '.') {
                setNumbers('0');
            }
        } catch (Exception e) {
            //Exception caught when second number is empty. Ignoring now for the use case.
            //Requires extra check for string length
        }
    }

    /**
     * Execute stored operation on stored numbers.
     *
     * @param number1 {@link Calculator#firstNumber} casted as double
     * @param number2 {@link Calculator#secondNumber} casted as double
     */
    private void executeOperation(double number1, double number2) {
        double result = 0;
        switch (operator) {
            case PLUS:
                result = number1 + number2;
                break;
            case MINUS:
                result = number1 - number2;
                break;
            case MULTIPLY:
                result = number1 * number2;
                break;
            case DIVIDE:
                if (number2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = number1 / number2;
                break;
            default:
        }
        setResultAndCleanup(result);
    }

    /**
     * Set the result to {@link Calculator#firstNumber} for display.<br>
     * If result contains decimal, set result as double. Else, set as int.
     *
     * @param result Result of operation execution
     */
    private void setResultAndCleanup(double result) {
        firstNumber = new StringBuilder();
        secondNumber = new StringBuilder();
        if (hasDecimal(result)) {
            firstNumber.append(result);
        } else {
            firstNumber.append((int) result);
        }
    }

    /**
     * Check if a number has decimal
     *
     * @param number input number
     * @return true if number has decimal
     */
    private boolean hasDecimal(double number) {
        return number != Math.floor(number);
    }

    /**
     * Inputs point (.) to {@link Calculator#firstNumber} and {@link Calculator#secondNumber}
     */
    private void inputPoint() {
        if (operator == null && !firstNumber.toString().contains(".")) {
            firstNumber.append('.');
        } else if (!secondNumber.toString().contains(".")) {
            secondNumber.append('.');
        }
    }

    /**
     * Observe to action listener
     *
     * @param actionEvent event registered by listener
     */
    public void actionPerformed(ActionEvent actionEvent) {
        char action = actionEvent.getActionCommand().charAt(0);
        switch (action) {
            case '+':
                setOperation(Operator.PLUS);
                break;
            case '-':
                setOperation(Operator.MINUS);
                break;
            case '*':
                setOperation(Operator.MULTIPLY);
                break;
            case '/':
                setOperation(Operator.DIVIDE);
                break;
            case '=':
                setOperation(null);
                break;
            case '.':
                inputPoint();
                break;
            default:
                setNumbers(action);
        }
    }
}

/**
 * This class demonstrates implementation of a countdown
 *
 * @author Sahil Saini
 */
class CountDown extends Program {

    private final int DELAY = 1000;
    private int count = 0;

    private JTextField input;
    private JButton start;

    public static void main(String[] args) {
        new CountDown().start();
    }

    /**
     * Main program thread to set countdown on display
     */
    public void run() {
        while (true) {
            if (count > 0) {
                count--;
                input.setText(String.valueOf(count));
            }
            pause(DELAY);
        }
    }

    /**
     * Initialize UI and register action listener
     */
    public void init() {
        setSize(300, 200);

        input = new JTextField(String.valueOf(count));
        input.setHorizontalAlignment(SwingConstants.CENTER);
        input.setFont(new Font("Ariel", Font.PLAIN, 50));
        add(input, CENTER);

        start = new JButton("Start");
        add(start, SOUTH);

        addActionListeners();
    }

    /**
     * Get count from text field on button click
     *
     * @param actionEvent
     */
    public void actionPerformed(ActionEvent actionEvent) {
        this.count = Math.abs(Integer.parseInt(input.getText()));

    }
}

/**
 * This class demonstrates an implementation of drawing editor
 *
 * @author Sahil Saini
 */
class DrawingEditor extends Program {

    private JRadioButton rect;
    private JRadioButton oval;
    private JCheckBox filled;
    private JComboBox<String> color;
    private JTextArea display;

    public static void main(String[] args) {
        new DrawingEditor().start();
    }

    /**
     * Initialize UI
     */
    public void init() {
        initSouth();
        initCenter();
    }

    /**
     * Set the display text
     */
    public void run() {
        while (true) {
            String displayText = "Color: " + color.getSelectedItem() + "\n"
                    + "Rect: " + rect.isSelected() + "\n"
                    + "Oval: " + oval.isSelected() + "\n"
                    + "Filled: " + filled.isSelected();
            display.setText(displayText);
            pause(40);
        }
    }

    /**
     * Initialize center UI consisting of text area
     */
    private void initCenter() {

        display = new JTextArea();
        display.setEditable(false);
        add(display, CENTER);

    }

    /**
     * Initialize South UI consisting of Radio buttons, Checkbox, and ComboBox
     */
    private void initSouth() {
        rect = new JRadioButton("Rect", true);
        add(rect, SOUTH);

        oval = new JRadioButton("Oval", false);
        add(oval, SOUTH);

        filled = new JCheckBox("Filled", true);
        add(filled, SOUTH);

        String[] colorOptions = {"Green", "Red", "Blue", "Yellow"};
        color = new JComboBox<>(colorOptions);
        add(color, SOUTH);
    }
}

/**
 * This class demonstrates an implementation of Editor
 *
 * @author Sahil Saini
 */
class Editor extends Program {

    private final String open = "Open";
    private final String save = "Save";

    private JTextArea inputText;
    private JTextField filename;
    private JButton saveButton;
    private JButton openButton;

    public static void main(String[] args) {
        new Editor().start();
    }

    /**
     * Initialize UI consisting of Text Area, Text Field, and Buttons
     */
    public void init() {
        inputText = new JTextArea();
        inputText.setFont(new Font("Monospaced", Font.BOLD, 20));
        add(inputText, CENTER);

        filename = new JTextField(20);
        filename.setFont(new Font("Monospaced", Font.PLAIN, 16));
        add(filename, SOUTH);

        openButton = new JButton(open);
        add(openButton, SOUTH);

        saveButton = new JButton(save);
        add(saveButton, SOUTH);

        addActionListeners();
    }

    /**
     * Observe registered button events
     *
     * @param actionEvent
     */
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        if (command.equals(open)) {
            println("File opened: " + filename.getText());
        } else if (command.equals(save)) {
            println("File saved: " + filename.getText());
        }
    }
}


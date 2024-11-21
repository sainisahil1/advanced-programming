package io.sahil.assignment1;

public class Answers {

    /**
     * Karel
     *
     * 1.	The top-down approach requires understanding a bigger problem and dividing it into smaller problems. It is a highly important lesson in programming because it builds the thought process of solving problems by breaking it and taking small steps to solve a bigger problem.
     *
     * 2.	The four basic commands that Karel understand are “move”, “turnLeft”, “pickBeeper”, and “putBeeper”. These commands can be combined to perform multiple tasks like calculation and problem solving. The “move” command allows Karel to move forward whereas the “turnLeft” command gives Karel the freedom of changing direction in 2D space. “putBeeper” and “pickBeeper” can be utilized to do calculations such as multiplication and division.
     *
     * 3.	Karel’s world consist of walls and beepers. It can move around in the world, pick and put beepers. Karel is also equipped with sensors which can detect if there is a wall or the beeper is present.
     *
     * 4.	In the context of Karel, when a loop is run to perform any task, there are certain scenarios where Karel will run into errors. For example, filling up a row of 5 with beepers. A loop can be run 5 times to put the beeper and move forward but since there is no space after the last place, Karel will crash into the wall. This is known as OBOB (Off by one bug). To resolve this, the loop can be run 4 times and “move” method can be avoided for the 5th time, i.e. outside the loop.
     *
     * 5.	Loops allows Karel to perform repetitive tasks. For example, Karel will move 5 times with this loop,
     *
     * for( int i=0; i<5; i++){
     *   move();
     * }
     *
     * 6.	Karel has sensor to detect walls and beepers. For example,
     *
     * while(frontIsBlocked() && beeperPresent()){
     *   putBeeper();
     *   move();
     * }
     *
     * 	This allows Karel to put beeper and move if no beeper is present on the place and the front place is not blocked by wall.
     *
     * 7.	While loop allows the Karel to perform a task if a certain condition is fulfilled.
     * private void moveToWall(){
     *     while(frontIsClear()){
     *         move();
     *     }
     * }
     *
     * This method makes Karel to move forward until a wall is in front of him.
     *
     * 8.	Comments in programming are text blocks written by developers to provide information about a certain code block. Method comments usually contains information about parameters, return type and what it does. In the context of Karel, comments provide pre-conditions and post-conditions of a method, i.e. the position of Karel.
     * The below example shows a simple method “turnaround” which moves Karel from facing east to facing west
     * /*
     * Pre-Condition: Karel is facing east
     * Post-Condition: Karel is facing west
     *  */
     /*

    private void turnAround() {
     *turnLeft();
     *turnLeft();
     *}

     9.	Top-down approach allows us to divide a complex problem into smaller simpler problems. In WindowCleaningKarel example, the problem was to move Karel on the building to clean Windows of 5 skyscrapers. This was broken into a smaller problem of cleaning one skyscraper 5 times. Similarly, Cleaning one skyscraper was broken into smaller problems of move up, move over and move down.

     */

    /*
    Graphics

    Methods are used to segregate code allowing code reusability, modularity and abstraction. In Java, a new method can be defined as follows:
public void demoMethod(int parameter) {
	//TODO
}

2.	Every method in Java has a return type. Methods with return type as “void” don’t return anything. A method runs some calculation or computing and give back a value. For example,
public int demoMethod(int a){
	return a+5;
}

3.	Local variables are defined in a local code block or have a limited scope. Whereas as instance variable have scope throughout the class or are defined to the object of class.

Class MyClass {
	int x = 2;
	public void demo(){
	int x = 3;
	System.out.print(x); //prints 3
	System.out.println(this.x); //prints 2
}

}

4.	Parameters help passing data between methods. Any value passed in the parenthesis of method call will be passed to the method declaration for using the data for computation.

5.	“void” keyword means a method does not return anything. When a certain value is expected from a method, different return type should be used.

6.	“Game loop” keeps the thread of animation alive. The game loop runs the animation code in every iteration. The frames of animation can be controlled by adding a delay.

7.	In the context of animation, it is a good practice to declare GObjects as instance variable, allowing it to be controlled from different methods. If a GObject is declared as a local variable, there would be no access to it once the method completes it’s execution.

8.	To register mouse events, a mouse click event receiver must be declared and listeners must be registered in run method. Example,

public class Test extends GraphicsProgram{

public void run(){
	addMouseListeners();	//Register mouse listener
}

//default overridden method from ACM
public void mouseClicked(MouseEvent mouseEvent){
	//Run when mouse click is detected
}
}

9.	RandomGenerator is a class available in ACM library that is used to generate random numbers and colors.

RandomGenerator rg = new RandomGenerator();
//To generate random number:
rg.nextInt(int);
//To generate random color:
rg.nextColor();


10.	All the object classes in GraphicsProgram are subclass of GObject. It allows the object to be added on the screen. GCanvas is the drawing palette of the Graphics Program.

     */

}

//Daniel Tapia - Final Project


package edu.angelo.finaltapia;

/**
 * Public class Paddle for creating a paddle object
 * I declared the paddle locations variables as static variables
 * for efficiency in this class.
 * 
 * @author Daniel
 *
 */
public class Paddle {
	
	public static final int minX = 16;                      // farthest left the center of a target can go
	public static final int maxX = World.WORLD_WIDTH - 45;  // farthest right the center of a target can go
	public static final int maxY = World.WORLD_HEIGHT - 10; // leaving room for the radius of the target
	
	// Make these static so that the Ball class can access it (efficiency)
	public static int paddleX, paddleY;
	
	/**
	 * Default constructor for Paddle class
	 * Paddle png image is 45x15
	 */
	public Paddle()
	{
		// Set default location of paddle 
		paddleX = World.WORLD_WIDTH / 2;
		paddleY = World.WORLD_HEIGHT - 21;
	}
	
}

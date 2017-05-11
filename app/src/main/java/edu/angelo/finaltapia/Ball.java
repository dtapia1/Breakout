//Daniel Tapia - Final Project


package edu.angelo.finaltapia;

/**
 * Ball class will initialize a new game ball with default location and velocity values.
 * 
 * EFFICIENCY NOTE: I removed the paddleLocX and paddleLocY variables, because they were
 * unnecessary and wasteful in terms of using resources. I released it was much smarter and
 * efficient to access the paddleX and paddleY variables directly from the Paddle class.
 * It's a minor, but helpful change, and a good habit to form.
 * 
 * @author Daniel
 *
 */
public class Ball {
	
	// Variables for ball location on the x and y axis
	public int ballX, ballY;
	
	// Variables for the velocity of the ball movement
	public int b_velocityX ;
	public int b_velocityY ;
	
	
	public static final int minX = 1;                      // farthest left the center of a target can go
	public static final int maxX = World.WORLD_WIDTH - 11;  // farthest right the center of a target can go
	public static final int minY = 55;                      // leaving room at the top of the screen for pause and score
	public static final int maxY = World.WORLD_HEIGHT - 11; // leaving room for the radius of the target
	
	/*
	 * NOTE: I originally had these variables declared so that I could use them to
	 * calculate the offset of the actual Paddle object location and where it was actually
	 * drawn on the game screen.
	 */
	//public static final int paddleLocY = Paddle.paddleY - 15;
	//public static final int paddleLocX = Paddle.paddleX;
	
	/**
	 * Default constructor for Ball class
	 */
	public Ball()
	{
		// Place ball on top of paddle at start of game
		ballX = Paddle.paddleX;
		ballY = Paddle.paddleY - 15;
		
		// Set initial velocity values
		b_velocityX = 0;
		b_velocityY = 15;	
	}

}	



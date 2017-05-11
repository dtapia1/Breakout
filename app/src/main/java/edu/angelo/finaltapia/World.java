/**
 * Daniel Tapia
 * 
 * In the World.java file I created a variable to hold the head of the snake (SnakePart head). 
 * I then assigned the x and y values to variables headX and headY, respectively.
 * Then I assigned headX and headY to stainX and stainY so that the rectangle would first appear at the snake's head.
 * 
 * I made changes to the TICK_INITIAL and TICK_DECREMENT variables. My original goal was to manipulate the
 * starting values so that the snake would gradually speed up as the score increased. My results 
 * did cause the snake to increase it's speed, but it increased sooner than what I wanted; however,
 * I decided to keep it that way, mostly because you seemed to enjoy the extra challenge during my demo! 
 * The fun factor alone was enough reason to allow the game to play at a higher difficulty. 
 */

package edu.angelo.finaltapia;

import java.util.Random;

/**
 * The World class will create all game objects to appear on the GameScreen.
 * Objects - brickSet, paddle, and ball.
 * 
 * I added various boolean values to keep track of the game state (current level, ballCount, and gameWon).
 * I implemented collision methods that calculate whether the Ball object has collided with a 
 * brick, paddle, and game screen area (left, right, and top walls).
 * 
 * I made changes to the colors of existing png images, such as the buttons and Game Over image.
 * 
 * @author Daniel
 *
 */
public class World {
	Random random = new Random();
    static final int WORLD_WIDTH = 320;
    static final int WORLD_HEIGHT = 455;
    static final int SCORE_INCREMENT = 10;
    static final int SCORE_DECREMENT = 1;
    static final int SCORE_BONUS = 50;
    static final float TICK_INITIAL = 0.1f;
    
    // Variables will determine if game has been won or lost
    public boolean gameOver = false;
    public boolean gameWon = false;
    // Variables will determine which level is active
    public boolean level1 = true;
    public boolean level2 = false;
    public boolean level3 = false;
    // Variable will determine if a new ball is available
    public boolean newBall = false;
    
    // Variables will determine which level is active
    public boolean level1Complete = false;
    public boolean level2Complete = false;
    public boolean level3Complete = false;
    public int score = 0;
    // Variable for tracking the number of consecutive times the ball hits the paddle
    public int paddleHitCount = 0;
    // Variable for the number of balls per new game
    public int ballCount = 3;
    
    // Boolean will keep track of whether a brick has been hit
    public boolean hit = false;
    
    
    float tickTime = 0;
    float tick = TICK_INITIAL;

    BrickSet brickSet;  
    Brick brick;
    Ball ball;
    Paddle paddle;
    
    /**
     * The World class will create the BrickSet, Paddle, and Ball objects
     */
    public World() 
    {
        brickSet = new BrickSet();
        paddle = new Paddle();
        ball = new Ball();
    }

    /**
     * The collision method will be used to calculate collisions between the ball
     * object and the gameScreen area. It will ensure that the ball bounces
     * off the surrounding walls and stays within the game area.
     */
    public void collision()
	{
		int a, b;
		
		// Hit right side wall
		if(ball.ballX + ball.b_velocityX > ball.maxX )
		{
			a = ball.maxX - ball.ballX;
			b = ball.b_velocityX - a;
			ball.ballX = ball.maxX - b;
			ball.b_velocityX = ball.b_velocityX * (-1);	
			
		}
		
		// Hit left side wall
		else if(ball.ballX + ball.b_velocityX < ball.minX)
		{
			a = ball.minX - ball.ballX;
			b = ball.b_velocityX - a;
			ball.ballX = ball.minX - b;
			ball.b_velocityX = ball.b_velocityX * (-1);	
		}
		
		// Ball falls below the paddle on the screen
		else if(ball.ballY + ball.b_velocityY > ball.maxY)
		{
			// Rest hit count
			paddleHitCount = 0;
			// Decrement ball count
			ballCount--;
			if(ballCount == 0)
			{
				gameOver = true;
			}
			else
				newBall = true;
		}
		
		// Hit top wall
		else if(ball.ballY + ball.b_velocityY < ball.minY)
		{
			a = ball.minY - ball.ballY;
			b = ball.b_velocityY - a;
			ball.ballY = ball.minY - b;

			ball.b_velocityY = ball.b_velocityY * (-1);
		}
		// No collision detected
		else
			ball.ballX = ball.ballX + ball.b_velocityX;
			ball.ballY = ball.ballY + ball.b_velocityY;
	}	
    
    /**
     * The paddleHit method will be used to calculate collisions between the Ball
     * and Paddle objects. It will ensure that the ball bounces
     * off the paddle accordingly.
     */
    public void paddleHit()
	{
    	
    	int distanceX, distanceY;
    	int paddleHitY;
    	// Distance calculated between the Ball and Paddle objects
    	distanceY = Math.abs(ball.ballY - paddle.paddleY);
    	distanceX =(ball.ballX - paddle.paddleX);
    	// Offset the paddle location on the screen (paddle 45x15)
    	paddleHitY = paddle.paddleY - 15;
    	
    	//Calculate ball hitting the middle of the paddle
    	if(distanceY <= 15 && distanceX <= 7 && distanceX >= 0)
		{	
			int a, b;
			a = paddleHitY  - ball.ballY;
			b = ball.b_velocityY - a;
			ball.ballY = paddleHitY - b;

			ball.b_velocityY = ball.b_velocityY * (-1);	
			// Ball will be shot up
			ball.b_velocityX = 0;
			// Increment paddleHitCount for bonus points
			paddleHitCount++;
		}
    	 
    	//Calculate ball hitting the left side of the paddle
    	else if(distanceY <= 15 && distanceX < 0 && distanceX >= -25 )
		{
    		
			int a, b;
			a = paddleHitY  - ball.ballY;
			b = ball.b_velocityY - a;
			ball.ballY = paddleHitY - b;

			ball.b_velocityY = ball.b_velocityY * (-1);	
			// Ball will be shot off to the left
			ball.b_velocityX = random.nextInt((-1 - (-7)) + 1) + (-7);
			// Increment paddleHitCount for bonus points
			paddleHitCount++;
		}
    	//Calculate ball hitting the right side of the paddle
    	else if(distanceY <= 15 && distanceX < 30 && distanceX > 7)
		{
    		
			int a, b;
			
			a = paddleHitY  - ball.ballY;
			b = ball.b_velocityY - a;
			ball.ballY = paddleHitY - b;

			ball.b_velocityY = ball.b_velocityY * (-1);	
			// Ball will be shot off to the right
			ball.b_velocityX = random.nextInt((7 - (1)) + 1) + (1);
			// Increment paddleHitCount for bonus points
			paddleHitCount++;
		}
    	// 10 consecutive paddle hits
    	if(paddleHitCount == 10)
    	{
    		// Award bonus points for 10 consecutive paddle hits
    		score = score + SCORE_BONUS;
    		// Reset value after 10 hits
    		paddleHitCount = 0;
    	}
	}
	
    /**
     * Method will detect collision between the Ball and Brick objects
     */
    public void brickCollision()
	{
		int distanceX, distanceY;
		// reset hit value to false at the start of the method
		hit = false;
		// Iterate and check for collision for every Brick object in the ArrayList
		for(int i = 0; i < brickSet.bricks.size() ; i++)
		{
			// Distance calculated between the Ball and Brick objects
			distanceX = (ball.ballX -brickSet.bricks.get(i).locationX);
			distanceY = Math.abs(ball.ballY -brickSet.bricks.get(i).locationY);
		
			// brick 50x30
			int brickHitY = brickSet.bricks.get(i).locationY + 30;
			
			// Calculate whether the ball hits any brick	
			if(distanceY <= 30 && distanceX <= 50 && distanceX >= -25)    
			{
				int a, b;
				// Set hit value to true to award points
				hit = true;
				
				a = brickHitY  - ball.ballY;
				b = ball.b_velocityY - a;
				ball.ballY = brickHitY - b;

				
				ball.b_velocityY = ball.b_velocityY * (-1);	
				// Ball will bounce off a brick in either direction on the x axis (left, right, down)
				ball.b_velocityX = random.nextInt((5 - (-5)) + 1) + (-5);
				
				// Speed the ball up for every hit for increased difficulty
				ball.b_velocityX = ball.b_velocityX + 1;
				ball.b_velocityY = ball.b_velocityY + 1;
				// Remove brick from ArrayList if brick has been hit by the ball
				brickSet.bricks.remove(i);
				
			}
			// Award points for every time the ball hits a brick
			if(hit == true)
	    	{
	        	score = score + SCORE_INCREMENT;
	        	break;
	    	}
		
			
		}
		// Level 1 complete
		if(brickSet.bricks.size() == 0 && level1Complete == false)
		{
			level2 = true;
			level1Complete = true;
		}
		// Level 2 complete
		else if(brickSet.bricks.size() == 0 && level2Complete == false)
		{
			level3 = true;
			level2Complete = true;
		}
		// Level 3 complete
		else if(brickSet.bricks.size() == 0 && level3Complete == false)
		{
			gameWon = true;
			level3Complete = true;
		}
	}
  
   
    public void update(float deltaTime) {
        if (gameOver)
            return;

        tickTime += deltaTime;
        
       
        while (tickTime > tick) {
            tickTime -= tick;
            
            collision();
            brickCollision();
            paddleHit();
            // Call moveBricks() for level 3 only
            if(level2Complete == true)
            {	
            	brickSet.moveBricks();
            }
           
        }
    }
}

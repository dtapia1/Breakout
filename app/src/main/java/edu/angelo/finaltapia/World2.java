package edu.angelo.finaltapia;

import java.util.Random;
import edu.angelo.finaltapia.GameScreen.GameState;

public class World2 {

	   static final int WORLD_WIDTH = 320;
	    static final int WORLD_HEIGHT = 455;
	    static final int SCORE_INCREMENT = 10;
	    static final int SCORE_DECREMENT = 1;
	    static final float TICK_INITIAL = 0.1f;
	    
	    public boolean gameOver = false;
	    public boolean gameWon = false;
	    public boolean level2 = true;
	    public boolean level3 = false;
	    public int score = 0;
	    // Boolean will keep track of whether a target has been hit
	    public boolean hit = false;
	    
	    Random random = new Random();
	    float tickTime = 0;
	    float tick = TICK_INITIAL;

	    public static BrickSet brickSet;  
	    Brick brick;
	    Ball ball;
	    Paddle paddle;
	    
	    // Create new TargetSet object
	    public World2() 
	    {
	        brickSet = new BrickSet(4,6);
	        paddle = new Paddle();
	        ball = new Ball();
	    }

	    /**
	     * The shoot function will track the position of the player's touch
	     * on the game area and detect whether a Target object has been hit.
	     * 
	     * The score will be updated accordingly when a Target or Targets are hit.
	     * @param x - the x-coordinate of the touch on the game area
	     * @param y - the y-coordinate of the touch on the game area
	     */
	    
	    
	    public void collision()
		{
			int a, b;
			
			
			if(ball.ballX + ball.b_velocityX > ball.maxX )
			{
				a = ball.maxX - ball.ballX;
				b = ball.b_velocityX - a;
				ball.ballX = ball.maxX - b;
				ball.b_velocityX = ball.b_velocityX * (-1);	
				
			}
			
			else if(ball.ballX + ball.b_velocityX < ball.minX)
			{
				a = ball.minX - ball.ballX;
				b = ball.b_velocityX - a;
				ball.ballX = ball.minX - b;
				ball.b_velocityX = ball.b_velocityX * (-1);	
			}
			
			else if(ball.ballY + ball.b_velocityY > ball.maxY)
			{
				/*a = ball.maxY - ball.ballY;
				b = ball.b_velocityY - a;
				ball.ballY = ball.maxY - b;

				ball.b_velocityY = ball.b_velocityY * (-1);*/
				gameOver = true;
			}
			
			else if(ball.ballY + ball.b_velocityY < ball.minY)
			{
				a = ball.minY - ball.ballY;
				b = ball.b_velocityY - a;
				ball.ballY = ball.minY - b;

				ball.b_velocityY = ball.b_velocityY * (-1);
			}
			else
				ball.ballX = ball.ballX + ball.b_velocityX;
				ball.ballY = ball.ballY + ball.b_velocityY;
		}	
	    
	    
	    public void paddleHit()
		{
	    	
	    	int distanceX, distanceY;
	    	
	    	
	    	distanceX = Math.abs(ball.ballX - paddle.paddleX);
	    	distanceY = Math.abs(ball.ballY - paddle.paddleY);
	    	int paddleHitY = paddle.paddleY - 15;
	    	// paddle is 45x15
	    	if(distanceY <= 15 && distanceX <= 25)
			{
	    		
				int a, b;
				a = paddleHitY  - ball.ballY;
				b = ball.b_velocityY - a;
				ball.ballY = paddleHitY - b;

				ball.b_velocityY = ball.b_velocityY * (-1);	
				ball.b_velocityX = random.nextInt((5 - (-10)) + 1) + (-10);
			}
		}
		
	    
	    public void brickCollision()
		{
	    	//bricks 50x30
			int distanceX, distanceY;
	    	int brickRemove = 0; 
	    	// Will keep track of the number of targets hit at once
	        int numberOfHits = 0;
			for(int i = 0; i < World2.brickSet.bricks.size() ; i++)
			{
				distanceX = Math.abs(ball.ballX -brickSet.bricks.get(i).locationX);
				distanceY = Math.abs(ball.ballY -brickSet.bricks.get(i).locationY);
			
				// brick 50x30
				int brickHitY = brickSet.bricks.get(i).locationY + 30;
				//int brickHitX = World.brickSet.bricks.get(i).locationY + 30;
				if(distanceY <= 30 && distanceX <= 45)
					//(ballY == Paddle.paddleY - 21)
				{
					brickRemove = i;
					int a, b;
					a = brickHitY  - ball.ballY;
					b = ball.b_velocityY - a;
					ball.ballY = brickHitY - b;

					ball.b_velocityY = ball.b_velocityY * (-1);	
					ball.b_velocityX = random.nextInt((5 - (-5)) + 1) + (-5);
					brickSet.bricks.remove(i);
				}	
				
			}
			if(brickSet.bricks.size() == 0)
			{
				level3 = true;	
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
	           
	        }
	    }
}

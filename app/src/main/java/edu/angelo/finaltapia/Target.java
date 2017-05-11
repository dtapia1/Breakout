package edu.angelo.finaltapia;

import java.util.Random;

/**
 * Target class will be used to create the boundaries for the game world.
 * @author Daniel
 *
 */
public class Target {
	
	private static Random random = new Random();
	public static final int minX = 16;                      // farthest left the center of a target can go
	public static final int maxX = World.WORLD_WIDTH - 17;  // farthest right the center of a target can go
	public static final int minY = 80;                      // leaving room at the top of the screen for pause and score
	public static final int maxY = World.WORLD_HEIGHT - 17; // leaving room for the radius of the target
	
	public int locationX, locationY;
	public int velocityX, velocityY;
	
	/*
	 * Default constructor will call randomize function.
	 */
	public Target(int locationX, int locationY)
	{
		this.locationX = locationX;
		this.locationY = locationY;
		//randomize();
	}
	
	/**
	 * The advance method will check for boundary collision between the Target object
	 * and the edge of the game world. The method will redirect a Target object that
	 * reaches the edge to the opposite direction in order to create a bounce effect.
	 */
	public void advance()
	{
		int a, b;
		if(locationX + velocityX > maxX )
		{
			a = maxX - locationX;
			b = velocityX - a;
			locationX = maxX - b;
			velocityX = velocityX * (-1);	
			
		}
		
		else if(locationX + velocityX < minX)
		{
			a = minX - locationX;
			b = velocityX - a;
			locationX = minX - b;
			velocityX = velocityX * (-1);	
		}
		
		else if(locationY + velocityY > maxY)
		{
			a = maxY - locationY;
			b = velocityY - a;
			locationY = maxY - b;

			velocityY = velocityY * (-1);
		}
		
		else if(locationY + velocityY < minY)
		{
			a = minY - locationY;
			b = velocityY - a;
			locationY = minY - b;

			velocityY = velocityY * (-1);
		}
		else
			locationX = locationX + velocityX;
			locationY = locationY + velocityY;
	}	
	/**
	 * The randomize function will randomly choose the direction and
	 * velocity that a Target object will move across the game world area.
	 */
	public void randomize()
	{
		locationX = random.nextInt((maxX - minX)+ 1) + minX;
		locationY = random.nextInt((maxY - minY)+ 1) + minY;
			
		// value between -10 and 10
		velocityX = random.nextInt((10 - (-10)) + 1) + (-10);
		velocityY = random.nextInt((10 - (-10)) + 1) + (-10);
		
	}
			
}

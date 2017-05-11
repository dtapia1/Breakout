//Daniel Tapia - Final Project

package edu.angelo.finaltapia;

import java.util.Random;

/**
 * Brick class will initialize a new Brick object with default location and velocity values.
 * 
 * EFFICIENCY NOTE:
 * I originally had getter and setter methods created for retrieving the locationX and locationY
 * values for every Brick object (mostly because this is good practice in C++/Java programming), but 
 * according to the training information (http://developer.android.com/training/articles/perf-tips.html),
 * using these getter and setter methods are quite costly on Android. For efficiency purposes, I removed
 * my getter and setter methods and decided to access the locationX and locationY values 
 * directly (from outside the class).
 * 
 * @author Daniel
 *
 */
public class Brick 
{
	private static Random random = new Random();
	public int locationX, locationY;
	public int velocityX, velocityY;
	public boolean hit;
	
	public  final int minX = 1;                      // farthest left the center of a target can go
	public  final int maxX = World.WORLD_WIDTH - 50;  // farthest right the center of a target can go
	public  final int minY = 55;                      // leaving room at the top of the screen for pause and score
	public  final int maxY = World.WORLD_HEIGHT - 175; 
	/*
	 * Default constructor for Brick class
	 * Brick png images are size 50x30
	 */
	public Brick(int locationX, int locationY)
	{
		this.locationX = locationX;
		this.locationY = locationY;
		hit = false;
	}
	
	/**
	 * Constructor will be called for level 3 to initiate Bricks that 
	 * will place each Brick object in a random location and will
	 * randomly move each one throughout the game screen area.
	 */
	public Brick()
	{
		randomize();
		moveBricks();
		hit = false;
	}
	
	/**
	 * The MoveBricks method will put the Brick objects in random motion throughout the 
	 * game screen area.
	 */
	public void moveBricks()
	{
		int a, b;
		// Bounce off the right wall
		if(locationX + velocityX > maxX )
		{
			a = maxX - locationX;
			b = velocityX - a;
			locationX = maxX - b;
			velocityX = velocityX * (-1);	
			
		}
		// Bounce off the left wall
		else if(locationX + velocityX < minX)
		{
			a = minX - locationX;
			b = velocityX - a;
			locationX = minX - b;
			velocityX = velocityX * (-1);	
		}
		// Bounce off the bottom wall
		else if(locationY + velocityY > maxY)
		{
			a = maxY - locationY;
			b = velocityY - a;
			locationY = maxY - b;

			velocityY = velocityY * (-1);
		}
		// Bounce off the top wall
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
	 * velocity that a Ball object will move across the game world area.
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

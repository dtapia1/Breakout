//Daniel Tapia - Final Project

package edu.angelo.finaltapia;

import java.util.ArrayList;
import java.util.Random;

/**
 * BrickSet class will be used to create an ArrayList of Brick objects
 * A constructor will be called for each game level with the Brick objects
 * being placed on the game screen differently from the previous level.
 * 
 * @author Daniel
 *
 */
public class BrickSet {

	//ArrayList of Bricks
	public ArrayList<Brick> bricks = new ArrayList<Brick>();
	
	// These variables will not be accessed outside of this class
	private int xCoord = 7;
	private int  yCoord = 80;
	private int rows = 3;
	private int columns = 6;
	private static Random random = new Random();
	
	
	//Use constructor for level3
	public BrickSet(int level)
	{
		//create pixmap array
		for(int i = 0; i < level; i++)
		{
			
				// add new Target object to targets ArrayList
				bricks.add(new Brick());
				
		}
	}
			
	
	
	/*
	 * Use constructor for level 2
	 * 
	 * The bricks will be randomly placed into the Bricks ListArray
	 */
	public BrickSet(int row, int column)
	{
		int numb;
		//create pixmap array
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < column; j++)
			{
				// 50% chance that a brick will be added to the ArrayList
				numb = random.nextInt((2 - 1)+ 1) + 1;
				if(numb == 1)
				{
					bricks.add(new Brick(xCoord, yCoord));
					
				}
				xCoord = xCoord + 51;
			}
			//reset xCoord position to start a new row
			xCoord = 7;
			//creates a small space in between each row of bricks
			yCoord = yCoord + 31;
		}
		
	}
	
	
	/*
	 *  Use constructor for level 1
	 *  
	 *  The bricks will be placed into every element slot of the Bricks ArrayList
	 */
	public BrickSet()
	{
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				// add new Target object to targets ArrayList
				bricks.add(new Brick(xCoord, yCoord));
				//creates a small space in between each brick
				xCoord = xCoord + 51;
			}
			//reset xCoord position to start a new row
			xCoord = 7;
			//creates a small space in between each row of bricks
			yCoord = yCoord + 31;
		}
	}
	
	/**
	 * Call moveBricks function for each object in bricks ArrayList
	 */
	public void moveBricks()
	{
		// call advance function for each object in targets ArrayList
		for(int i = 0; i < bricks.size(); i++)
		{
			bricks.get(i).moveBricks();
		}
	}
	
}
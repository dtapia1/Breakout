package edu.angelo.finaltapia;

import java.util.ArrayList;

/**
 * TargetSet class will initialize an ArrayList of type Target.
 * 
 * @author Daniel
 *
 */
public class TargetSet {

	//ArrayList of Targets
	public ArrayList<Target> targets = new ArrayList<Target>();
	private int xCoord = 7;
	private int  yCoord = 80;
	
	public TargetSet(int row, int column)
	{
		//create pixmap array
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < column; j++)
			{
				// add new Target object to targets ArrayList
				targets.add(new Target(xCoord, yCoord));
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
	 * The default constructor will call the initializing constructor to
	 * add 10 Target elements to the ArrayList.
	 */
	public TargetSet()
	{
		this(4,6);
	}
	
	/**
	 * The advance method will call the advance method from the Target class.
	 */
	public void advance()
	{
		// call advance function for each object in targets ArrayList
		for(int i = 0; i < targets.size(); i++)
		{
			targets.get(i).advance();
		}
	}
	
}

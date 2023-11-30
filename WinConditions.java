/**
* This file is part of a solution to
*     CPSC 101 Team Project, January 2020.
*
* 		Checks all possible win conditions
*
* @author Prem Patel / Barbara Friesen
* Student Number:  / 230142078
* @version 1
*/

public class WinConditions
{
	private static int[][][] coordinates;

	public WinConditions(int[][][] coordinateArray)
	{
		coordinates = coordinateArray;
	}

	/**
	* PURPOSE:	Calls every win condition
	*
	* @param	int cVal = Colour value being checked
	*
	* @return	boolean - If condition is met or not
	*/
	public boolean checkConditions(int cVal, int x, int y, int z)
	{
		coordinates[x][y][z] = cVal;
		if(checkHorizontal(cVal))
		{
			return true;
		}
		if(checkVertical(cVal))
		{
			return true;
		}
		if(checkDiagonal(cVal))
		{
			return true;
		}
		return false;
	}

	/**
	* PURPOSE: Checks every possible horizontal line
	*
	* @param   int cVal - the colour value that is being checked
	*
	* @return  Boolean - If conditon is met or not
	*/
	private static boolean checkHorizontal(int cVal)
	{
		//creates counter
		int counter = 0;

		//goes through board horizontally
		for(int z = 0; z < 4; z++)
		{
			//Goes through rows in respect to x
			for(int x = 0; x < 4; x++)
			{
				for(int y = 0; y < 4; y++)
				{
					if(coordinates[x][y][z] == cVal)
					{ //if it finds an X, add 1 to counter
						counter += 1;
					}
					else
					{
						counter = 0; // if next piece is not an X, set counter to 0
					}
					if(counter == 4)
					{ //if counter is greater or equal to 4, player wins
						return true;
					}
				}
			}

			//Goes through rows in respect to y
			for(int y = 0; y < 4; y++)
			{
				for(int x = 0; x < 4; x++)
				{
					if(coordinates[x][y][z] == cVal)
					{
						counter += 1;
					}
					else
					{
						counter = 0; // if next piece is not an X, set counter to 0
					}
					if(counter == 4)
					{
						return true;
					}
				}
			}

			//Checks the horizontal lines cutting across the board
			for(int x = 0; x < 4; x++)
			{
				int y = x;
				if(coordinates[x][y][z] == cVal)
				{
					counter += 1;
				}
				else
				{
					counter = 0;
				}
				if(counter == 4)
				{
					return true;
				}
			}
		}
		return false;
    }

	/**
	* PURPOSE: Checks every possible vertical line
	*
	* @param   int cVal - the colour value that is being checked
	*
	* @return  Boolean - If conditon is met or not
	*/
    private static boolean checkVertical(int cVal)
	{
		int counter = 0;

		//Checks all vertical columns
		for(int x = 0; x < 4; x++)
		{
			for(int y = 0; y < 4; y++)
			{
				for(int z = 0; z < 4; z++)
				{
					if(coordinates[x][y][z] == cVal)
					{
						counter++;
					}
					else
					{
						counter = 0;
					}
					if(counter == 4)
					{
						return true;
					}
				}
			}
		}
		return false;
    }

	/**
	* PURPOSE: Checks every possible diagonal line
	*
	* @param   int cVal - the colour value that is being checked
	*
	* @return  Boolean - If conditon is met or not
	*/
    private static boolean checkDiagonal(int cVal)
    {
		//counter
		int counter = 0;

		//Checks diagonal lines accross the z dimension in respect to x
		for(int x  = 0; x < 4; x++)
		{
			//Checks lines starting at the bottom with respect to x
			for(int y = 0; y < 4; y++)
			{
				int z = y;
				if(coordinates[x][y][z] == cVal)
				{
					counter += 1;
				}
				else
				{
					counter = 0;
				}
				if(counter == 4)
				{
					return true;
				}
			}
			//checks lines starting at the top in respects to x
			for(int y = 0; y < 4; y++)
			{
				int z = 3 - y;
				if(coordinates[x][y][z] == cVal)
				{
					counter += 1;
				}
				else
				{
					counter = 0;
				}
				if(counter == 4)
				{
					return true;
				}
			}
		}
		//Checks diagonal lines accross the z dimension in respect to y
		for(int y = 0; y < 4; y++)
		{
			//checks lines starting at the bottom in respects to y
			for(int x = 0; x < 4; x++)
			{
				int z = x;
				if(coordinates[x][y][z] == cVal)
				{
					counter += 1;
				}
				else
				{
					counter = 0;
				}
				if(counter == 4)
				{
					return true;
				}
			}
			//checks lines starting at the top in respects to y
			for(int x = 0; x < 4; x++)
			{
				int z = 3 - x;
				if(coordinates[x][y][z] == cVal)
				{
					counter += 1;
				}
				else
				{
					counter = 0;
				}
				if(counter == 4)
				{
					return true;
				}
			}
		}
		//Checks diagonal line cutting accross the board starting at the bottom at x = 1
		for(int x = 0; x < 4; x++)
		{
			int y = x;
			int z = x;
			if(coordinates[x][y][z] == cVal)
			{
				counter += 1;
			}
			else
			{
				counter = 0;
			}
			if(counter == 4)
			{
				return true;
			}
		}
		//Checks diagonal line cutting accross the board starting at the top at x = 1
		for(int x = 0; x < 4; x++)
		{
			int y = x;
			int z = 3 - x;
			if(coordinates[x][y][z] == cVal)
			{
				counter += 1;
			}
			else
			{
				counter = 0;
			}
			if(counter == 4)
			{
				return true;
			}
		}
		//Checks diagonal line cutting accross the board starting at the bottom at x = 4
		for(int x = 3; x >= 0; x--)
		{
			int y = 3 - x;
			int z = 3 - x;
			if(coordinates[x][y][z] == cVal)
			{
				counter += 1;
			}
			else
			{
				counter = 0;
			}
			if(counter == 4)
			{
				return true;
			}
		}
		//Checks diagonal line cutting accross the board starting at the top at x = 4
		for(int x = 3; x >= 0; x--)
		{
			int y = 3 - x;
			int z = x;
			if(coordinates[x][y][z] == cVal)
			{
				counter += 1;
			}
			else
			{
				counter = 0;
			}
			if(counter == 4)
			{
				return true;
			}
		}
		return false;
    }
}
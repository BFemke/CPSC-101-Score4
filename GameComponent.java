/**
* This file is part of a solution to
*     CPSC 101 Team Project, January 2020.
*
* 		Runs all the mechanics of the game acting as a
*			bridge to other classes
*
* @author Barbara Friesen
* Student Number: 230142078
* @version 1
*/
import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;

public class GameComponent
{

	private static int[][][] coordinates;
	private SecureRandom random = new SecureRandom();
	private int playerTurnNum;
	private int gameMode;
	private WinConditions winCheck;
	private DrawConditions drawCheck;
	private Player p1;
	private Player p2;

    public GameComponent(int mode)
    {
		p1 = new Player();
		p2 = new Player();
		coordinates = new int[4][4][4];
		clearArray();
		gameMode = mode;
		winCheck = new WinConditions(coordinates);
		drawCheck = new DrawConditions(coordinates);
	}

	/**
	* PURPOSE: Creates player 1 as either a player or computer
	*
	* @param   int playerType - Determines whether computer or player is created
	*
	* @return  none
	*/
	public void createPlayer1(int playerType)
	{
		if(gameMode == 0)
		{
			//Creates player class with name
			if(playerType == 0)
			{
				p1 = new Player();
				String name = JOptionPane.showInputDialog((Component)null, "Player 1 what is your name?");
				p1.setName(name);
				p1.setPlayerType("Player");
			}

			//Creates computer class
			else if(playerType == 1)
			{
				p1 = new Computer();
				p1.setPlayerType("AI");
			}
		}
		else
		{
			p1 = new Player();
			p1.setName("Player 1");
			p1.setPlayerType("N/A");
			p1.setColourValue(0);
		}

	}

	/**
	* PURPOSE: Creates player 2 as either a player or computer
	*
	* @param   int playerType - Determines wether computer or player is created
	*
	* @return  none
	*/
	public void createPlayer2(int playerType)
	{
		if(gameMode == 0)
		{
			//Creates player class with name
			if(playerType == 0)
			{
				p2 = new Player();
				String name = JOptionPane.showInputDialog((Component)null, "Player 2 what is your name?");
				p2.setName(name);
				p2.setPlayerType("Player");
			}

			//Creates computer class
			else if(playerType == 1)
			{
				p2 = new Computer();
				p2.setPlayerType("AI");
			}
		}
		else
		{
			p2 = new Player();
			p2.setName("Player 2");
			p2.setPlayerType("N/A");
			p2.setColourValue(1);
		}

	}

	/**
	* PURPOSE: Sets players colour based on colour selected by player 1
	*
	* @param   int value - The colour value selected
	*
	* @return  none
	*/
	public void initializeColour(int value)
	{
		if(value == 0)
		{
			p1.setColourValue(0);
			p2.setColourValue(1);
			playerTurnNum = 0;
			if(p1.getPlayerType().equals("AI"))
			{
				int peg = chooseRandomPeg();
				int x = peg / 10;
				int y = peg % 10;
				placeBead(x, y);
			}
			System.out.println(value);
		}
		else
		{
			p1.setColourValue(1);
			p2.setColourValue(0);
			playerTurnNum = 1;
			if(p2.getPlayerType().equals("AI"))
			{
				int peg = chooseRandomPeg();
				int x = peg / 10;
				int y = peg % 10;
				placeBead(x, y);
			}

		}
	}

	/**
	* PURPOSE: Updates 3D array of value indicating bead colour at appropriate
	*				location.
	*
	* @param	int x - x-coordinate of peg
	* @Param	int y - y-coordinate of peg
	*
	* @return	none
	*/
    public void placeBead(int x, int y)
    {
		int colourNum = 0;
		int z = 0;

		//Gets colour value of player who placed bead
		if(playerTurnNum == 0)
		{
			colourNum = p1.getColourValue();
		}
		else if(playerTurnNum == 1)
		{
			colourNum = p2.getColourValue();
		}

		//Places colour value in proper coordinates of array
		for(int i = 0; i < 4; i++)
		{
			z = i;
			if(coordinates[x][y][z] == 2)
			{
				coordinates[x][y][z] = colourNum;
				i += 4;
			}
		}
		checkGameEnd(colourNum, x, y, z);
		switchPlayerTurn();

    }

	/**
	* PURPOSE:	Calls methods to see if game is over
	*
	* @param	int colourNum - Colour value of bead just played
	*
	* @return	none
	*/
    private void checkGameEnd(int colourNum, int x, int y, int z)
    {
		boolean win = winCheck.checkConditions(colourNum, x, y, z);
		//Executes if win is met
		if(win)
		{
			if(gameMode == 0)
			{
				switchPlayerTurn();
			}
			gameOver(playerTurnNum);
		}

		boolean gameDraw = drawCheck.checkConditions(colourNum, x, y, z);
		if(gameDraw == false)
		{
			gameOver(2);
		}
	}

   	/**
	* PURPOSE:	Switches number indicating whose tuen it is
	*
	* @param	none
	*
	* @return	none
	*/
    private void switchPlayerTurn()
    {
		if(playerTurnNum == 0)
		{
			playerTurnNum = 1;
			if(p2.getPlayerType().equals("AI"))
			{
				int peg = chooseRandomPeg();
				int x = peg / 10;
				int y = peg % 10;
				placeBead(x, y);
			}
		}
		else
		{
			playerTurnNum = 0;
			if(p1.getPlayerType().equals("AI"))
			{
				int peg = chooseRandomPeg();
				int x = peg / 10;
				int y = peg % 10;
				placeBead(x, y);
			}
		}

	}

	/**
	* PURPOSE: Checks if there is room for another bead on a peg
	*
	* @param	int x - x-coordinate of peg
	* @Param	int y - y-coordinate of peg
	*
	* @return	boolean - true if there is room, false otherwise
	*/
    public boolean checkSpace(int x, int y)
    {
		for(int i = 0; i < 4; i++)
		{
			if(coordinates[x][y][i] == 2)
			{
				return true;
			}
		}
		return false;
	}

	/**
	* PURPOSE:	Declares winner while asking to play another game
	*
	* @param	int winnerNum - Player number of the winning player or
	*				indicates draw
	*
	* @return	none
	*/
	private void gameOver(int winnerNum)
	{
		//increments appropriate win value
		if(winnerNum == 0)
		{
			p1.incrementWin();
		}
		else if(winnerNum == 1)
		{
			p2.incrementWin();
		}
		else
		{
			p1.incrementWin();
			p2.incrementWin();
		}
		JOptionPane.showMessageDialog(null, "You Won!");

	}

	/**
	* PURPOSE:	Calls methods to reset game variables to start a new game
	*
	* @param	none
	*
	* @return	none
	*/
	public void resetGame()
	{
		if(gameMode == 0)
		{
			clearArray();
			int p2Colour = switchColours();

			//Sets player turn to new white player
			if(p2Colour == 1)
			{
				playerTurnNum = 0;
			}
			else
			{
				playerTurnNum = 1;
			}
		}
		else
		{
			clearArray();
		}
	}

	/**
	* PURPOSE:	Switches color black/white for both players
	*
	* @param	none
	*
	* @return	int p1Colour - player 1's old colour
	*/
	private int switchColours()
	{
		int p1Colour = p1.getColourValue();
		if(p1Colour == 0)
		{
			p1.setColourValue(1);
			p2.setColourValue(0);
		}
		else
		{
			p1.setColourValue(0);
			p2.setColourValue(1);
		}
		return p1Colour;
	}


	/**
	* PURPOSE:	Resets every coordinates array value with 2
	*
	* @param	none
	*
	* @return	none
	*/
	public void clearArray()
	{
		//Loops through each dimension of array
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				for(int k = 0; k < 4; k++)
				{
					coordinates[i][j][k] = 2;
				}
			}
		}
	}

	/**
	* PURPOSE:	Removes bead from peg if it exists
	*
	* @param	int x - x-coordinate of peg
	* @param	int y - y-coordinate of peg
	*
	* @return	boolean - True if bead removed successfully, false if unable
	*/
	public boolean removeBead(int x, int y)
	{
		//Checks if a peg contains at least one bead
		for(int i = 3; i >= 0; i--)
		{
			if((coordinates[x][y][i] == 0) || (coordinates[x][y][i] == 1))
			{
				//rewrites bead value as empty
				coordinates[x][y][i] = 2;
				return true;
			}
		}
		return false;
	}

	/**
	* PURPOSE:	Adds bead to peg in Test Mode
	*
	* @param	int x - x-coordinate of peg
	* @param	int y - y-coordinate of peg
	* @param	int colourNum - colour value of bead that is added
	*
	* @return	boolean - True if bead added successfully, false if unable
	*/
	public boolean placeTestBead(int x, int y, int colourNum)
	{
		int z = 0;
		//Checks to find empty space on peg
		for(z = 0; z < 4; z++)
		{
			if(coordinates[x][y][z] == 2)
			{
				//adds bead to next available slot
				coordinates[x][y][z] = colourNum;
				checkGameEnd(colourNum, x, y, z);
				return true;
			}
		}
		return false;
	}

	public int chooseRandomPeg()
	{
		int randomX = 0;
		int randomY = 0;
		boolean legal = false;
		while(legal == false)
		{
			randomX = random.nextInt(4);
			randomY = random.nextInt(4);
			legal = checkSpace(randomX, randomY);
		}
		return (randomX*10) + randomY;

	}

	/**
	* PURPOSE:	Checks what bead is located on coordinate
	*
	* @param	int x - x-coordinate of peg
	* @param	int y - y-coordinate of peg
	* @param	int z - z-coordinate of slot to check
	*
	* @return	int value - value found in specified coordinates
	*/
	public int checkCoordinateArray(int x, int y, int z)
	{
		int value = coordinates[x][y][z];
		return value;
	}

	/**
	* PURPOSE:	Returns the player type of player 1
	*
	* @param	none
	*
	* @return	String - Contains the type of player either "Player" or "AI"
	*/
	public String getPlayer1Type()
	{
		return p1.getPlayerType();
	}

	/**
	* PURPOSE:	Returns the player type of player 2
	*
	* @param	none
	*
	* @return	String - Contains the type of player either "Player" or "AI"
	*/
	public String getPlayer2Type()
	{
		return p2.getPlayerType();
	}

	/**
	* PURPOSE:	Returns the name assigned to player 1
	*
	* @param	none
	*
	* @return	String - The name assigned to player 1
	*/
	public String getPlayer1Name()
	{
		String name = p1.getName();
		return name;
	}

	/**
	* PURPOSE:	Returns the name assigned to player 2
	*
	* @param	none
	*
	* @return	String - The name assigned to player 2
	*/
	public String getPlayer2Name()
	{
		return p2.getName();
	}

	/**
	* PURPOSE:	Returns the number of wins attributed to player 1
	*
	* @param	none
	*
	* @return	int = Number of wins by player 1
	*/
	public int player1WinCount()
	{
		return p1.getWinCount();
	}

	/**
	* PURPOSE:	Returns the number of wins attributed to player 2
	*
	* @param	none
	*
	* @return	int = Number of wins by player 2
	*/
	public int player2WinCount()
	{
		return p2.getWinCount();
	}

	/**
	* PURPOSE:	Returns the colour value associated with player 1
	*
	* @param	none
	*
	* @return	int = Colour value of player 1
	*/
	public int getPlayer1Colour()
	{
		return p1.getColourValue();
	}

	/**
	* PURPOSE:	Returns the colour value associated with player 2
	*
	* @param	none
	*
	* @return	int = Colour value of player 2
	*/
	public int getPlayer2Colour()
	{
		return p2.getColourValue();
	}

	/**
	* PURPOSE:	Returns the player number of whose turn it is
	*
	* @param	none
	*
	* @return	int = Player number of current turn
	*/
	public int getPlayerTurnNum()
	{
		return playerTurnNum;
	}

}
/**
* This file is part of a solution to
*     CPSC 101 Team Project, January 2020.
*
* 		Creates Player objects through which to play Scour Four game
*
* @author Barbara Friesen
* Student Number: 230142078
* @version 1
*/
import java.awt.Color;
import java.lang.NullPointerException;

public class Player
{
	private static int playerCounter = 0;
	private int playerNum;
	private String name;
	private int numWin =0;
	private int colourValue;
	private String playerType;

	public Player()
	{
		playerNum = playerCounter;
	}

	public void setPlayerType(String type)
	{
		this.playerType = type;
		playerCounter++;
	}

	/**
	* PURPOSE:	Sets player's name
	*
	* @param	String name - Name of player
	*
	* @return	none
	*/
	public void setName(String newName)
	{
		name = newName;
	}

	/**
	* PURPOSE:	Sets the player's bead colour
	*
	* @param	int colourValue - Colour associated with bead placed by player
	*
	* @return	none
	*/
	public void setColourValue(int value)
	{
		colourValue = value;
	}

	/**
	* PURPOSE:	Returns the colour of player's beads
	*
	* @param	none
	*
	* @return	int colourValue - Colour associated with player's beads
	*/
	public int getColourValue()
	{
		return colourValue;
	}

	/**
	* PURPOSE:	Increments player's number of wins by 1
	*
	* @param	none
	*
	* @return	none
	*/
	public void incrementWin()
	{
		numWin++;
	}

	/**
	* PURPOSE:	Returns the name of the player
	*
	* @param	none
	*
	* @return	String name - Player's name
	*/
	public String getName()
	{
		return this.name;
	}

	/**
	* PURPOSE:	Returns the number of times the player has won
	*
	* @param	none
	*
	* @return	int numWin - Number of times the player has won
	*/
	public int getWinCount()
	{
		return numWin;
	}

	/**
	* PURPOSE:	Returns the type of player, either "Player" or "AI"
	*
	* @param	none
	*
	* @return	String playerType - Either "Player" or "AI" type
	*/
	public String getPlayerType()
	{
		return playerType;
	}

}
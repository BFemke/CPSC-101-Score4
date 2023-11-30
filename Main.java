/**
* This file is part of a solution to
*     CPSC 101 Team Project, January 2020.
*
* 		Initializes the game, bringing it all together
*
* @author Barbara Friesen
* Student Number: 230142078
* @version 1
*/
import javax.swing.*;
import java.awt.*;

public class Main
{

	public static void main(String[] args)
	{
		int modeType = setModeType();

		if(modeType == 0)
		{
			GameComponent game = new GameComponent(0);
			game.createPlayer1(setPlayerType());
			game.createPlayer2(setPlayerType());

			game.initializeColour(setColourChoice());
			GameDisplay display = new GameDisplay(game);

		}
		else if(modeType == 1)
		{
			TestMode test = new TestMode();
		}
	}

	/**
	* PURPOSE:	Asks user to select type of player to create
	*
	* @param	none
	*
	* @return	Int type - Number value associated with player type
	*/
	public static int setPlayerType()
	{
		//Prompts selection of either "Player" or "Computer" type to be created
		Object[] options = { "Player", "Computer" };
		int type = JOptionPane.showOptionDialog((Component)null, "Please select type of player.", "Player Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return type;
	}

	/**
	* PURPOSE:	Asks user to select a game mode
	*
	* @param	none
	*
	* @return	int type - Value associated with game mode selection
	*/
	public static int setModeType()
	{
		Object[] options = { "Interactive", "Test" };
		int type = JOptionPane.showOptionDialog((Component)null, "Please select mode to start in", "Mode Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return type;
	}

	/**
	* PURPOSE:	Asks player 1 for colour selection
	*
	* @param	none
	*
	* @return	int value - Value associated with colour selection
	*/
	public static int setColourChoice()
	{
		Object[] options = { "White", "Black" };
		int value = JOptionPane.showOptionDialog((Component)null, "Please select colour for player 1", "Colour Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return value;
	}

}
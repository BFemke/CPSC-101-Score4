/**
* This file is part of a solution to
*     CPSC 101 Team Project, January 2020.
*
* 		Creates Test Mode environment allowing commands
*
* @author Barbara Friesen
* Student Number: 230142078
* @version 1
*/
import java.util.Scanner;

public class TestMode
{
	private static char[] xCoord = {'A', 'B', 'C', 'D'};
	private Scanner input = new Scanner(System.in);
	private String command = input.nextLine();
	private GameComponent game;

	public TestMode()
	{
		game = new GameComponent(1);
		game.createPlayer1(0);
		game.createPlayer2(0);
		runTestMode();
	}

	/**
	* PURPOSE:	Runs a loop waiting for command inputs
	*
	* @param	none
	*
	* @return	none
	*/
	private void runTestMode()
	{
		//Loops until command given to change game mode out of test
		while(!(command.equalsIgnoreCase("go interactive.")) && !(command.equalsIgnoreCase("go gui.")))
		{
			String peg;
			int x = 0;
			int y = 0;
			String yStr;

			//Clears coordinate array
			if(command.equalsIgnoreCase("clear."))
			{
				game.clearArray();
			}

			//Removes bead at a given location, with confirmation
			else if(command.contains("remove") && command.contains("bead")
			&& command.contains("."))
			{
				peg = command.substring(command.lastIndexOf(" ") +1);
				yStr = "" + peg.charAt(1);
				y = Integer.parseInt(yStr)-1;

				//assigns appropriate number to correspond with letter given
				for(int i =0; i < 4; i++)
				{
					if(xCoord[i] == (peg.charAt(0)))
					{
						x = i;
					}
				}

				boolean legal = game.removeBead(x, y);
				if(legal)
				{
					System.out.println("Done");
				}
				else
				{
					System.out.println("Impossible");
				}
			}

			//Adds bead at given location if able with confirmation
			else if(command.contains("add") && command.contains("bead")
			&& command.contains("."))
			{
				//Checks if bead being placed needs to be black
				if(command.contains("black"))
				{
					int colourValue = 1;
					peg = command.substring(command.lastIndexOf(" ")+1);
					System.out.println(peg);
					yStr = "" + peg.charAt(1);
					y = Integer.parseInt(yStr)-1;

					//assigns appropriate number to correspond with letter given
					for(int i = 0; i < 4; i++)
					{
						if(xCoord[i] == (peg.charAt(0)))
						{
							x = i;
						}
					}
					boolean legal = game.placeTestBead(x, y, colourValue);
					if(legal)
					{
						System.out.println("Done");
					}
					else
					{
						System.out.println("Impossible");
					}
				}

				//Checks if bead being placed needs to be white
				else if(command.contains("white"))
				{
					int colourValue = 0;
					peg = command.substring(command.lastIndexOf(" ")+1);
					System.out.println(peg);
					yStr = "" + peg.charAt(1);
					y = Integer.parseInt(yStr)-1;

					//assigns appropriate number to correspond with letter given
					for(int i =0; i < 4; i++)
					{
						if(xCoord[i] == (peg.charAt(0)))
						{
							x = i;
						}
					}
					boolean legal = game.placeTestBead(x, y, colourValue);
					if(legal)
					{
						System.out.println("Done");
					}
					else
					{
						System.out.println("Impossible");
					}
				}
				else
				{
					errorMessage();
				}
			}

			//Displays list representation of board
			else if(command.equalsIgnoreCase("show board."))
			{
				for(int i = 0; i < 4; i++)
				{
					for(int j = 0; j < 4; j++)
					{
						System.out.print(xCoord[i] + "" + (j+1) + ": ");
						for(int k = 0; k < 4; k++)
						{
							int slotValue = game.checkCoordinateArray(i, j, k);
							if(slotValue == 0)
							{
								System.out.print("W");
							}
							else if(slotValue == 1)
							{
								System.out.print("B");
							}
						}
						System.out.println();
					}

				}
			}

			//quits program
			else if(command.equalsIgnoreCase("quit."))
			{
				System.exit(0);
			}

			//Gets AI to place a bead
			else if(command.contains("get") && command.contains("move")
			&& command.contains("."))
			{
				//Checks if bead to be placed needs to be black
				if(command.contains("black"))
				{
					//need to integrate yet
					System.out.println("AI places black bead");
					int coord = game.chooseRandomPeg();
					x = coord / 10;
					y = coord % 10;
					game.placeTestBead(x, y, 1);
				}
				//Checks if bead to be placed needs to be white
				else if(command.contains("white"))
				{
					//need to integrate yet
					System.out.println("AI places white bead");
					int coord = game.chooseRandomPeg();
					x = coord / 10;
					y = coord % 10;
					game.placeTestBead(x, y, 0);
				}
				else
				{
					errorMessage();
				}
			}

			//Draws an easier to understand representation of the board
			else if(command.equalsIgnoreCase("draw board."))
			{
				int firstPegSpace = 4;
				int numRows = 0;
				for(y = 3; y >= 0; y--)
				{
					for(int z = 3; z >= 0; z--)
					{
						for(int i = 0; i < firstPegSpace; i++)
						{
							if(numRows < 4)
							{
								System.out.print("\t");
							}
						}

						for(x = 0; x < 4; x++)
						{
							int slotValue = game.checkCoordinateArray(x, y, z);

							if(slotValue == 0)
							{
								System.out.print("W");
							}
							else if(slotValue == 1)
							{
								System.out.print("B");
							}
							else
							{
								System.out.print("|");
							}
							System.out.print("\t\t");
						}
						System.out.println();
					}
					firstPegSpace -= 1;
					numRows++;
					System.out.println();
				}
			}

			//Exits the loop
			else if(command.equalsIgnoreCase("go interactive.") ||
			command.equalsIgnoreCase("go gui."))
			{
				return;
			}

			//Displays error message
			else
			{
				errorMessage();
			}
			command = input.nextLine();
		}
		GameDisplay display = new GameDisplay(game);

	}

	/**
	* PURPOSE:	Prints out an error message, incorrect input
	*
	* @param	none
	*
	* @return	none
	*/
	private void errorMessage()
	{
		System.out.println("Error command not valid. Please enter a valid command.");
	}

}
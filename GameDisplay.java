import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.PaintEvent;
/**
 * Creates a GUI to display to the user what is happening in-game to user and
 *		allows the player to interact with the game
 *
 * @author Tyson Ditner
 * @version 1
 * Student number: 230 147 201
 */

@SuppressWarnings("serial")
public class GameDisplay extends JFrame
{
    private GameComponent game;
    private BoardComponent board;
    private JButton[] pegButton;

    public GameDisplay(GameComponent gameStart)
    {
        //gets GameComponent
        this.game = gameStart;

        //Name of first player
        String player1 = game.getPlayer1Name();

        //Name of second player
        String player2 = game.getPlayer2Name();

        //Message for part of the player turn panel
        String playerTurnMessage = "Player Turn: ";

        //Sets 'colour' message
        String colourMessage = "Colour: ";

        //Sets 'win' message
        String winMessage = "Win Count: ";

        //Sets 'Player Type' message
        String playerTypeMessage = "Player Type: ";

        //Gets first player's colour
        int intFirstColour = game.getPlayer1Colour();
        String strFirstColour = null;
        if (intFirstColour == 0)
        {
            strFirstColour = "White";
        }
        if (intFirstColour == 1)
        {
            strFirstColour = "Black";
        }
        System.out.println(intFirstColour);

        //Gets second player's colour
        int intSecondColour = game.getPlayer2Colour();
        String strSecondColour = null;
        if (intSecondColour == 0)
        {
            strSecondColour = "White";
        }
        if (intSecondColour == 1)
        {
            strSecondColour = "Black";
        }

        //Gets first player's win count
        int playerOneWin = game.player1WinCount();
        String strPlayerOneWin = String.valueOf(playerOneWin);

        //Gets second player's win count
        int playerTwoWin = game.player2WinCount();
        String strPlayerTwoWin = String.valueOf(playerTwoWin);

        //Gets first player's player type (AI or Human)
        String playerOneType = game.getPlayer1Type();

        //Gets second player's player type (AI or Human)
        String playerTwoType = game.getPlayer2Type();

        //The name of the player who has current turn
        int intTurnName = game.getPlayerTurnNum();
        String strTurnName = null;
        if (intTurnName == 0)
        {
            strTurnName = player1;
        }
        if (intTurnName == 1)
        {
            strTurnName = player2;
        }

        //Create and define buttons
        JButton newGame = new JButton("New Game");
        JButton quit     = new JButton("Quit");
        newGame.setActionCommand("newGame");
        quit.setActionCommand("quit");

        /**
         * Implements an ActionListener and gives the buttons displayed on GUI a function
         *
         * @author Tyson Ditner
         */
        class scoreFourButtons implements ActionListener
        {
            /**
             * Allows the buttons to be clicked and prints the message
             *
             * @param ActionEvent event
             * @return none
             */
            public void actionPerformed(ActionEvent event)
            {
                String action = event.getActionCommand();

                if (action.equals("quit"))
                {
                    System.exit(0);
                }
                if (action.equals("newGame"))
                {
                    game.resetGame();
                    paint();
                }
            }
        }
        ActionListener scoreFourButtons = new scoreFourButtons();
        newGame.addActionListener(scoreFourButtons);
        quit.addActionListener(scoreFourButtons);

        //Create and define JTextArea panels
        JTextArea playerName1     =    new JTextArea();
        playerName1.setText(player1);
        playerName1.setEditable(false);

        JTextArea playerName2     =    new JTextArea();
        playerName2.setText(player2);
        playerName2.setEditable(false);

        JTextArea playerTurn    =    new JTextArea();
        playerTurn.setText(strTurnName);
        playerTurn.setEditable(false);

        JTextArea playerTurnName =    new    JTextArea();
        playerTurnName.setText(playerTurnMessage);
        playerTurnName.setEditable(false);

        JTextArea colourMessageOne = new JTextArea();
        colourMessageOne.setText(colourMessage);
        colourMessageOne.setEditable(false);

        JTextArea colourMessageTwo = new JTextArea();
        colourMessageTwo.setText(colourMessage);
        colourMessageTwo.setEditable(false);

        JTextArea firstPlayerType = new JTextArea();
        firstPlayerType.setText(playerOneType);
        firstPlayerType.setEditable(false);

        JTextArea secondPlayerType = new JTextArea();
        secondPlayerType.setText(playerTwoType);
        secondPlayerType.setEditable(false);

        JTextArea firstPlayerTypeMessage = new JTextArea();
        firstPlayerTypeMessage.setText(playerTypeMessage);
        firstPlayerTypeMessage.setEditable(false);

        JTextArea secondPlayerTypeMessage = new JTextArea();
        secondPlayerTypeMessage.setText(playerTypeMessage);
        secondPlayerTypeMessage.setEditable(false);

        JTextArea firstPlayerColour = new JTextArea();
        firstPlayerColour.setText(strFirstColour);
        firstPlayerColour.setEditable(false);

        JTextArea secondPlayerColour = new JTextArea();
        secondPlayerColour.setText(strSecondColour);
        secondPlayerColour.setEditable(false);

        //Create new panels
        JSplitPane main         =     new JSplitPane();
        JPanel playerInterface     =     new JPanel();
        JPanel gameView         =     new JPanel();
        JPanel turnPanel        =    new JPanel();
        JPanel buttons             =    new JPanel();

        //Place panels
        buttons.add(newGame);
        buttons.add(quit);

        turnPanel.add(playerTurnName);
        turnPanel.add(playerTurn);

        playerInterface.setLayout(new BoxLayout(playerInterface, BoxLayout.Y_AXIS));
        playerInterface.add(turnPanel);

        playerInterface.add(playerName1);
        playerInterface.add(colourMessageOne);
        playerInterface.add(firstPlayerColour);
        playerInterface.add(firstPlayerTypeMessage);
        playerInterface.add(firstPlayerType);

        playerInterface.add(playerName2);
        playerInterface.add(colourMessageTwo);
        playerInterface.add(secondPlayerColour);
        playerInterface.add(secondPlayerTypeMessage);
        playerInterface.add(secondPlayerType);

        playerInterface.add(buttons);
        board = new BoardComponent(game);

		JFrame frame = new JFrame("Score Four");

		pegButton = new JButton[16];
		pegButton[0] = new JButton("A1");
		pegButton[0].setActionCommand("A1");
		pegButton[1] = new JButton("B1");
		pegButton[1].setActionCommand("B1");
		pegButton[2] = new JButton("C1");
		pegButton[2].setActionCommand("C1");
		pegButton[3] = new JButton("D1");
		pegButton[3].setActionCommand("D1");
		pegButton[4] = new JButton("A2");
		pegButton[4].setActionCommand("A2");
		pegButton[5] = new JButton("B2");
		pegButton[5].setActionCommand("B2");
		pegButton[6] = new JButton("C2");
		pegButton[6].setActionCommand("C2");
		pegButton[7] = new JButton("D2");
		pegButton[7].setActionCommand("D2");
		pegButton[8] = new JButton("A3");
		pegButton[8].setActionCommand("A3");
		pegButton[9] = new JButton("B3");
		pegButton[9].setActionCommand("B3");
		pegButton[10] = new JButton("C3");
		pegButton[10].setActionCommand("C3");
		pegButton[11] = new JButton("D3");
		pegButton[11].setActionCommand("D3");
		pegButton[12] = new JButton("A4");
		pegButton[12].setActionCommand("A4");
		pegButton[13] = new JButton("B4");
		pegButton[13].setActionCommand("B4");
		pegButton[14] = new JButton("C4");
		pegButton[14].setActionCommand("C4");
		pegButton[15] = new JButton("D4");
		pegButton[15].setActionCommand("D4");
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(16, 1));
		for(int i = 0; i < 16; i++)
		{
			buttonPanel.add(pegButton[i]);
		}

		class pegButtons implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				String action = event.getActionCommand();

				if (action.equals("A1"))
				{
					game.placeBead(0,0);
					paint();
				}
				if (action.equals("B1"))
				{
					game.placeBead(1,0);
					paint();
				}
				if (action.equals("C1"))
				{
					game.placeBead(2,0);
					paint();
				}
				if (action.equals("D1"))
				{
					game.placeBead(3,0);
					paint();
				}
				if (action.equals("A2"))
				{
					game.placeBead(0,1);
					paint();
				}
				if (action.equals("B2"))
				{
					game.placeBead(1,1);
					paint();
				}
				if (action.equals("C2"))
				{
					game.placeBead(2,1);
					paint();
				}
				if (action.equals("D2"))
				{
					game.placeBead(3,1);
					paint();
				}
				if (action.equals("A3"))
				{
					game.placeBead(0,2);
					paint();
				}
				if (action.equals("B3"))
				{
					game.placeBead(1,2);
					paint();
				}
				if (action.equals("C3"))
				{
					game.placeBead(2,2);
					paint();
				}
				if (action.equals("D3"))
				{
					game.placeBead(3,2);
					paint();
				}
				if (action.equals("A4"))
				{
					game.placeBead(0,3);
					paint();
				}
				if (action.equals("B4"))
				{
					game.placeBead(1,3);
					paint();
				}
				if (action.equals("C4"))
				{
					game.placeBead(2,3);
					paint();
				}
				if (action.equals("D4"))
				{
					game.placeBead(3,3);
					paint();
				}
            }
		}

		ActionListener pegButtons = new pegButtons();
		for(int i = 0; i < 16; i++)
		{
			pegButton[i].addActionListener(pegButtons);
		}


		gameView.add(buttonPanel);
        gameView.add(board);

        main.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        main.setDividerLocation(200);
        main.setLeftComponent(playerInterface);
        main.setRightComponent(gameView);
        main.setEnabled(false);
        pack();

        //Create and set up a frame

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1200);
        frame.add(main);
    }

    public void paint()
    {
		System.out.println("paint");
		System.out.println(board.getClass());
		board.repaint();
	}
}

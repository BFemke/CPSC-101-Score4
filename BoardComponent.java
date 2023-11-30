/**
* This file is part of a solution to
*     CPSC 101 Team Project, January 2020.
*
* 	Creates the graphic components for game board
*
* @author Barbara Friesen
* Student Number: 230142078
* @version 1
*/
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*;

public class BoardComponent extends Canvas
{
	private GameComponent game;
	private JButton[] pegButton;

	public BoardComponent(GameComponent newGame)
	{
		game = newGame;

		setSize(840, 756);
		setBackground(new Color(192, 192, 192));
	}
    public void paint(Graphics g)
    {
       Graphics2D g2 = (Graphics2D)g;
       super.paint(g);

		//Creates brown board
       	g2.setColor(new Color(153, 102, 51));
       	int[] polyXTop = {28, 280, 784, 588};
        int[] polyYTop = {658, 168, 168, 658};
       	Polygon gameBoardTop = new Polygon(polyXTop, polyYTop, 4);
       	g2.fill(gameBoardTop);
       	g2.setColor(new Color(114, 77, 44));
       	int[] polyXSide = {784, 784, 588, 588};
       	int[] polyYSide = {168, 196, 686, 658};
       	Polygon gameBoardSide = new Polygon(polyXSide, polyYSide, 4);
       	g2.fill(gameBoardSide);
       	g2.setColor(new Color(129, 88, 51));
       	Rectangle gameBoardFront = new Rectangle(28, 658, 560, 28);
       	g2.fill(gameBoardFront);

		//draws the pegs
       	int xOrigin = 98;
		int yOrigin = 602;
       	for(int x = 0; x < 4; x++)
       	{
			int lineX = xOrigin;
			int lineY = yOrigin;
			for(int y = 0; y < 4; y++)
			{
				g2.setColor(Color.DARK_GRAY);
				g2.setStroke(new BasicStroke(10));
				g2.draw(new Line2D.Double(lineX, lineY, lineX, lineY-112));
				lineX = lineX + 140;
			}
			xOrigin = xOrigin + 70;
			yOrigin = yOrigin - 140;
		}

		//Draws beads on pegs based on array
		xOrigin = 84;
		yOrigin = 574;
		for(int y = 0; y < 4; y++)
		{
			int rowX = xOrigin;
			int rowY = yOrigin;
			for(int x = 0; x < 4; x++)
			{
				int heightX = rowX;
				int heightY = rowY;
				for(int z = 0; z < 4; z++)
				{
					int value = game.checkCoordinateArray(x, y, z);
					//draws white beads
					if(value == 0)
					{
						g2.setColor(Color.WHITE);
						g2.fill(new Ellipse2D.Double(heightX, heightY, 28, 28));
						heightY = heightY - 28;
					}
					//draws black beads
					else if(value == 1)
					{
						g2.setColor(Color.BLACK);
						g2.fill(new Ellipse2D.Double(heightX, heightY, 28, 28));
						heightY = heightY - 28;
					}
					//if there are no beads in that location
					else
					{
						heightY = heightY - 28;
					}
					value ++;
				}
				rowX = rowX + 140;
			}
			xOrigin = xOrigin + 70;
			yOrigin = yOrigin - 140;
		}

		//draws labels fro each peg
		String[] pegLabel = {"A1", "B1", "C1", "D1", "A2", "B2", "C2", "D2",
		"A3", "B3", "C3", "D3", "A4", "B4", "C4", "D4"};
		xOrigin = 84;
		yOrigin = 602;
		int pegLabelNum = 0;
		for(int y = 0; y < 4; y++)
		{
			int rowX = xOrigin;
			int rowY = yOrigin;
			for(int x = 0; x < 4; x++)
			{
				g2.setColor(Color.DARK_GRAY);
				g2.fill(new Rectangle(rowX, rowY, 28, 28));
				g2.setColor(new Color(224,224,224));
				g2.drawString(pegLabel[pegLabelNum], rowX + 6, rowY + 16);
				pegLabelNum++;
				rowX = rowX + 140;
			}
			xOrigin = xOrigin + 70;
			yOrigin = yOrigin - 140;
		}

    }
}


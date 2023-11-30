/**
* This file is part of a solution to
*     CPSC 101 Team Project, January 2020.
*
* 	Creates Person computer to play score 4 game
*
* @author Barbara Friesen
* Student Number: 230142078
* @version 1
*/

import java.security.SecureRandom;
import java.awt.Color;

public class Computer extends Player
{
	private static String[] AIName = {"Anakin", "Leia", "Palpatine",
	"Chewbacca", "Obi-Wan", "Yoda", "R2-D2", "C3PO",
	"Luke", "Han", "Vader", "Jabba", "Greedo"};
	private SecureRandom random = new SecureRandom();
	private int playerNum;
	private String name;
	private int numWin =0;
	private Color colour;
	private String playerType;

	public Computer()
	{
		name = AIName[random.nextInt(AIName.length)];
		playerType = "AI";
		super.setName(name);
	}

}
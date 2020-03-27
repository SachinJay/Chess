package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import chess.Constants;
import game.Game;

public class StatusPanel extends JPanel
{
	private JPanel gameStatusPanel;
	private JPanel turnPanel;	
	
	private JLabel gameStatus;
	private JLabel turn;
	
	public StatusPanel()
	{
		super(new BorderLayout());
		setBackground(Constants.STATUS_COLOR);
		setBorder(Constants.BOARD_BORDER);
		
		gameStatusPanel = new JPanel();
		turnPanel = new JPanel();
		
		//Game status panel has white background and black border, just like the entire panel, just like 
		//the turn panel
		gameStatusPanel.setBackground(Constants.STATUS_COLOR);
		gameStatusPanel.setBorder(Constants.BOARD_BORDER);
		
		turnPanel.setBackground(Constants.STATUS_COLOR);
		turnPanel.setBorder(Constants.BOARD_BORDER);
		
		//I want the game status (check, checkmate, etc) to be above whose turn it is
		this.add(gameStatusPanel, BorderLayout.NORTH);
		this.add(turnPanel, BorderLayout.SOUTH);
		
		setPreferredSize(Constants.STATUS_DIM);
	}
	
	public void redraw(Game game)
	{
		gameStatusPanel.removeAll();
		turnPanel.removeAll();
		
		
		
		
		validate();
	}

}

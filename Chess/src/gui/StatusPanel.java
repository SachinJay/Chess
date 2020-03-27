package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import chess.Constants;
import game.Game;
import game.Player;

public class StatusPanel extends JPanel
{
	private JPanel gameStatusPanel;
	private JPanel turnPanel;	
	
	private JLabel gameStatus;
	private JLabel turn;
	
	public StatusPanel(Game game)
	{
		super(new BorderLayout());
		
		setBackground(Constants.STATUS_COLOR);
		setBorder(Constants.BOARD_BORDER);
		
		gameStatusPanel = new JPanel();
		turnPanel = new JPanel();
		
		gameStatus = new JLabel();
		turn = new JLabel();
		
		//Game status panel has white background and black border, just like the entire panel, just like 
		//the turn panel
		gameStatusPanel.setBackground(Constants.STATUS_COLOR);
		turnPanel.setBackground(Constants.STATUS_COLOR);
		
		addToPanel(game);
		setPreferredSize(Constants.STATUS_DIM);
	}
	
	/**
	 * Used to update the status
	 * @param game game that is being played
	 */
	public void redraw(Game game)
	{
		gameStatusPanel.removeAll();
		turnPanel.removeAll();
		
		addToPanel(game);
		validate();
	}
	
	/**
	 * Function for parsing and setting up text to be displayed
	 * @param game
	 */
	private void setText(Game game)
	{
		gameStatusPanel.removeAll();
		turnPanel.removeAll();
		
		gameStatus.setText("The game is in "+game.getStatus().makeString());
		Player curPlayer = game.getTurn();
		turn.setText("It is " + curPlayer.getName()+"'s "+"turn ("+curPlayer.getSide().toString()+")");
		
		
		turn.setFont(Constants.STATUS_FONT);
		gameStatus.setFont(Constants.STATUS_FONT);
		
	}
	
	/**
	 * Adds the two sub status panels to the main status panel
	 */
	private void addToSubPanels()
	{
		gameStatusPanel.add(gameStatus);
		turnPanel.add(turn);
		
		//I want the game status (check, checkmate, etc) to be above whose turn it is
		this.add(gameStatusPanel,BorderLayout.NORTH);
		this.add(turnPanel, BorderLayout.SOUTH);		
	}
	
	/**
	 * sets text and then adds it
	 * @param game
	 */
	private void addToPanel(Game game)
	{
		setText(game);
		addToSubPanels();
	}

}

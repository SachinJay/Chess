package gui;
import chess.Constants;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import board.Board;
import board.Position;

public class Grid
{
	private final JFrame gameFrame;
	private final BoardPanel boardPanel;
	
	public Grid()
	{
		this.gameFrame = new JFrame("Chess Frame");
		this.gameFrame.setLayout(new BorderLayout());
		final JMenuBar menuBar= new JMenuBar();
		addToMenuBar(menuBar);
		this.gameFrame.setJMenuBar(menuBar);
		
		
		this.gameFrame.setSize(Constants.FRAME_DIM);
		this.boardPanel = new BoardPanel();
		this.gameFrame.add(this.boardPanel,BorderLayout.CENTER);
		this.gameFrame.setVisible(true);
	}

	private void addToMenuBar(JMenuBar menuBar)
	{
		menuBar.add(createFileMenu());
	}

	private JMenu createFileMenu()
	{
		final JMenu fileMenu = new JMenu("File");
		final JMenuItem item1 = new JMenuItem("Press me to see what I do");
		item1.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("This is what I do!");
			}
		});
		
		final JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
				
			}
		});
		
		fileMenu.add(item1);
		fileMenu.add(exit);
		return fileMenu;
	}
	
	private class BoardPanel extends JPanel
	{
		final List<SquarePanel> boardSquares;
		
		BoardPanel()
		{
			super(new GridLayout(Constants.MAX_POS,Constants.MAX_POS));
			boardSquares = new ArrayList<>();
			for(int r = 1; r <= Constants.MAX_POS; r++)
			{
				for(int c = 1; c <= Constants.MAX_POS; c++)
				{
					String position = Position.posToStr(c) + r;
					SquarePanel sp = new SquarePanel(this,position);
					boardSquares.add(sp);
					add(sp);
				}
			}
			
			setPreferredSize(Constants.BOARD_DIM);
			validate();
		}
	}
	
	private class SquarePanel extends JPanel
	{
		private String pos;
		
		SquarePanel(BoardPanel bp, String pos)
		{
			super(new GridBagLayout());
			this.pos =pos; 
			setPreferredSize(Constants.SQUARE_DIM);
			assignSquareColor();
			validate();
		}
		
//		private void assignSquarePiece(Board board)
//		{
//			this.removeAll();
//			if(!board.getSquare(this.pos).isEmpty())
//			{
//				
//			}
//		}

		private void assignSquareColor()
		{
			int col = Position.stringToPos(this.pos).getCol();
			int row = Position.stringToPos(this.pos).getRow();
			
			if(row % 2 == 0)
			{
				setBackground(col %2 == 0? Constants.COL_LIGHT : Constants.COL_DARK);
			}
			else
			{
				setBackground(col %2 == 0? Constants.COL_DARK : Constants.COL_LIGHT);
			}
		}
	}
	
}

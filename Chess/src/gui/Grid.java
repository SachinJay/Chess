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
			for(int i = 0; i < Constants.NUM_SQUARES; i++)
			{
				SquarePanel sp = new SquarePanel(this,i);
				boardSquares.add(sp);
				add(sp);
			}
			
			setPreferredSize(Constants.BOARD_DIM);
			validate();
		}
	}
	
	private class SquarePanel extends JPanel
	{
		private int id;
		
		SquarePanel(BoardPanel bp, int id)
		{
			super(new GridBagLayout());
			this.id = id; 
			setPreferredSize(Constants.SQUARE_DIM);
			assignSquareColor();
			validate();
		}

		private void assignSquareColor()
		{
			if(isInOddRow(id))
			{
				setBackground(this.id %2 == 0? Constants.COL_LIGHT : Constants.COL_DARK);
			}
			else
			{
				setBackground(this.id %2 == 0? Constants.COL_DARK : Constants.COL_LIGHT);
			}
		}

		private boolean isInOddRow(int id2)
		{
			return (id2>=0 && id2<=7)||(id2>=16 && id2<=23)||(id2>=32 && id2<=39)
					||(id2>=48 && id2<=55);
		}
	}
	
}

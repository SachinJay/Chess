package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import chess.Constants;
import pieces.Piece;

public class CapturedPanel extends JPanel
{
	private JPanel whitePanel;
	private JPanel blackPanel;
	
	public CapturedPanel()
	{
		super(new BorderLayout());
		//TODO put in constants (baby blue)
		setBackground(Constants.BABY_BLUE);
		setBorder(Constants.BORDER);
		
		//8x2 because a) we want it to be vertically long and b) you can only take 16 pieces from a player		
		whitePanel = new JPanel(new GridLayout(8,2));
		blackPanel = new JPanel(new GridLayout(8,2));
		
		whitePanel.setBackground(Constants.BABY_BLUE);
		blackPanel.setBackground(Constants.BABY_BLUE);
		
		this.add(whitePanel, BorderLayout.NORTH);
		this.add(blackPanel, BorderLayout.SOUTH);
		
		setPreferredSize(Constants.TAKEN_DIM);
	}
	
	public void redo(ArrayList<Piece> blackTaken, ArrayList<Piece> whiteTaken)
	{
		blackPanel.removeAll();
		whitePanel.removeAll();
		
		Collections.sort(blackTaken, new Comparator<Piece>()
		{

			@Override
			public int compare(Piece o1, Piece o2)
			{
				// TODO Auto-generated method stub
				return Integer.compare(o1.getPieceValue(), o2.getPieceValue());
			}
		});
		
	}
}

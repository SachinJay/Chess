package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
		
		Collections.sort(blackTaken);
		Collections.sort(whiteTaken);
		
		for(Piece piece: blackTaken)
		{
			//Add path to icon
			File file = new File(Constants.IMAGES_PATH + piece.toString().replaceAll("\\s", "") + Constants.IMG_SUFFIX);
			BufferedImage img;
			try
			{
				img = ImageIO.read(file);
				ImageIcon icon = new ImageIcon(img);
				JLabel imgLabel = new JLabel(icon);
				blackPanel.add(imgLabel);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		for(Piece piece: whiteTaken)
		{
			//Add path to icon
			File file = new File(Constants.IMAGES_PATH + piece.toString().replaceAll("\\s", "") + Constants.IMG_SUFFIX);
			BufferedImage img;
			try
			{
				img = ImageIO.read(file);
				ImageIcon icon = new ImageIcon(img);
				JLabel imgLabel = new JLabel(icon);
				blackPanel.add(imgLabel);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		validate();
	}
}

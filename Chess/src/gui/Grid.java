package gui;
import chess.Constants;
import game.Game;
import game.Player;
import pieces.Piece;
import pieces.Side;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import board.Board;
import board.Position;
import board.Square;

public class Grid
{
	private JFrame gameFrame;
	private BoardPanel boardPanel;
	private Board chessBoard;
	private Square start;
	private Square end;
	private Piece curPiece;
	private Game game; 
	private Player p1;
	private Player p2;
	
	public Grid() throws IOException
	{
		p1 = new Player("Sachin", Side.WHITE);
		p2 = new Player("Beep",Side.BLACK);
		
		game = new Game(p1,p2);
		this.gameFrame = new JFrame("Chess Frame");
		this.gameFrame.setLayout(new BorderLayout());
		JMenuBar menuBar= new JMenuBar();
		addToMenuBar(menuBar);
		this.gameFrame.setJMenuBar(menuBar);
		this.chessBoard = new Board();
		
		
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
		JMenu fileMenu = new JMenu("File");
		JMenuItem item1 = new JMenuItem("Press me to see what I do");
		item1.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("This is what I do!");
			}
		});
		
		JMenuItem exit = new JMenuItem("Exit");
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
		List<SquarePanel> boardSquares;
		
		BoardPanel() throws IOException
		{
			super(new GridLayout(Constants.MAX_POS,Constants.MAX_POS));
			boardSquares = new ArrayList<>();
			for(int r = 8; r >= Constants.MIN_POS; r--)
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
		
		//Like repaint
		public void drawBoard(Board board) throws IOException
		{
			removeAll();
			for(SquarePanel sqr : boardSquares)
			{
				sqr.drawSquare(board);
				add(sqr);
			}
			
			validate();
			repaint();
		}
	}
	
	private class SquarePanel extends JPanel
	{
		private String pos;
		
		private void reset()
		{
			start = null; 
			end = null; 
			curPiece = null;
		}
		
		SquarePanel(BoardPanel bp, String pos) throws IOException
		{
			super(new GridBagLayout());
			this.pos =pos; 
			setPreferredSize(Constants.SQUARE_DIM);
			assignSquareColor();
			assignSquarePiecePic(chessBoard);
			
			addMouseListener(new MouseListener()
			{
				
				@Override
				public void mouseReleased(MouseEvent e)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e)
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e)
				{
					//Right click cancels any selections 
					if(SwingUtilities.isRightMouseButton(e))
					{
						reset();
					}
					else if(SwingUtilities.isLeftMouseButton(e))
					{
						//First click
						if(start == null)
						{
							start = chessBoard.getSquare(pos).isEmpty()? null: chessBoard.getSquare(pos);
							curPiece = start == null ? null : start.getPiece();
							if(curPiece != null)
							{
								if(!(curPiece.getSide() == game.getTurn().getSide()))
								{
									start = null; 
									curPiece = null;
								}
							}
						}
						//second click, should move after this
						else
						{
							end = chessBoard.getSquare(pos);
							Boolean changeTurn = curPiece.canMove(chessBoard, start, end);
							curPiece.move(chessBoard, start, end);
							//TODO add move to list of moves
							reset();
							if(changeTurn) game.changeTurn();
						}					
						
						SwingUtilities.invokeLater(new Runnable()
						{
							
							@Override
							public void run()
							{
								try
								{
									bp.drawBoard(chessBoard);
								} catch (IOException e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
						});
						
					}
					
				}
			});
			
			validate();
		}
		
		public void drawSquare(Board board) throws IOException
		{
			assignSquareColor();
			assignSquarePiecePic(board);
			markLegalSquares(board);
			validate();
			repaint();
		}
		
		private void assignSquarePiecePic(Board board) throws IOException
		{
			this.removeAll();
			if(!board.getSquare(this.pos).isEmpty())
			{
				Piece piece = board.getSquare(this.pos).getPiece();
				String fileName =
						Constants.IMAGES_PATH + piece.toString().replaceAll("\\s", "") + Constants.IMG_SUFFIX;
				File file = new File(fileName);
				BufferedImage img = ImageIO.read(file);
				add(new JLabel(new ImageIcon(img)));
			}
		}

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
		
		private void markLegalSquares(Board board)
		{
			//Replace true with a predicate relating to preferences in menu bar
			if(true)
			{
				File file = new File(Constants.IMAGES_PATH + "Dot.jpg");
				for(Square sqr : legalSquares(board))
				{
					String curPos = Position.posToStr(sqr.getPos().getCol()) + sqr.getPos().getRow();
					if(this.pos.equals(curPos))
					{
						try
						{
							add(new JLabel(new ImageIcon(ImageIO.read(file))));
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}

		/**
		 * Returns legal moves for the current player 
		 * @param board the board in question
		 * @return legal squares one can move to
		 */
		private ArrayList<Square> legalSquares(Board board)
		{
			if(curPiece != null && curPiece.getSide().equals(game.getTurn().getSide()))
			{
				return curPiece.legalMoves(board, start);
			}
			else return new ArrayList<>();
		}
	}
	
}

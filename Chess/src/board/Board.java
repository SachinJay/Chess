package board;

import chess.Constants;
import pieces.*;

public class Board
{
	private Square[][] board;
	private Square whiteKingSquare;
	private Square blackKingSquare;
	
	public Board()
	{
		this.reset();
	}
	
	public void reset()
	{
		board = new Square[Constants.MAX_POS][Constants.MAX_POS];
		//First white row
		board[0][0] = new Square(new Position(1,1), new Rook(Side.WHITE));
		board[0][1] = new Square(new Position(2,1), new Knight(Side.WHITE));
		board[0][2] = new Square(new Position(3,1), new Bishop(Side.WHITE));
		
		whiteKingSquare = new Square(new Position(5,1), new King(Side.WHITE));
		board[0][4] = whiteKingSquare;
		
		board[0][3] = new Square(new Position(4,1), new Queen(Side.WHITE));
		board[0][5] = new Square(new Position(6,1), new Bishop(Side.WHITE));
		board[0][6] = new Square(new Position(7,1), new Knight(Side.WHITE));
		board[0][7] = new Square(new Position(8,1), new Rook(Side.WHITE));
		
		//Second white row (all pawns)
		board[1][0] = new Square(new Position(1,2), new Pawn(Side.WHITE));
		board[1][1] = new Square(new Position(2,2), new Pawn(Side.WHITE));
		board[1][2] = new Square(new Position(3,2), new Pawn(Side.WHITE));
		board[1][3] = new Square(new Position(4,2), new Pawn(Side.WHITE));
		board[1][4] = new Square(new Position(5,2), new Pawn(Side.WHITE));
		board[1][5] = new Square(new Position(6,2), new Pawn(Side.WHITE));
		board[1][6] = new Square(new Position(7,2), new Pawn(Side.WHITE));
		board[1][7] = new Square(new Position(8,2), new Pawn(Side.WHITE));
		
		//First black row
		board[7][0] = new Square(new Position(1,8), new Rook(Side.BLACK));
		board[7][1] = new Square(new Position(2,8), new Knight(Side.BLACK));
		board[7][2] = new Square(new Position(3,8), new Bishop(Side.BLACK));
		
		blackKingSquare =new Square(new Position(5,8), new King(Side.BLACK)); 
		board[7][4] = blackKingSquare;
		
		board[7][3] = new Square(new Position(4,8), new Queen(Side.BLACK));
		board[7][5] = new Square(new Position(6,8), new Bishop(Side.BLACK));
		board[7][6] = new Square(new Position(7,8), new Knight(Side.BLACK));
		board[7][7] = new Square(new Position(8,8), new Rook(Side.BLACK));
		
		//Black pawns
		board[6][0] = new Square(new Position(1,7), new Pawn(Side.BLACK));
		board[6][1] = new Square(new Position(2,7), new Pawn(Side.BLACK));
		board[6][2] = new Square(new Position(3,7), new Pawn(Side.BLACK));
		board[6][3] = new Square(new Position(4,7), new Pawn(Side.BLACK));
		board[6][4] = new Square(new Position(5,7), new Pawn(Side.BLACK));
		board[6][5] = new Square(new Position(6,7), new Pawn(Side.BLACK));
		board[6][6] = new Square(new Position(7,7), new Pawn(Side.BLACK));
		board[6][7] = new Square(new Position(8,7), new Pawn(Side.BLACK));
		
		//Remaining squares have no pieces on them
		for(int r = 2; r <= 5; r++)
		{
			for(int c = 0; c <= 7; c++)
			{
				board[r][c] = new Square(new Position(c+1,r+1),null);
			}
		}
	}

	/**
	 * @return the board
	 */
	public Square[][] getBoard()
	{
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Square[][] board)
	{
		this.board = board;
	}
	
	/**
	 * Returns square at given position
	 * @param pos the position of the Square to be returned
	 * @return the square at position pos
	 */
	public Square getSquare(Position pos)
	{
		return board[pos.getRow()-1][pos.getCol()-1];
	}
	
	/**
	 * Returns square at given position
	 * @param pos the position of the Square to be returned, a string in char then int format
	 * ex. a1 is a valid position but 1a is not
	 * @return the square at position pos
	 */
	public Square getSquare(String pos)
	{
		return getSquare(Position.stringToPos(pos));
	}
	
	/**
	 * Sets specified square
	 * @param pos position of square 
	 * @param sqr the square we are specifying
	 */
	public void setSquare(String pos, Square sqr)
	{
		if(!pos.equals(sqr.getPos().toString()))
		{
			throw new IllegalArgumentException("Input position mismatches the position input for the square");			
		}
		Position position = Position.stringToPos(pos);
		board[position.getRow()-1][position.getCol()-1] = sqr;
	}
	
	/**
	 * Rudimentary print function that prints out the board so that I can check everything is good
	 */
	public void print()
	{
		for(int r = 7; r >= 0; r--)
		{
			for(int c = 0; c <= 7; c++)
			{
				Square curSquare = this.board[r][c]; 
				if(curSquare.isEmpty())
				{
					System.out.print("-" + " ");
				}
				else
				{
					Character letter = curSquare.getPiece().toString().split("\\s")[1].charAt(0);
					String let = letter.toString();
					if(curSquare.getPiece().getSide().equals(Side.BLACK))
					{
						System.out.print(let+" ");					
					}
					else
					{
						System.out.print(let.toLowerCase() + " ");
					}
				}
			}
			System.out.println();
		}
	}

	/**
	 * return's the square a king is on
	 * @param side what side king we want
	 * @return the square that the king resides on
	 */
	public Square getKingPos(Side side)
	{
		Square ret = side.equals(Side.BLACK) ? blackKingSquare : whiteKingSquare;
		return ret;
	}
	
	public void setWhiteKingSquare(Square square)
	{
		this.whiteKingSquare = square;
	}
	
	public void setBlackKingSquare(Square square)
	{
		this.blackKingSquare = square;
	}
}

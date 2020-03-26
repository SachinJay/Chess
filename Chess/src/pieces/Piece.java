/**
 * Abstract class that any chess piece should implement
 * @author Sachin Devasahayam
 * 
 *
 */
package pieces;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import board.Position;
import board.Square;

public abstract class Piece
{
	/**Which side the piece is on*/
	private Side side; 
	
	private String name; 
	
	public Piece(Side side)
	{
		this.setSide(side);
	}
	
	public abstract Boolean canMove(Board board, Square start, Square end);
	
	/**
	 * Moves piece from start to end
	 * @param board the current game board
	 * @param start the start square where the moving piece is
	 * @param end the end square where our piece will move to
	 * @return the captured piece, null if moving to empty square or if the move was invalid
	 */
	public Piece move(Board board, Square start, Square end)
	{
		if(this.canMove(board, start, end))
		{
			Piece p = end.isEmpty() ? null: end.getPiece();
			
			Piece curPiece = start.getPiece();
			end.setPiece(curPiece);
			start.setPiece(null);
			
			return p;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Returns all valid moves
	 * @param board the relevant board
	 * @param start the Square the current piece is located on
	 * @return all Squares such that this piece can move to that square
	 */
	public ArrayList<Square> legalMoves(Board board, Square start)
	{
		ArrayList<Square> validEndSquares = new ArrayList<>();
		Square[][] chessBoard = board.getBoard();
		
		for(Square[] sqrArr : chessBoard)
		{
			for(Square sqr : sqrArr)
			{
				if(this.canMove(board, start, sqr))
				{
					validEndSquares.add(sqr);
				}
			}
		}
		
		return validEndSquares;
	}

	/**
	 * @return the side
	 */
	public Side getSide()
	{
		return side;
	}

	/**
	 * @param side the side to set
	 */
	public void setSide(Side side)
	{
		this.side = side;
	}
	
	public String toString()
	{
		String side = this.getSide().toString();
		//Return name in format: Color Name instead of COLOR Name
		return side.charAt(0) + side.substring(1).toLowerCase() + " " + this.name;
	}
	
	public void setName(String name)
	{
		this.name = name; 
	}
	
}

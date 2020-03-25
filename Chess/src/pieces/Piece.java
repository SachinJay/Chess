/**
 * Abstract class that any chess piece should implement
 * @author Sachin Devasahayam
 * 
 *
 */
package pieces;

import board.Board;
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

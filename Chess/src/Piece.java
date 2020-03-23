/**
 * 
 * @author Sachin Devasahayam
 * Abstract class that any chess piece should implement
 *
 */
public abstract class Piece
{
	/**Which side the piece is on*/
	private Side side; 
	
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
	
}

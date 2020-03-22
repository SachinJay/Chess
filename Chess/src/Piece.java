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
		this.side = side;
	}
	
	public abstract Boolean canMove(Board board, Square start, Square end);
	
}

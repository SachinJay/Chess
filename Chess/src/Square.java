import java.util.Optional;

/**
 * 
 * @author Sachin Devasahayam
 * Class to represent a single square of the 64 square board
 *
 */
public class Square
{
	//A square has only two things, a position and a piece
	private Position pos; 
	private Optional<Piece> piece;
	
	/**
	 * Default constructor, creates the a1 square with no piece on it
	 */
	public Square()
	{
		pos = new Position();
		piece = Optional.ofNullable(null);
	}
	
	/**
	 * Creates a square at given position with given piece on it
	 * @param pos the position of the square
	 * @param piece the piece option that is on this square. Optional of null if the square is blank
	 */
	public Square(Position pos, Optional<Piece> piece)
	{
		this.pos = pos; 
		this.piece = piece;
	}
	
	/**
	 * 
	 * @return true if there is no piece on this square
	 */
	public boolean isEmpty()
	{
		return !this.piece.isPresent();
	}
	
	public void setPiece(Optional<Piece> piece)
	{
		this.piece = piece;
	}
	
	public Piece getPiece()
	{
		return this.piece.get();
	}
}

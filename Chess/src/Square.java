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
		setPos(new Position());
		piece = Optional.ofNullable(null);
	}
	
	/**
	 * Creates a square at given position with given piece on it
	 * @param pos the position of the square
	 * @param piece the piece option that is on this square. Optional of null if the square is blank
	 */
	public Square(Position pos, Piece piece)
	{
		this.setPos(pos); 
		this.piece = Optional.ofNullable(piece);
	}
	
	/**
	 * 
	 * @return true if there is no piece on this square
	 */
	public boolean isEmpty()
	{
		return !this.piece.isPresent();
	}
	
	/**
	 * 
	 * @param piece the Piece Option to be set 
	 */
	public void setPiece(Piece piece)
	{
		this.piece = Optional.ofNullable(piece);
	}
	
	
	/**
	 * 
	 * @return the piece on this square
	 * @throws NoSuchElementException if square is empty 
	 */
	public Piece getPiece()
	{
		return this.piece.get();
	}

	/**
	 * @return the pos
	 */
	public Position getPos()
	{
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(Position pos)
	{
		this.pos = pos;
	}
}

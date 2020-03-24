/**
 * Class to represent a Knight
 * @author Sachin Devasahayam
 *
 */
public class Knight extends Piece
{

	public Knight(Side side)
	{
		super(side);
		this.setName("Knight");
	}

	@Override
	public Boolean canMove(Board board, Square start, Square end)
	{
		int changeInX = Math.abs(start.getPos().getRow() - end.getPos().getRow() );
		int changeInY = Math.abs(start.getPos().getCol() - end.getPos().getCol() );
		
		//true iff the start and end position form a valid knight shaped 'L'
		Boolean isValidKnightMove = changeInX * changeInY == Constants.KNIGHT_PRODUCT;
		
		//Cannot put second part of disjunction into a variable because it could throw error
		//If it does throw an error though, it will not be reached because this will short circuit
		return isValidKnightMove && (end.isEmpty() || !end.getPiece().getSide().equals(this.getSide()));
	}
	
}

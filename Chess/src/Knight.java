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
	}

	@Override
	public Boolean canMove(Board board, Square start, Square end)
	{
		int changeInX = Math.abs(start.getPos().getRow() - end.getPos().getRow() );
		int changeInY = Math.abs(start.getPos().getCol() - end.getPos().getCol() );
		
		//true iff the start and end position form a valid knight shaped 'L'
		Boolean isValidKnightMove = changeInX * changeInY == Constants.KNIGHT_PRODUCT;
		
		//True if the end square has a piece of opposing side
		Boolean endHasEnemy = !end.getPiece().getSide().equals(this.getSide());
		
		return isValidKnightMove && (end.isEmpty() || endHasEnemy);
	}
	
}

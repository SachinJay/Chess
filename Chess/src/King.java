
public class King extends Piece
{

	public King(Side side)
	{
		super(side);
		this.setName("King");
	}

	@Override
	public Boolean canMove(Board board, Square start, Square end)
	{
		
		//Only valid if change in x and change in y are both less than or equal to 1
		int changeInX = Math.abs(start.getPos().getRow() - end.getPos().getRow() );
		int changeInY = Math.abs(start.getPos().getCol() - end.getPos().getCol() );
		
		Boolean isValid = changeInX <=1 && changeInY <= 1 && !(changeInX == 0 && changeInY == 0);
		
		return isValid && (end.isEmpty() || !end.getPiece().getSide().equals(this.getSide()));
	}

}

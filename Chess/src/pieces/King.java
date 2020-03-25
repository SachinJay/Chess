package pieces;

import board.Board;
import board.Square;

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
		int changeInR = Math.abs(start.getPos().getRow() - end.getPos().getRow() );
		int changeInC = Math.abs(start.getPos().getCol() - end.getPos().getCol() );
		
		Boolean isValid = changeInR <=1 && changeInC <= 1 && !(changeInR == 0 && changeInC == 0);
		
		return isValid && (end.isEmpty() || !end.getPiece().getSide().equals(this.getSide()));
	}

}

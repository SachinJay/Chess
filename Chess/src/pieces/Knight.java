/**
 * Class to represent a Knight
 * @author Sachin Devasahayam
 *
 */
package pieces;

import board.Board;
import board.Square;
import chess.Constants;

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
		int changeInR = Math.abs(start.getPos().getRow() - end.getPos().getRow() );
		int changeInC = Math.abs(start.getPos().getCol() - end.getPos().getCol() );
		
		//true iff the start and end position form a valid knight shaped 'L'
		Boolean isValidKnightMove = changeInR * changeInC == Constants.KNIGHT_PRODUCT;
		
		//Cannot put second part of disjunction into a variable because it could throw error
		//If it does throw an error though, it will not be reached because this will short circuit
		return isValidKnightMove && (end.isEmpty() || !end.getPiece().getSide().equals(this.getSide()));
	}

	@Override
	public int getPieceValue()
	{
		return Constants.KNIGHT_VAL;
	}
	
}

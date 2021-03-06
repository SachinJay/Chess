package pieces;

import board.Board;
import board.Square;
import chess.Constants;

public class Bishop extends Piece
{

	public Bishop(Side side)
	{
		super(side);
		this.setName("Bishop");
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean canMove(Board board, Square start, Square end)
	{
		return canMoveBishoply(board, start, end);		
	}

	@Override
	public int getPieceValue()
	{
		return Constants.BISHOP_VAL;
	}	
}

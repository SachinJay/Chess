package pieces;

import board.Board;
import board.Square;
import chess.Constants;

public class Rook extends Piece
{

	public Rook(Side side)
	{
		super(side);
		this.setName("Rook");
	}

	@Override
	public Boolean canMove(Board board, Square start, Square end)
	{
		return canMoveRookily(board, start, end);		
	}

	@Override
	public int getPieceValue()
	{
		return Constants.ROOK_VAL;
	}
}

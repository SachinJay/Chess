package pieces;

import board.Board;
import board.Position;
import board.Square;

public class Queen extends Piece
{

	public Queen(Side side)
	{
		super(side);
		this.setName("Queen");
	}

	@Override
	public Boolean canMove(Board board, Square start, Square end)
	{
		return canMoveBishoply(board, start, end) || canMoveRookily(board, start, end);
	}
}

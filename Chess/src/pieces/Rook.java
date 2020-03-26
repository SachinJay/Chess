package pieces;

import board.Board;
import board.Position;
import board.Square;

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
}

package pieces;

import board.Board;
import board.Position;
import board.Square;

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
	
}

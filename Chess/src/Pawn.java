/**
 * Class to represent a pawn
 * @author Sachin Devasahayam
 *
 */
public class Pawn extends Piece
{

	public Pawn(Side side)
	{
		super(side);
		this.setName("Pawn");
	}

	@Override
	public Boolean canMove(Board board, Square start, Square end)
	{
		// TODO Auto-generated method stub
		return null;
	}

}


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
		int changeInR = start.getPos().getRow() - end.getPos().getRow() ;
		int changeInC = start.getPos().getCol() - end.getPos().getCol() ;
		
//		Boolean rBackwards = 
		
		changeInR = Math.abs(changeInR);
		changeInC = Math.abs(changeInC);
		
		Boolean pathIsStraightLine = changeInR !=0 && changeInC != 0;
		
		if(!pathIsStraightLine)
		{
			return false;
		}
		else
		{
			//TODO perform check that no other pieces are in the way from start to end
			//Obviously, color of pieces does not matter for the inbetween pieces
			//What matters is that the in between pieces do not exist
			
			//Rook could be moving forwards or backwards in a row or column, need to specify
			
			
			
		}
	}

}

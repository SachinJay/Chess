
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
		//Start and end row numbers
		int rStart = start.getPos().getRow();
		int rEnd = end.getPos().getRow();
		
		//Start and end column numbers
		int cStart = start.getPos().getCol();
		int cEnd = end.getPos().getCol();
		
		int changeInR = rStart - rEnd;
		int changeInC = cStart - cEnd;
		
		Boolean rForwards = changeInR >= 0;
		Boolean cForwards = changeInC >= 0;
		
		//TODO do I ever need to use this absolute value? I don't think so, maybe delete this
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
			
			//Set whether we increment or decrement
			int rInc = rForwards? 1 : -1;
			int cInc = cForwards? 1 : -1;
			
			if(changeInR == 0)
			{
				for(int c = cStart + cInc; c < cEnd; c+=cInc)
				{
					Position curPos = new Position(c,rStart);
					if(!board.getSquare(curPos).isEmpty())
					{
						return false;
					}
				}
			}
			else
			{
				for(int r = rStart + rInc; r < rEnd; r+=rInc)
				{
					return false;
				}
			}
			
			if(end.isEmpty())
			{
				return true;
			}
			else
			{
				Boolean opSide = !(this.getSide().equals(end.getPiece().getSide()));
				return opSide;
			}
			
		}
	}

}

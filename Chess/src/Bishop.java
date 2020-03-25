
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
		Position startPos = start.getPos();
		int rStart = startPos.getRow();
		int cStart = startPos.getCol();
		
		Position endPos = end.getPos();
		int rEnd = endPos.getRow();
		int cEnd = endPos.getCol();
		
		int changeInR = rEnd-rStart;
		int changeInC = cEnd -cStart;
		
		Boolean pathIsDiagonal = Math.abs(changeInR) == Math.abs(changeInC);
		
		if(!pathIsDiagonal)
			{
				return false;
			}
		
		Boolean rForwards = changeInR >= 0;
		Boolean cForwards = changeInC >= 0;
		
		int rInc = rForwards? 1 : -1;
		int cInc = cForwards? 1 : -1;
		
		//TODO change from add/sub Inc's into just plus 1 because that's all this is
		int rBegIndex = rForwards? rStart + rInc : rEnd - rInc;
		int cBegIndex = cForwards? cStart + cInc: cEnd - cInc;
		
		int rBound = rForwards? rEnd : rStart;
		int cBound = cForwards? cEnd : cStart;
		
		//Check that there is nothing on the path
		for(int r = rBegIndex; r < rBound; r++)
		{
			for(int c = cBegIndex; c < cBound; c++)
			{
				//I.e. if along the diagonal
				if(Math.abs(r-rBegIndex) == Math.abs(c-cBegIndex))
				{
					Position curPos = new Position(c,r);
					if(!board.getSquare(curPos).isEmpty())
					{
						return false;
					}
				}
			}
		}
		
		if(end.isEmpty())
		{
			return true;
		}
		else
		{
			return end.getPiece().getSide() != this.getSide();
		}
	}
	
}

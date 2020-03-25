
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
		
		int changeInR = rStart-rEnd;
		int changeInC = cStart - cEnd;
		
		Boolean pathIsDiagonal = Math.abs(changeInR) == Math.abs(changeInC);
		
		Boolean rForwards = changeInR >= 0;
		Boolean cForwards = changeInC >= 0;
		
		int rInc = rForwards? 1 : -1;
		int cInc = cForwards? 1 : -1;
		
		//Check that there is nothing on the path
		for(int r = rStart + rInc; r < rEnd; r += rInc)
		{
			for(int c = cStart + cInc; c < cEnd; c +=cInc)
			{
				//I.e. if along the diagonal
				if(Math.abs(r-rStart) == Math.abs(c-cStart))
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

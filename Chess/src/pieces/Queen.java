package pieces;
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

	private Boolean canMoveBishoply(Board board, Square start, Square end)
	{
		Position startPos = start.getPos();
		int rStart = startPos.getRow();
		int cStart = startPos.getCol();

		Position endPos = end.getPos();
		int rEnd = endPos.getRow();
		int cEnd = endPos.getCol();

		int changeInR = rEnd - rStart;
		int changeInC = cEnd - cStart;

		Boolean pathIsDiagonal = Math.abs(changeInR) == Math.abs(changeInC);

		if (!pathIsDiagonal)
		{
			return false;
		}

		Boolean rForwards = changeInR >= 0;
		Boolean cForwards = changeInC >= 0;

		int rInc = rForwards ? 1 : -1;
		int cInc = cForwards ? 1 : -1;

		// TODO change from add/sub Inc's into just plus 1 because that's all this is
		int rBegIndex = rForwards ? rStart + rInc : rEnd - rInc;
		int cBegIndex = cForwards ? cStart + cInc : cEnd - cInc;

		int rBound = rForwards ? rEnd : rStart;
		int cBound = cForwards ? cEnd : cStart;

		// Check that there is nothing on the path
		for (int r = rBegIndex; r < rBound; r++)
		{
			for (int c = cBegIndex; c < cBound; c++)
			{
				// I.e. if along the diagonal
				if (Math.abs(r - rBegIndex) == Math.abs(c - cBegIndex))
				{
					Position curPos = new Position(c, r);
					if (!board.getSquare(curPos).isEmpty())
					{
						return false;
					}
				}
			}
		}

		if (end.isEmpty())
		{
			return true;
		} else
		{
			return end.getPiece().getSide() != this.getSide();
		}
	}

	private Boolean canMoveRookily(Board board, Square start, Square end)
	{
		// Start and end row numbers
		int rStart = start.getPos().getRow();
		int rEnd = end.getPos().getRow();

		// Start and end column numbers
		int cStart = start.getPos().getCol();
		int cEnd = end.getPos().getCol();

		int changeInR = rEnd - rStart;
		int changeInC = cEnd - cStart;

		Boolean rForwards = changeInR >= 0;
		Boolean cForwards = changeInC >= 0;

		Boolean pathIsStraightLine = changeInR == 0 || changeInC == 0;

		if (!pathIsStraightLine || start.equals(end))
		{
			return false;
		} else
		{
			// Perform check that no other pieces are in the way from start to end
			// Obviously, color of pieces does not matter for the inbetween pieces
			// What matters is that the in between pieces do not exist

			// Rook could be moving forwards or backwards in a row or column, need to
			// specify
			// Set whether we increment or decrement
			int rInc = rForwards ? 1 : -1;
			int cInc = cForwards ? 1 : -1;

			if (changeInR == 0)
			{
				for (int c = cStart + cInc; c < cEnd; c += cInc)
				{
					Position curPos = new Position(c, rStart);
					if (!board.getSquare(curPos).isEmpty())
					{
						return false;
					}
				}
			} else
			{
				for (int r = (rStart + rInc); r < rEnd; r += rInc)
				{
					Position curPos = new Position(cStart, r);
					if (!board.getSquare(curPos).isEmpty())
					{
						return false;
					}
				}
			}

			if (end.isEmpty())
			{
				return true;
			} else
			{
				Boolean opSide = !(this.getSide().equals(end.getPiece().getSide()));
				return opSide;
			}

		}
	}
}

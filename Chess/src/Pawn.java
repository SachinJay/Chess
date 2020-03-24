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
		//A few options
		//If you're on the initial line, you can move two or one spaces forward
		//Not, you can only move one space forward
		//The above rules only apply if the spaces are empty
		//The only way to move to a non-empty space is if it is occupied by an enemy and is
		//diagonal to the pawn
		
		int startRow = start.getPos().getRow();
		int startCol = start.getPos().getCol();
		
		int endRow = end.getPos().getRow();
		int endCol = end.getPos().getCol();
		
		Piece endPiece = end.getPiece();
		Side endSide = endPiece.equals(null)? null:endPiece.getSide();
		
		Side side = this.getSide();
		

		Boolean straightMove = endCol == startCol;
		Boolean whiteMoveOne = endRow-startRow == 1 && straightMove;
		Boolean whiteMoveTwo = endRow-startRow == 2 && straightMove;
		
		Boolean blackMoveOne = endRow-startRow == -1 && straightMove;
		Boolean blackMoveTwo = endRow-startRow == -2 && straightMove;
		
		Boolean diagonalMove = Math.abs(endCol-startCol) == 1;
		Boolean whiteMoveDiagonal = endRow-startRow == 1 && diagonalMove;
		Boolean blackMoveDiagonal = endRow-startRow == -1 && diagonalMove;
		
		
		if(side.equals(Side.WHITE))
		{
			if(startRow == Constants.WHITE_PAWN_START)
			{
				return (whiteMoveOne && end.isEmpty()) || (whiteMoveTwo && end.isEmpty()) 
						||(whiteMoveDiagonal && !end.isEmpty() && endSide.equals(Side.BLACK));
			}
			else return (whiteMoveTwo && end.isEmpty()) 
					||(whiteMoveDiagonal && !end.isEmpty() && endSide.equals(Side.BLACK));
		}
		else
		{
			if(start.getPos().getRow() == Constants.BLACK_PAWN_START)
			{
				return (blackMoveOne && end.isEmpty()) || (blackMoveTwo && end.isEmpty()) 
				||(blackMoveDiagonal && !end.isEmpty() && endSide.equals(Side.WHITE));
			}
			else return (blackMoveTwo && end.isEmpty()) 
					||(blackMoveDiagonal && !end.isEmpty() && endSide.equals(Side.WHITE));
		}
		
	}

}

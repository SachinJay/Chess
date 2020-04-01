/**
 * Abstract class that any chess piece should implement
 * @author Sachin Devasahayam
 * 
 *
 */
package pieces;

import java.util.ArrayList;

import board.Board;
import board.Position;
import board.Square;
import chess.Constants;

public abstract class Piece implements Comparable<Piece>
{
	/** Which side the piece is on */
	private Side side;

	private String name;

	public Piece(Side side)
	{
		this.setSide(side);
	}

	public abstract Boolean canMove(Board board, Square start, Square end);

	/**
	 * Moves piece from start to end
	 * 
	 * @param board
	 *            the current game board
	 * @param start
	 *            the start square where the moving piece is
	 * @param end
	 *            the end square where our piece will move to
	 * @return the captured piece, null if moving to empty square or if the move was
	 *         invalid
	 */
	public Piece move(Board board, Square start, Square end)
	{
		if (this.canMove(board, start, end))
		{
			Piece p = end.isEmpty() ? null : end.getPiece();

			Piece curPiece = start.getPiece();
			end.setPiece(curPiece);
			start.setPiece(null);
			
			//If this piece is a king, update location in board
			if(curPiece.getPieceValue() == Constants.KING_VAL)
			{
				if(curPiece.getSide().equals(Side.WHITE))
				{
					board.setWhiteKingSquare(end);
				}
				else 
				{
					board.setBlackKingSquare(end);
				}
			}

			return p;
		} else
		{
			return null;
		}
	}
	
	/**
	 * Moves piece regardless of legality, used to reverse a piece movement
	 * @param board
	 * @param start
	 * @param end
	 * @return the piece that was taken, or null if moved to empty space
	 */
	public Piece overrideMove(Board board, Square start, Square end)
	{
		
			Piece p = end.isEmpty() ? null : end.getPiece();

			Piece curPiece = start.getPiece();
			end.setPiece(curPiece);
			start.setPiece(null);
			
			//If this piece is a king, update location in board
			if(curPiece.getPieceValue() == Constants.KING_VAL)
			{
				if(curPiece.getSide().equals(Side.WHITE))
				{
					board.setWhiteKingSquare(end);
				}
				else 
				{
					board.setBlackKingSquare(end);
				}
			}

			return p;	
	}

	/**
	 * Returns all valid moves
	 * 
	 * @param board
	 *            the relevant board
	 * @param start
	 *            the Square the current piece is located on
	 * @return all Squares such that this piece can move to that square
	 */
	public ArrayList<Square> legalMoves(Board board, Square start)
	{
		ArrayList<Square> validEndSquares = new ArrayList<>();
		Square[][] chessBoard = board.getBoard();

		for (Square[] sqrArr : chessBoard)
		{
			for (Square sqr : sqrArr)
			{
				if (this.canMove(board, start, sqr))
				{
					validEndSquares.add(sqr);
				}
			}
		}

		return validEndSquares;
	}

	/**
	 * @return the side
	 */
	public Side getSide()
	{
		return side;
	}

	/**
	 * @param side
	 *            the side to set
	 */
	public void setSide(Side side)
	{
		this.side = side;
	}

	public String toString()
	{
		String side = this.getSide().toString();
		// Return name in format: Color Name instead of COLOR Name
		return side.charAt(0) + side.substring(1).toLowerCase() + " " + this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Basically the rook's canMove function
	 * @param board
	 * @param start
	 * @param end
	 * @return
	 */
	public Boolean canMoveRookily(Board board, Square start, Square end)
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
			int rInc = rForwards ? 1 : -1;
			int cInc = cForwards ? 1 : -1;

			// TODO change from add/sub Inc's into just plus 1 because that's all this is
			int rBegIndex = rForwards ? rStart + rInc : rEnd + 1;
			int cBegIndex = cForwards ? cStart + cInc : cEnd + 1;

			int rBound = rForwards ? rEnd : rStart;
			int cBound = cForwards ? cEnd : cStart;

			if (changeInR == 0)
			{
				for (int c = cBegIndex; c < cBound; c++)
				{
					Position curPos = new Position(c, rStart);
					if (!board.getSquare(curPos).isEmpty())
					{
						return false;
					}
				}
			} else
			{
				for (int r = rBegIndex; r < rBound; r++)
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

	/**
	 * The bishops's canMove function
	 * @param board
	 * @param start
	 * @param end
	 * @return
	 */
	public Boolean canMoveBishoply(Board board, Square start, Square end)
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
		
		if (!pathIsDiagonal)
		{
			return false;
		}
		
		if(isBlockingPiece(board, changeInR, changeInC, rStart, cStart, rEnd, cEnd)) return false; 
		
		if(end.isEmpty())
		{
			return true;
		}
		else
		{
			return end.getPiece().getSide() != this.getSide();
		}
	}
	
	private Boolean isBlockingPiece(Board board,int changeInR, int changeInC, int rStart, int cStart, int rEnd, int cEnd)
	{
		Boolean rForwards = changeInR >= 0;
		Boolean cForwards = changeInC >= 0;
		
		int rInc=0;
		int cInc=0;
		
		//TODO change from add/sub Inc's into just plus 1 because that's all this is
		int rBegIndex =0;
		int cBegIndex=0;
		
		int rBound=0;
		int cBound=0;
		
		Boolean upLeft = rForwards && !cForwards;
		Boolean downRight = !rForwards && cForwards;
		Boolean upRight = rForwards && cForwards;
		Boolean downLeft = !rForwards && !cForwards;
		
		if(upLeft)
		{
			rBegIndex = rStart+1;
			cBegIndex = cStart -1;
			
			rInc = 1; 
			cInc = -1; 
			
			rBound = rEnd-1;
			cBound = cEnd+1;
			
			for(int r = rBegIndex; r <= rBound; r+=rInc)
			{
				for(int c = cBegIndex; c >= cBound; c+=cInc)
				{
					if (pieceAlongDiag(board, rBegIndex, cBegIndex, r, c)) return true;
				}
			}
			return false;
		}
		else if(downRight)
		{
			rBegIndex = rStart-1;
			cBegIndex = cStart +1;
			
			rInc = -1; 
			cInc = 1; 
			
			rBound = rEnd +1; 
			cBound = cEnd -1; 
			for(int r = rBegIndex; r >= rBound; r+=rInc)
			{
				for(int c = cBegIndex; c <= cBound; c+=cInc)
				{
					if (pieceAlongDiag(board, rBegIndex, cBegIndex, r, c)) return true;
				}
			}
			return false;
		}
		else if(upRight)
		{
			rBegIndex = rStart+1;
			cBegIndex = cStart+1;	
			
			rInc = 1; 
			cInc = 1; 
			
			rBound = rEnd -1; 
			cBound = cEnd -1; 
			
			for(int r = rBegIndex; r <= rBound; r+=rInc)
			{
				for(int c = cBegIndex; c <= cBound; c+=cInc)
				{
					if (pieceAlongDiag(board, rBegIndex, cBegIndex, r, c)) return true;
				}
			}
			return false;
		}
		else if(downLeft)
		{
			rBegIndex = rStart-1;
			cBegIndex = cStart-1;
			
			rInc = -1; 
			cInc = -1; 
			
			rBound = rEnd + 1; 
			cBound = cEnd + 1; 
			
			for(int r = rBegIndex; r >= rBound; r+=rInc)
			{
				for(int c = cBegIndex; c >= cBound; c+=cInc)
				{
					if (pieceAlongDiag(board, rBegIndex, cBegIndex, r, c)) return true;
				}
			}
			return false;
		}
		else
		{
			return false;			
		}
	}
	
	private Boolean pieceAlongDiag(Board board, int rBeg, int cBeg, int r,int c)
	{
		//I.e. if along the diagonal
		if(Math.abs(r-rBeg) == Math.abs(c-cBeg))
		{
			Position curPos = new Position(c,r);
			if(!board.getSquare(curPos).isEmpty())
			{
				return true;
			}
		}
		
		return false; 
	}

	/**
	 * Used in order to compare pieces for sorting
	 * @return Piece value
	 */
	public abstract int getPieceValue();
	
	@Override
	public int compareTo(Piece o)
	{
		return this.getPieceValue() - o.getPieceValue();
	}
}

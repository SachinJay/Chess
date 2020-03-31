package game;

import java.util.ArrayList;

import board.Board;
import board.Square;
import board.Tuple;
import pieces.Piece;
import pieces.Side;

public class Game
{
	private Player[] players = new Player[2];
	private Board board; 
	private Status status;
	private Player turn;
	private int turnInd;
	
	public Game(Board board)
	{
		players[0] = new Player();
		players[1] = new Player();
		setBoard(board);
		setStatus(Status.IN_PLAY);
		turnInd = 0;
		setTurn(players[turnInd]);
	}
	
	public Game(Player p1, Player p2,Board board)
	{
		players[0] = p1;
		players[1] = p2;
		setBoard(board);
		setStatus(Status.IN_PLAY);
		turnInd = 0;
		setTurn(players[turnInd]);
	}

	/**
	 * @return the status
	 */
	public Status getStatus()
	{
		this.calcStatus();
		return this.status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status)
	{
		this.status = status;
	}

	/**
	 * @return the turn
	 */
	public Player getTurn()
	{
		return turn;
	}

	/**
	 * @param turn the turn to set
	 */
	public void setTurn(Player turn)
	{
		this.turn = turn;
	}

	/**
	 * @return the board
	 */
	public Board getBoard()
	{
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board)
	{
		this.board = board;
	}
	
	/**
	 * Toggles turn
	 */
	public void changeTurn()
	{
		this.turnInd = 1-this.turnInd;
		this.turn = this.players[this.turnInd];
	}
	
	/**
	 * Based on the board at hand, calculates status
	 * @return The status of the game
	 */
	private void calcStatus()
	{
		Boolean whiteTurn = this.turn.getSide().equals(Side.WHITE);
		Boolean blackTurn = this.turn.getSide().equals(Side.BLACK);
		Boolean whiteCheckMate = this.isInCheckMate(Side.WHITE);
		Boolean blackCheckMate = this.isInCheckMate(Side.BLACK);
		Boolean whiteCheck = this.isInCheck(Side.WHITE);
		Boolean blackCheck = this.isInCheck(Side.BLACK);
		
		if(whiteTurn && whiteCheckMate)
		{
			this.status = Status.BLACK_WIN;
		}
		else if(blackTurn && blackCheckMate)
		{
			this.status = Status.WHITE_WIN;
		}
		else if(whiteTurn && whiteCheck)
		{
			this.status = Status.WHITE_IS_IN_CHECK;
		}
		else if(blackTurn && blackCheck)
		{
			this.status = Status.BLACK_IS_IN_CHECK;
		}
		else
		{
			this.status = Status.IN_PLAY;
		}
	}

	/**
	 * Checks if a certain side is in checkmate
	 * @param side the side that caused checkmate
	 * @return true if side caused checkmate
	 */
	public boolean isInCheckMate(Side side)
	{
		//If in check, see if this side can make any move to escape check
		if(this.isInCheck(side))
		{
			ArrayList<Tuple<Piece,Square>> piecesAndStarts = this.board.piecesWithStarts(side);	
			for(Tuple<Piece,Square> pieceAndStart : piecesAndStarts)
			{
				Piece curPiece = pieceAndStart.getFirst();
				Square start = pieceAndStart.getSecond();
				ArrayList<Square> moves = curPiece.legalMoves(board, start);
				
				for(Square end : moves)
				{
					Piece takenPiece = curPiece.move(board, start, end);
					
					//If this move caused us to no longer be in check
					//Then we weren't in checkmate
					if(!this.isInCheck(side))
					{
						return false;
					}
					
					//TODO undo move
					curPiece.overrideMove(board, end, start);
					end.setPiece(takenPiece);
				}
			}
			return true;
		}
		//If this side is not in check, it cannot be in checkmate
		else
		{
			return false;
		}
	}

	/**
	 * Checks is side is in check
	 * @param side side that may be in check
	 * @return true if side is in check
	 */
	public boolean isInCheck(Side side)
	{
		Side sideWhoseLegalMovesMatter = side.equals(Side.WHITE) ? Side.BLACK : Side.WHITE;
		
		//Find side's king
		Square kingSquare  = this.board.getKingPos(side);
		
		//check if any of sideWhoseLegalMovesMatter's pieces can legally get the king
		//i.e. if any of those pieces can legally move to king's position		
		ArrayList<Tuple<Piece,ArrayList<Square>>> pieceMoves = this.board.nextMoves(sideWhoseLegalMovesMatter);
		
		for(Tuple<Piece,ArrayList<Square>> pair : pieceMoves)
		{
			if(pair.getSecond().contains(kingSquare))
			{
				return true;
			}
		}
		

		return false;
	}
	
	
}

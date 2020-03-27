package game;

import board.Board;
import board.Square;
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
		return this.calcStatus();
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
	private Status calcStatus()
	{
		//TODO implement
		if(this.isInCheck(Side.WHITE))
		{
			return Status.WHITE_IS_IN_CHECK;
		}
		else if(this.isInCheck(Side.BLACK))
		{
			return Status.BLACK_IS_IN_CHECK;
		}
		else if(this.sideWon(Side.BLACK))
		{
			return Status.BLACK_WIN;
		}
		else if(this.sideWon(Side.WHITE))
		{
			return Status.WHITE_WIN;
		}
		else
		{
			return Status.IN_PLAY;
		}
	}

	/**
	 * Checks if a certain side is in checkmate
	 * @param side the side that caused checkmate
	 * @return true if side caused checkmate
	 */
	private boolean sideWon(Side side)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Checks is side is in check
	 * @param side side that may be in check
	 * @return true if side is in check
	 */
	private boolean isInCheck(Side side)
	{
		Side sideWhoseLegalMovesMatter = side.equals(Side.WHITE) ? Side.BLACK : Side.WHITE;
		
		//Find side's king
		Square kingSquare  = this.board.getKingPos(side);
		
		//check if any of sideWhoseLegalMovesMatter's pieces can legally get the king
		//i.e. if any of those pieces can legally move to king's position
		
		for (Square[] arr : board.getBoard())
		{
			for (Square start : arr)
			{
				if(!start.isEmpty() && start.getPiece().getSide().equals(sideWhoseLegalMovesMatter))
				{
					Piece curPiece = start.getPiece();
					if (curPiece.legalMoves(board, start).contains(kingSquare))
					{
						return true;
					}
				}
			}
		}

		return false;
	}
	
	
}

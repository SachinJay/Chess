package game;

import board.Board;

public class Game
{
	private Player[] players;
	private Board board; 
	private Status status;
	private Player turn;
	
	Game()
	{
		players[0] = new Player();
		players[1] = new Player();
		setBoard(new Board());
		setStatus(Status.IN_PLAY);
		setTurn(players[0]);
	}
	
	Game(Player p1, Player p2)
	{
		players[0] = p1;
		players[1] = p2;
		setBoard(new Board());
		setStatus(Status.IN_PLAY);
		setTurn(players[0]);
	}

	/**
	 * @return the status
	 */
	public Status getStatus()
	{
		return status;
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
	
	
}

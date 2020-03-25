package game;

import chess.Constants;
import pieces.Side;

public class Player
{
	private String name;
	private Side side;
	
	public Player()
	{
		this.setName(Constants.DEFAULT_NAME);
		this.setSide(Constants.DEFAULT_SIDE);
	}
	
	public Player(String name, Side side)
	{
		this.setName(name); 
		this.setSide(side);
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the side
	 */
	public Side getSide()
	{
		return side;
	}

	/**
	 * @param side the side to set
	 */
	public void setSide(Side side)
	{
		this.side = side;
	}

}

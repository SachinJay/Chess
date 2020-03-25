
public class Player
{
	private String name;
	private Side side;
	
	public Player()
	{
		this.name = Constants.DEFAULT_NAME;
		this.side = Constants.DEFAULT_SIDE;
	}
	
	public Player(String name, Side side)
	{
		this.name = name; 
		this.side = side;
	}

}

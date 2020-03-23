
public class Constants
{
	//Max row/col number is 8, min is 1
	public static final Integer MAX_POS = 8; 
	public static final Integer MIN_POS = 1; 
	
	public static final String POS_ERR_MSG = "Row and column number must be bewteen 1 and 8 inclusive";
	
	//Change in x times change in y position for a knight must always be 2. 
	//Further, if the change in x times the change in y is 2, the move is a valid knight L
	public static final int KNIGHT_PRODUCT = 2; 
}

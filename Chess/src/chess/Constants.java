package chess;

import pieces.Side;

public class Constants
{
	//Max row/col number is 8, min is 1
	public static final Integer MAX_POS = 8; 
	public static final Integer MIN_POS = 1; 
	
	public static final String POS_ERR_MSG = "Row and column number must be bewteen 1 and 8 inclusive";
	
	//Change in x times change in y position for a knight must always be 2. 
	//Further, if the change in x times the change in y is 2, the move is a valid knight L
	public static final int KNIGHT_PRODUCT = 2; 
	
	//Start rows for black and white pawns
	public static final int WHITE_PAWN_START = 2;
	public static final int BLACK_PAWN_START = 7;
	
	public static final String DEFAULT_NAME = "Sachin";
	public static final Side DEFAULT_SIDE = Side.WHITE;
}

package chess;

import java.awt.Color;
import java.awt.Dimension;

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
	
	public static final Dimension FRAME_DIM = new Dimension(700,700);
	public static final Dimension BOARD_DIM = new Dimension(500,450);
	public static final Dimension SQUARE_DIM = new Dimension(12,12);
	
	//Colors for light and dark squares, found using an online color picker on a board
	//Did not use black and white because the black pieces did not show up
	public static final Color COL_LIGHT = new Color(255, 241, 220);
	public static final Color COL_DARK = new Color(182,148,110);
	public static final String FILE_PATH = "src/images/";
	public static final String FILE_SUFFIX = ".png";
}

package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

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
	
	public static final Dimension FRAME_DIM = new Dimension(800,800);
	public static final Dimension BOARD_DIM = new Dimension(500,500);
	public static final Dimension SQUARE_DIM = new Dimension(12,12);
	public static final Dimension STATUS_DIM = new Dimension(500,100);
	
	//Colors for light and dark squares, found using an online color picker on a board
	//Did not use black and white because the black pieces did not show up
	public static final Color COL_LIGHT = new Color(255, 241, 220);
	public static final Color COL_DARK = new Color(182,148,110);
	
	public static final String IMAGES_PATH = "src/images/";
	public static final String IMG_SUFFIX = ".png";
	public static final String DOT_NAME  = "dot.png";
	public static final EtchedBorder CAPTURED_BORDER = new EtchedBorder(EtchedBorder.RAISED);
	public static final MatteBorder BOARD_BORDER = new MatteBorder(5, 5, 5, 5, Color.BLACK);
	public static final Color STATUS_COLOR = Color.WHITE;
	
	//Baby blue
	public static final Color BABY_BLUE = Color.decode("0x89CFF0");
	public static final Dimension TAKEN_DIM = new Dimension(40,80);
	
	//Chess piece values
	public static final int QUEEN_VAL = 9; 
	public static final int ROOK_VAL = 5; 
	public static final int BISHOP_VAL = 3;
	public static final int KNIGHT_VAL = 3; 
	public static final int PAWN_VAL = 1; 
	public static final int KING_VAL = 1000;
	
	
	public static final Font STATUS_FONT = new Font("Times New Roman", 1, 20);
	
}

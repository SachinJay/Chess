/**
 * 
 * @author Sachin Devasahayam
 * Class used to represent the position of a chess piece
 */
public class Position
{
	/**The following lines represent the column and line respectively*/
	private int col;
	private int row;
	
	/**
	 * Default position is a1 in the lower lefthand corner of the board
	 */
	public Position()
	{
		setCol(1);
		setRow(1);
	}
	
	/**
	 * Creates the position c,r
	 * @param c the column number to be set
	 * @param r the row number to be set
	 */
	public Position(int c, int r)
	{
		setCol(c); 
		setRow(r); 
	}

	/**
	 * @return the column letter 
	 */
	public int getCol()
	{
		return col;
	}

	/**
	 * @param col the column to set
	 */
	public void setCol(int col)
	{
		this.col = col;
	}

	/**
	 * @return the row number
	 */
	public int getRow()
	{
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row)
	{
		this.row = row;
	}
}

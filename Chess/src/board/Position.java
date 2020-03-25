/**
 * 
 * @author Sachin Devasahayam
 * Class used to represent the position of a chess piece
 */
package board;
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
	 * @throws IllegalArgumentException if row or column number are not in bounds
	 */
	public Position(int c, int r)
	{
		Boolean cIsValid = (c <= Constants.MAX_POS && c >= Constants.MIN_POS);
		Boolean rIsValid = (r <=Constants.MAX_POS && r >= Constants.MIN_POS);
		if(!cIsValid || !rIsValid)
		{
			throw new IllegalArgumentException(Constants.POS_ERR_MSG);
		}
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
	
	@Override
	public String toString()
	{
		String pos = posToStr(this.getCol())+ this.getRow();
		
		return pos;
	}
	
	/**
	 * 
	 * @param pos column position to be converted into a letter
	 * @return the letter corresponding to the entered position
	 */
	public String posToStr(int pos)
	{
		switch(pos)
		{
		case 1: 
			return "a";
		case 2: 
			return "b";
		case 3: 
			return "c";
		case 4: 
			return "d";
		case 5: 
			return "e";
		case 6: 
			return "f";
		case 7: 
			return "g";
		case 8: 
			return "h";
		default:
			return "a";
				
		}
	}

	public static Position stringToPos(String pos)
	{
		if(pos.length() != 2)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			//Get the first part of the string, i.e. just the letter
			char firstLetter =  pos.charAt(0);
			
			int num2 = Integer.parseInt(pos.substring(1));
			
			int num1 = 0; 
			
			switch(firstLetter)
			{
			case 'a': num1 = 1; break;
			case 'b': num1 = 2; break;
			case 'c': num1 = 3; break;
			case 'd': num1 = 4; break;
			case 'e': num1 = 5; break;
			case 'f': num1 = 6; break;
			case 'g': num1 = 7; break;
			case 'h': num1 = 8; break;
			default: num1 = 10; //nonsense number, causes illegal argument to be thrown by Position constructor
			}
			
			return new Position(num1,num2);
		}
	}
}

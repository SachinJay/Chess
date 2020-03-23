import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class ChessTests
{

	@Test
	void positionTests()
	{
		//Default position
		Position pos = new Position();
		
		//Each position along the diagonal
		Position pos1 = new Position(1,1);
		Position pos2 = new Position(2,2);
		Position pos3 = new Position(3,3);
		Position pos4 = new Position(4,4);
		Position pos5 = new Position(5,5);
		Position pos6 = new Position(6,6);
		Position pos7 = new Position(7,7);
		Position pos8 = new Position(8,8);
		Position pos9 = new Position(5,8);
	
		//Covers all cases for the Position constructor
		assertEquals("a1",pos.toString() );
		assertEquals("a1",pos1.toString());
		assertEquals("b2",pos2.toString());
		assertEquals("c3",pos3.toString());
		assertEquals("d4",pos4.toString() );
		assertEquals("e5",pos5.toString());
		assertEquals("f6",pos6.toString());
		assertEquals("g7",pos7.toString());
		assertEquals("h8",pos8.toString());
		assertEquals("e8",pos9.toString());
		
		//Invalid attempts at constructing a position should throw IllegalArgumentException
		assertThrows(IllegalArgumentException.class, () -> new Position(0,8));// row too low
		assertThrows(IllegalArgumentException.class, () -> new Position(9,8));// row too high
		assertThrows(IllegalArgumentException.class, () -> new Position(1,0));//col too low
		assertThrows(IllegalArgumentException.class, () -> new Position(1,9));//col too high
		assertThrows(IllegalArgumentException.class, () -> new Position(0,0));//both too low
		assertThrows(IllegalArgumentException.class, () -> new Position(9,9));//both too high
		assertThrows(IllegalArgumentException.class, () -> new Position(0,9));//row too low, col too high
		assertThrows(IllegalArgumentException.class, () -> new Position(9,0));//col too low, row too high
		
		//Test stringToPos method
		assertEquals(1,Position.stringToPos("a1").getCol());
		assertEquals(1,Position.stringToPos("a1").getRow());
		
		assertEquals(2,Position.stringToPos("b2").getCol());
		assertEquals(2,Position.stringToPos("b2").getRow());
		
		assertEquals(3,Position.stringToPos("c3").getCol());
		assertEquals(3,Position.stringToPos("c3").getRow());
		
		assertEquals(4,Position.stringToPos("d4").getCol());
		assertEquals(4,Position.stringToPos("d4").getRow());
		
		assertEquals(5,Position.stringToPos("e5").getCol());
		assertEquals(5,Position.stringToPos("e5").getRow());
		
		assertEquals(6,Position.stringToPos("f6").getCol());
		assertEquals(6,Position.stringToPos("f6").getRow());
		
		assertEquals(7,Position.stringToPos("g7").getCol());
		assertEquals(7,Position.stringToPos("g7").getRow());
		
		assertEquals(8,Position.stringToPos("h8").getCol());
		assertEquals(8,Position.stringToPos("h8").getRow());
		
		assertEquals(5,Position.stringToPos("e8").getCol());
		assertEquals(8,Position.stringToPos("e8").getRow());
		
	}
	
	@Test
	void squareTests()
	{
		//Default square
		Square sqr1 = new Square();
		
		Pawn pawn = new Pawn(Side.WHITE);
		
		//Square in default position with pawn on it
		Square sqr2 = new Square(new Position(), pawn);
		
		//Test default and non-default constructors
		assertTrue(sqr1.isEmpty()); //Default square is empty
		assertFalse(sqr2.isEmpty()); //Square with a pawn on it is not empty
		
		//Test that the getPiece method works for empty and filled squares 
		//To 'work' on an empty square means it throws the right excpetion
		assertThrows(NoSuchElementException.class , () -> sqr1.getPiece());
		assertEquals(pawn, sqr2.getPiece());
	}
	
	@Test
	void pieceTests()
	{
		//Test that the get and set side methods work
		
		//Creates a black pawn
		Piece pawn = new Pawn(Side.BLACK);
		assertEquals(Side.BLACK, pawn.getSide());
		pawn.setSide(Side.WHITE); //change pawn color to white
		assertEquals(Side.WHITE, pawn.getSide());
		
		//Create black knight
		Piece knight = new Knight(Side.BLACK);
		assertEquals(Side.BLACK, knight.getSide());
		knight.setSide(Side.WHITE); //change knight color to white
		assertEquals(Side.WHITE, knight.getSide());
	}
	
	@Test
	void boardTests()
	{
		Board board = new Board();
		
		Square a1 = board.getSquare("a1");  //a white rook
		Square a2 = board.getSquare("a2");  //a white pawn
		Square a3 = board.getSquare("a3"); //blank square
		Square e1 = board.getSquare("e1"); //white queen
		
		//Select tests
		assertFalse(a1.isEmpty());
		assertFalse(a2.isEmpty());
		assertTrue(a3.isEmpty());
		assertFalse(e1.isEmpty());
		
		//Comprehensive tests
		for(int c = 1; c <= 8; c++)
		{
			//White rows
			for(int r = 1; r<= 2; r++)
			{
				Square curSquare = board.getSquare(new Position(c,r));
				assertFalse(curSquare.isEmpty());
				assertEquals(Side.WHITE, curSquare.getPiece().getSide());
			}
			
			//Space between both sides is empty
			for(int r = 3; r <=6; r++)
			{
				Square curSquare = board.getSquare(new Position(c,r));
				assertTrue(curSquare.isEmpty());
			}
			
			//Black rows are non empty
			for(int r = 7; r <=8; r++)
			{
				Square curSquare = board.getSquare(new Position(c,r));
				assertFalse(curSquare.isEmpty());
				assertEquals(Side.BLACK, curSquare.getPiece().getSide());
			}
			
		}
		
		assertEquals("a2", a2.getPos().toString());
		
		
		
	}
	
	@Test
	void knightTests()
	{
		
	}

}

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
		
		//Invalid attempts at constructing a position should throw IllegalArgumentException
		assertThrows(IllegalArgumentException.class, () -> new Position(0,8));// row too low
		assertThrows(IllegalArgumentException.class, () -> new Position(9,8));// row too high
		assertThrows(IllegalArgumentException.class, () -> new Position(1,0));//col too low
		assertThrows(IllegalArgumentException.class, () -> new Position(1,9));//col too high
		assertThrows(IllegalArgumentException.class, () -> new Position(0,0));//both too low
		assertThrows(IllegalArgumentException.class, () -> new Position(9,9));//both too high
		assertThrows(IllegalArgumentException.class, () -> new Position(0,9));//row too low, col too high
		assertThrows(IllegalArgumentException.class, () -> new Position(9,0));//col too low, row too high
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

}

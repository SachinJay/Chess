import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class ChessTests
{

	@Test
	void positionTests()
	{
		//Default position
		Position pos1 = new Position();
		
		//TODO: Add a test for each position on the diagonal
		Position pos2 = new Position(1,1);
		Position pos3 = new Position(4,4);
		
		assertEquals("a1",pos1.toString() );
		assertEquals("a1",pos2.toString() );
		assertEquals("d4",pos3.toString() );
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
		assertEquals(true, sqr1.isEmpty()); //Default square is empty
		assertEquals(false, sqr2.isEmpty()); //Square with a pawn on it is not empty
		
		//Test that the getPiece method works for empty and filled squares 
		//To 'work' on an empty square means it throws the right excpetion
		assertThrows(NoSuchElementException.class , () -> sqr1.getPiece());
		assertEquals(pawn, sqr2.getPiece());
		
		
	}

}

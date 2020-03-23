import static org.junit.jupiter.api.Assertions.*;

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
		
		//Test that the default square is empty
		assertEquals(true, sqr1.isEmpty());
	}

}

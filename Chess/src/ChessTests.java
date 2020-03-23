import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ChessTests
{

	@Test
	void positionTests()
	{
		Position pos1 = new Position();
		Position pos2 = new Position(1,1);
		Position pos3 = new Position(4,4);
		
		assertEquals("a1",pos1.toString() );
		assertEquals("a1",pos2.toString() );
		assertEquals("d4",pos3.toString() );
	}

}

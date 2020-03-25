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
		
		//Test that the positions are correct
		assertEquals("a1", a1.getPos().toString());
		assertEquals("a2", a2.getPos().toString());
		assertEquals("a3", a3.getPos().toString());
		assertEquals("e1", e1.getPos().toString());
		assertEquals("h6",board.getSquare("h6").getPos().toString());
		
		//Test that the pieces are correct
		assertEquals("White Rook", a1.getPiece().toString());
		assertEquals("White Pawn", a2.getPiece().toString());
		assertEquals("White Queen", e1.getPiece().toString());
	}
	
	@Test
	void knightTests()
	{
		Board board = new Board();
		Square knightSquare = board.getSquare("b1");
		Knight knight = (Knight) knightSquare.getPiece();
		
		//This piece can indeed move to these two spots only
		assertTrue(knight.canMove(board, knightSquare , board.getSquare("c3")));
		assertTrue(knight.canMove(board, knightSquare , board.getSquare("a3")));
		
		//Check that a place that is two knight moves away is not reachable
		assertFalse(knight.canMove(board, knightSquare , board.getSquare("d5")));
		
		//Check that knight cannot move to random spot on board, except the two it can move to
		for(int i = 0; i < 64; i++)
		{
			String pos = randPos(1, 8, 3, 8);
			while(pos.equals("c3") || pos.equals("a3"))
			{
				pos = randPos(1, 8, 3, 6);
			}
			assertFalse(knight.canMove(board, knightSquare , board.getSquare(pos)));
		}
		
		//Empty a spot so that the knight can move there as well
		board.setSquare("d2", new Square(new Position(4,2), null));
		assertTrue(knight.canMove(board, knightSquare, board.getSquare("d2")));
		
		//Fill empty spot with black pawns, knight should be able to move there now
		//Further, fill the two spots the knight could originally move to with black pawns
		//Knight should now also be able to move there
		board.setSquare("d2", new Square(new Position(4,2), new Pawn(Side.BLACK)));
		board.setSquare("a3", new Square(new Position(1,3), new Pawn(Side.BLACK)));
		board.setSquare("c3", new Square(new Position(3,3), new Pawn(Side.BLACK)));
	}
	
	@Test
	void kingTests()
	{
		//TODO: Test a few things
		//Valid space to move to, but there's an ally there blocking you
		//Valid space to move to, and its empty
		//Valid space to move to, and can capture
		//Invalid space to move to (for example, space you start at)
		
		Board board = new Board();
		Square square = board.getSquare("d1");
		King king = (King) square.getPiece();
		
		//Space to move but white piece is blocking
		assertFalse(king.canMove(board, square, board.getSquare("d2")));
		assertFalse(king.canMove(board, square, board.getSquare("e1")));
		assertFalse(king.canMove(board, square, board.getSquare("c1")));
		assertFalse(king.canMove(board, square, board.getSquare("e2")));
		assertFalse(king.canMove(board, square, board.getSquare("c2")));
		
		//remove allies from relevant spaces
		board.setSquare("d2", new Square(new Position(4,2), null));
		board.setSquare("e1", new Square(new Position(5,1), null));
		board.setSquare("c1", new Square(new Position(3,1), null));
		board.setSquare("e2", new Square(new Position(5,2), null));
		board.setSquare("c2", new Square(new Position(3,2), null));
		
		assertTrue(king.canMove(board, square, board.getSquare("d2")));
		assertTrue(king.canMove(board, square, board.getSquare("e1")));
		assertTrue(king.canMove(board, square, board.getSquare("c1")));
		assertTrue(king.canMove(board, square, board.getSquare("e2")));
		assertTrue(king.canMove(board, square, board.getSquare("c2")));	
		
		//Assert that king cannot move to invalid spots (i.e. anywhere not the above positions)
		for(int i  = 0; i < 64; i++)
		{
			String pos = randPos(1, 8, 3, 8);
			String pos2 = randPos(1,2,1,2);
			String pos3 = randPos(6,8,1,2);
			assertFalse(king.canMove(board, square, board.getSquare(pos)));
			assertFalse(king.canMove(board, square, board.getSquare(pos2)));
			assertFalse(king.canMove(board, square, board.getSquare(pos3)));
		}
		
		//Change board so that there are capturable pieces in valid spaces
		board.setSquare("d2", new Square(new Position(4,2), new Pawn(Side.BLACK)));
		board.setSquare("e1", new Square(new Position(5,1), new Pawn(Side.BLACK)));
		board.setSquare("c1", new Square(new Position(3,1), new Pawn(Side.BLACK)));
		board.setSquare("e2", new Square(new Position(5,2), new Pawn(Side.BLACK)));
		board.setSquare("c2", new Square(new Position(3,2), new Pawn(Side.BLACK)));
		
		assertTrue(king.canMove(board, square, board.getSquare("d2")));
		assertTrue(king.canMove(board, square, board.getSquare("e1")));
		assertTrue(king.canMove(board, square, board.getSquare("c1")));
		assertTrue(king.canMove(board, square, board.getSquare("e2")));
		assertTrue(king.canMove(board, square, board.getSquare("c2")));
		
	}
	
	@Test
	void rookTests()
	{
		//TODO: Checklist
		//[x]Valid space to move to, but there's an ally there blocking you
		//[x]Valid space to move to, and its empty
		//[x]Valid space to move to, and can capture
		//[x]Invalid space to move to (for example, space you start at)
		//[x]Otherwise all good except that there is a piece on the rook's path
		
		Board board = new Board();
		Square square = board.getSquare("a1");
		Rook rook = (Rook) square.getPiece();
		
		//Valid space to move but white is blocking 
		for(int i = 0; i < 64; i++)
		{
			String pos1 = randPos(1, 1, 1, 8);
			String pos2 = randPos(1, 8, 1, 1);
			
			assertFalse(rook.canMove(board, square, board.getSquare(pos1)));
			assertFalse(rook.canMove(board, square, board.getSquare(pos2)));
		}
		
		//remove all relevant white pieces so rook can move freely
		board.setSquare("a2", new Square(new Position(1,2), null));
		board.setSquare("b1", new Square(new Position(2,1), null));
		board.setSquare("c1", new Square(new Position(3,1), null));
		board.setSquare("d1", new Square(new Position(4,1), null));
		board.setSquare("e1", new Square(new Position(5,1), null));
		board.setSquare("f1", new Square(new Position(6,1), null));
		board.setSquare("g1", new Square(new Position(7,1), null));
		board.setSquare("h1", new Square(new Position(8,1), null));
		
		//Rook can move to valid empty spaces
		assertTrue(rook.canMove(board, square, board.getSquare("a2")));
		assertTrue(rook.canMove(board, square, board.getSquare("b1")));
		assertTrue(rook.canMove(board, square, board.getSquare("c1")));
		assertTrue(rook.canMove(board, square, board.getSquare("d1")));
		assertTrue(rook.canMove(board, square, board.getSquare("e1")));
		assertTrue(rook.canMove(board, square, board.getSquare("f1")));
		assertTrue(rook.canMove(board, square, board.getSquare("g1")));
		assertTrue(rook.canMove(board, square, board.getSquare("h1")));
		
		//*******Begin rook capture tests********//
		
		//Now that the pawn at a2 was removed, rook should be able to get the pawn at a7
		assertTrue(rook.canMove(board, square, board.getSquare("a7")));
		
		//fill two valid spaces with enemy pawns
		board.setSquare("a2", new Square(new Position(1,2), new Pawn(Side.BLACK)));
		assertTrue(rook.canMove(board, square, board.getSquare("a2")));
		
		//fill then unfill 
		board.setSquare("b1", new Square(new Position(2,1), new Pawn(Side.BLACK)));
		assertTrue(rook.canMove(board, square, board.getSquare("b1")));
		board.setSquare("b1", new Square(new Position(2,1), null));
		
		board.setSquare("c1", new Square(new Position(3,1), new Pawn(Side.BLACK)));
		assertTrue(rook.canMove(board, square, board.getSquare("c1")));		
		board.setSquare("c1", new Square(new Position(3,1), null));
		
		board.setSquare("d1", new Square(new Position(4,1), new Pawn(Side.BLACK)));
		assertTrue(rook.canMove(board, square, board.getSquare("d1")));
		board.setSquare("d1", new Square(new Position(4,1), null));
		
		board.setSquare("e1", new Square(new Position(5,1), new Pawn(Side.BLACK)));
		assertTrue(rook.canMove(board, square, board.getSquare("e1")));
		board.setSquare("e1", new Square(new Position(5,1), null));
		
		//*******End rook capture tests********//
		
		//invalid spaces
		for(int i = 0; i < 64; i++)
		{
			String pos = randPos(2, 8, 2, 8);
			assertFalse(rook.canMove(board, square, board.getSquare(pos)));
		}
		
		//Put a piece in each of the rook's paths
		board.setSquare("a4", new Square(new Position(1,4), new Pawn(Side.BLACK)));
		board.setSquare("e1", new Square(new Position(5,1), new Pawn(Side.BLACK)));
		
		assertFalse(rook.canMove(board, square, board.getSquare("a7")));
		assertFalse(rook.canMove(board, square, board.getSquare("h1")));
		
		
	}
	
	@Test
	void pawnTests()
	{
		//TODO: Checklist
		//[x]For all of the below, do it for both black and white pawns
		//[x]Valid space to move to, but there's an ally there blocking you
		//[x]Valid space to move to, and its empty
		//----[x]For one space and two space
		//[x]Valid space to move to, and can capture
		//[x]Valid space to move two spaces towards but there is an intermediary piece blocking
		//[x]Invalid space to move to (for example, space you start at)
		
		Board board = new Board();
		Pawn whitePawn = (Pawn)board.getSquare("d2").getPiece();
		Square whiteSquare = board.getSquare("d2");
		Pawn blackPawn = (Pawn) board.getSquare("d7").getPiece();
		Square blackSquare = board.getSquare("d7");
		
		//Two space and one space white
		assertTrue(whitePawn.canMove(board, whiteSquare, board.getSquare("d3")));
		assertTrue(whitePawn.canMove(board, whiteSquare, board.getSquare("d4")));
		
		//Two space and one space black
		assertTrue(blackPawn.canMove(board, blackSquare, board.getSquare("d6")));
		assertTrue(blackPawn.canMove(board, blackSquare, board.getSquare("d5")));
		
		//Add pieces for white capture
		board.setSquare("c3", new Square(new Position(3,3),new Pawn(Side.BLACK)));
		board.setSquare("e3", new Square(new Position(5,3), new Pawn(Side.BLACK)));
		
		//Add pieces for black capture
		board.setSquare("c6", new Square(new Position(3,6),new Pawn(Side.WHITE)));
		board.setSquare("e6", new Square(new Position(5,6), new Pawn(Side.WHITE)));
		
		//Test capture ability white
		assertTrue(whitePawn.canMove(board, whiteSquare, board.getSquare("c3")));
		assertTrue(whitePawn.canMove(board, whiteSquare, board.getSquare("e3")));
		
		//Test capture ability black
		assertTrue(blackPawn.canMove(board, blackSquare, board.getSquare("c6")));
		assertTrue(blackPawn.canMove(board, blackSquare, board.getSquare("e6")));
		
		//Put allies in capturable position
		board.setSquare("c3", new Square(new Position(3,3),new Pawn(Side.WHITE)));
		board.setSquare("e3", new Square(new Position(5,3), new Pawn(Side.WHITE)));
		board.setSquare("c6", new Square(new Position(3,6),new Pawn(Side.BLACK)));
		board.setSquare("e6", new Square(new Position(5,6), new Pawn(Side.BLACK)));
		
		//Test you can't capture allied pieces
		assertFalse(whitePawn.canMove(board, whiteSquare, board.getSquare("c3")));
		assertFalse(whitePawn.canMove(board, whiteSquare, board.getSquare("e3")));
		assertFalse(blackPawn.canMove(board, blackSquare, board.getSquare("c6")));
		assertFalse(blackPawn.canMove(board, blackSquare, board.getSquare("e6")));
		
		//Put allies in blocking positions
		board.setSquare("d3", new Square(new Position(4,3), new Pawn(Side.WHITE)));
		board.setSquare("d6", new Square(new Position(4,6),new Pawn(Side.BLACK)));
		
		//Test you cannot move where your allies are
		assertFalse(whitePawn.canMove(board, whiteSquare, board.getSquare("d3")));
		assertFalse(blackPawn.canMove(board, blackSquare, board.getSquare("d6")));
		
		//Intermediary blocking two space movement that would otherwise be okay
		assertFalse(whitePawn.canMove(board, whiteSquare, board.getSquare("d4")));
		assertFalse(blackPawn.canMove(board, blackSquare, board.getSquare("d5")));
		
		//Invalid spots
		for(int i = 0; i < 64; i++)
		{
			String posWhite = randPos(1, 8, 5,8);
			assertFalse(whitePawn.canMove(board, whiteSquare, board.getSquare(posWhite)));
			
			String posBlack = randPos(1,8,1,4);
			assertFalse(blackPawn.canMove(board, blackSquare, board.getSquare(posBlack)));
		}
	}
	
	@Test
	void bishopTests()
	{
		//TODO: Checklist
		//[x]Valid space to move to, but there's an ally there blocking you
		//[x]Valid space to move to, and its empty
		//[x]Valid space to move to, and can capture
		//[x]Valid space to move to but there is an intermediary piece blocking
		//[]Invalid space to move to
		
		Board board = new Board();
		Square square = board.getSquare("c1");
		Bishop bishop = (Bishop) square.getPiece();
		
		//Valid space (only one away) but ally blocks
		assertFalse(bishop.canMove(board, square, board.getSquare("b2")));
		assertFalse(bishop.canMove(board, square, board.getSquare("d2")));
		
		//Remove the blocking pawns
		board.setSquare("b2", new Square(new Position(2,2),null));
		board.setSquare("d2", new Square(new Position(4,2),null));
		
		//Put blocking pawns further away
		board.setSquare("a3", new Square(new Position(1,3),new Pawn(Side.WHITE)));
		board.setSquare("h6", new Square(new Position(8,6),new Pawn(Side.WHITE)));
		
		//Valid space but ally blocks
		assertFalse(bishop.canMove(board, square, board.getSquare("a3")));
		assertFalse(bishop.canMove(board, square, board.getSquare("h6")));
		
		//Valid empty space
		assertTrue(bishop.canMove(board, square, board.getSquare("b2")));
		assertTrue(bishop.canMove(board, square, board.getSquare("d2")));
		assertTrue(bishop.canMove(board, square, board.getSquare("e3")));
		assertTrue(bishop.canMove(board, square, board.getSquare("f4")));
		assertTrue(bishop.canMove(board, square, board.getSquare("g5")));
		
		//Replace allies with enemies, should be able to capture
		board.setSquare("a3", new Square(new Position(1,3),new Pawn(Side.BLACK)));
		board.setSquare("h6", new Square(new Position(8,6),new Pawn(Side.BLACK)));
		
		assertTrue(bishop.canMove(board, square, board.getSquare("a3")));
		assertTrue(bishop.canMove(board, square, board.getSquare("h6")));
		
		//put back pawns on front line to block legal moves (note what side they are is irrelevant)
		board.setSquare("b2", new Square(new Position(2,2),new Pawn(Side.BLACK)));
		board.setSquare("d2", new Square(new Position(4,2),new Pawn(Side.WHITE)));
		
		//Can't move to valid square if a piece blocks you
		assertFalse(bishop.canMove(board, square, board.getSquare("a3")));		
		assertFalse(bishop.canMove(board, square, board.getSquare("e3")));
		assertFalse(bishop.canMove(board, square, board.getSquare("f4")));
		assertFalse(bishop.canMove(board, square, board.getSquare("g5")));
		assertFalse(bishop.canMove(board, square, board.getSquare("h6")));
		
		//Test a few invalid spaces after resetting the board
		board.reset();
		assertFalse(bishop.canMove(board, square, board.getSquare("c2")));		
		assertFalse(bishop.canMove(board, square, board.getSquare("b1")));
		assertFalse(bishop.canMove(board, square, board.getSquare("d1")));
		assertFalse(bishop.canMove(board, square, board.getSquare("c1")));
		assertFalse(bishop.canMove(board, square, board.getSquare("h7")));
			
	}
	
	/**
	 * Creates a string that represents a random position on the board
	 * @param lr lower bound inclusive on the row
	 * @param ur upper bound inclusive on the row
	 * @param lc lower bound inclusive on the column
	 * @param uc upper bound inclusive on the column
	 * @return
	 */
	public String randPos(int lc, int uc, int lr, int ur)
	{
		String letter = "";
		int number = (int)( (Math.random() * (ur+1-lr) ) + lr );
		int let = (int)( (Math.random() * (uc+1-lc) ) + lc );
		
		letter = Character.toString((char) (96 + let));
		
		return letter + number;
	}

}

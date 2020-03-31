package chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import board.Board;
import game.Game;
import game.Player;
import game.Status;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;
import pieces.Side;

class GameTests
{

	@Test
	void statusTests()
	{
		Status[] statuses = {Status.BLACK_WIN,Status.BLACK_IS_IN_CHECK,Status.WHITE_IS_IN_CHECK,Status.FORFEIT,Status.IN_PLAY,
				Status.PAUSED, Status.STALEMATE, Status.WHITE_WIN};
		
		String[] strings = {"Black win", "Black is in check", "White is in check","Forfeit", "In play", 
				"Paused", "Stalemate", "White win"};
		
		for(int i = 0; i < statuses.length; i++)
		{
			assertEquals(strings[i], statuses[i].makeString());
			
		}
	}
	
	@Test
	void playerTests()
	{
		Player sachin = new Player();
		Player jay = new Player("Jay",Side.BLACK);
		
		Side white = Side.WHITE;
		Side black = Side.BLACK;
		
		assertEquals("Sachin",sachin.getName());
		assertEquals(white,sachin.getSide());
		
		assertEquals("Jay",jay.getName());
		assertEquals(black,jay.getSide());
	}
	
	@Test
	void gameTests()
	{
		Player p1 = new Player();
		Player p2 = new Player("Jay", Side.BLACK);
		
		Board board = new Board();
		
		Game game1 = new Game(board);
		Game game2 = new Game(p1,p2,board);
		
		assertEquals(Status.IN_PLAY.makeString(), game1.getStatus().makeString());
		assertEquals(Status.IN_PLAY.makeString(), game2.getStatus().makeString());
		
		board.initArabian();
		
		assertTrue(game1.isInCheckMate(Side.BLACK));
		
	}

}

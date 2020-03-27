package chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.Game;
import game.Player;
import game.Status;
import pieces.Side;

class GameTests
{

	@Test
	void statusTests()
	{
		Status[] statuses = {Status.BLACK_WIN,Status.CHECK,Status.FORFEIT,Status.IN_PLAY,
				Status.PAUSED, Status.STALE_MATE, Status.WHITE_WIN};
		
		String[] strings = {"Black Win", "Check", "Forfeit", "In Play", "Paused", "Stale Mate", "White Win"};
		
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
		Game game = new Game();
	}

}

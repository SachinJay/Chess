package chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.Status;

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
		
	}

}

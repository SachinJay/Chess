package chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import board.Board;
import game.Game;
import game.Player;
import game.Status;
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
		
		//*******************CHECKMATE TESTS*****************//
		board.initArabian();
		
		assertTrue(game1.isInCheckMate(Side.BLACK));
		
		board.initAnderssen();
		assertTrue(game1.isInCheck(Side.BLACK));
		
		board.initBackRankMate();
		assertTrue(game1.isInCheckMate(Side.WHITE));
		
		board.initDamianoBishop();
		assertTrue(game1.isInCheckMate(Side.WHITE));
		
		board.initBox();
		assertTrue(game1.isInCheckMate(Side.WHITE));
		//****************************************************//
		
		//****************STALEMATE TESTS*********************//
		board.initKingPawnKing();
		assertEquals(Side.WHITE, game2.getTurn().getSide());
		assertTrue(game2.isInStaleMate());
		
		
		board.initKingPawnKingVariation();
		assertEquals(Side.WHITE,game2.getTurn().getSide());
		assertEquals(p1,game2.getTurn());
		game2.changeTurn();
		assertEquals(p2, game2.getTurn());
		assertEquals(Side.BLACK,game2.getTurn().getSide());
		assertTrue(game2.isInStaleMate());
		
		board.initKingKingQueen();
		assertTrue(game2.isInStaleMate());
		
		
		//****************************************************//
		
	}

}

package krazune.krps.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest
{
	@Test
	public void computeGameWinTest()
	{
		assertEquals(GameOutcome.WIN, Game.computeOutcome(GameChoice.ROCK, GameChoice.SCISSORS));
		assertEquals(GameOutcome.WIN, Game.computeOutcome(GameChoice.PAPER, GameChoice.ROCK));
		assertEquals(GameOutcome.WIN, Game.computeOutcome(GameChoice.SCISSORS, GameChoice.PAPER));
	}

	@Test
	public void computeGameLossTest()
	{
		assertEquals(GameOutcome.LOSS, Game.computeOutcome(GameChoice.ROCK, GameChoice.PAPER));
		assertEquals(GameOutcome.LOSS, Game.computeOutcome(GameChoice.PAPER, GameChoice.SCISSORS));
		assertEquals(GameOutcome.LOSS, Game.computeOutcome(GameChoice.SCISSORS, GameChoice.ROCK));
	}

	@Test
	public void computeGameDrawTest()
	{
		assertEquals(GameOutcome.DRAW, Game.computeOutcome(GameChoice.ROCK, GameChoice.ROCK));
		assertEquals(GameOutcome.DRAW, Game.computeOutcome(GameChoice.PAPER, GameChoice.PAPER));
		assertEquals(GameOutcome.DRAW, Game.computeOutcome(GameChoice.SCISSORS, GameChoice.SCISSORS));
	}

	@Test
	public void computeGameNullTest()
	{
		assertEquals(null, Game.computeOutcome(null, GameChoice.ROCK));
		assertEquals(null, Game.computeOutcome(GameChoice.ROCK, null));
		assertEquals(null, Game.computeOutcome(null, null));
	}

	@Test
	public void fullConstructorOutcomeTest()
	{
		Game winGame = new Game(1, null, GameChoice.ROCK, GameChoice.SCISSORS, null);
		Game lossGame = new Game(1, null, GameChoice.ROCK, GameChoice.PAPER, null);
		Game drawGame = new Game(1, null, GameChoice.ROCK, GameChoice.ROCK, null);

		assertEquals(GameOutcome.WIN, winGame.getOutcome());
		assertEquals(GameOutcome.LOSS, lossGame.getOutcome());
		assertEquals(GameOutcome.DRAW, drawGame.getOutcome());
	}

	@Test
	public void userAndGameChoicesConstructorOutcomeTest()
	{
		Game winGame = new Game(null, GameChoice.ROCK, GameChoice.SCISSORS);
		Game lossGame = new Game(null, GameChoice.ROCK, GameChoice.PAPER);
		Game drawGame = new Game(null, GameChoice.ROCK, GameChoice.ROCK);

		assertEquals(GameOutcome.WIN, winGame.getOutcome());
		assertEquals(GameOutcome.LOSS, lossGame.getOutcome());
		assertEquals(GameOutcome.DRAW, drawGame.getOutcome());
	}

	@Test
	public void gameChoicesConstructorOutcomeTest()
	{
		Game winGame = new Game(GameChoice.ROCK, GameChoice.SCISSORS);
		Game lossGame = new Game(GameChoice.ROCK, GameChoice.PAPER);
		Game drawGame = new Game(GameChoice.ROCK, GameChoice.ROCK);

		assertEquals(GameOutcome.WIN, winGame.getOutcome());
		assertEquals(GameOutcome.LOSS, lossGame.getOutcome());
		assertEquals(GameOutcome.DRAW, drawGame.getOutcome());
	}

	@Test
	public void playWinTest()
	{
		assertEquals(GameOutcome.WIN, new Game().play(GameChoice.ROCK, GameChoice.SCISSORS));
		assertEquals(GameOutcome.WIN, new Game().play(GameChoice.PAPER, GameChoice.ROCK));
		assertEquals(GameOutcome.WIN, new Game().play(GameChoice.SCISSORS, GameChoice.PAPER));
	}

	@Test
	public void playLossTest()
	{
		assertEquals(GameOutcome.LOSS, new Game().play(GameChoice.ROCK, GameChoice.PAPER));
		assertEquals(GameOutcome.LOSS, new Game().play(GameChoice.PAPER, GameChoice.SCISSORS));
		assertEquals(GameOutcome.LOSS, new Game().play(GameChoice.SCISSORS, GameChoice.ROCK));
	}

	@Test
	public void playDrawTest()
	{
		assertEquals(GameOutcome.DRAW, new Game().play(GameChoice.ROCK, GameChoice.ROCK));
		assertEquals(GameOutcome.DRAW, new Game().play(GameChoice.PAPER, GameChoice.PAPER));
		assertEquals(GameOutcome.DRAW, new Game().play(GameChoice.SCISSORS, GameChoice.SCISSORS));
	}
}

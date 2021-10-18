package krazune.krps;

import org.junit.jupiter.api.Test;

import krazune.krps.game.GameChoice;
import krazune.krps.game.GameResult;
import krazune.krps.game.Game;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest
{
	@Test
	public void playGameWinTest()
	{
		Game game = new Game();

		game.setUserChoice(GameChoice.ROCK);
		game.setCPUChoice(GameChoice.SCISSORS);

		assertTrue(game.getResult() == GameResult.WIN);

		game.setUserChoice(GameChoice.PAPER);
		game.setCPUChoice(GameChoice.ROCK);

		assertTrue(game.getResult() == GameResult.WIN);

		game.setUserChoice(GameChoice.SCISSORS);
		game.setCPUChoice(GameChoice.PAPER);

		assertTrue(game.getResult() == GameResult.WIN);
	}

	@Test
	public void playGameLossTest()
	{
		Game game = new Game();

		game.setUserChoice(GameChoice.ROCK);
		game.setCPUChoice(GameChoice.PAPER);

		assertTrue(game.getResult() == GameResult.LOSS);

		game.setUserChoice(GameChoice.PAPER);
		game.setCPUChoice(GameChoice.SCISSORS);

		assertTrue(game.getResult() == GameResult.LOSS);

		game.setUserChoice(GameChoice.SCISSORS);
		game.setCPUChoice(GameChoice.ROCK);

		assertTrue(game.getResult() == GameResult.LOSS);
	}

	@Test
	public void playGameDrawTest()
	{
		Game game = new Game();

		game.setUserChoice(GameChoice.ROCK);
		game.setCPUChoice(GameChoice.ROCK);

		assertTrue(game.getResult() == GameResult.DRAW);

		game.setUserChoice(GameChoice.PAPER);
		game.setCPUChoice(GameChoice.PAPER);

		assertTrue(game.getResult() == GameResult.DRAW);

		game.setUserChoice(GameChoice.SCISSORS);
		game.setCPUChoice(GameChoice.SCISSORS);

		assertTrue(game.getResult() == GameResult.DRAW);
	}

	@Test
	public void playGameUnknownTest()
	{
		Game game = new Game();

		assertTrue(game.getResult() == GameResult.UNKNOWN);

		game.setUserChoice(GameChoice.ROCK);

		assertTrue(game.getResult() == GameResult.UNKNOWN);

		game.setUserChoice(GameChoice.UNKNOWN);
		game.setCPUChoice(GameChoice.ROCK);

		assertTrue(game.getResult() == GameResult.UNKNOWN);
	}

	@Test
	public void calculateGameResultWinTest()
	{
		GameResult result = GameResult.UNKNOWN;

		result = Game.calculateGameResult(GameChoice.ROCK, GameChoice.SCISSORS);

		assertTrue(result == GameResult.WIN);

		result = Game.calculateGameResult(GameChoice.PAPER, GameChoice.ROCK);

		assertTrue(result == GameResult.WIN);

		result = Game.calculateGameResult(GameChoice.SCISSORS, GameChoice.PAPER);

		assertTrue(result == GameResult.WIN);
	}

	@Test
	public void calculateGameResultLossTest()
	{
		GameResult result = GameResult.UNKNOWN;

		result = Game.calculateGameResult(GameChoice.ROCK, GameChoice.PAPER);

		assertTrue(result == GameResult.LOSS);

		result = Game.calculateGameResult(GameChoice.PAPER, GameChoice.SCISSORS);

		assertTrue(result == GameResult.LOSS);

		result = Game.calculateGameResult(GameChoice.SCISSORS, GameChoice.ROCK);

		assertTrue(result == GameResult.LOSS);
	}

	@Test
	public void calculateGameResultDrawTest()
	{
		GameResult result = GameResult.UNKNOWN;

		result = Game.calculateGameResult(GameChoice.ROCK, GameChoice.ROCK);

		assertTrue(result == GameResult.DRAW);

		result = Game.calculateGameResult(GameChoice.PAPER, GameChoice.PAPER);

		assertTrue(result == GameResult.DRAW);

		result = Game.calculateGameResult(GameChoice.SCISSORS, GameChoice.SCISSORS);

		assertTrue(result == GameResult.DRAW);
	}

	@Test
	public void calculateGameResultUnknownTest()
	{
		GameResult result = GameResult.WIN;

		result = Game.calculateGameResult(GameChoice.UNKNOWN, GameChoice.ROCK);

		assertTrue(result == GameResult.UNKNOWN);

		result = Game.calculateGameResult(GameChoice.ROCK, GameChoice.UNKNOWN);

		assertTrue(result == GameResult.UNKNOWN);

		result = Game.calculateGameResult(GameChoice.UNKNOWN, GameChoice.UNKNOWN);

		assertTrue(result == GameResult.UNKNOWN);
	}

	@Test
	public void calculateGameChoiceFromResultRockTest()
	{
		GameChoice choice = GameChoice.UNKNOWN;

		choice = Game.calculateGameChoiceFromResult(GameChoice.PAPER, GameResult.WIN);

		assertTrue(choice == GameChoice.ROCK);

		choice = Game.calculateGameChoiceFromResult(GameChoice.SCISSORS, GameResult.LOSS);

		assertTrue(choice == GameChoice.ROCK);

		choice = Game.calculateGameChoiceFromResult(GameChoice.ROCK, GameResult.DRAW);

		assertTrue(choice == GameChoice.ROCK);
	}

	@Test
	public void calculateGameChoiceFromResultPaperTest()
	{
		GameChoice choice = GameChoice.UNKNOWN;

		choice = Game.calculateGameChoiceFromResult(GameChoice.SCISSORS, GameResult.WIN);

		assertTrue(choice == GameChoice.PAPER);

		choice = Game.calculateGameChoiceFromResult(GameChoice.ROCK, GameResult.LOSS);

		assertTrue(choice == GameChoice.PAPER);

		choice = Game.calculateGameChoiceFromResult(GameChoice.PAPER, GameResult.DRAW);

		assertTrue(choice == GameChoice.PAPER);
	}

	@Test
	public void calculateGameChoiceFromResultScissorsTest()
	{
		GameChoice choice = GameChoice.UNKNOWN;

		choice = Game.calculateGameChoiceFromResult(GameChoice.ROCK, GameResult.WIN);

		assertTrue(choice == GameChoice.SCISSORS);

		choice = Game.calculateGameChoiceFromResult(GameChoice.PAPER, GameResult.LOSS);

		assertTrue(choice == GameChoice.SCISSORS);

		choice = Game.calculateGameChoiceFromResult(GameChoice.SCISSORS, GameResult.DRAW);

		assertTrue(choice == GameChoice.SCISSORS);
	}

	@Test
	public void calculateGameChoiceFromResultUnknownTest()
	{
		GameChoice choice = GameChoice.ROCK;

		choice = Game.calculateGameChoiceFromResult(GameChoice.UNKNOWN, GameResult.WIN);

		assertTrue(choice == GameChoice.UNKNOWN);

		choice = Game.calculateGameChoiceFromResult(GameChoice.PAPER, GameResult.UNKNOWN);

		assertTrue(choice == GameChoice.UNKNOWN);

		choice = Game.calculateGameChoiceFromResult(GameChoice.UNKNOWN, GameResult.UNKNOWN);

		assertTrue(choice == GameChoice.UNKNOWN);
	}
}
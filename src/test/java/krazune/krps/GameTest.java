/*
	MIT License

	Copyright (c) 2021 Miguel Sousa

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.
*/
package krazune.krps;

import krazune.krps.game.GameChoice;
import krazune.krps.game.GameResult;
import krazune.krps.game.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest
{
	@Test
	public void playGameWinTest()
	{
		Game game = new Game();

		game.setUserChoice(GameChoice.ROCK);
		game.setComputerChoice(GameChoice.SCISSORS);

		assertTrue(game.getResult() == GameResult.WIN);

		game.setUserChoice(GameChoice.PAPER);
		game.setComputerChoice(GameChoice.ROCK);

		assertTrue(game.getResult() == GameResult.WIN);

		game.setUserChoice(GameChoice.SCISSORS);
		game.setComputerChoice(GameChoice.PAPER);

		assertTrue(game.getResult() == GameResult.WIN);
	}

	@Test
	public void playGameLossTest()
	{
		Game game = new Game();

		game.setUserChoice(GameChoice.ROCK);
		game.setComputerChoice(GameChoice.PAPER);

		assertTrue(game.getResult() == GameResult.LOSS);

		game.setUserChoice(GameChoice.PAPER);
		game.setComputerChoice(GameChoice.SCISSORS);

		assertTrue(game.getResult() == GameResult.LOSS);

		game.setUserChoice(GameChoice.SCISSORS);
		game.setComputerChoice(GameChoice.ROCK);

		assertTrue(game.getResult() == GameResult.LOSS);
	}

	@Test
	public void playGameDrawTest()
	{
		Game game = new Game();

		game.setUserChoice(GameChoice.ROCK);
		game.setComputerChoice(GameChoice.ROCK);

		assertTrue(game.getResult() == GameResult.DRAW);

		game.setUserChoice(GameChoice.PAPER);
		game.setComputerChoice(GameChoice.PAPER);

		assertTrue(game.getResult() == GameResult.DRAW);

		game.setUserChoice(GameChoice.SCISSORS);
		game.setComputerChoice(GameChoice.SCISSORS);

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
		game.setComputerChoice(GameChoice.ROCK);

		assertTrue(game.getResult() == GameResult.UNKNOWN);
	}

	@Test
	public void calculateGameResultWinTest()
	{
		GameResult result;

		result = Game.getResultFromChoices(GameChoice.ROCK, GameChoice.SCISSORS);

		assertTrue(result == GameResult.WIN);

		result = Game.getResultFromChoices(GameChoice.PAPER, GameChoice.ROCK);

		assertTrue(result == GameResult.WIN);

		result = Game.getResultFromChoices(GameChoice.SCISSORS, GameChoice.PAPER);

		assertTrue(result == GameResult.WIN);
	}

	@Test
	public void calculateGameResultLossTest()
	{
		GameResult result;

		result = Game.getResultFromChoices(GameChoice.ROCK, GameChoice.PAPER);

		assertTrue(result == GameResult.LOSS);

		result = Game.getResultFromChoices(GameChoice.PAPER, GameChoice.SCISSORS);

		assertTrue(result == GameResult.LOSS);

		result = Game.getResultFromChoices(GameChoice.SCISSORS, GameChoice.ROCK);

		assertTrue(result == GameResult.LOSS);
	}

	@Test
	public void calculateGameResultDrawTest()
	{
		GameResult result;

		result = Game.getResultFromChoices(GameChoice.ROCK, GameChoice.ROCK);

		assertTrue(result == GameResult.DRAW);

		result = Game.getResultFromChoices(GameChoice.PAPER, GameChoice.PAPER);

		assertTrue(result == GameResult.DRAW);

		result = Game.getResultFromChoices(GameChoice.SCISSORS, GameChoice.SCISSORS);

		assertTrue(result == GameResult.DRAW);
	}

	@Test
	public void calculateGameResultUnknownTest()
	{
		GameResult result;

		result = Game.getResultFromChoices(GameChoice.UNKNOWN, GameChoice.ROCK);

		assertTrue(result == GameResult.UNKNOWN);

		result = Game.getResultFromChoices(GameChoice.ROCK, GameChoice.UNKNOWN);

		assertTrue(result == GameResult.UNKNOWN);

		result = Game.getResultFromChoices(GameChoice.UNKNOWN, GameChoice.UNKNOWN);

		assertTrue(result == GameResult.UNKNOWN);
	}

	@Test
	public void calculateGameChoiceFromResultRockTest()
	{
		GameChoice choice;

		choice = Game.getChoiceFromResult(GameChoice.PAPER, GameResult.WIN);

		assertTrue(choice == GameChoice.ROCK);

		choice = Game.getChoiceFromResult(GameChoice.SCISSORS, GameResult.LOSS);

		assertTrue(choice == GameChoice.ROCK);

		choice = Game.getChoiceFromResult(GameChoice.ROCK, GameResult.DRAW);

		assertTrue(choice == GameChoice.ROCK);
	}

	@Test
	public void calculateGameChoiceFromResultPaperTest()
	{
		GameChoice choice;

		choice = Game.getChoiceFromResult(GameChoice.SCISSORS, GameResult.WIN);

		assertTrue(choice == GameChoice.PAPER);

		choice = Game.getChoiceFromResult(GameChoice.ROCK, GameResult.LOSS);

		assertTrue(choice == GameChoice.PAPER);

		choice = Game.getChoiceFromResult(GameChoice.PAPER, GameResult.DRAW);

		assertTrue(choice == GameChoice.PAPER);
	}

	@Test
	public void calculateGameChoiceFromResultScissorsTest()
	{
		GameChoice choice;

		choice = Game.getChoiceFromResult(GameChoice.ROCK, GameResult.WIN);

		assertTrue(choice == GameChoice.SCISSORS);

		choice = Game.getChoiceFromResult(GameChoice.PAPER, GameResult.LOSS);

		assertTrue(choice == GameChoice.SCISSORS);

		choice = Game.getChoiceFromResult(GameChoice.SCISSORS, GameResult.DRAW);

		assertTrue(choice == GameChoice.SCISSORS);
	}

	@Test
	public void calculateGameChoiceFromResultUnknownTest()
	{
		GameChoice choice;

		choice = Game.getChoiceFromResult(GameChoice.UNKNOWN, GameResult.WIN);

		assertTrue(choice == GameChoice.UNKNOWN);

		choice = Game.getChoiceFromResult(GameChoice.PAPER, GameResult.UNKNOWN);

		assertTrue(choice == GameChoice.UNKNOWN);

		choice = Game.getChoiceFromResult(GameChoice.UNKNOWN, GameResult.UNKNOWN);

		assertTrue(choice == GameChoice.UNKNOWN);
	}
}
package krazune.krps;

import org.junit.jupiter.api.Test;

import krazune.krps.game.GameChoice;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class GameChoiceTest
{
	@Test
	public void convertCharToChoiceTest()
	{
		assumeTrue(GameChoice.convertToChoice('r') == GameChoice.ROCK);
		assumeTrue(GameChoice.convertToChoice('R') == GameChoice.ROCK);

		assumeTrue(GameChoice.convertToChoice('p') == GameChoice.PAPER);
		assumeTrue(GameChoice.convertToChoice('P') == GameChoice.PAPER);

		assumeTrue(GameChoice.convertToChoice('s') == GameChoice.SCISSORS);
		assumeTrue(GameChoice.convertToChoice('S') == GameChoice.SCISSORS);

		assumeTrue(GameChoice.convertToChoice('a') == GameChoice.UNKNOWN);
		assumeTrue(GameChoice.convertToChoice('b') == GameChoice.UNKNOWN);
		assumeTrue(GameChoice.convertToChoice('c') == GameChoice.UNKNOWN);
	}

	@Test
	public void convertStringToChoiceTest()
	{
		assumeTrue(GameChoice.convertToChoice("rock") == GameChoice.ROCK);
		assumeTrue(GameChoice.convertToChoice("ROCK") == GameChoice.ROCK);

		assumeTrue(GameChoice.convertToChoice("paper") == GameChoice.PAPER);
		assumeTrue(GameChoice.convertToChoice("PAPER") == GameChoice.PAPER);

		assumeTrue(GameChoice.convertToChoice("scissors") == GameChoice.SCISSORS);
		assumeTrue(GameChoice.convertToChoice("SCISSORS") == GameChoice.SCISSORS);

		assumeTrue(GameChoice.convertToChoice("aaa") == GameChoice.UNKNOWN);
		assumeTrue(GameChoice.convertToChoice("bbb") == GameChoice.UNKNOWN);
		assumeTrue(GameChoice.convertToChoice("ccc") == GameChoice.UNKNOWN);
	}

	@Test
	public void convertToCharTest()
	{
		assumeTrue(GameChoice.convertToChar(GameChoice.ROCK) == 'r');
		assumeTrue(GameChoice.convertToChar(GameChoice.PAPER) == 'p');
		assumeTrue(GameChoice.convertToChar(GameChoice.SCISSORS) == 's');
		assumeTrue(GameChoice.convertToChar(GameChoice.UNKNOWN) == '\0');
	}

	@Test
	public void convertToStringTest()
	{
		assumeTrue(GameChoice.convertToString(GameChoice.ROCK).equalsIgnoreCase("rock"));
		assumeTrue(GameChoice.convertToString(GameChoice.PAPER).equalsIgnoreCase("paper"));
		assumeTrue(GameChoice.convertToString(GameChoice.SCISSORS).equalsIgnoreCase("scissors"));
		assumeTrue(GameChoice.convertToString(GameChoice.UNKNOWN).equalsIgnoreCase("unknown"));
	}
}
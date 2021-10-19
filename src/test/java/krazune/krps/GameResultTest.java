package krazune.krps;

import krazune.krps.game.GameResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class GameResultTest
{
	@Test
	public void convertCharToGameResultTest()
	{
		assumeTrue(GameResult.convertToGameResult('w') == GameResult.WIN);
		assumeTrue(GameResult.convertToGameResult('W') == GameResult.WIN);

		assumeTrue(GameResult.convertToGameResult('l') == GameResult.LOSS);
		assumeTrue(GameResult.convertToGameResult('L') == GameResult.LOSS);

		assumeTrue(GameResult.convertToGameResult('d') == GameResult.DRAW);
		assumeTrue(GameResult.convertToGameResult('D') == GameResult.DRAW);

		assumeTrue(GameResult.convertToGameResult('a') == GameResult.UNKNOWN);
		assumeTrue(GameResult.convertToGameResult('b') == GameResult.UNKNOWN);
		assumeTrue(GameResult.convertToGameResult('c') == GameResult.UNKNOWN);
	}

	@Test
	public void convertStringToGameResultTest()
	{
		assumeTrue(GameResult.convertToGameResult("win") == GameResult.WIN);
		assumeTrue(GameResult.convertToGameResult("WIN") == GameResult.WIN);

		assumeTrue(GameResult.convertToGameResult("loss") == GameResult.LOSS);
		assumeTrue(GameResult.convertToGameResult("LOSS") == GameResult.LOSS);

		assumeTrue(GameResult.convertToGameResult("draw") == GameResult.DRAW);
		assumeTrue(GameResult.convertToGameResult("DRAW") == GameResult.DRAW);

		assumeTrue(GameResult.convertToGameResult("AAA") == GameResult.UNKNOWN);
		assumeTrue(GameResult.convertToGameResult("BBB") == GameResult.UNKNOWN);
		assumeTrue(GameResult.convertToGameResult("CCC") == GameResult.UNKNOWN);
	}

	@Test
	public void convertToCharTest()
	{
		assumeTrue(GameResult.convertToChar(GameResult.WIN) == 'w');
		assumeTrue(GameResult.convertToChar(GameResult.LOSS) == 'l');
		assumeTrue(GameResult.convertToChar(GameResult.DRAW) == 'd');
		assumeTrue(GameResult.convertToChar(GameResult.UNKNOWN) == '\0');
	}

	@Test
	public void convertToStringTest()
	{
		assumeTrue(GameResult.convertToString(GameResult.WIN).equalsIgnoreCase("win"));
		assumeTrue(GameResult.convertToString(GameResult.LOSS).equalsIgnoreCase("loss"));
		assumeTrue(GameResult.convertToString(GameResult.DRAW).equalsIgnoreCase("draw"));
		assumeTrue(GameResult.convertToString(GameResult.UNKNOWN).equalsIgnoreCase("unknown"));
	}
}
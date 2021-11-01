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
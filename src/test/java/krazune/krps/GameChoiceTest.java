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
import org.junit.jupiter.api.Test;

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
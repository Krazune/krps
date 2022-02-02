/*
 * MIT License
 *
 * Copyright (c) 2022 Miguel Sousa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package krazune.krps.game;

public enum GameChoice
{
	ROCK('r'),
	PAPER('p'),
	SCISSORS('s');

	private final char symbol;

	GameChoice(char symbol)
	{
		this.symbol = symbol;
	}

	public static GameChoice getGameChoice(String symbolString)
	{
		return getGameChoice(symbolString.charAt(0));
	}

	public static GameChoice getGameChoice(char symbol)
	{
		switch (symbol)
		{
			case 'r':
			case 'R':
				return ROCK;

			case 'p':
			case 'P':
				return PAPER;

			case 's':
			case 'S':
				return SCISSORS;

			default:
				return null;
		}
	}

	public char getSymbol()
	{
		return symbol;
	}
}

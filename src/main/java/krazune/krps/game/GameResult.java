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
package krazune.krps.game;

public enum GameResult
{
	UNKNOWN,
	WIN,
	LOSS, // l, li, ll, l_
	DRAW;

	public static GameResult convertToGameResult(char result)
	{
		if (result == 'w' || result == 'W')
		{
			return WIN;
		}

		if (result == 'l' || result == 'L')
		{
			return LOSS;
		}

		if (result == 'd' || result == 'D')
		{
			return DRAW;
		}

		return UNKNOWN;
	}

	public static GameResult convertToGameResult(String result)
	{
		if (result.equalsIgnoreCase("win"))
		{
			return WIN;
		}

		if (result.equalsIgnoreCase("loss"))
		{
			return LOSS;
		}

		if (result.equalsIgnoreCase("draw"))
		{
			return DRAW;
		}

		return UNKNOWN;
	}

	public static char convertToChar(GameResult result)
	{
		if (result == WIN)
		{
			return 'w';
		}

		if (result == LOSS)
		{
			return 'l';
		}

		if (result == DRAW)
		{
			return 'd';
		}

		return '\0';
	}

	public static String convertToString(GameResult result)
	{
		if (result == WIN)
		{
			return "win";
		}

		if (result == LOSS)
		{
			return "loss";
		}

		if (result == DRAW)
		{
			return "draw";
		}

		return "unknown";
	}
}
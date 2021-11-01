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

public enum GameChoice
{
	UNKNOWN,
	ROCK,
	PAPER,
	SCISSORS;

	public static GameChoice convertToChoice(char choice)
	{
		if (choice == 'r' || choice == 'R')
		{
			return ROCK;
		}

		if (choice == 'p' || choice == 'P')
		{
			return PAPER;
		}

		if (choice == 's' || choice == 'S')
		{
			return SCISSORS;
		}

		return UNKNOWN;
	}

	public static GameChoice convertToChoice(String choice)
	{
		if (choice.equalsIgnoreCase("rock"))
		{
			return ROCK;
		}

		if (choice.equalsIgnoreCase("paper"))
		{
			return PAPER;
		}

		if (choice.equalsIgnoreCase("scissors"))
		{
			return SCISSORS;
		}

		return UNKNOWN;
	}

	public static char convertToChar(GameChoice choice)
	{
		if (choice == ROCK)
		{
			return 'r';
		}

		if (choice == PAPER)
		{
			return 'p';
		}

		if (choice == SCISSORS)
		{
			return 's';
		}

		return '\0';
	}

	public static String convertToString(GameChoice choice)
	{
		if (choice == ROCK)
		{
			return "rock";
		}

		if (choice == PAPER)
		{
			return "paper";
		}

		if (choice == SCISSORS)
		{
			return "scissors";
		}

		return "unknown";
	}
}
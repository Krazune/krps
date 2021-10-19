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
}
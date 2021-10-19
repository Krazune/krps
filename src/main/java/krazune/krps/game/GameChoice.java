package krazune.krps.game;

public enum GameChoice
{
	UNKNOWN,
	ROCK,
	PAPER,
	SCISSORS;

	public static GameChoice convertToChoice(char choice)
	{
		if (choice == 'r')
		{
			return ROCK;
		}

		if (choice == 'p')
		{
			return PAPER;
		}

		if (choice == 's')
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
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
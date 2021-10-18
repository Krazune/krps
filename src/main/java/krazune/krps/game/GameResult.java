package krazune.krps.game;

public enum GameResult
{
	UNKNOWN,
	WIN,
	LOSS, // l, li, ll, l_
	DRAW;

	public static GameResult calculateResult(char result)
	{
		if (result == 'w')
		{
			return WIN;
		}

		if (result == 'l')
		{
			return LOSS;
		}

		if (result == 'd')
		{
			return DRAW;
		}

		return UNKNOWN;
	}

	public static char calculateResultCharacter(GameResult result)
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
}
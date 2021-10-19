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
}
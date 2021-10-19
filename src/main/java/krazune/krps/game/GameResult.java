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
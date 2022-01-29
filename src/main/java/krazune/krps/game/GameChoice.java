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

package krazune.krps.game;

public enum GameOutcome
{
	WIN('w'),
	LOSS('l'),
	DRAW('d');

	private final char symbol;

	GameOutcome(char symbol)
	{
		this.symbol = symbol;
	}

	public static GameOutcome getGameOutcome(char symbol)
	{
		switch (symbol)
		{
			case 'w':
			case 'W':
				return WIN;

			case 'l':
			case 'L':
				return LOSS;

			case 'd':
			case 'D':
				return DRAW;

			default:
				return null;
		}
	}

	public char getSymbol()
	{
		return symbol;
	}
}

package krazune.krps.game;

import java.sql.Timestamp;
import krazune.krps.user.User;

public class Game
{
	private int id;
	private User user;
	private GameChoice userChoice;
	private GameChoice computerChoice;
	private GameOutcome outcome;
	private Timestamp creationDate;

	public Game()
	{
	}

	public Game(int id, User user, GameChoice userChoice, GameChoice computerChoice, Timestamp creationDate)
	{
		this.id = id;
		this.user = user;
		this.userChoice = userChoice;
		this.computerChoice = computerChoice;
		this.creationDate = creationDate;

		updateOutcome();
	}

	public Game(User user, GameChoice userChoice, GameChoice computerChoice)
	{
		this.user = user;
		this.userChoice = userChoice;
		this.computerChoice = computerChoice;

		updateOutcome();
	}

	public Game(GameChoice userChoice, GameChoice computerChoice)
	{
		this.userChoice = userChoice;
		this.computerChoice = computerChoice;

		updateOutcome();
	}

	public static GameOutcome computeOutcome(GameChoice choice1, GameChoice choice2)
	{
		if (choice1 == GameChoice.ROCK)
		{
			if (choice2 == GameChoice.ROCK)
			{
				return GameOutcome.DRAW;
			}

			if (choice2 == GameChoice.PAPER)
			{
				return GameOutcome.LOSS;
			}

			if (choice2 == GameChoice.SCISSORS)
			{
				return GameOutcome.WIN;
			}

			return null;
		}

		if (choice1 == GameChoice.PAPER)
		{
			if (choice2 == GameChoice.ROCK)
			{
				return GameOutcome.WIN;
			}

			if (choice2 == GameChoice.PAPER)
			{
				return GameOutcome.DRAW;
			}

			if (choice2 == GameChoice.SCISSORS)
			{
				return GameOutcome.LOSS;
			}

			return null;
		}

		if (choice1 == GameChoice.SCISSORS)
		{
			if (choice2 == GameChoice.ROCK)
			{
				return GameOutcome.LOSS;
			}

			if (choice2 == GameChoice.PAPER)
			{
				return GameOutcome.WIN;
			}

			if (choice2 == GameChoice.SCISSORS)
			{
				return GameOutcome.DRAW;
			}

			return null;
		}

		return null;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public GameOutcome getOutcome()
	{
		return outcome;
	}

	public Timestamp getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate)
	{
		this.creationDate = creationDate;
	}

	public GameOutcome play(GameChoice userChoice, GameChoice computerChoice)
	{
		this.userChoice = userChoice;
		this.computerChoice = computerChoice;

		return updateOutcome();
	}

	private GameOutcome updateOutcome()
	{
		outcome = computeOutcome(userChoice, computerChoice);

		return outcome;
	}
}

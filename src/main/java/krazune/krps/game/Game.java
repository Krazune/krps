package krazune.krps.game;

import java.sql.Timestamp;
import krazune.krps.user.User;

public class Game
{
	private int id;
	private User user;
	private GameChoice userChoice;
	private GameChoice computerChoice;
	private GameResult result;
	private Timestamp creationDate;

	public Game()
	{
		userChoice = GameChoice.UNKNOWN;
		computerChoice = GameChoice.UNKNOWN;

		result = GameResult.UNKNOWN;
	}

	public Game(User user, GameChoice userChoice, GameChoice cpuChoice)
	{
		this.user = user;
		this.userChoice = userChoice;
		this.computerChoice = cpuChoice;

		this.result = updateResult();
	}

	public Game(int id, User user, GameChoice userChoice, GameChoice cpuChoice, Timestamp creationDate)
	{
		this.id = id;
		this.user = user;
		this.userChoice = userChoice;
		this.computerChoice = cpuChoice;
		this.creationDate = creationDate;

		updateResult();
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

	public GameChoice getUserChoice()
	{
		return userChoice;
	}

	public GameResult setUserChoice(GameChoice choice)
	{
		userChoice = choice;

		return updateResult();
	}

	public GameChoice getComputerChoice()
	{
		return computerChoice;
	}

	public GameResult setComputerChoice(GameChoice choice)
	{
		computerChoice = choice;

		return updateResult();
	}

	public GameResult getResult()
	{
		return result;
	}

	public Timestamp getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate)
	{
		this.creationDate = creationDate;
	}

	private GameResult updateResult()
	{
		result = getResultFromChoices(userChoice, computerChoice);

		return result;
	}

	public static GameResult getResultFromChoices(GameChoice choiceA, GameChoice choiceB)
	{
		switch (choiceA)
		{
			case ROCK:
			if (choiceB == GameChoice.ROCK)
			{
				return GameResult.DRAW;
			}
			else if (choiceB == GameChoice.PAPER)
			{
				return GameResult.LOSS;
			}
			else if (choiceB == GameChoice.SCISSORS)
			{
				return GameResult.WIN;
			}
			break;

			case PAPER:
			if (choiceB == GameChoice.ROCK)
			{
				return GameResult.WIN;
			}
			else if (choiceB == GameChoice.PAPER)
			{
				return GameResult.DRAW;
			}
			else if (choiceB == GameChoice.SCISSORS)
			{
				return GameResult.LOSS;
			}
			break;

			case SCISSORS:
			if (choiceB == GameChoice.ROCK)
			{
				return GameResult.LOSS;
			}
			else if (choiceB == GameChoice.PAPER)
			{
				return GameResult.WIN;
			}
			else if (choiceB == GameChoice.SCISSORS)
			{
				return GameResult.DRAW;
			}
			break;
		}

		return GameResult.UNKNOWN;
	}

	public static GameChoice getChoiceFromResult(GameChoice choice, GameResult result)
	{
		switch (choice)
		{
			case ROCK:
			if (result == GameResult.WIN)
			{
				return GameChoice.SCISSORS;
			}
			else if (result == GameResult.LOSS)
			{
				return GameChoice.PAPER;
			}
			else if (result == GameResult.DRAW)
			{
				return GameChoice.ROCK;
			}
			break;

			case PAPER:
			if (result == GameResult.WIN)
			{
				return GameChoice.ROCK;
			}
			else if (result == GameResult.LOSS)
			{
				return GameChoice.SCISSORS;
			}
			else if (result == GameResult.DRAW)
			{
				return GameChoice.PAPER;
			}
			break;

			case SCISSORS:
			if (result == GameResult.WIN)
			{
				return GameChoice.PAPER;
			}
			else if (result == GameResult.LOSS)
			{
				return GameChoice.ROCK;
			}
			else if (result == GameResult.DRAW)
			{
				return GameChoice.SCISSORS;
			}
			break;
		}

		return GameChoice.UNKNOWN;
	}
}

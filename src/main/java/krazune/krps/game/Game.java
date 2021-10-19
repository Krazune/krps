package krazune.krps.game;

import java.sql.Timestamp;
import krazune.krps.user.User;

public class Game
{
	private int id;
	private User user;
	private GameChoice userChoice;
	private GameChoice cpuChoice;
	private GameResult result;
	private Timestamp creationDate;

	public Game()
	{
		userChoice = GameChoice.UNKNOWN;
		cpuChoice = GameChoice.UNKNOWN;

		result = GameResult.UNKNOWN;
	}

	public Game(User user, GameChoice userChoice, GameChoice cpuChoice)
	{
		this.user = user;
		this.userChoice = userChoice;
		this.cpuChoice = cpuChoice;

		this.result = updateResult();
	}

	public Game(int id, User user, GameChoice userChoice, GameChoice cpuChoice, Timestamp creationDate)
	{
		this.id = id;
		this.user = user;
		this.userChoice = userChoice;
		this.cpuChoice = cpuChoice;
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

	public GameChoice getCPUChoice()
	{
		return cpuChoice;
	}

	public GameResult setCPUChoice(GameChoice choice)
	{
		cpuChoice = choice;

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
		result = calculateGameResult(userChoice, cpuChoice);

		return result;
	}

	public static GameResult calculateGameResult(GameChoice choiceA, GameChoice ChoiceB)
	{
		switch (choiceA)
		{
			case ROCK:
			if (ChoiceB == GameChoice.ROCK)
			{
				return GameResult.DRAW;
			}
			else if (ChoiceB == GameChoice.PAPER)
			{
				return GameResult.LOSS;
			}
			else if (ChoiceB == GameChoice.SCISSORS)
			{
				return GameResult.WIN;
			}
			break;

			case PAPER:
			if (ChoiceB == GameChoice.ROCK)
			{
				return GameResult.WIN;
			}
			else if (ChoiceB == GameChoice.PAPER)
			{
				return GameResult.DRAW;
			}
			else if (ChoiceB == GameChoice.SCISSORS)
			{
				return GameResult.LOSS;
			}
			break;

			case SCISSORS:
			if (ChoiceB == GameChoice.ROCK)
			{
				return GameResult.LOSS;
			}
			else if (ChoiceB == GameChoice.PAPER)
			{
				return GameResult.WIN;
			}
			else if (ChoiceB == GameChoice.SCISSORS)
			{
				return GameResult.DRAW;
			}
			break;
		}

		return GameResult.UNKNOWN;
	}

	public static GameChoice calculateGameChoiceFromResult(GameChoice choice, GameResult result)
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

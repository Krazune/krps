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

	public Game(User user, GameChoice userChoice, GameChoice computerChoice)
	{
		this.user = user;
		this.userChoice = userChoice;
		this.computerChoice = computerChoice;

		this.result = updateResult();
	}

	public Game(int id, User user, GameChoice userChoice, GameChoice computerChoice, Timestamp creationDate)
	{
		this.id = id;
		this.user = user;
		this.userChoice = userChoice;
		this.computerChoice = computerChoice;
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
			switch (choiceB)
			{
				case ROCK:
				return GameResult.DRAW;

				case PAPER:
				return GameResult.LOSS;

				case SCISSORS:
				return GameResult.WIN;
			}
			break;


			case PAPER:
			switch (choiceB)
			{
				case ROCK:
				return GameResult.WIN;

				case PAPER:
				return GameResult.DRAW;

				case SCISSORS:
				return GameResult.LOSS;
			}
			break;


			case SCISSORS:
			switch (choiceB)
			{
				case ROCK:
				return GameResult.LOSS;

				case PAPER:
				return GameResult.WIN;

				case SCISSORS:
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
			switch (result)
			{
				case WIN:
				return GameChoice.SCISSORS;

				case LOSS:
				return GameChoice.PAPER;

				case DRAW:
				return GameChoice.ROCK;
			}
			break;


			case PAPER:
			switch (result)
			{
				case WIN:
				return GameChoice.ROCK;

				case LOSS:
				return GameChoice.SCISSORS;

				case DRAW:
				return GameChoice.PAPER;
			}
			break;


			case SCISSORS:
			switch (result)
			{
				case WIN:
				return GameChoice.PAPER;

				case LOSS:
				return GameChoice.ROCK;

				case DRAW:
				return GameChoice.SCISSORS;
			}
			break;

		}

		return GameChoice.UNKNOWN;
	}
}

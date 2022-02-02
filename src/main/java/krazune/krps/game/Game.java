/*
 * MIT License
 *
 * Copyright (c) 2022 Miguel Sousa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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

	public GameChoice getUserChoice()
	{
		return userChoice;
	}

	public GameChoice getComputerChoice()
	{
		return computerChoice;
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

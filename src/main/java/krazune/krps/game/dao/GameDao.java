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
package krazune.krps.game.dao;

import java.util.List;
import krazune.krps.dao.DaoException;
import krazune.krps.game.Game;
import krazune.krps.game.GameChoice;
import krazune.krps.game.GameOutcome;
import krazune.krps.user.User;

public interface GameDao
{
	boolean insert(Game game) throws DaoException;

	Game get(int id) throws DaoException;
	List<Game> getGames() throws DaoException;
	List<Game> getGames(int start, int size) throws DaoException;
	List<Game> getLastGames(User user, int size) throws DaoException;
	List<Game> getUserGames(int id) throws DaoException;
	List<Game> getUserGames(int id, int start, int size) throws DaoException;
	List<Game> getUserGames(User user) throws DaoException;
	List<Game> getUserGames(User user, int start, int size) throws DaoException;

	boolean update(Game game) throws DaoException;

	boolean delete(int id) throws DaoException;
	boolean delete(Game game) throws DaoException;

	int getGameCount() throws DaoException;
	int getUserGameCount(int id) throws DaoException;
	int getUserGameCount(User user) throws DaoException;
	int getOutcomeCount(GameOutcome outcome) throws DaoException;
	int getOutcomeCount(GameOutcome outcome, User user) throws DaoException;
	int getUserChoiceCount(GameChoice userChoice) throws DaoException;
	int getUserChoiceCount(GameChoice userChoice, User user) throws DaoException;
	int getComputerChoiceCount(GameChoice computerChoice) throws DaoException;
}

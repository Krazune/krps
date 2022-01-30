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
	int getUserChoiceCount(GameChoice userChoice) throws DaoException;
	int getComputerChoiceCount(GameChoice computerChoice) throws DaoException;
}

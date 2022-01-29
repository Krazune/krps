package krazune.krps.game.dao;

import java.sql.SQLException;
import java.util.List;
import krazune.krps.game.Game;
import krazune.krps.game.GameChoice;
import krazune.krps.game.GameOutcome;
import krazune.krps.user.User;

public interface GameDao
{
	boolean insert(Game game) throws SQLException;

	Game get(int id) throws SQLException;
	List<Game> getGames() throws SQLException;
	List<Game> getGames(int start, int size) throws SQLException;
	List<Game> getUserGames(int id) throws SQLException;
	List<Game> getUserGames(int id, int start, int size) throws SQLException;
	List<Game> getUserGames(User user) throws SQLException;
	List<Game> getUserGames(User user, int start, int size) throws SQLException;

	boolean update(Game game) throws SQLException;

	boolean delete(int id) throws SQLException;
	boolean delete(Game game) throws SQLException;

	int getGameCount() throws SQLException;
	int getUserGameCount(int id) throws SQLException;
	int getUserGameCount(User user) throws SQLException;
	int getOutcomeCount(GameOutcome outcome) throws SQLException;
	int getUserChoiceCount(GameChoice userChoice) throws SQLException;
	int getComputerChoiceCount(GameChoice computerChoice) throws SQLException;
}

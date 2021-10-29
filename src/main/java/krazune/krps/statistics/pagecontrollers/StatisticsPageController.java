package krazune.krps.statistics.pagecontrollers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import krazune.krps.game.Game;
import krazune.krps.game.GameChoice;
import krazune.krps.game.GameDAO;
import krazune.krps.game.GameResult;
import krazune.krps.user.Authentication;
import krazune.krps.user.User;
import krazune.krps.util.ConnectionFactory;
import krazune.krps.util.PropertiesLoader;

public class StatisticsPageController extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		try
		{
			populateStatisticsAttributes(request);
		}
		catch (SQLException e)
		{
			throw new ServletException(e);
		}

		request.getRequestDispatcher("/WEB-INF/jsp/statistics.jsp").forward(request, response);
	}

	private void populateStatisticsAttributes(HttpServletRequest request) throws SQLException
	{
		populateGlobalStatisticsAttributes(request);
		populateUserStatisticsAttributes(request);
	}

	private void populateGlobalStatisticsAttributes(HttpServletRequest request) throws SQLException
	{
		PropertiesLoader propertiesLoader = (PropertiesLoader)request.getAttribute("propertiesLoader");
		String jdbcUrl = propertiesLoader.getJdbcUrl();
		String jdbcUsername = propertiesLoader.getJdbcUser();
		String jdbcPassword = propertiesLoader.getJdbcPassword();

		ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, jdbcUsername, jdbcPassword);
		GameDAO gameDao = new GameDAO(connectionFactory);

		request.setAttribute("gameCount", gameDao.getGameCount());
		request.setAttribute("winCount", gameDao.getGameResultCount(GameResult.WIN));
		request.setAttribute("lossCount", gameDao.getGameResultCount(GameResult.LOSS));
		request.setAttribute("drawCount", gameDao.getGameResultCount(GameResult.DRAW));

		request.setAttribute("userChoiceRockCount", gameDao.getUserChoiceCount(GameChoice.ROCK));
		request.setAttribute("userChoicePaperCount", gameDao.getUserChoiceCount(GameChoice.PAPER));
		request.setAttribute("userChoiceScissorsCount", gameDao.getUserChoiceCount(GameChoice.SCISSORS));
	}

	private void populateUserStatisticsAttributes(HttpServletRequest request) throws SQLException
	{
		User sessionUser = Authentication.getSessionUser(request);

		if (sessionUser == null)
		{
			return;
		}

		PropertiesLoader propertiesLoader = (PropertiesLoader)request.getAttribute("propertiesLoader");
		String jdbcUrl = propertiesLoader.getJdbcUrl();
		String jdbcUsername = propertiesLoader.getJdbcUser();
		String jdbcPassword = propertiesLoader.getJdbcPassword();

		ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, jdbcUsername, jdbcPassword);
		GameDAO gameDao = new GameDAO(connectionFactory);

		request.setAttribute("sessionUserGameCount", gameDao.getGameCountByUser(sessionUser));
		request.setAttribute("sessionUserWinCount", gameDao.getGameResultCountByUser(sessionUser, GameResult.WIN));
		request.setAttribute("sessionUserLossCount", gameDao.getGameResultCountByUser(sessionUser, GameResult.LOSS));
		request.setAttribute("sessionUserDrawCount", gameDao.getGameResultCountByUser(sessionUser, GameResult.DRAW));

		request.setAttribute("sessionUserRockCount", gameDao.getUserChoiceCountByUser(sessionUser, GameChoice.ROCK));
		request.setAttribute("sessionUserPaperCount", gameDao.getUserChoiceCountByUser(sessionUser, GameChoice.PAPER));
		request.setAttribute("sessionUserScissorsCount", gameDao.getUserChoiceCountByUser(sessionUser, GameChoice.SCISSORS));

		List<Game> lastGames = gameDao.findByUser(sessionUser, 10);

		request.setAttribute("lastGames", lastGames);
	}
}

package krazune.krps.statistics.pagecontrollers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import krazune.krps.game.Game;
import krazune.krps.game.GameDAO;
import krazune.krps.statistics.StatisticsDAO;
import krazune.krps.user.User;
import krazune.krps.user.UserDAO;
import krazune.krps.util.ConnectionFactory;
import krazune.krps.util.PropertiesLoader;

public class StatisticsPageController extends HttpServlet
{
	PropertiesLoader propertiesLoader;

	public void init() throws ServletException
	{
		propertiesLoader = new PropertiesLoader();

		try
		{
			propertiesLoader.load();
		}
		catch (IOException e)
		{
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		try
		{
			String jdbcUrl = propertiesLoader.getJdbcUrl();
			String jdbcUsername = propertiesLoader.getJdbcUser();
			String jdbcPassword = propertiesLoader.getJdbcPassword();

			ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, jdbcUsername, jdbcPassword);
			StatisticsDAO statisticsDao = new StatisticsDAO(connectionFactory);

			request.setAttribute("totalGameCount", statisticsDao.getTotalGameCount());
			request.setAttribute("totalWinCount", statisticsDao.getTotalWinCount());
			request.setAttribute("totalLossCount", statisticsDao.getTotalLossCount());
			request.setAttribute("totalDrawCount", statisticsDao.getTotalDrawCount());

			request.setAttribute("playerRockCount", statisticsDao.getPlayerRockCount());
			request.setAttribute("playerPaperCount", statisticsDao.getPlayerPaperCount());
			request.setAttribute("playerScissorsCount", statisticsDao.getPlayerScissorsCount());

			HttpSession session = request.getSession(false);

			if (session != null && session.getAttribute("sessionUserId") != null)
			{
				UserDAO userDao = new UserDAO(connectionFactory);
				User sessionUser = userDao.find((int)session.getAttribute("sessionUserId"));

				request.setAttribute("userGameCount", statisticsDao.getTotalGameCountByUser(sessionUser));
				request.setAttribute("userWinCount", statisticsDao.getTotalWinCountByUser(sessionUser));
				request.setAttribute("userLossCount", statisticsDao.getTotalLossCountByUser(sessionUser));
				request.setAttribute("userDrawCount", statisticsDao.getTotalDrawCountByUser(sessionUser));

				request.setAttribute("userRockCount", statisticsDao.getPlayerRockCountByUser(sessionUser));
				request.setAttribute("userPaperCount", statisticsDao.getPlayerPaperCountByUser(sessionUser));
				request.setAttribute("userScissorsCount", statisticsDao.getPlayerScissorsCountByUser(sessionUser));

				GameDAO gameDao = new GameDAO(connectionFactory);

				List<Game> lastGames = gameDao.findByUser(sessionUser, 10);

				request.setAttribute("lastGames", lastGames);
			}
		}
		catch (Exception e)
		{
			throw new ServletException(e);
		}

		request.getRequestDispatcher("/WEB-INF/jsp/statistics.jsp").forward(request, response);
	}
}

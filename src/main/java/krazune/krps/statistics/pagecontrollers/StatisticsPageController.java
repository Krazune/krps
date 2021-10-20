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
import krazune.krps.user.User;
import krazune.krps.util.ConnectionFactory;
import krazune.krps.util.PropertiesLoader;

public class StatisticsPageController extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		try
		{
			PropertiesLoader propertiesLoader = (PropertiesLoader)request.getAttribute("propertiesLoader");
			String jdbcUrl = propertiesLoader.getJdbcUrl();
			String jdbcUsername = propertiesLoader.getJdbcUser();
			String jdbcPassword = propertiesLoader.getJdbcPassword();

			ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, jdbcUsername, jdbcPassword);
			GameDAO gameDao = new GameDAO(connectionFactory);

			request.setAttribute("totalGameCount", gameDao.getTotalGameCount());
			request.setAttribute("totalWinCount", gameDao.getTotalWinCount());
			request.setAttribute("totalLossCount", gameDao.getTotalLossCount());
			request.setAttribute("totalDrawCount", gameDao.getTotalDrawCount());

			request.setAttribute("playerRockCount", gameDao.getPlayerRockCount());
			request.setAttribute("playerPaperCount", gameDao.getPlayerPaperCount());
			request.setAttribute("playerScissorsCount", gameDao.getPlayerScissorsCount());

			HttpSession session = request.getSession(false);

			if (session != null && session.getAttribute("sessionUser") != null)
			{
				User sessionUser = (User)session.getAttribute("sessionUser");

				request.setAttribute("userGameCount", gameDao.getTotalGameCountByUser(sessionUser));
				request.setAttribute("userWinCount", gameDao.getTotalWinCountByUser(sessionUser));
				request.setAttribute("userLossCount", gameDao.getTotalLossCountByUser(sessionUser));
				request.setAttribute("userDrawCount", gameDao.getTotalDrawCountByUser(sessionUser));

				request.setAttribute("userRockCount", gameDao.getPlayerRockCountByUser(sessionUser));
				request.setAttribute("userPaperCount", gameDao.getPlayerPaperCountByUser(sessionUser));
				request.setAttribute("userScissorsCount", gameDao.getPlayerScissorsCountByUser(sessionUser));

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

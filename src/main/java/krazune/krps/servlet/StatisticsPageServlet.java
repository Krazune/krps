package krazune.krps.servlet;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import krazune.krps.dao.DaoException;
import krazune.krps.dao.DaoFactory;
import krazune.krps.game.GameChoice;
import krazune.krps.game.GameOutcome;
import krazune.krps.game.dao.GameDao;
import krazune.krps.user.Authentication;
import krazune.krps.user.User;

public class StatisticsPageServlet extends HttpServlet
{
	private static final String TOTAL_GAME_COUNT = "gameCount";

	private static final String TOTAL_GAME_WINS = "gameWins";
	private static final String TOTAL_GAME_LOSSES = "gameLosses";
	private static final String TOTAL_GAME_DRAWS = "gameDraws";

	private static final String TOTAL_ROCKS = "totalRocks";
	private static final String TOTAL_PAPERS = "totalPapers";
	private static final String TOTAL_SCISSORS = "totalScissors";

	private static final String USER_GAME_COUNT = "userGameCount";

	private static final String USER_GAME_WINS = "userWins";
	private static final String USER_GAME_LOSSES = "userLosses";
	private static final String USER_GAME_DRAWS = "userDraws";

	private static final String USER_ROCKS = "userRocks";
	private static final String USER_PAPERS = "userPapers";
	private static final String USER_SCISSORS = "userScissors";

	private static final Duration DURATION_BETWEEN_UPDATES = Duration.ofSeconds(120);

	private Map<String, Integer> statistics;
	private Instant statisticsUpdateInstant;
	private DaoFactory daoFactory;

	@Override
	public void init() throws ServletException
	{
		daoFactory = (DaoFactory) getServletContext().getAttribute("mainDaoFactory");

		try
		{
			statistics = getGlobalStatistics();
			statisticsUpdateInstant = Instant.now();
		}
		catch (DaoException e)
		{
			throw new ServletException(e);
		}
	}

	private Map<String, Integer> getGlobalStatistics() throws DaoException
	{
		Map<String, Integer> newStatistics = new HashMap<>();
		GameDao gameDao = daoFactory.createGameDao();

		newStatistics.put(TOTAL_GAME_COUNT, gameDao.getGameCount());

		newStatistics.put(TOTAL_GAME_WINS, gameDao.getOutcomeCount(GameOutcome.WIN));
		newStatistics.put(TOTAL_GAME_LOSSES, gameDao.getOutcomeCount(GameOutcome.LOSS));
		newStatistics.put(TOTAL_GAME_DRAWS, gameDao.getOutcomeCount(GameOutcome.DRAW));

		newStatistics.put(TOTAL_ROCKS, gameDao.getUserChoiceCount(GameChoice.ROCK));
		newStatistics.put(TOTAL_PAPERS, gameDao.getUserChoiceCount(GameChoice.PAPER));
		newStatistics.put(TOTAL_SCISSORS, gameDao.getUserChoiceCount(GameChoice.SCISSORS));

		return newStatistics;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		setupNavigationLinks(request);

		if (Instant.now().isAfter(statisticsUpdateInstant.plus(DURATION_BETWEEN_UPDATES)))
		{
			try
			{
				statistics = getGlobalStatistics();
				statisticsUpdateInstant = Instant.now();
			}
			catch (DaoException e)
			{
				throw new ServletException(e);
			}
		}

		setupGlobalStatisticsAttributes(request);

		User sessionUser = Authentication.getSessionUser(request.getSession(false));

		if (sessionUser != null)
		{
			try
			{
				request.setAttribute("showUserStatistics", true);
				setupUserStatisticsAttributes(request, sessionUser);
			}
			catch (DaoException e)
			{
				throw new ServletException(e);
			}
		}

		request.getRequestDispatcher("/WEB-INF/jsp/statistics.jsp").forward(request, response);
	}

	private void setupNavigationLinks(HttpServletRequest request)
	{
		boolean authenticated = Authentication.getSessionUser(request.getSession()) != null;

		request.setAttribute("showHomeLink", true);
		request.setAttribute("showLoginLink", !authenticated);
		request.setAttribute("showRegistrationLink", !authenticated);
		request.setAttribute("showStatisticsLink", false);
		request.setAttribute("showInformationLink", true);
		request.setAttribute("showSettingsLink", authenticated);
		request.setAttribute("showLogoutLink", authenticated);
	}

	private void setupGlobalStatisticsAttributes(HttpServletRequest request)
	{
		request.setAttribute(TOTAL_GAME_COUNT, statistics.get(TOTAL_GAME_COUNT));

		request.setAttribute(TOTAL_GAME_WINS, statistics.get(TOTAL_GAME_WINS));
		request.setAttribute(TOTAL_GAME_LOSSES, statistics.get(TOTAL_GAME_LOSSES));
		request.setAttribute(TOTAL_GAME_DRAWS, statistics.get(TOTAL_GAME_DRAWS));

		request.setAttribute(TOTAL_ROCKS, statistics.get(TOTAL_ROCKS));
		request.setAttribute(TOTAL_PAPERS, statistics.get(TOTAL_PAPERS));
		request.setAttribute(TOTAL_SCISSORS, statistics.get(TOTAL_SCISSORS));
	}

	private void setupUserStatisticsAttributes(HttpServletRequest request, User user) throws DaoException
	{
		GameDao gameDao = daoFactory.createGameDao();

		request.setAttribute(USER_GAME_COUNT, gameDao.getUserGameCount(user));

		request.setAttribute(USER_GAME_WINS, gameDao.getOutcomeCount(GameOutcome.WIN, user));
		request.setAttribute(USER_GAME_LOSSES, gameDao.getOutcomeCount(GameOutcome.LOSS, user));
		request.setAttribute(USER_GAME_DRAWS, gameDao.getOutcomeCount(GameOutcome.DRAW, user));

		request.setAttribute(USER_ROCKS, gameDao.getUserChoiceCount(GameChoice.ROCK, user));
		request.setAttribute(USER_PAPERS, gameDao.getUserChoiceCount(GameChoice.PAPER, user));
		request.setAttribute(USER_SCISSORS, gameDao.getUserChoiceCount(GameChoice.SCISSORS, user));
	}
}

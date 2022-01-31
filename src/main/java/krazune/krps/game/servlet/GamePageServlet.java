package krazune.krps.game.servlet;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import krazune.krps.dao.DaoException;
import krazune.krps.dao.DaoFactory;
import krazune.krps.game.Game;
import krazune.krps.game.GameChoice;
import krazune.krps.game.dao.GameDao;
import krazune.krps.user.Authentication;
import krazune.krps.user.User;

public class GamePageServlet extends HttpServlet
{
	private GameDao gameDao;

	@Override
	public void init() throws ServletException
	{
		gameDao = ((DaoFactory) getServletContext().getAttribute("mainDaoFactory")).createGameDao();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		setupNavigationLinks(request);
		setupSessionInformation(request);

		request.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		GameChoice userChoice = getUserChoice(request);

		if (userChoice == null)
		{
			response.sendRedirect("/");
		}

		GameChoice computerChoice = getRandomComputerChoice();
		User sessionUser = Authentication.getSessionUser(request.getSession());
		Game newGame = new Game(sessionUser, userChoice, computerChoice);

		try
		{
			gameDao.insert(newGame);
		}
		catch (DaoException e)
		{
			throw new ServletException(e);
		}

		setGameResponse(response, newGame);
	}

	private void setupNavigationLinks(HttpServletRequest request)
	{
		boolean authenticated = Authentication.getSessionUser(request.getSession()) != null;

		request.setAttribute("showHomeLink", false);
		request.setAttribute("showLoginLink", !authenticated);
		request.setAttribute("showRegistrationLink", !authenticated);
		request.setAttribute("showStatisticsLink", true);
		request.setAttribute("showInformationLink", true);
		request.setAttribute("showSettingsLink", authenticated);
		request.setAttribute("showLogoutLink", authenticated);
	}

	private void setupSessionInformation(HttpServletRequest request)
	{
		User sessionUser = Authentication.getSessionUser(request.getSession());

		request.setAttribute("showAccountWarning", sessionUser == null);
		request.setAttribute("gameUsername", (sessionUser == null) ? "Guest" : sessionUser.getUsername());
	}

	private GameChoice getUserChoice(HttpServletRequest request)
	{
		String gameChoiceString = request.getParameter("choice");

		if (gameChoiceString == null)
		{
			return null;
		}

		return GameChoice.getGameChoice(gameChoiceString);
	}

	private GameChoice getRandomComputerChoice()
	{
		int randomResult = new Random().nextInt(3);

		switch (randomResult)
		{
			case 0:
				return GameChoice.ROCK;

			case 1:
				return GameChoice.PAPER;

			default:
				return GameChoice.SCISSORS;
		}
	}

	private void setGameResponse(HttpServletResponse response, Game newGame) throws IOException
	{
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		char userChoiceChar = newGame.getUserChoice().getSymbol();
		char computerChoiceChar = newGame.getComputerChoice().getSymbol();
		char gameOutcomeChar = newGame.getOutcome().getSymbol();

		StringBuilder resultJsonString = new StringBuilder("{");

		resultJsonString.append("\"userChoice\":\"").append(userChoiceChar).append("\",");
		resultJsonString.append("\"computerChoice\":\"").append(computerChoiceChar).append("\",");
		resultJsonString.append("\"outcome\":\"").append(gameOutcomeChar).append("\"");
		resultJsonString.append("}");

		response.getWriter().print(resultJsonString);
	}
}

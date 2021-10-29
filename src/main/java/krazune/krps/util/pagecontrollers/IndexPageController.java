package krazune.krps.util.pagecontrollers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import krazune.krps.game.Game;
import krazune.krps.game.GameChoice;
import krazune.krps.game.GameDAO;
import krazune.krps.user.Authentication;
import krazune.krps.user.User;
import krazune.krps.util.ConnectionFactory;
import krazune.krps.util.PropertiesLoader;

public class IndexPageController extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		User sessionUser = Authentication.getSessionUser(request);

		if (sessionUser == null)
		{
			return;
		}

		String gameDecisionString = request.getParameter("decision");

		if (gameDecisionString == null)
		{
			response.sendRedirect("/");

			return;
		}

		GameChoice playerChoice = GameChoice.convertToChoice(gameDecisionString);

		if (playerChoice == GameChoice.UNKNOWN)
		{
			response.sendRedirect("/");

			return;
		}

		GameChoice computerChoice = getRandomGameChoice();

		try
		{
			PropertiesLoader propertiesLoader = (PropertiesLoader)request.getAttribute("propertiesLoader");
			String jdbcUrl = propertiesLoader.getJdbcUrl();
			String jdbcUsername = propertiesLoader.getJdbcUser();
			String jdbcPassword = propertiesLoader.getJdbcPassword();

			ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, jdbcUsername, jdbcPassword);

			Game newGame = new Game(sessionUser, playerChoice, computerChoice);
			GameDAO gameDao = new GameDAO(connectionFactory);

			gameDao.insert(newGame);

			setGameResponse(response, newGame);
		}
		catch (SQLException e)
		{
			throw new ServletException(e);
		}
	}

	private GameChoice getRandomGameChoice()
	{
		int choiceNumber = new Random().nextInt(3);

		switch (choiceNumber)
		{
			case 0:
			return GameChoice.ROCK;

			case 1:
			return GameChoice.PAPER;

			case 2:
			return GameChoice.SCISSORS;
		}

		return GameChoice.UNKNOWN;
	}

	private void setGameResponse(HttpServletResponse response, Game game) throws IOException
	{
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		String playerChoiceString = game.getUserChoice().toString().toLowerCase();
		String computerChoiceString = game.getComputerChoice().toString().toLowerCase();

		StringBuilder resultJsonString = new StringBuilder("{");

		resultJsonString.append("\"playerChoice\":\"").append(playerChoiceString).append("\",");
		resultJsonString.append("\"computerChoice\":\"").append(computerChoiceString).append("\",");
		resultJsonString.append("\"result\":\"").append(game.getResult().toString().toLowerCase()).append("\"");
		resultJsonString.append("}");

		response.getWriter().print(resultJsonString);
	}
}

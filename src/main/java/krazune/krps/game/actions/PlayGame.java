package krazune.krps.game.actions;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import krazune.krps.game.Game;
import krazune.krps.game.GameChoice;
import krazune.krps.game.GameDAO;
import krazune.krps.user.User;
import krazune.krps.user.UserDAO;
import krazune.krps.util.ConnectionFactory;
import krazune.krps.util.PropertiesLoader;

public class PlayGame extends HttpServlet
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String gameDecisionString = request.getParameter("decision");

		if (gameDecisionString == null)
		{
			response.sendRedirect("/");

			return;
		}

		GameChoice playerChoice;

		if (gameDecisionString.equalsIgnoreCase("rock"))
		{
			playerChoice = GameChoice.ROCK;
		}
		else if (gameDecisionString.equalsIgnoreCase("paper"))
		{
			playerChoice = GameChoice.PAPER;
		}
		else if (gameDecisionString.equalsIgnoreCase("scissors"))
		{
			playerChoice = GameChoice.SCISSORS;
		}
		else
		{
			response.sendRedirect("/");

			return;
		}

		int computerChoiceInteger = new Random().nextInt(3);
		GameChoice computerChoice = GameChoice.UNKNOWN;

		switch (computerChoiceInteger)
		{
			case 0:
			computerChoice = GameChoice.ROCK;
			break;

			case 1:
			computerChoice = GameChoice.PAPER;
			break;

			case 2:
			computerChoice = GameChoice.SCISSORS;
			break;
		}

		HttpSession session = request.getSession(true);
		int sessionUserId = (Integer)session.getAttribute("sessionUserId");

		try
		{
			String jdbcUrl = propertiesLoader.getJdbcUrl();
			String jdbcUsername = propertiesLoader.getJdbcUser();
			String jdbcPassword = propertiesLoader.getJdbcPassword();

			ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, jdbcUsername, jdbcPassword);
			UserDAO userDao = new UserDAO(connectionFactory);

			User sessionUser = userDao.find(sessionUserId);

			Game newGame = new Game(sessionUser, playerChoice, computerChoice);
			GameDAO gameDao = new GameDAO(connectionFactory);

			gameDao.insert(newGame);

			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");

			StringBuilder resultJsonString = new StringBuilder("{");

			resultJsonString.append("\"playerChoice\":\"" + newGame.getUserChoice().toString().toLowerCase() + "\",");
			resultJsonString.append("\"computerChoice\":\"" + newGame.getCPUChoice().toString().toLowerCase() + "\",");
			resultJsonString.append("\"result\":\"" + newGame.getResult().toString().toLowerCase() + "\"");

			resultJsonString.append("}");

			response.getWriter().print(resultJsonString);
		}
		catch (Exception e)
		{
			throw new ServletException(e);
		}
	}
}

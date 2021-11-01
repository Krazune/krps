/*
	MIT License

	Copyright (c) 2021 Miguel Sousa

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.
*/
package krazune.krps.user;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import krazune.krps.util.ConnectionFactory;

public class Authentication
{
	public static User logIn(HttpServletRequest request, ConnectionFactory connectionFactory, String username, String password) throws SQLException
	{
		UserDAO userDao = new UserDAO(connectionFactory);
		User user = userDao.findByName(username);

		if (user == null)
		{
			return null;
		}

		if (!validPassword(user, password))
		{
			return null;
		}

		createUserSession(request, user);

		return user;
	}

	public static void logOut(HttpSession session)
	{
		if (session != null)
		{
			session.removeAttribute("sessionUser");
		}
	}

	public static void createUserSession(HttpServletRequest request, User user)
	{
		HttpSession session = request.getSession(true);

		session.setAttribute("sessionUser", user);
	}

	public static User getSessionUser(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);

		if (session == null)
		{
			return null;
		}

		return (User)session.getAttribute("sessionUser");
	}

	public static String getPasswordHash(String password, int saltSize, int hashSize, int iterations, int memory, int parallelism)
	{
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, saltSize, hashSize);
		char[] passwordCharArray = password.toCharArray();
		String hash = argon2.hash(iterations, memory, parallelism, passwordCharArray);

		argon2.wipeArray(passwordCharArray);

		return hash;
	}

	public static boolean validPassword(User user, String password)
	{
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		char[] passwordArray = password.toCharArray();
		boolean validPassword = argon2.verify(user.getPasswordHash(), passwordArray);

		argon2.wipeArray(passwordArray);

		return validPassword;
	}
}
/*
 * MIT License
 *
 * Copyright (c) 2022 Miguel Sousa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package krazune.krps.user;

import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import krazune.krps.dao.DaoException;
import krazune.krps.hash.HashGenerator;
import krazune.krps.user.dao.UserDao;

public class Authentication
{
	public static User authenticateUser(HttpSession session, UserDao userDao, HashGenerator hashGenerator, String username, String password) throws SQLException
	{
		User user = null;
		try
		{
			user = userDao.get(username);
		}
		catch (DaoException e)
		{
			throw new SQLException(e);
		}

		if (user == null)
		{
			return null;
		}

		if (!hashGenerator.verify(password, user.getPasswordHash()))
		{
			return null;
		}

		createUserSession(session, user);

		return user;
	}

	public static void createUserSession(HttpSession session, User user)
	{
		session.setAttribute("sessionUser", user);
	}

	public static void destroyUserSession(HttpSession session)
	{
		if (session == null)
		{
			return;
		}

		session.removeAttribute("sessionUser");
	}

	public static User getSessionUser(HttpSession session)
	{
		if (session == null)
		{
			return null;
		}

		return (User)session.getAttribute("sessionUser");
	}
}

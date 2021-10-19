package krazune.krps.user.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionRedirectFilter implements Filter
{
	private String redirectUrl;
	private boolean redirectOnUserSession = false;
	private ArrayList<String> urlList;

	public void init(FilterConfig filterConfig) throws ServletException
	{
		redirectUrl = filterConfig.getInitParameter("redirect-url");

		String redirectOnUserSessionParameter = filterConfig.getInitParameter("redirect-on-user-session");

		if (redirectOnUserSessionParameter != null)
		{
			redirectOnUserSession = redirectOnUserSessionParameter.equalsIgnoreCase("true");
		}

		String urlListParameter = filterConfig.getInitParameter("url-list-parameter");

		if (urlListParameter != null)
		{
			StringTokenizer token = new StringTokenizer(urlListParameter, ",");

			urlList = new ArrayList<String>();

			while (token.hasMoreTokens())
			{
				urlList.add(token.nextToken());
			}
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest)request;

		if (!urlList.contains(httpRequest.getServletPath()))
		{
			chain.doFilter(request, response);

			return;
		}

		HttpSession session = httpRequest.getSession(false);
		boolean userSessionExists = session != null && session.getAttribute("sessionUserId") != null && session.getAttribute("sessionUserName") != null;

		if (redirectOnUserSession != userSessionExists)
		{
			chain.doFilter(request, response);

			return;
		}

		HttpServletResponse httpResponse = (HttpServletResponse)response;

		httpResponse.sendRedirect(redirectUrl);
	}

	public void destroy()
	{
	}
}

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
import krazune.krps.user.Authentication;

public class SessionRedirectFilter implements Filter
{
	private String redirectUrl;
	private boolean redirectOnUserSession = false;
	private ArrayList<String> urlList = new ArrayList<>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		redirectUrl = filterConfig.getInitParameter("redirect-url");

		loadRedirectOnUserSessionParameter(filterConfig);
		loadUrlListParameter(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (!urlList.contains(httpRequest.getServletPath()))
		{
			chain.doFilter(request, response);

			return;
		}

		boolean userSessionExists = Authentication.getSessionUser(httpRequest.getSession()) != null;

		if (redirectOnUserSession != userSessionExists)
		{
			chain.doFilter(request, response);

			return;
		}

		HttpServletResponse httpResponse = (HttpServletResponse) response;

		httpResponse.sendRedirect(redirectUrl);
	}

	@Override
	public void destroy()
	{
	}

	private void loadRedirectOnUserSessionParameter(FilterConfig filterConfig)
	{
		String redirectOnUserSessionParameter = filterConfig.getInitParameter("redirect-on-user-session");

		if (redirectOnUserSessionParameter != null)
		{
			redirectOnUserSession = redirectOnUserSessionParameter.equalsIgnoreCase("true");
		}
	}

	private void loadUrlListParameter(FilterConfig filterConfig)
	{
		String urlListParameter = filterConfig.getInitParameter("url-list-parameter");

		if (urlListParameter != null)
		{
			StringTokenizer token = new StringTokenizer(urlListParameter, ",");

			urlList = new ArrayList<>();

			while (token.hasMoreTokens())
			{
				urlList.add(token.nextToken());
			}
		}
	}
}

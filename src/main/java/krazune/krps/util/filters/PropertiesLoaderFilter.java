package krazune.krps.util.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import krazune.krps.util.PropertiesLoader;

public class PropertiesLoaderFilter implements Filter
{
	private PropertiesLoader propertiesLoader;

	public void init(FilterConfig filterConfig) throws ServletException
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

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		request.setAttribute("propertiesLoader", propertiesLoader);

		chain.doFilter(request, response);
	}

	public void destroy()
	{
	}
}

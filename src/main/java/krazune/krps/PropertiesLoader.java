package krazune.krps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader
{
	private final String propertiesPath = "krps.properties";

	private String jdbcUrl;
	private String jdbcUser;
	private String jdbcPassword;

	public void load() throws IOException
	{
		Properties properties = new Properties();
		ClassLoader classLoader = getClass().getClassLoader();

		try (InputStream propertiesStream = classLoader.getResourceAsStream(propertiesPath))
		{
			if (propertiesStream == null)
			{
				throw new FileNotFoundException();
			}

			properties.load(propertiesStream);
		}

		jdbcUrl = properties.getProperty("jdbcUrl");
		jdbcUser = properties.getProperty("jdbcUser");
		jdbcPassword = properties.getProperty("jdbcPassword");
	}

	public String getJdbcUrl()
	{
		return jdbcUrl;
	}

	public String getJdbcUser()
	{
		return jdbcUser;
	}

	public String getJdbcPassword()
	{
		return jdbcPassword;
	}
}
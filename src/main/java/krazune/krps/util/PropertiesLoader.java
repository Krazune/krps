package krazune.krps.util;

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

	private int argon2SaltSize;
	private int argon2HashSize;
	private int argon2Iterations;
	private int argon2IMemory;
	private int argon2Parallelism;

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

		argon2SaltSize = Integer.parseInt(properties.getProperty("argon2SaltSize"));
		argon2HashSize = Integer.parseInt(properties.getProperty("argon2HashSize"));
		argon2Iterations = Integer.parseInt(properties.getProperty("argon2Iterations"));
		argon2IMemory = Integer.parseInt(properties.getProperty("argon2IMemory"));
		argon2Parallelism = Integer.parseInt(properties.getProperty("argon2Parallelism"));
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

	public int getArgon2SaltSize()
	{
		return argon2SaltSize;
	}

	public int getArgon2HashSize()
	{
		return argon2HashSize;
	}

	public int getArgon2Iterations()
	{
		return argon2Iterations;
	}

	public int getArgon2Memory()
	{
		return argon2IMemory;
	}

	public int getArgon2Parallelism()
	{
		return argon2Parallelism;
	}
}
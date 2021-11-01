package krazune.krps.util;

public class PropertiesLoader
{
	private String jdbcUrl;
	private String jdbcUser;
	private String jdbcPassword;

	private int argon2SaltSize;
	private int argon2HashSize;
	private int argon2Iterations;
	private int argon2IMemory;
	private int argon2Parallelism;

	public void load()
	{
		jdbcUrl = System.getenv("jdbcUrl");
		jdbcUser = System.getenv("jdbcUser");
		jdbcPassword = System.getenv("jdbcPassword");

		argon2SaltSize = Integer.parseInt(System.getenv("argon2SaltSize"));
		argon2HashSize = Integer.parseInt(System.getenv("argon2HashSize"));
		argon2Iterations = Integer.parseInt(System.getenv("argon2Iterations"));
		argon2IMemory = Integer.parseInt(System.getenv("argon2IMemory"));
		argon2Parallelism = Integer.parseInt(System.getenv("argon2Parallelism"));
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
package krazune.krps.user;

import java.sql.Timestamp;

public class User
{
	private int id;
	private String username;
	private String passwordHash;
	private Timestamp creationDate;

	public User(int id, String username, String passwordHash, Timestamp creationDate)
	{
		this.id = id;
		this.username = username;
		this.passwordHash = passwordHash;
		this.creationDate = creationDate;
	}

	public User()
	{
	}

	public User(String username, String passwordHash)
	{
		this.username = username;
		this.passwordHash = passwordHash;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPasswordHash()
	{
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash)
	{
		this.passwordHash = passwordHash;
	}

	public Timestamp getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate)
	{
		this.creationDate = creationDate;
	}
}

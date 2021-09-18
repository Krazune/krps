package krazune.krps;

import java.sql.Timestamp;

public class User
{
	private int id;
	private String name;
	private String passwordHash;
	private Timestamp creationDate;

	public User()
	{
	}

	public User(String name, String passwordHash)
	{
		this.name = name;
		this.passwordHash = passwordHash;
	}

	public User(int id, String name, String passwordHash, Timestamp creationDate)
	{
		this.id = id;
		this.name = name;
		this.passwordHash = passwordHash;
		this.creationDate = creationDate;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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
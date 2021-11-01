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
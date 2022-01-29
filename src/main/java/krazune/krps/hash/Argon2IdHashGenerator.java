package krazune.krps.hash;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class Argon2IdHashGenerator implements HashGenerator
{
	private int saltSize;
	private int hashSize;

	private int iterations;
	private int memory;
	private int parallelism;

	public Argon2IdHashGenerator(int saltSize, int hashSize, int iterations, int memory, int parallelism)
	{
		this.saltSize = saltSize;
		this.hashSize = hashSize;
		this.iterations = iterations;
		this.memory = memory;
		this.parallelism = parallelism;
	}

	@Override
	public String generate(String input)
	{
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, saltSize, hashSize);
		char[] passwordCharArray = input.toCharArray();
		String hash = argon2.hash(iterations, memory, parallelism, passwordCharArray);

		argon2.wipeArray(passwordCharArray);

		return hash;
	}

	@Override
	public boolean verify(String input, String hash)
	{
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		char[] passwordArray = input.toCharArray();
		boolean validPassword = argon2.verify(hash, passwordArray);

		argon2.wipeArray(passwordArray);

		return validPassword;
	}
}

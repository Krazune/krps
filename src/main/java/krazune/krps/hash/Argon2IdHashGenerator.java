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

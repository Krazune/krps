package krazune.krps.hash;

public interface HashGenerator
{
	String generate(String input);
	boolean verify(String input, String hash);
}

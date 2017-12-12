package se.anders_raberg.adventofcode.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Day4b {
	private static final Logger LOGGER = Logger.getLogger(Day4b.class.getName());

	public static void run(String inputPath) throws IOException  {
		try (Stream<String> lines = Files.lines(Paths.get(inputPath + "/input4.txt"))){
			long validPassphrases = lines.filter(Day4b::passphraseValid).count();
			LOGGER.log(Level.INFO, "Valid pass phrases : {0}\n", validPassphrases);
		} 

	}

	private static boolean passphraseValid(String passPhrase) {
		String[] words = passPhrase.split("\\s+");
		return Arrays.stream(words)
				.map(Day4b::toCharacterSet)
				.distinct()
				.count() == words.length; 

	}
	
	private static Set<String> toCharacterSet(String s) {
		return new HashSet<>(Arrays.asList(s.split("")));
	}
}

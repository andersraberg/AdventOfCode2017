package se.anders_raberg.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * @author anders
 * 
 * Supply the file with the data as args[0]
 *
 */
public class Day4b {
	private static final Logger LOGGER = Logger.getLogger(Day4b.class.getName());

	public static void main(String[] args) throws IOException  {
		try (Stream<String> lines = Files.lines(Paths.get(args[0]))){
			long validPassphrases = lines.filter(Day4b::passphraseValid).count();
			LOGGER.log(Level.INFO, "Valid pass phrases : {0}", validPassphrases);
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

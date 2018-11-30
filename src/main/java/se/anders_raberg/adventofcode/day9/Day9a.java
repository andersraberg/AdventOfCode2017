package se.anders_raberg.adventofcode.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class Day9a {
	private static final Logger LOGGER = Logger.getLogger(Day9a.class.getName());

	public static void run(String inputPath) throws IOException {
		byte[] input = Files.readAllBytes(Paths.get(inputPath + "/input9.txt"));

		boolean inGarbage = false;
		int i = 0;
		int level = 0;
		int score = 0;
		while (i < input.length) {
			byte c = input[i];
			if (inGarbage) {
				if (c == '>') {
					inGarbage = false;
				}

				if (c == '!') {
					i++;
				}
			} else {
				if (c == '<') {
					inGarbage = true;
				} else {
					if (c == '{') {
						level++;
					}
					
					if (c == '}') {
						score = score + level;
						level--;
					}
				}
			}

			i++;
		}
		LOGGER.info("Score: " + score);
	}

}
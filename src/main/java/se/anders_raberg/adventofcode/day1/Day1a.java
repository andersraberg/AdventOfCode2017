package se.anders_raberg.adventofcode.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Day1a {
	private static final Logger LOGGER = Logger.getLogger(Day1a.class.getName());

	public static void run(String inputPath) throws IOException {
		String rawData = new String(Files.readAllBytes(Paths.get(inputPath + "/input1.txt"))).trim();
		
		List<Integer> digits = Arrays.stream(rawData.split(""))
				.map(Integer::valueOf)
				.collect(Collectors.toList());

		int size = digits.size();
		int sum = 0;

		for (int i = 0; i < size; i++) {
			if (digits.get((i + 1) % size) == digits.get(i)) {
				sum = sum + digits.get(i);
			}
		}
		
		LOGGER.log(Level.INFO, "Sum : {0}\n", sum);
	}
}

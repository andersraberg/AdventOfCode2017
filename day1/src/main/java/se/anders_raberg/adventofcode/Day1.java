package se.anders_raberg.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author anders
 * 
 * Supply the file with the data as args[0]
 *
 */
public class Day1 {
	private static final Logger LOGGER = Logger.getLogger(Day1.class.getName());

	public static void main(String[] args) throws IOException {
		String rawData = new String(Files.readAllBytes(Paths.get(args[0]))).trim();
		
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
		
		LOGGER.log(Level.INFO, "Sum : {0}", sum);
	}
}

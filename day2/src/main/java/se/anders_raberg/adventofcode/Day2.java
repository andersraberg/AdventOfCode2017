package se.anders_raberg.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author anders
 * 
 * Supply the file with the data as args[0]
 *
 */
public class Day2 {
	private static final Logger LOGGER = Logger.getLogger(Day2.class.getName());

	public static void main(String[] args) throws IOException {
		try (Stream<String> lines = Files.lines(Paths.get(args[0]))){

			// Parse file
			List<List<Integer>> table = lines.map(line -> Arrays.stream(line.split("\\s+"))
					.map(Integer::valueOf)
					.collect(Collectors.toList()))
					.collect(Collectors.toList());


			// Calculate checksum
			Integer checksum = table.stream().mapToInt(Day2::processLine).sum();

			LOGGER.log(Level.INFO, "Checksum : {0}", checksum);
		} 

	}
	
	private static Integer processLine(List<Integer> line) {
		for (int a : line) {
			for (int b : line) {
				if (a != b && a % b == 0) {
					return a / b;
				}
			}
		}
		
		throw new IllegalArgumentException("No suitable numbers found");
	}
}

package se.anders_raberg.adventofcode.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2a {
	private static final Logger LOGGER = Logger.getLogger(Day2a.class.getName());

	public static void run(String inputPath) throws IOException {
		try (Stream<String> lines = Files.lines(Paths.get(inputPath + "/input2.txt"))){

			// Parse file
			List<List<Integer>> table = lines.map(line -> Arrays.stream(line.split("\\s+"))
					.map(Integer::valueOf)
					.collect(Collectors.toList()))
					.collect(Collectors.toList());


			// Calculate checksum
			Integer checksum = table.stream()
					.map(line -> 
					line.stream().max(Integer::compare).get() - 
					line.stream().min(Integer::compare).get())
					.reduce(0, (result, element) -> result + element);

			LOGGER.log(Level.INFO, "Checksum : {0}\n", checksum);
		} 

	}
}

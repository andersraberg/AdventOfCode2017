package se.anders_raberg.adventofcode.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Day11b {
	private static final Logger LOGGER = Logger.getLogger(Day11b.class.getName());

	public static void run(String inputPath) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get(inputPath + "/input11.txt"))).trim();

		List<String> steps = Arrays.stream(input.split(","))
				.collect(Collectors.toList());


		int north = 0;
		int east = 0;
		int maxDistance = 0;
		
		for (String step : steps) {
			switch (step) {
			case "n":
				north = north + 2;
				break;

			case "s":
				north = north - 2;
				break;

			case "ne":
				north = north + 1;
				east = east + 1;
				break;

			case "nw":
				north = north + 1;
				east = east - 1;
				break;

			case "se":
				north = north - 1;
				east = east + 1;
				break;

			case "sw":
				north = north - 1;
				east = east - 1;
				break;

			default:
				throw new IllegalArgumentException(step);
			}

			int diag = (Math.abs(north) - Math.abs(east)) / 2;
			int distance = diag + Math.abs(east);
			
			maxDistance = Math.max(maxDistance, distance);
		}


		LOGGER.info("Max distance: " + maxDistance);

	}
}
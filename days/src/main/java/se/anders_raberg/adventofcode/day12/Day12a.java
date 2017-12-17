package se.anders_raberg.adventofcode.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12a {
	private static final Logger LOGGER = Logger.getLogger(Day12a.class.getName());

	public static void run(String inputPath) throws IOException {
		Map<Integer, List<Integer>> programs = new HashMap<>();

		try (Stream<String> input = Files.lines(Paths.get(inputPath + "/input12.txt"))) {
			input.forEach(l -> {
				List<Integer> parsedLine = Arrays.stream(l.split("\\W+")).map(Integer::parseInt)
						.collect(Collectors.toList());

				programs.put(parsedLine.get(0), parsedLine.subList(1, parsedLine.size()));
			});
		}

		Set<Integer> found = new HashSet<>();
		findPrograms(0, programs, found);
		LOGGER.info("Result: " + found.size());
	}

	private static void findPrograms(Integer p,  Map<Integer, List<Integer>> programs, Set<Integer> foundPrograms) {
		if (foundPrograms.add(p)) {
			programs.get(p).forEach(a -> findPrograms(a, programs, foundPrograms));
		}
	}

}
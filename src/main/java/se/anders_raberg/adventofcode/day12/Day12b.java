package se.anders_raberg.adventofcode.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12b {
	private static final Logger LOGGER = Logger.getLogger(Day12b.class.getName());

	public static void run(String inputPath) throws IOException {
		Map<Integer, List<Integer>> programs = new HashMap<>();

		try (Stream<String> input = Files.lines(Paths.get(inputPath + "/input12.txt"))) {
			input.forEach(l -> {
				List<Integer> parsedLine = Arrays.stream(l.split("\\W+")).map(Integer::parseInt)
						.collect(Collectors.toList());

				programs.put(parsedLine.get(0), parsedLine.subList(1, parsedLine.size()));
			});
		}

		int counter = 0;
		while (!programs.isEmpty()) {
			Set<Integer> found = new HashSet<>();
			Integer start = new ArrayList<>(programs.keySet()).get(0);
			findPrograms(start, programs, found);
			found.forEach(programs::remove);
			counter++;
		}
		
		LOGGER.info("Counter: " + counter);
	}

	private static void findPrograms(Integer p,  Map<Integer, List<Integer>> programs, Set<Integer> foundPrograms) {
		if (foundPrograms.add(p)) {
			programs.get(p).forEach(a -> findPrograms(a, programs, foundPrograms));
		}
	}

}
package se.anders_raberg.adventofcode.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7a {
	private static final Logger LOGGER = Logger.getLogger(Day7a.class.getName());

	public static void run(String inputPath) throws IOException {
		Map<String, Program> programs = new HashMap<>();

		try (Stream<String> input = Files.lines(Paths.get(inputPath + "/input7.txt"))){
			input.forEach(s -> {
				String[] splitString = s.split("\\W+");
				programs.put(splitString[0], new Program(Integer.parseInt(splitString[1]),
						Arrays.asList(Arrays.copyOfRange(splitString, 2, splitString.length))));
			});
		} 

		List<String> programNamesNotAtRoot = programs.values().stream()
				.map(Program::getSubprograms)
				.flatMap(List::stream)
				.collect(Collectors.toList());

		Set<String> allProgramNames = programs.keySet();
		
		allProgramNames.removeAll(programNamesNotAtRoot);
		LOGGER.log(Level.INFO, "Result: {0}\n", allProgramNames);
	}

}

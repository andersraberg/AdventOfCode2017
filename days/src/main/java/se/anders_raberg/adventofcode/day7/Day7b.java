package se.anders_raberg.adventofcode.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7b {
	private static final Logger LOGGER = Logger.getLogger(Day7b.class.getName());

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

		List<String> allProgramNames = new ArrayList<>(programs.keySet());

		allProgramNames.removeAll(programNamesNotAtRoot);

		String rootName = allProgramNames.get(0);

		treeWeight(programs, programs.get(rootName));
	}


	private static int treeWeight(Map<String, Program> programs, Program program) {
		List<String> subtree = program.getSubprograms();
		
		List<Integer> subtreeWeights = subtree
				.stream()
				.map(s -> treeWeight(programs, programs.get(s)))
				.collect(Collectors.toList());

		if (subtreeWeights.stream().distinct().count() > 1) {
			LOGGER.log(Level.INFO, "{0}", prettyPrintSubtree(programs, subtree, subtreeWeights));
			System.exit(0);
		}

		return program.getWeight() + subtreeWeights.stream().mapToInt(Integer::valueOf).sum();
	}

	private static String prettyPrintSubtree(Map<String, Program> programs, List<String> subtree, List<Integer> subtreeWeights) {
		StringBuilder sb = new StringBuilder("Unbalance: ");
		
		for (int i = 0; i < subtree.size(); i++) {
			int weight = programs.get(subtree.get(i)).getWeight();
			sb.append(subtree.get(i) + " (" + weight + "/" + subtreeWeights.get(i) + ") ");
		}
		
		return sb.toString();
	}
}

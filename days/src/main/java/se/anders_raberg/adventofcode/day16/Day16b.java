package se.anders_raberg.adventofcode.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Day16b {
	private static final Logger LOGGER = Logger.getLogger(Day16b.class.getName());

	public static void run(String inputPath) throws IOException {
		List<String> programs = Arrays.stream("abcdefghijklmnop".split("")).collect(Collectors.toList());
		String input = new String(Files.readAllBytes(Paths.get(inputPath + "/input16.txt"))).trim();
		List<String> moves = Arrays.stream(input.split(","))
				.collect(Collectors.toList());

		// A test run gave that the program pattern repeats in cycles of 48
		// therefore we only need to a number of times equal to the remainder of
		// 1_000_000_000 divided by 48 .
		for (int i = 0; i < 1_000_000_000 % 48; i++) {
			programs = doDance(programs, moves);
		}

		LOGGER.info("Result: " + programs.stream().reduce("", (res, item) -> res + item));
	}

	private static List<String> doDance(List<String> programs, List<String> moves) {
		List<String> result = programs;
		for (String move : moves) {
			if (move.substring(0, 1).equals("s")) {
				result = spin(result, Integer.parseInt(move.substring(1, move.length())));
			}

			if (move.substring(0, 1).equals("x")) {
				String[] positions = move.substring(1, move.length()).split("/");
				result = exchange(result, Integer.parseInt(positions[0]), Integer.parseInt(positions[1]));
			}

			if (move.substring(0, 1).equals("p")) {
				String[] positions = move.substring(1, move.length()).split("/");
				result = partner(result, positions[0], positions[1]);
			}
		}
		return result;
	}

	private static List<String> spin(List<String> programs, int number) {
		List<String> result = new ArrayList<>();
		result.addAll(programs.subList(programs.size() - number, programs.size()));
		result.addAll(programs.subList(0, programs.size() - number));
		return result;
	}

	private static List<String> exchange(List<String> programs, int posA, int posB) {
		List<String> result = new ArrayList<>(programs);
		result.set(posA, programs.get(posB));
		result.set(posB, programs.get(posA));
		return result;
	}

	private static List<String> partner(List<String> programs, String progA, String progB) {
		int posA = programs.indexOf(progA);
		int posB = programs.indexOf(progB);
		return exchange(programs, posA, posB);
	}
}
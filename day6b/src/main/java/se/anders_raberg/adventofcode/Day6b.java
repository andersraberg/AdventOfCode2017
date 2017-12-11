package se.anders_raberg.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author anders
 * 
 * Supply the file with the data as args[0]
 *
 */
public class Day6b {
	private static final Logger LOGGER = Logger.getLogger(Day6b.class.getName());

	public static void main(String[] args) throws IOException {
		String line = new String(Files.readAllBytes((Paths.get(args[0]))));

		List<Integer> banks = Arrays.stream(line.split("\\s+"))
				.map(Integer::valueOf)
				.collect(Collectors.toList());


		Set<List<Integer>> configurations = new HashSet<>();
		for (int i = 0; i < 2; i++) {
			configurations.clear();
			while (configurations.add(banks)) {
				int index = findIndexWithMaxBlocks(banks);
				int blocks = banks.get(index);
				banks.set(index, 0);
				distributeBlocks(banks, index, blocks);
			} 
		}
		LOGGER.log(Level.INFO, "Cycles: {0}", configurations.size());
	}

	private static int findIndexWithMaxBlocks(List<Integer> banks) {
		int index = 0;
		for (int i = 0; i < banks.size(); i++) {
			if (banks.get(i) > banks.get(index)) {
				index = i;
			}
		}
		return index;
	}

	private static void distributeBlocks(List<Integer> banks, int startIndex, int blocks) {
		int index = startIndex;
		for (int i = 0; i < blocks; i++) {
			index = (index + 1) % banks.size();
			banks.set(index, banks.get(index) + 1);
		}
	}
}

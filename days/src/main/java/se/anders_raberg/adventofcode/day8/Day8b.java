package se.anders_raberg.adventofcode.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8b {
	private static final Logger LOGGER = Logger.getLogger(Day8b.class.getName());

	public static void run(String inputPath) throws IOException {

		try (Stream<String> input = Files.lines(Paths.get(inputPath + "/input8.txt"))){
			List<Instruction> instructions = input.map(Instruction::new)
					.collect(Collectors.toList());

			
			int totalMax = 0;
			Map<String, Integer> registers = new HashMap<>();
			for (Instruction instruction : instructions) {
				instruction.apply(registers);
				int max = registers.values().stream().max(Integer::compare).get();
				totalMax = Math.max(totalMax, max);
			}
			
			LOGGER.info("Total max: " + totalMax);
		}
	}
	

}
package se.anders_raberg.adventofcode.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day10a {
	private static final Logger LOGGER = Logger.getLogger(Day10a.class.getName());

	public static void run(String inputPath) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get(inputPath + "/input10.txt"))).trim();

		List<Integer> lengths = Arrays.stream(input.split(","))
				.map(Integer::valueOf)
				.collect(Collectors.toList());
		
		int currentPos = 0;
		int skipSize = 0;
		
		int[] array = IntStream.range(0, 256).toArray();
		
		for (int len : lengths) {
			reverse(array, currentPos, len);
			currentPos = (currentPos + len + skipSize) % array.length;
			skipSize++;
		}
		
		LOGGER.info("Result: " + array[0] * array[1]);
	}
	
	private static void reverse(int[] array, int pos, int len) {
		for (int i = 0; i < len / 2; i++) {
			int posA = (pos + i) % array.length;
			int posB = (pos + len - 1 - i) % array.length;
			int tmp = array[posA];
			array[posA] = array[posB];
			array[posB] = tmp;
		}
	}

}
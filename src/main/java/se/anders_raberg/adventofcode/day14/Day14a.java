package se.anders_raberg.adventofcode.day14;

import java.util.Arrays;
import java.util.logging.Logger;

public class Day14a {
	private static final Logger LOGGER = Logger.getLogger(Day14a.class.getName());
	private static final String INPUT = "hfdlxzhv";
	public static void run() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 128; i++) {
			sb.append(KnotHash.getHash(INPUT + "-" + i));
		}
		
		long count = Arrays.stream(sb.toString().split("")).filter(s -> s.equals("1")).count();
		LOGGER.info("Count: " + count);
	}
}
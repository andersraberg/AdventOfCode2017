package se.anders_raberg.adventofcode.day15;

import java.util.logging.Logger;

public class Day15b {
	private static final Logger LOGGER = Logger.getLogger(Day15b.class.getName());
	private static final Long GEN_A_START = 703L;
	private static final Long GEN_B_START = 516L;
	private static final Long GEN_A_FACTOR = 16807L;
	private static final Long GEN_B_FACTOR = 48271L;
	private static final Long DIVISOR = 2147483647L;
	
	public static void run() {
		long matchCounter = 0;
		long genA = GEN_A_START;
		long genB = GEN_B_START;
		for (int i = 0; i < 5_000_000; i++) {
			do {
				genA = genA * GEN_A_FACTOR % DIVISOR;
			} while (genA % 4 != 0);
			
			do {
				genB = genB * GEN_B_FACTOR % DIVISOR;
			} while (genB % 8 != 0);
			
			if ((genA & 0xFFFF) == (genB & 0xFFFF)) {
				matchCounter++;
			}
		}
		
		LOGGER.info("Matches: " + matchCounter);
	}
}
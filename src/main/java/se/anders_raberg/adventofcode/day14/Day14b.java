package se.anders_raberg.adventofcode.day14;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class Day14b {
	private static final int SIZE = 128;
	private static final Logger LOGGER = Logger.getLogger(Day14b.class.getName());
	private static final String INPUT = "hfdlxzhv";

	public static void run() {
		int[][] matrix = new int[SIZE][SIZE];
		for (int y = 0; y < SIZE; y++) {
			String[] row = KnotHash.getHash(INPUT + "-" + y).split("");
			for (int x = 0; x < SIZE; x++) {
				matrix[y][x] = Integer.parseInt(row[x]);
			}
		}

		int region = 2;  // 0 -> free, 1 -> used but not yet in a region
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				if (matrix[y][x] == 1) {
					matrix[y][x] = region;
					setNeighbours(matrix, y, x);
					region++;
				}
			}
		} 
		
		Set<Integer> regions = new HashSet<>();
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				int value = matrix[y][x];
				if (value != 0) {
					regions.add(value);
				}
			}
		}
		
		LOGGER.info("Number of regions: " + regions.size());
		
	}

	private static void setNeighbours(int[][] matrix, int y, int x) {
		int current = matrix[y][x];

		if (x + 1 < SIZE && matrix[y][x + 1] == 1) {
			matrix[y][x + 1] = current;
			setNeighbours(matrix, y, x + 1);
		}

		if (x - 1 >= 0 && matrix[y][x - 1] == 1) {
			matrix[y][x - 1] = current;
			setNeighbours(matrix, y, x - 1);
		}

		if (y + 1 < SIZE && matrix[y + 1][x] == 1) {
			matrix[y + 1][x] = current;
			setNeighbours(matrix, y + 1, x);
		}

		if (y - 1 >= 0 && matrix[y - 1][x] == 1) {
			matrix[y -1][x] = current;
			setNeighbours(matrix, y - 1, x);
		}
	}

}
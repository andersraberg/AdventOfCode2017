package se.anders_raberg.adventofcode.day3;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Day3b {
	private static final Logger LOGGER = Logger.getLogger(Day3b.class.getName());

	private static final Position RIGHT_STEP = new Position(1, 0);
	private static final Position UP_STEP = new Position(0, 1);
	private static final Position LEFT_STEP = new Position(-1, 0);
	private static final Position DOWN_STEP = new Position(0, -1);

	private static final int TARGET_VALUE = 347991;

	public static void run() {
		Position pos = new Position(0, 0);
		Map<Position, Integer> values = new HashMap<>();
		values.put(pos, 1);

		int i = 1;
		while (pos != null) {
			if (i % 2 != 0) {
				pos = doSteps(values, pos, RIGHT_STEP, i);
				pos = doSteps(values, pos, UP_STEP, i);
			} else {
				pos = doSteps(values, pos, LEFT_STEP, i);
				pos = doSteps(values, pos, DOWN_STEP, i);
			}

			i++;			
		}

	}

	private static Position doSteps(Map<Position, Integer> values, Position pos, Position step, int number) {
		Position newPos = pos;
		for (int i = 0; i < number && pos != null; i++) {
			newPos = newPos.add(step);

			Integer valueAtpos = 
					values.getOrDefault(newPos.add(RIGHT_STEP), 0) +
					values.getOrDefault(newPos.add(RIGHT_STEP).add(UP_STEP), 0) +
					values.getOrDefault(newPos.add(UP_STEP), 0) +
					values.getOrDefault(newPos.add(UP_STEP).add(LEFT_STEP), 0) +
					values.getOrDefault(newPos.add(LEFT_STEP), 0) +
					values.getOrDefault(newPos.add(LEFT_STEP).add(DOWN_STEP), 0) +
					values.getOrDefault(newPos.add(DOWN_STEP), 0) +
					values.getOrDefault(newPos.add(DOWN_STEP).add(RIGHT_STEP), 0);

			values.put(newPos, valueAtpos);

			if (valueAtpos > TARGET_VALUE) {
				LOGGER.info("Result: "+ valueAtpos + "\n");
				return null;
			}
		}

		return newPos;
	}
}

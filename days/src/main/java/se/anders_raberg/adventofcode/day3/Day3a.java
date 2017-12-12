package se.anders_raberg.adventofcode.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Day3a {
	private static final Logger LOGGER = Logger.getLogger(Day3a.class.getName());

	private static final Distance RIGHT_STEP = new Distance(1, 0);
	private static final Distance UP_STEP = new Distance(0, 1);
	private static final Distance LEFT_STEP = new Distance(-1, 0);
	private static final Distance DOWN_STEP = new Distance(0, -1);
	
	private static final int TARGET_VALUE = 347991;

	public static void run() {
		final List<Distance> steps = new ArrayList<>();

		int i = 1;
		while (true) {
			if (i % 2 != 0) {
				addSteps(steps, RIGHT_STEP, i);
				addSteps(steps, UP_STEP, i);
			} else {
				addSteps(steps, LEFT_STEP, i);
				addSteps(steps, DOWN_STEP, i);
			}

			if (steps.size() >= TARGET_VALUE) {
				break;
			}

			i++;			
		}

		Distance distance = steps.stream()
				.limit(TARGET_VALUE - 1L)
				.reduce(new Distance(0, 0), Distance::add);
		
		LOGGER.info("Manhattan: " + (Math.abs(distance.getHorizontal()) + Math.abs(distance.getVertical())) + "\n");
	}

	private static void addSteps(List<Distance> list, Distance step, int number) {
		for (int i = 0; i < number; i++) {
			list.add(step);
		}
	}
}

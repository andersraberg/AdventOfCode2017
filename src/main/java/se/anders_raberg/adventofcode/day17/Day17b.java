package se.anders_raberg.adventofcode.day17;

import java.util.logging.Logger;

public class Day17b {
    private static final Logger LOGGER = Logger.getLogger(Day17b.class.getName());
    private static final int INPUT = 369;

    public static void run() {
        int valueAtIndexOne = 0;
        int index = 0;
        for (int i = 1; i <= 50_000_000; i++) {
            index = (index + INPUT) % i + 1;

            if (index == 1) {
                valueAtIndexOne = i;
            }
        }

        LOGGER.info("Value at index one: " + valueAtIndexOne);
    }
}
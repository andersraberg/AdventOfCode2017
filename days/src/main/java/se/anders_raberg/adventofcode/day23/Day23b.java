package se.anders_raberg.adventofcode.day23;

import java.util.logging.Logger;
import java.util.stream.LongStream;

public class Day23b {
    private static final Logger LOGGER = Logger.getLogger(Day23b.class.getName());

    public static void run() {
        long b = 0;
        long c = 0;
        long h = 0;

        b = 84;
        b = b * 100;
        b = b + 100_000;
        c = b;
        c = c + 17000;

        for (long i = b; i < c + 1; i = i + 17) {
            if (!prime(i)) {
                h++;
            }
        }
        LOGGER.info("H: " + h);
    }

    public static boolean prime(long number) {
        return number >= 2 && LongStream.range(2, number).noneMatch(n -> (number % n == 0));
    }
}
package se.anders_raberg.adventofcode.day22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day22a {
    private static final int BURSTS = 10000;
    private static final Logger LOGGER = Logger.getLogger(Day22b.class.getName());
    private static final Map<Position, Boolean> _board = new HashMap<>();

    public static void run(String inputPath) throws IOException {
        try (Stream<String> input = Files.lines(Paths.get(inputPath + "/input22.txt"))) {
            List<String> rows = input.collect(Collectors.toList());
            int distFromOrigo = rows.size() / 2;

            for (int yPos = 0; yPos < rows.size(); yPos++) {
                List<String> row = Arrays.asList(rows.get(yPos).split(""));
                for (int xPos = 0; xPos < row.size(); xPos++) {
                    _board.put(new Position(-distFromOrigo + xPos, distFromOrigo - yPos), row.get(xPos).equals("#"));
                }
            }
        }

        long counter = 0;
        CarrierPosition currentPos = new CarrierPosition(new Position(0, 0), Direction.UP);

        for (int i = 0; i < BURSTS; i++) {
            if (_board.getOrDefault(currentPos.getPos(), false)) {
                currentPos = currentPos.turnRight();
                _board.put(currentPos.getPos(), false);
            } else {
                currentPos = currentPos.turnLeft();
                _board.put(currentPos.getPos(), true);
                counter++;
            }
            currentPos = currentPos.move();
        }
        LOGGER.info("counter : " + counter);
    }

}
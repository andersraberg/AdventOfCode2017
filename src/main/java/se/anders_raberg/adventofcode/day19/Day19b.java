package se.anders_raberg.adventofcode.day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day19b {
    private static final Logger LOGGER = Logger.getLogger(Day19b.class.getName());
    private static final Map<Position, String> _board = new HashMap<>();
    private static final Set<Position> _visited = new HashSet<>();
    private static final String STRING_TO_FIND = "VTWBPYAQFU";

    public static void run(String inputPath) throws IOException {
        try (Stream<String> input = Files.lines(Paths.get(inputPath + "/input19.txt"))) {
            List<String> rows = input.collect(Collectors.toList());
            for (int yPos = 0; yPos < rows.size(); yPos++) {
                List<String> row = Arrays.asList(rows.get(yPos).split(""));
                for (int xPos = 0; xPos < row.size(); xPos++) {
                    _board.put(new Position(xPos, yPos), row.get(xPos));
                }
            }
        }

        Position p = new Position(0, 0);
        while (!_board.get(p).equals("|")) {
            p = p.right();
        }

        String letters = "";
        Direction direction = Direction.DOWN;
        boolean finished = false;
        int counter = 0;
        while (!finished) {
            _visited.add(p);
            String charcterAtPos = _board.get(p);
            if (charcterAtPos.matches("[A-Z]")) {
                letters = letters + charcterAtPos;
                finished = letters.equals(STRING_TO_FIND); 
            }
            if (charcterAtPos.equals("+")) {
                if (validNextPos(p.right())) {
                    direction = Direction.RIGHT;
                } else if (validNextPos(p.left())) {
                    direction = Direction.LEFT;
                } else if (validNextPos(p.up())) {
                    direction = Direction.UP;
                } else if (validNextPos(p.down())) {
                    direction = Direction.DOWN;
                } else {
                    finished = true;
                }
            }
            switch (direction) {
            case DOWN:
                p = p.down();
                break;
            case LEFT:
                p = p.left();
                break;
            case RIGHT:
                p = p.right();
                break;
            case UP:
                p = p.up();
                break;
            }
            counter++;
        }

        LOGGER.info("Letters: " + String.join("", letters) + ", Counter: " + counter);
    }

    private static boolean validNextPos(Position p) {
        return !_visited.contains(p) && !_board.get(p).equals(" ");
    }
}
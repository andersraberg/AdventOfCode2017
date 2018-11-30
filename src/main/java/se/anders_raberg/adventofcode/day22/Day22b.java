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

public class Day22b {
    private static final int BURSTS = 10_000_000;
    private static final Logger LOGGER = Logger.getLogger(Day22a.class.getName());
    private static final Map<Position, NodeState> _board = new HashMap<>();

    public static void run(String inputPath) throws IOException {
        try (Stream<String> input = Files.lines(Paths.get(inputPath + "/input22.txt"))) {
            List<String> rows = input.collect(Collectors.toList());
            int distFromOrigo = rows.size() / 2;

            for (int yPos = 0; yPos < rows.size(); yPos++) {
                List<String> row = Arrays.asList(rows.get(yPos).split(""));
                for (int xPos = 0; xPos < row.size(); xPos++) {
                    NodeState startState = row.get(xPos).equals("#") ? NodeState.INFECTED : NodeState.CLEAN;
                    _board.put(new Position(-distFromOrigo + xPos, distFromOrigo - yPos), startState);
                }
            }
        }

        long counter = 0;
        CarrierPosition currentPos = new CarrierPosition(new Position(0, 0), Direction.UP);

        for (int i = 0; i < BURSTS; i++) {
            NodeState nodeState = _board.getOrDefault(currentPos.getPos(), NodeState.CLEAN);
            
            switch (nodeState) {
            case CLEAN:
                currentPos = currentPos.turnLeft();
                _board.put(currentPos.getPos(), NodeState.WEAKENED);
                break;
            case WEAKENED:
                _board.put(currentPos.getPos(), NodeState.INFECTED);
                counter++;
                break;
            case INFECTED:
                currentPos = currentPos.turnRight();
                _board.put(currentPos.getPos(), NodeState.FLAGGED);
                break;
            case FLAGGED:
                currentPos = currentPos.turnRight().turnRight();
                _board.put(currentPos.getPos(), NodeState.CLEAN);
                break;
            default:
                throw new IllegalStateException();
            }
            currentPos = currentPos.move();
        }
        LOGGER.info("counter : " + counter);
    }}
package se.anders_raberg.adventofcode.day23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day23a {
    private static final Logger LOGGER = Logger.getLogger(Day23a.class.getName());
    private static final Map<String, Long> REGISTERS = new HashMap<>();

    public static void run(String inputPath) throws IOException {
        List<List<String>> instructions = new ArrayList<>();

        try (Stream<String> input = Files.lines(Paths.get(inputPath + "/input23.txt"))) {
            input.forEach(l -> {
                List<String> parsedLine = Arrays.stream(l.split("\\s+")).collect(Collectors.toList());

                instructions.add(parsedLine);

            });
        }

        long instructionCounter = 0;
        long mulCounter = 0;
        while (instructionCounter < instructions.size()) {
            List<String> instr = instructions.get((int) instructionCounter);

            switch (instr.get(0)) {
            case "set":
                REGISTERS.put(instr.get(1), value(instr.get(2)));
                instructionCounter++;
                break;
            case "sub":
                REGISTERS.put(instr.get(1), value(instr.get(1)) - value(instr.get(2)));
                instructionCounter++;
                break;
            case "mul":
                REGISTERS.put(instr.get(1), value(instr.get(1)) * value(instr.get(2)));
                instructionCounter++;
                mulCounter++;
                break;
            case "jnz":
                if (value(instr.get(1)) != 0) {
                    instructionCounter = instructionCounter + value(instr.get(2));
                } else {
                    instructionCounter++;
                }
                break;
            default:
                throw new IllegalArgumentException(instr.get(0));
            }
        }
        LOGGER.info("mul counter: " + mulCounter);
    }

    private static long value(String s) {
        if (s.matches("[a-z]")) {
            return REGISTERS.getOrDefault(s, 0L);
        }
        return Integer.parseInt(s);
    }

}
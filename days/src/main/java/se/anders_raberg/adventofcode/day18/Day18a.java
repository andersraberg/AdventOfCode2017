package se.anders_raberg.adventofcode.day18;

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

public class Day18a {
    private static final Logger LOGGER = Logger.getLogger(Day18a.class.getName());
    private static final Map<String, Long> REGISTERS = new HashMap<>();

    public static void run(String inputPath) throws IOException {
        List<List<String>> instructions = new ArrayList<>();

        try (Stream<String> input = Files.lines(Paths.get(inputPath + "/input18.txt"))) {
            input.forEach(l -> {
                List<String> parsedLine = Arrays.stream(l.split("\\s+")).collect(Collectors.toList());

                instructions.add(parsedLine);

            });
        }

        long lastPlayedSoundFreq = 0;
        boolean recoveredSoundFreq = false;
        long instructionCounter = 0;
        while (!recoveredSoundFreq) {
            List<String> instr = instructions.get((int) instructionCounter);

            switch (instr.get(0)) {
            case "snd":
                lastPlayedSoundFreq = value(instr.get(1));
                instructionCounter++;
                break;
            case "set":
                REGISTERS.put(instr.get(1), value(instr.get(2)));
                instructionCounter++;
                break;
            case "add":
                REGISTERS.put(instr.get(1), value(instr.get(1)) + value(instr.get(2)));
                instructionCounter++;
                break;
            case "mul":
                REGISTERS.put(instr.get(1), value(instr.get(1)) * value(instr.get(2)));
                instructionCounter++;
                break;
            case "mod":
                REGISTERS.put(instr.get(1), value(instr.get(1)) % value(instr.get(2)));
                instructionCounter++;
                break;
            case "rcv":
                if (value(instr.get(1)) != 0) {
                    recoveredSoundFreq = true;
                }
                instructionCounter++;
                break;
            case "jgz":
                if (value(instr.get(1)) > 0) {
                    instructionCounter = instructionCounter + value(instr.get(2));
                } else {
                    instructionCounter++;
                }
                break;
            default:
                throw new IllegalArgumentException(instr.get(0));
            }
        }

        LOGGER.info("lastPlayedSoudFreq: " + lastPlayedSoundFreq);
    }

    private static long value(String s) {
        if (s.matches("[a-z]")) {
            return REGISTERS.getOrDefault(s, 0L);
        }
        return Integer.parseInt(s);
    }

}
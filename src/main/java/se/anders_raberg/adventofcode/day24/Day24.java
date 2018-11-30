package se.anders_raberg.adventofcode.day24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day24 {
    private static final Logger LOGGER = Logger.getLogger(Day24.class.getName());
    private static final Pattern PATTERN = Pattern.compile("(.*)/(.*)");
    private static final Map<Integer, List<BridgeComponent>> BRIDGES = new HashMap<>();

    public static void run(String inputPath) throws IOException {
        final List<BridgeComponent> components = new ArrayList<>();
        List<String> rows;
        try (Stream<String> input = Files.lines(Paths.get(inputPath + "/input24.txt"))) {
            rows = input.collect(Collectors.toList());
        }

        for (String row : rows) {
            Matcher matcher = PATTERN.matcher(row);
            matcher.find();

            BridgeComponent component = new BridgeComponent(Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)));

            components.add(component);
        }

        sumComponents(0, 0, new ArrayList<>(), components);
        
        int max = BRIDGES.keySet().stream().mapToInt(c -> c).max().getAsInt();
        
        BRIDGES.entrySet()
                .stream()
                .sorted((l, r) -> l.getValue().size() - r.getValue().size())
                .forEach(e -> LOGGER.info("Length: " + e.getValue().size() + ", Strength : " + e.getKey()));
        
        LOGGER.info("Max : " + max);
    }

    private static void sumComponents(int pins, int strength, List<BridgeComponent> bridge, List<BridgeComponent> components) {
        List<BridgeComponent> possibleNextComponents = components
                .stream()
                .filter(c -> c.leftPort() == pins || c.rightPort() == pins)
                .collect(Collectors.toList());
        
        if (possibleNextComponents.isEmpty()) {
            BRIDGES.put(strength, bridge);
        } else {
            for (BridgeComponent comp : possibleNextComponents) {
                int nextPins;
                if (comp.leftPort() == pins) {
                    nextPins = comp.rightPort();
                } else {
                    nextPins = comp.leftPort();
                }
                
                List<BridgeComponent> remainingComponents = new ArrayList<>(components);
                remainingComponents.remove(comp);
                List<BridgeComponent> bridgeSoFar = new ArrayList<>(bridge);
                bridgeSoFar.add(comp);
                sumComponents(nextPins, strength + comp.strength(), bridgeSoFar, remainingComponents);
            }
        }
    }
}

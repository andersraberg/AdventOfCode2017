package se.anders_raberg.adventofcode.day20;

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

public class Day20b {
    private static final int NUMBER_OF_MOVES = 500;
    private static final Logger LOGGER = Logger.getLogger(Day20b.class.getName());
    private static final String PATTERN = "p=<(.*),(.*),(.*)>, v=<(.*),(.*),(.*)>, a=<(.*),(.*),(.*)>";

    public static void run(String inputPath) throws IOException {
        Map<Long, Particle> _particles = new HashMap<>();
        Pattern pattern = Pattern.compile(PATTERN);
        List<String> rows;
        try (Stream<String> input = Files.lines(Paths.get(inputPath + "/input20.txt"))) {
            rows = input.collect(Collectors.toList());
        }

        for (int i = 0; i < rows.size(); i++) {
            Matcher m = pattern.matcher(rows.get(i));
            m.find();

            _particles.put((long) i, new Particle(i, Long.parseLong(m.group(1)), // px
                    Long.parseLong(m.group(2)), // py
                    Long.parseLong(m.group(3)), // pz
                    Long.parseLong(m.group(4)), // vx
                    Long.parseLong(m.group(5)), // vy
                    Long.parseLong(m.group(6)), // vz
                    Long.parseLong(m.group(7)), // ax
                    Long.parseLong(m.group(8)), // ay
                    Long.parseLong(m.group(9)))); // az
        }

        for (int i = 0; i < NUMBER_OF_MOVES; i++) {
            _particles.values().forEach(Particle::move);

            List<Particle> copyOfParticles = new ArrayList<>(_particles.values());
            for (Particle part : copyOfParticles) {
                List<Long> matchingIds = _particles.values().stream()
                        .filter(p -> p.getPos().equals(part.getPos()))
                        .map(x -> x.getId())
                        .collect(Collectors.toList());

                if (matchingIds.size() > 1 ) {
                    matchingIds.stream().forEach(id -> _particles.remove(id));
                }
            }
        }
        LOGGER.info("Number of particles left: " + _particles.size());
    }

}

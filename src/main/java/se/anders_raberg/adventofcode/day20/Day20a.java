package se.anders_raberg.adventofcode.day20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day20a {
    private static final int NUMBER_OF_MOVES = 100_000;
    private static final Logger LOGGER = Logger.getLogger(Day20a.class.getName());
    private static final String PATTERN = "p=<(.*),(.*),(.*)>, v=<(.*),(.*),(.*)>, a=<(.*),(.*),(.*)>";

    public static void run(String inputPath) throws IOException {
        List<Particle> _particles = new ArrayList<>();
        Pattern pattern = Pattern.compile(PATTERN);
        List<String> rows;
        try (Stream<String> input = Files.lines(Paths.get(inputPath + "/input20.txt"))) {
            rows = input.collect(Collectors.toList());
        }

        for (int i = 0; i < rows.size(); i++) {
            Matcher m = pattern.matcher(rows.get(i));
            m.find();

            _particles.add(new Particle(i,
                    Long.parseLong(m.group(1)),    // px
                    Long.parseLong(m.group(2)),    // py
                    Long.parseLong(m.group(3)),    // pz
                    Long.parseLong(m.group(4)),    // vx
                    Long.parseLong(m.group(5)),    // vy
                    Long.parseLong(m.group(6)),    // vz
                    Long.parseLong(m.group(7)),    // ax
                    Long.parseLong(m.group(8)),    // ay
                    Long.parseLong(m.group(9))));  // az
        }

        for (int i = 0; i < NUMBER_OF_MOVES; i++) {
            _particles.forEach(Particle::move);
        }

        Particle part = particleClosestToOrigo(_particles);
        LOGGER.info("ID: " + part.getId() + ", DIST: " + distanceToOrigo(part));

    }

    private static Particle particleClosestToOrigo(List<Particle> particles) {
        return particles.stream().min(Day20a::compare).get();
    }

    private static int compare(Particle p1, Particle p2) {
        return Long.compare(distanceToOrigo(p1), distanceToOrigo(p2));
    }

    private static long distanceToOrigo(Particle p) {
        return Math.abs(p.getPx()) + Math.abs(p.getPy()) + Math.abs(p.getPz());
    }

}

package se.anders_raberg.adventofcode.day21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day21a {
    private static final Logger LOGGER = Logger.getLogger(Day21a.class.getName());
    private static final Pattern PATTERN_RULE = Pattern.compile("(.*) => (.*)");
    private static final Pattern PATTERN2 = Pattern.compile("(.*)/(.*)");
    private static final Pattern PATTERN3 = Pattern.compile("(.*)/(.*)/(.*)");
    private static final Pattern PATTERN4 = Pattern.compile("(.*)/(.*)/(.*)/(.*)");
    private static final Map<ArtPattern, ArtPattern> _ruleBook = new HashMap<>();

    public static void run(String inputPath) throws IOException {
        List<String> rows;
        try (Stream<String> input = Files.lines(Paths.get(inputPath + "/input21.txt"))) {
            rows = input.collect(Collectors.toList());
        }

        for (String row : rows) {
            Matcher matcher_rule = PATTERN_RULE.matcher(row);
            matcher_rule.find();
            ArtPattern p1 = parse(matcher_rule.group(1));
            ArtPattern p2 = parse(matcher_rule.group(2));
            
            for (int i = 0; i < 4; i++) {
                _ruleBook.put(p1, p2);
                p1 = p1.getRotated();
            }

            p1 = p1.getHorzontallyFlipped();
            
            for (int i = 0; i < 4; i++) {
                _ruleBook.put(p1, p2);
                p1 = p1.getRotated();
            }
        }


        String[][] start = {
                {".", "#", "."},
                {".", ".", "#"},
                {"#", "#", "#"}};

        ArtPattern grid = new ArtPattern(start);
        
        for (int c = 0; c < 5; c++) {
            System.out.println("Round: " + c);
            System.out.println("------------");
            System.out.println("Before split");
            System.out.println(grid);
            ArtPattern[][] split = grid.split();
            System.out.println("After split");
            for (int i = 0; i < split.length; i++) {
                for (int j = 0; j < split.length; j++) {
                    System.out.println(i + "," + j);
                    System.out.println(split[i][j]);
                }
            }
            ArtPattern[][] transformedSplit = new ArtPattern[split.length][split.length];
            for (int i = 0; i < split.length; i++) {
                for (int j = 0; j < split.length; j++) {
                    transformedSplit[i][j] = _ruleBook.get(split[i][j]);
                    if (transformedSplit[i][j] == null) {
                        throw new IllegalArgumentException();
                    }
                }
            }

            System.out.println("After transformation");
            for (int i = 0; i < transformedSplit.length; i++) {
                for (int j = 0; j < transformedSplit.length; j++) {
                    System.out.println(i + "," + j);
                    System.out.println(transformedSplit[i][j]);
                }
            }
            grid = ArtPattern.joinPatterns(transformedSplit);
            System.out.println("After join");
            System.out.println(grid);
        }
        
        long count = Arrays.stream(grid.toString().split("")).filter(s -> s.equals("#")).count();
        System.out.println(count);
        System.out.println(grid);
    }

    private static ArtPattern parse(String rulePatternString) {
        Matcher matcher4 = PATTERN4.matcher(rulePatternString);
        if (matcher4.find()) {
            String[][] p  = {
                    matcher4.group(1).split(""),
                    matcher4.group(2).split(""),
                    matcher4.group(3).split(""),
                    matcher4.group(4).split("")};
            return new ArtPattern(p);
        };

        Matcher matcher3 = PATTERN3.matcher(rulePatternString);
        if (matcher3.find()) {
            String[][] p  = {
                    matcher3.group(1).split(""),
                    matcher3.group(2).split(""),
                    matcher3.group(3).split("")};
            return new ArtPattern(p);
        };

        Matcher matcher2 = PATTERN2.matcher(rulePatternString);
        if (matcher2.find()) {
            String[][] p  = {
                    matcher2.group(1).split(""),
                    matcher2.group(2).split("")};
            return new ArtPattern(p);
        };

        throw new IllegalArgumentException();
    }

}

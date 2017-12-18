package se.anders_raberg.adventofcode.day13;

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

public class Day13a {
	private static final Logger LOGGER = Logger.getLogger(Day13a.class.getName());

	public static void run(String inputPath) throws IOException {
		Map<Integer, Layer> layers = new HashMap<>();

		try (Stream<String> input = Files.lines(Paths.get(inputPath + "/input13.txt"))) {
			input.forEach(l -> {
				List<Integer> parsedLine = Arrays.stream(l.split("\\W+")).map(Integer::parseInt)
						.collect(Collectors.toList());

				int layerpos = parsedLine.get(0);
				int layerDepth = parsedLine.get(1);
				layers.put(layerpos, new Layer(layerDepth));
			});
		}
		
		int lastLayer = layers.keySet().stream().max(Integer::compare).get();
		int severity = 0;

		for (int i = 0; i < lastLayer + 1; i++) {
			if (layers.containsKey(i) && layers.get(i).scannerInFirstPos()) {
				severity = severity + i * layers.get(i).getDepth();
			}
		
			layers.values().forEach(Layer::moveScanner);
		}
		
		LOGGER.info("Severity: " + severity);
	}
}
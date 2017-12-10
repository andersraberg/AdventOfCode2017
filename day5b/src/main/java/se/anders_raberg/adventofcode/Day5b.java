package se.anders_raberg.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author anders
 * 
 * Supply the file with the data as args[0]
 *
 */
public class Day5b {
	private static final Logger LOGGER = Logger.getLogger(Day5b.class.getName());

	public static void main(String[] args) throws IOException {
		try (Stream<Integer> input = Files.lines(Paths.get(args[0])).map(Integer::valueOf)){
			List<Integer> jumpOffsets = input.collect(Collectors.toList());
			
			int index = 0;
			int jumps = 0;
			while (index < jumpOffsets.size()) {
				int offset = jumpOffsets.get(index);
				if (offset >= 3) {
					jumpOffsets.set(index, offset - 1);
				} else {
					jumpOffsets.set(index, offset + 1);					
				}
				index = index + offset;
				jumps++;
			}
			
			LOGGER.log(Level.INFO, "Jumps: {0}", jumps);
		} 

	}
	
}

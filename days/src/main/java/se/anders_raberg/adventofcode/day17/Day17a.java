package se.anders_raberg.adventofcode.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Day17a {
	private static final Logger LOGGER = Logger.getLogger(Day17a.class.getName());
	private static final int INPUT = 369;

	public static void run() {
	    List<Integer>  buffer = new ArrayList<>();
	    
	    buffer.add(0);
	    int index = 0;
	    
	    for (int i = 1; i <= 2017; i++) {
            index = (index + INPUT) % buffer.size() + 1;
            buffer.add(index, i);
        }
	    
	    LOGGER.info(buffer.get(index + 1).toString());
	}
}
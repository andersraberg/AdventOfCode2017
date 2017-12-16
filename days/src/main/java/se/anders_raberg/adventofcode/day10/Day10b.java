package se.anders_raberg.adventofcode.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class Day10b {
	private static final Logger LOGGER = Logger.getLogger(Day10b.class.getName());

	public static void run(String inputPath) throws IOException {
		byte[] bytes = Files.readAllBytes(Paths.get(inputPath + "/input10.txt"));
		byte[] additionalBytes = {17, 31, 73, 47, 23};

		List<Byte> totalBytes = new ArrayList<>();
		
		for (int i = 0; i < bytes.length - 1; i++) {  // -1 to remove end of file char
			totalBytes.add(bytes[i]);
		}
		
		for (int i = 0; i < additionalBytes.length; i++) {
			totalBytes.add(additionalBytes[i]);
		}

		int currentPos = 0;
		int skipSize = 0;

		int[] array = IntStream.range(0, 256).toArray();
		for (int i = 0; i < 64; i++) {
			for (int len : totalBytes.toArray(new Byte[0])) {
				reverse(array, currentPos, len);
				currentPos = (currentPos + len + skipSize) % array.length;
				skipSize++;
			} 
		}
		
		StringBuilder denseHash = new StringBuilder();
		
		for (int i = 0; i < 16; i++) {
			Byte number = 0;
			for (int j = 16 * i; j < 16 * (i + 1); j++) {
				number = (byte) (number ^ array[j]);
			}
			denseHash.append(String.format("%02x", number));
		}
	
		LOGGER.info("Hash: " + denseHash.toString());
	}
	
	private static void reverse(int[] array, int pos, int len) {
		for (int i = 0; i < len / 2; i++) {
			int posA = (pos + i) % array.length;
			int posB = (pos + len - 1 - i) % array.length;
			int tmp = array[posA];
			array[posA] = array[posB];
			array[posB] = tmp;
		}
	}

}
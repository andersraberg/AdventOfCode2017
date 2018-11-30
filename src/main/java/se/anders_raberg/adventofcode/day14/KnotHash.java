package se.anders_raberg.adventofcode.day14;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class KnotHash {
	public static String getHash(String input) {
		byte[] bytes = input.getBytes();
		byte[] additionalBytes = {17, 31, 73, 47, 23};

		List<Byte> totalBytes = new ArrayList<>();
		
		for (int i = 0; i < bytes.length; i++) {
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
			String binaryString = 
					String.format("%8s", Integer.toBinaryString(number & 0xFF)).replace(' ', '0');
			denseHash.append(binaryString);
		}
	
		return denseHash.toString();
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
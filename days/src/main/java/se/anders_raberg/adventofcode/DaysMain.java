package se.anders_raberg.adventofcode;

import java.io.IOException;

import se.anders_raberg.adventofcode.day1.Day1a;
import se.anders_raberg.adventofcode.day1.Day1b;
import se.anders_raberg.adventofcode.day2.Day2a;
import se.anders_raberg.adventofcode.day2.Day2b;
import se.anders_raberg.adventofcode.day3.Day3a;
import se.anders_raberg.adventofcode.day3.Day3b;
import se.anders_raberg.adventofcode.day4.Day4a;
import se.anders_raberg.adventofcode.day4.Day4b;
import se.anders_raberg.adventofcode.day5.Day5a;
import se.anders_raberg.adventofcode.day5.Day5b;
import se.anders_raberg.adventofcode.day6.Day6a;
import se.anders_raberg.adventofcode.day6.Day6b;
import se.anders_raberg.adventofcode.day7.Day7a;
import se.anders_raberg.adventofcode.day7.Day7b;
import se.anders_raberg.adventofcode.day8.Day8a;

public class DaysMain {
	private static final String INPUT_PATH = 
			System.getProperty("se.anders_raberg.adventofcode.inputFilePath");

	public static void main(String[] args) throws IOException {
		Day1a.run(INPUT_PATH);
		Day1b.run(INPUT_PATH);
		Day2a.run(INPUT_PATH);
		Day2b.run(INPUT_PATH);
		Day3a.run();
		Day3b.run();
		Day4a.run(INPUT_PATH);
		Day4b.run(INPUT_PATH);
		Day5a.run(INPUT_PATH);
		Day5b.run(INPUT_PATH);
		Day6a.run(INPUT_PATH);
		Day6b.run(INPUT_PATH);
		Day7a.run(INPUT_PATH);
		Day7b.run(INPUT_PATH);
		Day8a.run(INPUT_PATH);
	}
}

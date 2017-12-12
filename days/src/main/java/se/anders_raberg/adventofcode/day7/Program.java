package se.anders_raberg.adventofcode.day7;

import java.util.Collections;
import java.util.List;

public class Program {
	private final int _weight;
	private final List<String> _subprograms;

	public Program(int weight, List<String> subprograms) {
		_weight = weight;
		_subprograms = subprograms; 
	}

	public int getWeight() {
		return _weight;
	}

	public List<String> getSubprograms() {
		return Collections.unmodifiableList(_subprograms);
	}
	
	public String toString() {
		return _weight + " : " + _subprograms;
	}
}

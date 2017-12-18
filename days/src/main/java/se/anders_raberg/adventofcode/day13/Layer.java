package se.anders_raberg.adventofcode.day13;

public class Layer {
	private final int _stepsBetweenSucessivePos0;
	private int _scannerPos;
	
	public Layer(int depth) {
		_stepsBetweenSucessivePos0 = depth * 2 - 2;
		_scannerPos = 0;
	}

	public boolean scannerInFirstPos() {
		return _scannerPos == 0;
	}
	
	public int getDepth() {
		return _stepsBetweenSucessivePos0;
	}
	
	public void moveScanner() {
		_scannerPos = (_scannerPos + 1) % _stepsBetweenSucessivePos0;
	}
	
	public void moveScanner(int steps) {
		_scannerPos = (_scannerPos + steps) % _stepsBetweenSucessivePos0;
	}
	
	public void resetScanner() {
		_scannerPos = 0;
	}

}

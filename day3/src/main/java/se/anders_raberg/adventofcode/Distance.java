package se.anders_raberg.adventofcode;

public class Distance {
	private final int _horizontal;
	private final int _vertical;

	public Distance(int horizontal, int vertical) {
		_horizontal = horizontal;
		_vertical = vertical;
	}

	public static Distance add (Distance a, Distance b) {
		return new Distance(a._horizontal + b._horizontal, a._vertical + b._vertical);
	}

	public int getHorizontal() {
		return _horizontal;
	}

	public int getVertical() {
		return _vertical;
	}
}
package se.anders_raberg.adventofcode.day3;

public class Position {
	private final int _horizontal;
	private final int _vertical;

	public Position(int horizontal, int vertical) {
		_horizontal = horizontal;
		_vertical = vertical;
	}

	public Position add (Position d) {
		return new Position(_horizontal + d._horizontal, _vertical + d._vertical);
	}
	
	public int getHorizontal() {
		return _horizontal;
	}

	public int getVertical() {
		return _vertical;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _horizontal;
		result = prime * result + _vertical;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Position other = (Position) obj;
		if (_horizontal != other._horizontal) {
			return false;
		}
		if (_vertical != other._vertical) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return _horizontal + "," + _vertical;
	}
	
	
}
package se.anders_raberg.adventofcode.day20;

public class Position {
    private final long _x;
    private final long _y;
    private final long _z;
    
    public Position(long x, long y, long z) {
        _x = x;
        _y = y;
        _z = z;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (_x ^ (_x >>> 32));
        result = prime * result + (int) (_y ^ (_y >>> 32));
        result = prime * result + (int) (_z ^ (_z >>> 32));
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
        if (_x != other._x) {
            return false;
        }
        if (_y != other._y) {
            return false;
        }
        if (_z != other._z) {
            return false;
        }
        return true;
    }
    

    
}

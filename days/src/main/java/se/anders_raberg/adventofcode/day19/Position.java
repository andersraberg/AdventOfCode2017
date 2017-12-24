package se.anders_raberg.adventofcode.day19;

public class Position {
    private final int _x;
    private final int _y;
    
    public Position(int x, int y) {
        _x = x;
        _y = y;
    }

    public int x() {
        return _x;
    }

    public int y() {
        return _y;
    }
    
    
    public Position up() {
        return new Position(_x, _y - 1);
    }

    public Position down() {
        return new Position(_x, _y + 1);
    }

    public Position right() {
        return new Position(_x + 1, _y);
    }
    
    public Position left() {
        return new Position(_x - 1, _y);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + _x;
        result = prime * result + _y;
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
        return true;
    }

    @Override
    public String toString() {
        return "Position [_x=" + _x + ", _y=" + _y + "]";
    }

}

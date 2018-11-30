package se.anders_raberg.adventofcode.day22;

public class CarrierPosition {
    private final Position _pos;
    private final Direction _dir;
    
    public CarrierPosition(Position position, Direction direction) {
        _pos = position;
        _dir = direction;
    }

    public Position getPos() {
        return _pos;
    }
    
    public CarrierPosition turnRight() {
        switch (_dir) {
        case DOWN:
            return new CarrierPosition(_pos, Direction.LEFT);
        case LEFT:
            return new CarrierPosition(_pos, Direction.UP);
        case UP:
            return new CarrierPosition(_pos, Direction.RIGHT);
        case RIGHT:
            return new CarrierPosition(_pos, Direction.DOWN);
        default:
            throw new IllegalStateException();
                
        }
    }

    public CarrierPosition turnLeft() {
        switch (_dir) {
        case DOWN:
            return new CarrierPosition(_pos, Direction.RIGHT);
        case RIGHT:
            return new CarrierPosition(_pos, Direction.UP);
        case UP:
            return new CarrierPosition(_pos, Direction.LEFT);
        case LEFT:
            return new CarrierPosition(_pos, Direction.DOWN);
        default:
            throw new IllegalStateException();
                
        }
    }

    public CarrierPosition move() {
        switch (_dir) {
        case DOWN:
            return new CarrierPosition(new Position(_pos.x(), _pos.y() - 1), _dir);
        case RIGHT:
            return new CarrierPosition(new Position(_pos.x() + 1, _pos.y()), _dir);
        case UP:
            return new CarrierPosition(new Position(_pos.x(), _pos.y() + 1), _dir);
        case LEFT:
            return new CarrierPosition(new Position(_pos.x() - 1, _pos.y()), _dir);
        default:
            throw new IllegalStateException();
                
        }
    }
    
    @Override
    public String toString() {
        return _pos.toString() + _dir;
    }
}

package se.anders_raberg.adventofcode.day20;

public class Particle {
    private final long _id;
    private long _px;
    private long _py;
    private long _pz;
    private long _vx;
    private long _vy;
    private long _vz;
    private final long _ax;
    private final long _ay;
    private final long _az;
    
    public Particle(long id, long px, long py, long pz, long vx, long vy, long vz, long ax, long ay, long az) {
        _id = id;
        _px = px;
        _py = py;
        _pz = pz;
        _vx = vx;
        _vy = vy;
        _vz = vz;
        _ax = ax;
        _ay = ay;
        _az = az;
    };
    
    public void move() {
        _vx = _vx + _ax;
        _vy = _vy + _ay;
        _vz = _vz + _az;

        _px = _px + _vx;
        _py = _py + _vy;
        _pz = _pz + _vz;
    }

    
    public long getId() {
        return _id;
    }

    public long getPx() {
        return _px;
    }

    public long getPy() {
        return _py;
    }

    public long getPz() {
        return _pz;
    }

    public Position getPos() {
        return new Position(_px, _py, _pz);
    }
    
    @Override
    public String toString() {
        return "Particle [_id=" + _id + ", _px=" + _px + ", _py=" + _py + ", _pz=" + _pz + ", _vx=" + _vx + ", _vy="
                + _vy + ", _vz=" + _vz + ", _ax=" + _ax + ", _ay=" + _ay + ", _az=" + _az + "]";
    }
    
}

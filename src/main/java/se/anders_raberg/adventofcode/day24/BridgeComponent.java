package se.anders_raberg.adventofcode.day24;

public class BridgeComponent {
    private final int _leftPort;
    private final int _rightPort;

    public BridgeComponent(int leftPort, int rightPort) {
        _leftPort = leftPort;
        _rightPort = rightPort;
    }

    public int leftPort() {
        return _leftPort;
    }

    public int rightPort() {
        return _rightPort;
    }
    
    public int strength() {
        return _leftPort + _rightPort;
    }
    
    @Override
    public String toString() {
        return _leftPort + "/" + _rightPort;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + _leftPort;
        result = prime * result + _rightPort;
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
        BridgeComponent other = (BridgeComponent) obj;
        if (_leftPort != other._leftPort) {
            return false;
        }
        if (_rightPort != other._rightPort) {
            return false;
        }
        return true;
    }
    
}

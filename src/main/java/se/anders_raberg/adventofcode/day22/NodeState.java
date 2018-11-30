package se.anders_raberg.adventofcode.day22;

public enum NodeState {
    CLEAN,
    WEAKENED,
    INFECTED,
    FLAGGED;
    
    public static NodeState next(NodeState ns) {
        switch (ns) {
        case CLEAN:
            return WEAKENED;
        case WEAKENED:
            return INFECTED;
        case INFECTED:
            return FLAGGED;
        case FLAGGED:
            return CLEAN;
        default:
            throw new IllegalStateException();
        }
    }
}

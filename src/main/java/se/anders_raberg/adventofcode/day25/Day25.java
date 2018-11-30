package se.anders_raberg.adventofcode.day25;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Day25 {
    private static final Logger LOGGER = Logger.getLogger(Day25.class.getName());
    private static final Map<Integer, Integer> TAPE = new HashMap<>();
    private static State STATE = State.A;
    private static int CURSOR = 0;

    public static void run() {
        
        for (int i = 0; i < 12683008; i++) {
            switch (STATE) {
            case A:
                if (value() == 0) {
                    action(1, +1, State.B);
                } else {
                    action(0, -1, State.B);
                }
                break;
                
            case B:
                if (value() == 0) {
                    action(1, -1, State.C);
                } else {
                    action(0, +1, State.E);
                }
                break;

            case C:
                if (value() == 0) {
                    action(1, +1, State.E);
                } else {
                    action(0, -1, State.D);
                }
                break;
            
            case D:
                action(1, -1, State.A);
                break;

            case E:
                if (value() == 0) {
                    action(0, +1, State.A);
                } else {
                    action(0, +1, State.F);
                }
                break;
            
            case F:
                if (value() == 0) {
                    action(1, +1, State.E);
                } else {
                    action(0, +1, State.A);
                }
                break;
                
            default:
                throw new IllegalStateException();
            }
        }
        

        LOGGER.info("Sum: " +TAPE.values().stream().mapToInt(Integer::intValue).sum());
    }

    private static int value() {
        return TAPE.getOrDefault(CURSOR, 0);
    }
    
    private static void action(int value, int step, State nextState) {
        TAPE.put(CURSOR, value);
        CURSOR = CURSOR + step;
        STATE = nextState;
    }
}
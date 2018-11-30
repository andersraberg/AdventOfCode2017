package se.anders_raberg.adventofcode.day8;

import java.util.function.IntBinaryOperator;

public enum Operation {
	INC("inc", (a, b) -> a + b),
	DEC("dec", (a, b) -> a - b);
	
	private final String _stringToken;
	private final IntBinaryOperator _op;
	
	private Operation(String stringToken, IntBinaryOperator op) {
		_stringToken =  stringToken; 
		_op = op;
	}
	
	public static Operation parse(String stringToken) {
		
		for (Operation operation : Operation.values()) {
			if (operation._stringToken.equals(stringToken)) {
				return operation;
			}
		}

		throw new IllegalArgumentException();
		
	}
	
	public int apply(int a, int b) {
		return _op.applyAsInt(a, b);
	}
	
}


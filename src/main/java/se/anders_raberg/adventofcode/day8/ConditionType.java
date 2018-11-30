package se.anders_raberg.adventofcode.day8;

import java.util.function.BiPredicate;

public enum ConditionType {
	GT(">", (a, b) -> a > b),
	LT("<", (a, b) -> a < b),
	GTE(">=", (a, b) -> a >= b),
	LTE("<=", (a, b) -> a <= b),
	EQ("==", (a, b) -> a.equals(b)),
	NEQ("!=", (a, b) -> !a.equals(b));
	
	private final String _stringToken;
	private final BiPredicate<Integer, Integer> _op;
	
	private ConditionType(String stringToken, BiPredicate<Integer, Integer> op) {
		_stringToken = stringToken;
		_op = op;
	}

	public static ConditionType parse(String stringToken) {
		for (ConditionType conditionType : ConditionType.values()) {
			if (conditionType._stringToken.equals(stringToken)) {
				return conditionType;
			}
		}
		
		throw new IllegalArgumentException(stringToken);
	}
	
	public boolean apply(int a, int b) {
		return _op.test(a, b);
	}
	
}

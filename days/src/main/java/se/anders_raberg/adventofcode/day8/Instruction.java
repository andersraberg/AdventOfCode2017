package se.anders_raberg.adventofcode.day8;

import java.util.Map;

public class Instruction {
	private final String _register;
	private final Operation _operation;
	private final int _value;
	private final String _conditonRegister;
	private final ConditionType _conditionType;
	private final int _conditionValue;

	public Instruction(String line) {
		String[] strings = line.split("\\s+");
		_register = strings[0];
		_operation = Operation.parse(strings[1]);
		_value = Integer.parseInt(strings[2]);
		_conditonRegister = strings[4];
		_conditionType = ConditionType.parse(strings[5]);
		_conditionValue = Integer.parseInt(strings[6]);
	}

	public void apply(Map<String, Integer> registers) {
		int regValue = registers.getOrDefault(_register, 0);
		int condRegValue = registers.getOrDefault(_conditonRegister, 0);
		if (_conditionType.apply(condRegValue, _conditionValue)) {
			int newRegValue = _operation.apply(regValue, _value);
			registers.put(_register, newRegValue);
		}
	}
}

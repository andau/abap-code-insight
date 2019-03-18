package abap.code.insight.debug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.debug.core.DebugException;

import com.sap.adt.debugger.variables.IAbapVariable;
import com.sap.adt.debugger.variables.IAbapVariableValue;

public class VariableValueExtractor {
	static final String DEBUG_VARIABLES_PARAMETERS = "Parameters";
	static final String DEBUG_VARIABLES_CURRENT_CLASS = "ME";

	static final String DEBUG_VARIABLES_LOCALS = "Locals";

	public VariableValueExtractor() {
	}

	public List<IAbapVariable> extract(final IAbapVariable[] variables) throws DebugException {
		final List<IAbapVariable> variableValues = new ArrayList<>();
		for (final IAbapVariable variable : variables) {
			final IAbapVariableValue variableValue = variable.getValue();
			if (variable.getName().equals(DEBUG_VARIABLES_LOCALS)
					|| variable.getName().contentEquals(DEBUG_VARIABLES_CURRENT_CLASS)) {
				variableValues.addAll(Arrays.asList(variableValue.getVariables()));
			} else if (variable.getName().equals(DEBUG_VARIABLES_PARAMETERS)) {
				variableValues.addAll(Arrays.asList(variableValue.getStackFrame().getVariables()));
			}
		}
		return variableValues;
	}

}
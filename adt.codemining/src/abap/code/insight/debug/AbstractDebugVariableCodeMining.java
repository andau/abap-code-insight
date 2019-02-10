package abap.code.insight.debug;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.codemining.LineContentCodeMining;

public abstract class AbstractDebugVariableCodeMining<T extends IStackFrame> extends LineContentCodeMining {

	private final String variableInformation;

	protected AbstractDebugVariableCodeMining(Position position, String variableName, T stackFrame,
			AbstractDebugVariableCodeInsightProvider<T> provider) {
		super(position, provider, null);
		this.variableInformation = variableName;
	}

	@Override
	public String getLabel() {
		final String label = super.getLabel();
		if (label == null) {
			updateLabel(variableInformation);
		}
		return super.getLabel();
	}

	/**
	 * Update the debug mining label with the debug variable value.
	 *
	 * @param variableName the variable name
	 */
	private void updateLabel(String variableName) {
		super.setLabel(variableName); // $NON-NLS-1$
	}

	protected abstract IVariable findVariable(String variableName) throws DebugException;

}
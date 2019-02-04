package abap.code.insight.debug;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.codemining.LineContentCodeMining;
import org.eclipse.swt.graphics.RGB;

/**
 *
 * Abstract class to display debug variable value in a given position.
 *
 * @param <T> the stack frame
 */
public abstract class AbstractDebugVariableCodeMining<T extends IStackFrame> extends LineContentCodeMining {

	private final String fVariableName;
	private final T fStackFrame;
	private RGB fRgb;

	/**
	 * Debug variable mining constructor
	 *
	 * @param position     the position where the mining must be drawn.
	 * @param variableName the variable name to search in the given debug stack
	 *                     frame
	 * @param stackFrame   the current debug stack frame
	 * @param provider     the owner codemining provider which creates this mining.
	 */
	protected AbstractDebugVariableCodeMining(Position position, String variableName, T stackFrame,
			AbstractDebugVariableCodeInsightProvider<T> provider) {
		super(position, provider, null);
		this.fVariableName = variableName;
		this.fStackFrame = stackFrame;
	}

	@Override
	public String getLabel() {
		final String label = super.getLabel();
		if (label == null) {
			updateLabel(fVariableName);
		}
		return super.getLabel();
	}

	/**
	 * Update the debug mining label with the debug variable value.
	 *
	 * @param variableName the variable name
	 */
	private void updateLabel(String variableName) {
		super.setLabel("variable = some value"); //$NON-NLS-1$
	}

	protected abstract IVariable findVariable(String variableName) throws DebugException;

}
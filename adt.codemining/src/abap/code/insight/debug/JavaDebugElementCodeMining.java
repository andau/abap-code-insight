package abap.code.insight.debug;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jdt.debug.core.IJavaStackFrame;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Position;

import com.sap.adt.debugger.IAbapStackFrame;

public class JavaDebugElementCodeMining extends AbstractDebugVariableCodeMining<IAbapStackFrame> {

	public JavaDebugElementCodeMining(String codeminingString, int offset, IAbapStackFrame frame, ITextViewer viewer,
			AbstractDebugVariableCodeInsightProvider provider) {
		super(new Position(offset, 1), codeminingString, frame, provider);
	}

	@Override
	protected IVariable findVariable(String variableName) throws DebugException {
		return getStackFrame().findVariable(variableName);
	}

	private IJavaStackFrame getStackFrame() {
		// TODO Auto-generated method stub
		return null;
	}

}
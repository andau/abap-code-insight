package abap.code.insight.debug;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Position;

import com.sap.adt.debugger.IAbapStackFrame;

public class AbapDebugElementCodeMining extends AbstractDebugVariableCodeMining<IAbapStackFrame> {

	public AbapDebugElementCodeMining(String codeminingString, int offset, IAbapStackFrame frame, ITextViewer viewer,
			AbstractDebugVariableCodeInsightProvider provider) {
		super(new Position(offset, 1), codeminingString, frame, provider);
	}

}
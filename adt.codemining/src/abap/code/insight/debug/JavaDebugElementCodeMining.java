package abap.code.insight.debug;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.debug.core.IJavaStackFrame;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Position;

public class JavaDebugElementCodeMining extends AbstractDebugVariableCodeMining<IJavaStackFrame> {

	public JavaDebugElementCodeMining(String simpleName, IJavaStackFrame frame, ITextViewer viewer,
			AbstractDebugVariableCodeInsightProvider provider) {
		super(new Position(1, 30), "testvariable", frame, provider);
	}

	private static Position getPosition(ASTNode node, IDocument document) {
		final int offset = node.getStartPosition();
		try {
			final IRegion region = document.getLineInformationOfOffset(offset);
			return new Position(region.getOffset() + region.getLength(), 30);
		} catch (final BadLocationException e) {
			return new Position(offset, 30);
		}
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
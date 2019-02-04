package abap.code.insight.debug;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jdt.debug.core.IJavaStackFrame;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.ui.texteditor.ITextEditor;

public class AbapDebugCodeInsightProvider extends AbstractDebugVariableCodeInsightProvider<IJavaStackFrame> {

	@SuppressWarnings("unchecked")
	@Override
	protected List provideCodeMinings(ITextViewer viewer, IJavaStackFrame frame, IProgressMonitor monitor) {
		final ITextEditor textEditor = super.getAdapter(ITextEditor.class);

		final List<ICodeMining> minings = new ArrayList<>();
		collectMinings(textEditor, viewer, minings, frame);
		return minings;

	}

	private void collectMinings(ITextEditor textEditor, ITextViewer viewer, List<ICodeMining> minings,
			IJavaStackFrame frame) {
		final AbstractDebugVariableCodeMining<IJavaStackFrame> m = new JavaDebugElementCodeMining("simpleName", frame,
				viewer, this);
		minings.add(m);

	}

	@Override
	protected IJavaStackFrame getStackFrame(ITextViewer viewer, ITextEditor textEditor) {
		final IAdaptable adaptable = DebugUITools.getPartDebugContext(textEditor.getSite());
		if (adaptable != null) {
			return adaptable.getAdapter(IJavaStackFrame.class);
		}
		return null;
	}

}
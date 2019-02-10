package abap.code.insight.debug;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.ui.texteditor.ITextEditor;

import com.sap.adt.debugger.IAbapStackFrame;
import com.sap.adt.debugger.variables.IAbapCustomVariablesContainer;
import com.sap.adt.debugger.variables.IAbapVariable;
import com.sap.adt.debugger.variables.IAbapVariableValue;

public class AbapDebugCodeInsightProvider extends AbstractDebugVariableCodeInsightProvider<IAbapStackFrame> {

	@SuppressWarnings("unchecked")
	@Override
	protected List provideCodeMinings(ITextViewer viewer, IAbapStackFrame frame, IProgressMonitor monitor) {
		final ITextEditor textEditor = super.getAdapter(ITextEditor.class);

		final List<ICodeMining> minings = new ArrayList<>();
		collectMinings(textEditor, viewer, minings, frame);
		return minings;

	}

	@SuppressWarnings("restriction")
	private void collectMinings(ITextEditor textEditor, ITextViewer viewer, List<ICodeMining> minings,
			IAbapStackFrame frame) {

		try {

			if (frame != null) {

				final VariableExtractor variableExtractor = new VariableExtractor(textEditor);
				final List<VariableLineInfo> variableLineInfos = variableExtractor
						.getVariableInfoForLastLines(frame.getLineNumber(), 10);

				final IAbapVariable[] variables = frame.getVariables();
				final IAbapCustomVariablesContainer customVariables = frame.getCustomVariables();

				IAbapVariableValue abapVariableValue = null;
				for (final IAbapVariable variable : variables) {
					final int index = variable.getIndex();
					final String name = variable.getName();
					if (variable.getName().equals("Locals")) {
						final IAbapVariable[] localVariables = variable.getStackFrame().getVariables();
						abapVariableValue = variable.getValue();
					}
				}

				for (final VariableLineInfo variableLineInfo : variableLineInfos) {
					for (final String variableName : variableLineInfo.getVariableNames()) {
						for (final IAbapVariable abapVariable : abapVariableValue.getVariables()) {
							if (abapVariable.getName().toLowerCase().equals(variableName.toLowerCase())) {
								final String codeMiningText = "  " + abapVariable.getValue().toString();
								final AbstractDebugVariableCodeMining<IAbapStackFrame> m = new JavaDebugElementCodeMining(
										codeMiningText, variableLineInfo.getOffsetEnd(), frame, viewer, this);
								minings.add(m);

							}
						}
					}
				}
			}
		} catch (final DebugException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected IAbapStackFrame getStackFrame(ITextViewer viewer, ITextEditor textEditor) {
		final IAdaptable adaptable = DebugUITools.getPartDebugContext(textEditor.getSite());
		if (adaptable != null) {
			return adaptable.getAdapter(IAbapStackFrame.class);
		}
		return null;
	}

}
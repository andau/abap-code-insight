package abap.code.insight.debug;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.ui.texteditor.ITextEditor;

import com.sap.adt.debugger.IAbapStackFrame;
import com.sap.adt.debugger.variables.IAbapVariable;

import abap.codemining.editor.EditorFacade;
import abap.codemining.feature.FeatureFacade;

public class AbapDebugCodeInsightProvider extends AbstractDebugVariableCodeInsightProvider<IAbapStackFrame> {

	private final FeatureFacade featureFacade;

	public AbapDebugCodeInsightProvider() {
		featureFacade = new FeatureFacade();
	}

	@Override
	protected List<ICodeMining> provideCodeMinings(ITextViewer viewer, IAbapStackFrame frame,
			IProgressMonitor monitor) {
		if (featureFacade.getDebugCodeInsightFeature().isActive()) {

			final ITextEditor textEditor = super.getAdapter(ITextEditor.class);

			final List<ICodeMining> minings = new ArrayList<>();
			collectMinings(textEditor, viewer, minings, frame);
			return minings;
		} else {
			return new ArrayList<>();
		}

	}

	@SuppressWarnings("restriction")
	private void collectMinings(ITextEditor textEditor, ITextViewer viewer, List<ICodeMining> minings,
			IAbapStackFrame frame) {

		try {

			final URI frameUri = frame.getUri();
			final EditorFacade editorFacade = new EditorFacade(textEditor);
			final URI editorUri = editorFacade.getAdtObjectReference().getUri();

			if (frameUri.toString().startsWith(editorUri.toString())) {

				final VariableExtractor variableExtractor = new VariableExtractor(textEditor);
				final List<VariableLineInfo> variableLineInfos = variableExtractor
						.getVariableInfoForLastLines(frame.getLineNumber() - 1, 10);

				final IAbapVariable[] variables = frame.getVariables();
				final List<IAbapVariable> variableValues = new ArrayList<>();

				for (final IAbapVariable variable : variables) {
					if (variable.getName().equals("Locals") || variable.getName().contentEquals("ME")) {
						variableValues.addAll(Arrays.asList(variable.getValue().getVariables()));
					} else if (variable.getName().equals("Parameters")) {
						variableValues.addAll(Arrays.asList(variable.getValue().getStackFrame().getVariables()));
					}
				}

				for (final VariableLineInfo variableLineInfo : variableLineInfos) {
					for (final String variableName : variableLineInfo.getVariableNames()) {
						for (final IAbapVariable abapVariable : variableValues) {
							if (abapVariable.getName().toLowerCase().equals(variableName.toLowerCase())) {
								final String codeMiningText = "  " + abapVariable.getValue().toString();
								final AbstractDebugVariableCodeMining<IAbapStackFrame> m = new JavaDebugElementCodeMining(
										codeMiningText, variableLineInfo.getOffsetEnd(), frame, viewer, this);
								minings.add(m);
							}
						}
					}
				}

				// textEditor.setFocus();
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
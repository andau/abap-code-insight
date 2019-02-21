package abap.code.insight.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.texteditor.ITextEditor;

import abap.codemining.editor.EditorFacade;

public class VariableExtractor {

	ITextEditor textEditor;

	public VariableExtractor(ITextEditor textEditor) {
		this.textEditor = textEditor;
	}

	public List<VariableLineInfo> getVariableInfoForLastLines(int lastLine, int numLinesBefore) {
		final List<VariableLineInfo> variableLineInfos = new ArrayList<>();

		for (int currentLineNumber = lastLine - numLinesBefore; currentLineNumber < lastLine; currentLineNumber++) {
			if (currentLineNumber > 0) {
				variableLineInfos.add(getVariableInfoForLine(currentLineNumber));
			}
		}

		return variableLineInfos;
	}

	public VariableLineInfo getVariableInfoForLine(int lineNumber) {

		final IRegion region = getRegion(textEditor, lineNumber);
		if (region != null) {
			final List<String> variables = getVariables(textEditor, region.getOffset(), region.getLength());
			return new VariableLineInfo(lineNumber, region.getOffset() + region.getLength(), variables);
		}
		return new VariableLineInfo(lineNumber, 0, new ArrayList<String>());
	}

	private IRegion getRegion(ITextEditor textEditor, int line) {
		final EditorFacade editorFacade = new EditorFacade(textEditor);
		final IDocument document = editorFacade.getDocument();
		try {
			return document.getLineInformation(line);
		} catch (final BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private List<String> getVariables(ITextEditor textEditor, int offsetStart, int offsetEnd) {
		final EditorFacade editorFacade = new EditorFacade(textEditor);
		final IDocument document = editorFacade.getDocument();
		try {
			final String lineContent = document.get(offsetStart, offsetEnd);
			return extractVariablesOfString(lineContent);
		} catch (final BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private List<String> extractVariablesOfString(String lineContent) {
		final List<String> variableNames = new ArrayList<>();
		final String patternString = "([\\w|\\->]+)";

		final Pattern pattern = Pattern.compile(patternString);
		final Matcher matcher = pattern.matcher(lineContent);
		while (matcher.find()) {
			variableNames.add(matcher.group(1));
		}

		return variableNames;
	}

}

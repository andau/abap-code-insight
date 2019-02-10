package abap.code.insight.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.texteditor.ITextEditor;

import abap.codemining.editor.EditorFacade;

public class VariableExtractor {

	ITextEditor textEditor;

	public VariableExtractor(ITextEditor textEditor) {
		this.textEditor = textEditor;
	}

	public List<VariableLineInfo> getVariableInfoForLastLines(int lastLine, int numLinesBefore) {
		final List<VariableLineInfo> variableLineInfos = new ArrayList<>();

		for (int currentLineNumber = lastLine - 10; currentLineNumber <= lastLine; currentLineNumber++) {
			if (currentLineNumber > 0) {
				variableLineInfos.add(getVariableInfoForLine(currentLineNumber));
			}
		}

		return variableLineInfos;
	}

	public VariableLineInfo getVariableInfoForLine(int lineNumber) {

		final int offsetStart = getOffsetLineStart(textEditor, lineNumber);
		final int offsetEnd = getOffsetLineEnd(textEditor, lineNumber);
		final List<String> variables = getVariables(textEditor, offsetStart, offsetEnd - offsetStart);

		return new VariableLineInfo(lineNumber, offsetEnd, variables);

	}

	private int getOffsetLineStart(ITextEditor textEditor, int line) {
		final EditorFacade editorFacade = new EditorFacade(textEditor);
		final IDocument document = editorFacade.getDocument();
		try {
			return document.getLineOffset(line - 1);
		} catch (final BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	private int getOffsetLineEnd(ITextEditor textEditor, int line) {
		final EditorFacade editorFacade = new EditorFacade(textEditor);
		final IDocument document = editorFacade.getDocument();
		try {
			return document.getLineOffset(line) - 2;
		} catch (final BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
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
		final String patternString = "(\\w+)";
		final Pattern pattern = Pattern.compile(patternString);
		final Matcher matcher = pattern.matcher(lineContent);
		while (matcher.find()) {
			variableNames.add(matcher.group(1));
		}

		return variableNames;
	}

}

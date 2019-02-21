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
import abap.codemining.utils.StringUtils;

public class VariableExtractor {

	final IDocument document;

	public VariableExtractor(ITextEditor textEditor) {
		final EditorFacade editorFacade = new EditorFacade(textEditor);
		document = editorFacade.getDocument();

	}

	public List<VariableLineInfo> getVariableInfoForLastLines(int lastLine, int numLinesBefore) {
		final List<VariableLineInfo> variableLineInfos = new ArrayList<>();

		for (int currentLineNumber = lastLine; currentLineNumber > lastLine - numLinesBefore; currentLineNumber--) {
			if (currentLineNumber > 0) {
				final VariableLineInfo variableLineInfo = getVariableInfoForLine(currentLineNumber);
				if (!variableLineInfo.isMethodStart()) {
					variableLineInfos.add(variableLineInfo);
				} else {
					break;
				}
			}
		}

		return variableLineInfos;
	}

	public VariableLineInfo getVariableInfoForLine(int lineNumber) {

		final IRegion region = getRegion(lineNumber);
		if (region != null) {
			final String lineContent = getLineContent(region.getOffset(), region.getLength());
			final List<String> variables = getVariables(lineContent);
			return new VariableLineInfo(lineNumber, region.getOffset() + region.getLength(), variables,
					lineContent.toLowerCase().startsWith("method"));
		}
		return new VariableLineInfo(lineNumber, 0, new ArrayList<String>(), false);
	}

	private IRegion getRegion(int linenumber) {
		try {
			return document.getLineInformation(linenumber);
		} catch (final BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String getLineContent(int offsetStart, int offsetEnd) {
		try {
			return document.get(offsetStart, offsetEnd);
		} catch (final BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return StringUtils.EMPTY;
	}

	private List<String> getVariables(String lineContent) {
		return extractVariablesOfString(lineContent);
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

package abap.code.insight.debug;

import java.util.List;

public class VariableLineInfo {

	private final int lineNumber;
	private final int offsetEnd;
	private final List<String> variables;
	private final boolean methodStart;

	public VariableLineInfo(int lineNumber, int offsetEnd, List<String> variables, boolean methodStart) {
		this.lineNumber = lineNumber;
		this.offsetEnd = offsetEnd;
		this.variables = variables;
		this.methodStart = methodStart;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public int getOffsetEnd() {
		return offsetEnd;
	}

	public List<String> getVariableNames() {
		return variables;
	}

	public boolean isMethodStart() {
		return methodStart;
	}

}

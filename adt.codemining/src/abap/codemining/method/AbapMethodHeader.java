package abap.codemining.method;

import java.util.HashSet;
import java.util.Set;

import abap.codemining.editor.AbapVisibility;

public class AbapMethodHeader {
	private final String methodName;
	private final String label;
	private final Set<AbapMethodParam> params;
	private final AbapVisibility visibility;

	public AbapMethodHeader(String methodName, String label, AbapVisibility visibility) {
		this.visibility = visibility;
		this.methodName = methodName;
		this.label = label;
		this.params = new HashSet<>();

	}

	public void addParam(AbapMethodParam param) {
		params.add(param);
	}

	public String getMethodHeader() {
		return visibility + " " + label;
	}

	public String getMethodname() {
		return methodName;
	}
}

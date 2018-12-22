package org.eclipse.jdt.experimental.ui.javaeditor.codemining;

import org.apache.tools.ant.util.StringUtils;

public class SampleDocumentContent {

	public String getOneSimpleMethodContent() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("class sampleClass." + StringUtils.LINE_SEP);
		stringBuilder.append("public section." + StringUtils.LINE_SEP);
		stringBuilder.append("methods: sampleMethod1." + StringUtils.LINE_SEP);
		stringBuilder.append("endclass." + StringUtils.LINE_SEP);
		stringBuilder.append(StringUtils.LINE_SEP);
		stringBuilder.append("method sampleMethod1." + StringUtils.LINE_SEP);
		stringBuilder.append("endmethod." + StringUtils.LINE_SEP);
		stringBuilder.append(StringUtils.LINE_SEP);
		stringBuilder.append("method sampleMethod2.");
		stringBuilder.append(StringUtils.LINE_SEP);
		stringBuilder.append("endmethod.");

		return stringBuilder.toString();

	}
}

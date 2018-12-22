package abap.codemining.general;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.tools.ant.util.StringUtils;

import abap.codemining.editor.AbapVisibility;
import abap.codemining.method.AbapMethodHeader;
import abap.codemining.section.AbapMethodsHeaderSection;
import abap.codemining.section.AbapSectionExtractor;

public class AbapClassSectionParser {

	private static final String METHODS_PREFIX = "methods:";
	private final AbapVisibility visibility;

	public AbapClassSectionParser(AbapVisibility visibility) {
		this.visibility = visibility;
	}

	public Collection<AbapMethodHeader> getMethodHeaders(String sectionContent) {
		AbapSectionExtractor abapSectionExtractor = new AbapSectionExtractor(new AbapMethodsHeaderSection());
		String methodsSection = abapSectionExtractor.extract(sectionContent);

		return getMethods(methodsSection);

	}

	private Set<AbapMethodHeader> getMethods(String methodSection) {
		Set<AbapMethodHeader> abapMethodHeaders = new HashSet<>();

		String methodRawString = methodSection.replace(METHODS_PREFIX, "");
		String[] methods = methodRawString.split(",");
		for (String method : methods) {
			AbapMethodHeader abapMethodHeader = extractAbapMethodHeader(method);
			if (method.length() > 0) {
				abapMethodHeaders.add(abapMethodHeader);
			}
		}

		return abapMethodHeaders;
	}

	private AbapMethodHeader extractAbapMethodHeader(String abapMethodHeaderString) {

		abapMethodHeaderString = abapMethodHeaderString.replace(StringUtils.LINE_SEP, " ");
		String content = abapMethodHeaderString;
		content = content.trim() + " ";
		content = content.replace(".", " .");
		String methodName = content.substring(0, content.indexOf(" "));

		/*
		 * content.replace("IMPORTING", StringUtils.LINE_SEP + "IMPORTING");
		 * content.replace("EXPORTING", StringUtils.LINE_SEP + "EXPORTING");
		 * content.replace("CHANGING", StringUtils.LINE_SEP + "CHANGING");
		 * content.replace("RAISING", StringUtils.LINE_SEP + "RAISING");
		 * content.replace("RESUMABLE", StringUtils.LINE_SEP + "RESUMABLE");
		 * content.replace("EXCEPTIONS", StringUtils.LINE_SEP + "EXCEPTIONS");
		 *
		 * String[] contentLines = content.split(StringUtils.LINE_SEP); String
		 * methodHeader = contentLines[0];
		 *
		 * for(String contentLine : contentLines) { if
		 * (contentLine.startsWith("IMPORTING")) { extractParams(contentLine,
		 * "IMPORTING"); } }
		 *
		 * String methodName; return new AbapMethodHeader(methodName, visibility);
		 */
		return new AbapMethodHeader(methodName, abapMethodHeaderString, visibility);
	}

	private void extractParams(String contentLine, String direction) {
		contentLine = contentLine.substring(direction.length());

	}

}

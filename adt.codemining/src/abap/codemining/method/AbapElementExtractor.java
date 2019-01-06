package abap.codemining.method;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import abap.codemining.element.AbapClassBody;
import abap.codemining.element.AbapMethodBody;

public class AbapElementExtractor {

	private static final String METHOD_BODY_REGEX = "\\s*" + "method" + "\\s+" + "([\\w|~]+)" + "\\s*" + "\\." + "\\s*";
	private static final String METHOD_CLASS_REGEX = "\\s*" + "class" + "\\s+" + "([\\w|~]+)" + "\\s*"
			+ "implementation" + "\\s*" + "\\." + "\\s*";
	private static final String METHOD_CLASS_DEF_REGEX = "\\s*" + "class" + "\\s+" + "([\\w|~]+)" + "\\s*"
			+ "definition";

	public AbapMethodBody extractMethodBody(String line, int linenumber) {
		ElementMatchInformation elementMatchInformation = findMatch(METHOD_BODY_REGEX, line, linenumber);
		if (elementMatchInformation != null) {
			return new AbapMethodBody(elementMatchInformation.getClassname(), linenumber,
					elementMatchInformation.getStartindex());
		}

		return null;
	}

	public AbapClassBody extractClassBody(String line, int linenumber) {
		ElementMatchInformation elementMatchInformation = findMatch(METHOD_CLASS_REGEX, line, linenumber);
		if (elementMatchInformation != null) {
			return new AbapClassBody(elementMatchInformation.getClassname(), linenumber,
					elementMatchInformation.getStartindex());
		}

		return null;
	}

	public AbapClassBody extractClassDef(String line, int linenumber) {
		ElementMatchInformation elementMatchInformation = findMatch(METHOD_CLASS_DEF_REGEX, line, linenumber);
		if (elementMatchInformation != null) {
			return new AbapClassBody(elementMatchInformation.getClassname(), linenumber,
					elementMatchInformation.getStartindex());
		}

		return null;
	}

	
	private ElementMatchInformation findMatch(String regex, String line, int linenumber) {
		if (line.matches(regex)) {

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(line);

			if (matcher.find()) {
				String elementName = matcher.group(1);
				int startindex = matcher.end(1) - 1;

				return new ElementMatchInformation(elementName, startindex);
			}
		}

		return null;
	}

}

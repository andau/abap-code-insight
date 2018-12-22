package abap.codemining.method;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbapMethodBodyExtractor {

	private static final String METHOD_BODY_REGEX = "\\s*" + "method" + "\\s+" + "(\\w+)" + "\\s*" + "\\." + "\\s*";

	public AbapMethodBody extract(String line, int linenumber) {
		if (line.matches(METHOD_BODY_REGEX)) {
			String methodname = "";

			Pattern pattern = Pattern.compile(METHOD_BODY_REGEX);
			Matcher matcher = pattern.matcher(line);
			if (matcher.find()) {
				methodname = matcher.group(1);
			}

			return new AbapMethodBody(methodname, linenumber);
		} else {
			return null;
		}

	}

}

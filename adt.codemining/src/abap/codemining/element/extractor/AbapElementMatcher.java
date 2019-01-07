package abap.codemining.element.extractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import abap.codemining.method.ElementMatchInformation;

public class AbapElementMatcher {
	public ElementMatchInformation findMatch(String regex, String line, int linenumber) {
		if (line.matches(regex)) {

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(line);

			if (matcher.find()) {
				String elementName = matcher.group(1);
				int startindex = matcher.end(1) - 1;

				return new ElementMatchInformation(elementName, startindex, linenumber);
			}
		}

		return null;
	}

}

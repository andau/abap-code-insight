package abap.codemining.section;

public class RegexSectionParts {
	public static final String POINT = "\\.";
	public static final String ANY = ".*";
	public static String WHITESPACES = "\\s*";

	public static String withSpaces(String string) {
		return WHITESPACES + string + WHITESPACES;
	}
}

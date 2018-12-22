package abap.codemining.section;

public class AbapMethodsHeaderSection implements IAbapSectionDefinitionRegex {

	private static final String STARTPOINT_REGEX = "methods:.*";
	private static final String ENDPOINT_REGEX = ".*\\..*";

	@Override
	public boolean matchesStartpoint(String s) {
		return s.matches(STARTPOINT_REGEX);
	}

	@Override
	public boolean matchesEndpoint(String s) {
		return s.matches(ENDPOINT_REGEX);
	}

}

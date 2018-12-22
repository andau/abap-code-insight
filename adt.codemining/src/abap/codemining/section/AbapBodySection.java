package abap.codemining.section;

public class AbapBodySection implements IAbapSectionDefinitionRegex {

	private static final String STARTPOINT_REGEX = "class\\s+.+\\s+implementation\\s+";
	private static final String ENDPOINT_PREFIX = "*EOF*";

	@Override
	public boolean matchesStartpoint(String s) {
		return s.matches(STARTPOINT_REGEX);
	}

	@Override
	public boolean matchesEndpoint(String s) {
		return s.matches(ENDPOINT_PREFIX);
	}

}

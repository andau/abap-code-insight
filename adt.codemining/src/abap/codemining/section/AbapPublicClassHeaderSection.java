package abap.codemining.section;

public class AbapPublicClassHeaderSection implements IAbapSectionDefinitionRegex {

	private static final String STARTPOINT_REGEX = RegexSectionParts.withSpaces("public")
			+ RegexSectionParts.withSpaces("section") + RegexSectionParts.POINT + RegexSectionParts.ANY;
	private static final String ENDPOINT_REGEX = RegexSectionParts.withSpaces("endclass") + RegexSectionParts.POINT
			+ RegexSectionParts.ANY;

	@Override
	public boolean matchesStartpoint(String s) {
		return s.matches(STARTPOINT_REGEX);
	}

	@Override
	public boolean matchesEndpoint(String s) {
		return s.matches(ENDPOINT_REGEX);
	}

}

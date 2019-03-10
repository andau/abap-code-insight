package abap.codemining.element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElementLinkInfo {

	private static final String REGEX_LINENUMBER = "start=" + "([0-9]+)" + ",";
	private static final String REGEX_ELEMENT_STARTINDEX = "start=[0-9]+," + "([0-9]+)" + ";";

	private final String link;
	private final String relation;
	private final String type;

	public ElementLinkInfo(String link, String relation, String type) {
		this.link = link;
		this.relation = relation;
		this.type = type;
	}

	public String getLink() {
		return link;
	}

	public int getLinenumber() throws InvalidElementLinkException {
		final Pattern pattern = Pattern.compile(REGEX_LINENUMBER);
		final Matcher matcher = pattern.matcher(link.toString());
		if (matcher.find()) {
			return Integer.parseInt(matcher.group(1));
		} else {
			throw new InvalidElementLinkException();
		}
	}

	public int getElementStartposition() throws InvalidElementLinkException {
		final Pattern pattern = Pattern.compile(REGEX_ELEMENT_STARTINDEX);
		final Matcher matcher = pattern.matcher(link.toString());
		if (matcher.find()) {
			return Integer.parseInt(matcher.group(1));
		} else {
			throw new InvalidElementLinkException();
		}
	}

	public String getRelation() {
		return relation;
	}

	public String getType() {
		return type;
	}

}

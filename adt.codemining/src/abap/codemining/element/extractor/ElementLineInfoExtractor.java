package abap.codemining.element.extractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sap.adt.tools.abapsource.sources.objectstructure.IObjectStructureElement;
import com.sap.adt.tools.core.model.atom.IAtomLink;

public class ElementLineInfoExtractor {
	// #start=7,12;end=7,16,

	private static final String METHOD_CLASS_HEADER_REGEX = "start=" + "([0-9]+)" + ",";

	public int extractLinenumber(IObjectStructureElement childObjectStructureElement) {
		for (final IAtomLink link : childObjectStructureElement.getLinks()) {
			final Pattern pattern = Pattern.compile(METHOD_CLASS_HEADER_REGEX);
			final Matcher matcher = pattern.matcher(link.toString());
			if (matcher.find()) {
				return Integer.parseInt(matcher.group(1));
			}
		}
		return 0;
	}
}
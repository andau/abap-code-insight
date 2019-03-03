package abap.codemining.element.extractor;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sap.adt.tools.abapsource.sources.objectstructure.IObjectStructureElement;
import com.sap.adt.tools.core.model.atom.IAtomLink;

public class ElementLineInfoExtractor {
	public final String DEFINITION_BLOCK_IDENTIFIER = "http://www.sap.com/adt/relations/source/definitionBlock";
	public final String IMPLEMENTATION_BLOCK_IDENTIFIER = "http://www.sap.com/adt/relations/source/implementationBlock";

	private static final String METHOD_CLASS_HEADER_REGEX = "start=" + "([0-9]+)" + ",";

	public Set<Integer> extractLinenumbers(IObjectStructureElement childObjectStructureElement) {
		final Set<Integer> linenumbers = new HashSet<>();

		for (final IAtomLink link : childObjectStructureElement.getLinks()) {
			if (relationdoesnotMatchBlock(link)) {

				final Pattern pattern = Pattern.compile(METHOD_CLASS_HEADER_REGEX);
				final Matcher matcher = pattern.matcher(link.toString());
				if (matcher.find()) {
					linenumbers.add(Integer.parseInt(matcher.group(1)));
				}
			}
		}
		return linenumbers;
	}

	private boolean relationdoesnotMatchBlock(final IAtomLink link) {
		return !link.getRel().equals(DEFINITION_BLOCK_IDENTIFIER)
				&& !link.getRel().equals(IMPLEMENTATION_BLOCK_IDENTIFIER);
	}
}
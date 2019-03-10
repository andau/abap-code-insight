package abap.codemining.element.extractor;

import java.util.HashSet;
import java.util.Set;

import com.sap.adt.tools.abapsource.sources.objectstructure.IObjectStructureElement;
import com.sap.adt.tools.core.model.atom.IAtomLink;

import abap.codemining.element.ElementLinkInfo;

public class ElementLineInfoExtractor {
	public final String DEFINITION_BLOCK_IDENTIFIER = "http://www.sap.com/adt/relations/source/definitionBlock";
	public final String IMPLEMENTATION_BLOCK_IDENTIFIER = "http://www.sap.com/adt/relations/source/implementationBlock";

	public Set<ElementLinkInfo> extractElementLinkInfos(IObjectStructureElement childObjectStructureElement) {
		final Set<ElementLinkInfo> elementLinkInfos = new HashSet<>();

		for (final IAtomLink link : childObjectStructureElement.getLinks()) {
			if (relationdoesnotMatchBlock(link)) {
				elementLinkInfos.add(new ElementLinkInfo(link.getHref(), link.getRel(),
						childObjectStructureElement.getAttribute("type")));
			}
		}
		return elementLinkInfos;
	}

	private boolean relationdoesnotMatchBlock(final IAtomLink link) {
		return !link.getRel().equals(DEFINITION_BLOCK_IDENTIFIER)
				&& !link.getRel().equals(IMPLEMENTATION_BLOCK_IDENTIFIER);
	}
}
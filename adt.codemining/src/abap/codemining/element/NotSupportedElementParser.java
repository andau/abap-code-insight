package abap.codemining.element;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.text.IDocument;

import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.FeatureFacade;

public class NotSupportedElementParser implements IAbapElementParser {

	boolean publicSector = false;
	FeatureFacade featureFacade;

	public NotSupportedElementParser(FeatureFacade featureFacade) {
		this.featureFacade = featureFacade;
	}

	public AbapElementInformation getElementInformation(IDocument doc) {

		Set<IAbapElement> abapElements = new HashSet<IAbapElement>();
		return new AbapElementInformation(abapElements);
	}

}

package abap.codemining.element.parser;

import abap.codemining.element.extractor.InterfaceHeaderElementExtractor;
import abap.codemining.feature.FeatureFacade;

public class AbapInterfaceElementParser extends AbstractElementParser {

	public AbapInterfaceElementParser(FeatureFacade featureFacade) {
		super(featureFacade);
	}

	@Override
	protected void initializeElementExtractors() {
		elementExtractors.add(new InterfaceHeaderElementExtractor(featureFacade.getInterfaceMiningFeature()));
	}

}

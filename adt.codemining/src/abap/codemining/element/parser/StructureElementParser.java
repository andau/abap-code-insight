package abap.codemining.element.parser;

import abap.codemining.element.extractor.StructureElementExtractor;
import abap.codemining.feature.FeatureFacade;

public class StructureElementParser extends AbstractElementParser {

	public StructureElementParser(FeatureFacade featureFacade) {
		super(featureFacade);
	}

	@Override
	protected void initializeElementExtractors() {
		elementExtractors.add(new StructureElementExtractor(featureFacade.getStructureFeature()));
	}

}

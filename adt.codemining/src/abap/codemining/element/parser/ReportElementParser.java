package abap.codemining.element.parser;

import abap.codemining.element.extractor.ReportHeaderElementExtractor;
import abap.codemining.element.extractor.ReportModuleElementExtractor;
import abap.codemining.feature.FeatureFacade;

public class ReportElementParser extends AbstractElementParser {

	public ReportElementParser(FeatureFacade featureFacade) {
		super(featureFacade);
	}

	@Override
	protected void initializeElementExtractors() {
		elementExtractors.add(new ReportHeaderElementExtractor(featureFacade.getReportMiningFeature()));
		elementExtractors.add(new ReportModuleElementExtractor(featureFacade.getReportMiningFeature()));
	}

}

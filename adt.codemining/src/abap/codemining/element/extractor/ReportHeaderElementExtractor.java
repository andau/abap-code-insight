package abap.codemining.element.extractor;

import abap.codemining.element.domain.CdsHeader;
import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.ReferenceMiningFeature;
import abap.codemining.label.ReferenceOnlyMiningLabelBuilder;
import abap.codemining.label.IMiningLabelBuilder;
import abap.codemining.method.ElementMatchInformation;

public class ReportHeaderElementExtractor extends AbapElementExtractor implements IAbapElementExtractor {

	private static final String REPORT_HEADER_REGEX = "\\s*" + "report" + "\\s+" + "([\\w|~]+).*";

	private final ReferenceMiningFeature reportMiningFeature;

	public ReportHeaderElementExtractor(ReferenceMiningFeature reportMiningFeature) {
		this.reportMiningFeature = reportMiningFeature;
	}

	@Override
	protected IAbapElement createAbapElement(ElementMatchInformation elementMatchInformation) {
		return new CdsHeader(elementMatchInformation.getClassname(), elementMatchInformation.getLinenumber(),
				elementMatchInformation.getStartindex(), getMiningLabelBuilder());
	}

	@Override
	protected IMiningLabelBuilder getMiningLabelBuilder() {
		return new ReferenceOnlyMiningLabelBuilder(reportMiningFeature);
	}

	@Override
	protected String getRegex() {
		return REPORT_HEADER_REGEX;
	}
}

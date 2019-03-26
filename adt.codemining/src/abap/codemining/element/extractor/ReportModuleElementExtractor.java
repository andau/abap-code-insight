package abap.codemining.element.extractor;

import abap.codemining.element.domain.CdsHeader;
import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.ReferenceMiningFeature;
import abap.codemining.label.IMiningLabelBuilder;
import abap.codemining.label.ReferenceOnlyMiningLabelBuilder;
import abap.codemining.method.ElementMatchInformation;

public class ReportModuleElementExtractor extends AbapElementExtractor implements IAbapElementExtractor {

	private static final String REPORT_MODULE_REGEX = "\\s*" + "module" + "\\s+" + ELEMENT_NAME_REGEX + ".*";

	private final ReferenceMiningFeature cdsMiningFeature;

	public ReportModuleElementExtractor(ReferenceMiningFeature cdsMiningFeature) {
		this.cdsMiningFeature = cdsMiningFeature;
	}

	@Override
	protected IAbapElement createAbapElement(ElementMatchInformation elementMatchInformation) {
		return new CdsHeader(elementMatchInformation.getClassname(), elementMatchInformation.getLinenumber(),
				elementMatchInformation.getStartindex(), elementMatchInformation.getStartindex(),
				getMiningLabelBuilder());
	}

	@Override
	protected IMiningLabelBuilder getMiningLabelBuilder() {
		return new ReferenceOnlyMiningLabelBuilder(cdsMiningFeature);
	}

	@Override
	protected String getRegex() {
		return REPORT_MODULE_REGEX;
	}
}

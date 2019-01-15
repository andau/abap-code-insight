package abap.codemining.element.extractor;

import abap.codemining.element.domain.CdsHeader;
import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.ReferenceMiningFeature;
import abap.codemining.label.ReferenceOnlyMiningLabelBuilder;
import abap.codemining.label.IMiningLabelBuilder;
import abap.codemining.method.ElementMatchInformation;

public class FunctionModuleElementExtractor extends AbapElementExtractor implements IAbapElementExtractor {

	private static final String FM_HEADER_REGEX = "\\s*" + "function" + "\\s+" + "([\\w|~]+)" + ".*";

	private final ReferenceMiningFeature cdsMiningFeature;

	public FunctionModuleElementExtractor(ReferenceMiningFeature cdsMiningFeature) {
		this.cdsMiningFeature = cdsMiningFeature;
	}

	@Override
	protected IAbapElement createAbapElement(ElementMatchInformation elementMatchInformation) {
		return new CdsHeader(elementMatchInformation.getClassname(), elementMatchInformation.getLinenumber(),
				elementMatchInformation.getStartindex(), getMiningLabelBuilder());
	}

	@Override
	protected IMiningLabelBuilder getMiningLabelBuilder() {
		return new ReferenceOnlyMiningLabelBuilder(cdsMiningFeature);
	}

	@Override
	protected String getRegex() {
		return FM_HEADER_REGEX;
	}
}

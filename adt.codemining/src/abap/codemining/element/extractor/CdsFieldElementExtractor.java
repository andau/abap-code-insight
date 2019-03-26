package abap.codemining.element.extractor;

import abap.codemining.element.domain.CdsHeader;
import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.ReferenceMiningFeature;
import abap.codemining.label.CdsFieldMiningLabelBuilder;
import abap.codemining.label.IMiningLabelBuilder;
import abap.codemining.method.ElementMatchInformation;

public class CdsFieldElementExtractor extends AbapElementExtractor implements IAbapElementExtractor {

	private static final String CDS_HEADER_REGEX = "\\s*" + ELEMENT_NAME_REGEX + "\\s*" + ",*";

	private final ReferenceMiningFeature cdsMiningFeature;

	public CdsFieldElementExtractor(ReferenceMiningFeature cdsMiningFeature) {
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
		return new CdsFieldMiningLabelBuilder(cdsMiningFeature);
	}

	@Override
	protected String getRegex() {
		return CDS_HEADER_REGEX;
	}
}

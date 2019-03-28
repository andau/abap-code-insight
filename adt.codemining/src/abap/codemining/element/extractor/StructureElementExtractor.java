package abap.codemining.element.extractor;

import abap.codemining.element.domain.CdsHeader;
import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.ReferenceMiningFeature;
import abap.codemining.label.IMiningLabelBuilder;
import abap.codemining.label.ReferenceOnlyMiningLabelBuilder;
import abap.codemining.method.ElementMatchInformation;

public class StructureElementExtractor extends AbapElementExtractor implements IAbapElementExtractor {

	private static final String STRUCTURE_MODULE_REGEX = "\\s*" + "define type" + "\\s+" + ELEMENT_NAME_REGEX + ".*";

	private final ReferenceMiningFeature structureMiningFeature;

	public StructureElementExtractor(ReferenceMiningFeature structureMiningFeature) {
		this.structureMiningFeature = structureMiningFeature;
	}

	@Override
	protected IAbapElement createAbapElement(ElementMatchInformation elementMatchInformation) {
		return new CdsHeader(elementMatchInformation.getClassname(), elementMatchInformation.getLinenumber(),
				elementMatchInformation.getStartindex(), elementMatchInformation.getStartindex(),
				getMiningLabelBuilder());
	}

	@Override
	protected IMiningLabelBuilder getMiningLabelBuilder() {
		return new ReferenceOnlyMiningLabelBuilder(structureMiningFeature);
	}

	@Override
	protected String getRegex() {
		return STRUCTURE_MODULE_REGEX;
	}
}

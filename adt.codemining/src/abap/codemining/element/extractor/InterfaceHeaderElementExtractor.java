package abap.codemining.element.extractor;

import abap.codemining.element.domain.AbapClassBody;
import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.ReferenceMiningFeature;
import abap.codemining.label.IMiningLabelBuilder;
import abap.codemining.label.ReferenceOnlyMiningLabelBuilder;
import abap.codemining.method.ElementMatchInformation;

public class InterfaceHeaderElementExtractor extends AbapElementExtractor implements IAbapElementExtractor {
	private static final String INTERFACE_HEADER_REGEX = "\\s*" + "interface" + "\\s+" + ELEMENT_NAME_REGEX + ".*";

	private final ReferenceMiningFeature interfaceMiningFeature;

	public InterfaceHeaderElementExtractor(ReferenceMiningFeature interfaceMiningFeature) {
		this.interfaceMiningFeature = interfaceMiningFeature;
	}

	@Override
	protected IAbapElement createAbapElement(ElementMatchInformation elementMatchInformation) {
		return new AbapClassBody(elementMatchInformation.getClassname(), elementMatchInformation.getLinenumber(),
				elementMatchInformation.getStartindex(), getMiningLabelBuilder());
	}

	@Override
	protected IMiningLabelBuilder getMiningLabelBuilder() {
		return new ReferenceOnlyMiningLabelBuilder(interfaceMiningFeature);
	}

	@Override
	protected String getRegex() {
		return INTERFACE_HEADER_REGEX;
	}
}

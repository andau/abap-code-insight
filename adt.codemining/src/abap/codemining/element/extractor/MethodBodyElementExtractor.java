package abap.codemining.element.extractor;

import abap.codemining.element.domain.AbapClassBody;
import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.MethodBodyMiningFeature;
import abap.codemining.label.IMiningLabelBuilder;
import abap.codemining.label.MethodMiningLabelBuilder;
import abap.codemining.method.ElementMatchInformation;

public class MethodBodyElementExtractor extends AbapElementExtractor implements IAbapElementExtractor {

	private static final String METHOD_BODY_REGEX = "\\s*" + "method" + "\\s+" + ELEMENT_NAME_REGEX + ".*";
	private final MethodBodyMiningFeature methodBodyMiningFeature;

	public MethodBodyElementExtractor(MethodBodyMiningFeature methodBodyMiningFeature) {
		this.methodBodyMiningFeature = methodBodyMiningFeature;
	}

	@Override
	protected IAbapElement createAbapElement(ElementMatchInformation elementMatchInformation) {
		return new AbapClassBody(elementMatchInformation.getClassname(), elementMatchInformation.getLinenumber(),
				elementMatchInformation.getStartindex(), getMiningLabelBuilder());
	}

	@Override
	protected IMiningLabelBuilder getMiningLabelBuilder() {
		return new MethodMiningLabelBuilder(methodBodyMiningFeature);
	}

	@Override
	protected String getRegex() {
		return METHOD_BODY_REGEX;
	}
}

package abap.codemining.element.extractor;

import abap.codemining.element.domain.AbapClassBody;
import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.ClassMiningFeature;
import abap.codemining.label.ClassMiningLabelBuilder;
import abap.codemining.label.IMiningLabelBuilder;
import abap.codemining.method.ElementMatchInformation;

public class ClassBodyElementExtractor extends AbapElementExtractor implements IAbapElementExtractor {

	private static final String METHOD_CLASS_REGEX = "\\s*" + "class" + "\\s+" + ELEMENT_NAME_REGEX + ".*"
			+ "implementation" + ".*";

	private final ClassMiningFeature classBodyMiningFeature;

	public ClassBodyElementExtractor(ClassMiningFeature classBodyMiningFeature) {
		this.classBodyMiningFeature = classBodyMiningFeature;
	}

	@Override
	protected IAbapElement createAbapElement(ElementMatchInformation elementMatchInformation) {
		return new AbapClassBody(elementMatchInformation.getClassname(), elementMatchInformation.getLinenumber(),
				elementMatchInformation.getStartindex(), getMiningLabelBuilder());
	}

	@Override
	protected IMiningLabelBuilder getMiningLabelBuilder() {
		return new ClassMiningLabelBuilder(classBodyMiningFeature);
	}

	@Override
	protected String getRegex() {
		return METHOD_CLASS_REGEX;
	}
}

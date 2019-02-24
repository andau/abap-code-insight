package abap.codemining.element.extractor;

import abap.codemining.element.domain.AbapClassBody;
import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.ClassMiningFeature;
import abap.codemining.label.ClassMiningLabelBuilder;
import abap.codemining.label.IMiningLabelBuilder;
import abap.codemining.method.ElementMatchInformation;

public class ElementInfoExtractor extends AbapElementExtractor implements IAbapElementExtractor {

	private static final String ELEMENT_INFO_REGEX = "\\s*" + "class" + "\\s+" + ELEMENT_NAME_REGEX + "\\s+"
			+ "definition" + ".*";

	private final ClassMiningFeature classHeaderMiningFeature;

	public ElementInfoExtractor(ClassMiningFeature classHeaderMiningFeature) {
		this.classHeaderMiningFeature = classHeaderMiningFeature;
	}

	@Override
	protected IAbapElement createAbapElement(ElementMatchInformation elementMatchInformation) {
		return new AbapClassBody(elementMatchInformation.getClassname(), elementMatchInformation.getLinenumber(),
				elementMatchInformation.getStartindex(), getMiningLabelBuilder());
	}

	@Override
	protected IMiningLabelBuilder getMiningLabelBuilder() {
		return new ClassMiningLabelBuilder(classHeaderMiningFeature);
	}

	@Override
	protected String getRegex() {
		return ELEMENT_INFO_REGEX;
	}
}

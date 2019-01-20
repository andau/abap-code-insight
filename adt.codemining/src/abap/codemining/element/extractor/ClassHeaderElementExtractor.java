package abap.codemining.element.extractor;

import abap.codemining.element.domain.AbapClassBody;
import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.ClassMiningFeature;
import abap.codemining.label.ClassMiningLabelBuilder;
import abap.codemining.label.IMiningLabelBuilder;
import abap.codemining.method.ElementMatchInformation;

public class ClassHeaderElementExtractor extends AbapElementExtractor implements IAbapElementExtractor {

	private static final String METHOD_CLASS_HEADER_REGEX = "\\s*" + "class" + "\\s+" + "([\\w|~]+)" + "\\s+"
			+ "definition" + "\\s*" + ".*";

	private final ClassMiningFeature classHeaderMiningFeature;

	public ClassHeaderElementExtractor(ClassMiningFeature classHeaderMiningFeature) {
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
		return METHOD_CLASS_HEADER_REGEX;
	}
}

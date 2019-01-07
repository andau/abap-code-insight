package abap.codemining.element.extractor;

import abap.codemining.element.domain.IAbapElement;
import abap.codemining.label.IMiningLabelBuilder;
import abap.codemining.method.ElementMatchInformation;

public abstract class AbapElementExtractor implements IAbapElementExtractor {

	@Override
	public IAbapElement extractFromLine(String line, int linenumber) {
		AbapElementMatcher abapElementMatcher = new AbapElementMatcher();
		ElementMatchInformation elementMatchInformation = abapElementMatcher.findMatch(getRegex(), line, linenumber);
		if (elementMatchInformation != null) {
			return createAbapElement(elementMatchInformation);
		}

		return null;
	}

	protected abstract String getRegex();

	protected abstract IAbapElement createAbapElement(ElementMatchInformation elementMatchInformation);

	protected abstract IMiningLabelBuilder getMiningLabelBuilder();
}

package abap.codemining.element.extractor;

import abap.codemining.element.domain.CdsHeader;
import abap.codemining.element.domain.IAbapElement;
import abap.codemining.feature.CdsMiningFeature;
import abap.codemining.label.CdsMiningLabelBuilder;
import abap.codemining.label.IMiningLabelBuilder;
import abap.codemining.method.ElementMatchInformation;

public class CdsViewElementExtractor extends AbapElementExtractor implements IAbapElementExtractor {

	private static final String CDS_HEADER_REGEX = "\\s*" + "define view" + "\\s+" + "([\\w|~]+)";


	private final CdsMiningFeature cdsMiningFeature;

	public CdsViewElementExtractor(CdsMiningFeature cdsMiningFeature) {
		this.cdsMiningFeature = cdsMiningFeature;
	}

	@Override
	protected IAbapElement createAbapElement(ElementMatchInformation elementMatchInformation) {
		return new CdsHeader(elementMatchInformation.getClassname(), elementMatchInformation.getLinenumber(),
				elementMatchInformation.getStartindex(), getMiningLabelBuilder());
	}

	@Override
	protected IMiningLabelBuilder getMiningLabelBuilder() {
		return new CdsMiningLabelBuilder(cdsMiningFeature);
	}

	@Override
	protected String getRegex() {
		return CDS_HEADER_REGEX;
	}
}

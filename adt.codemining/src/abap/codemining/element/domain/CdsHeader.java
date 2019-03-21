package abap.codemining.element.domain;

import abap.codemining.label.IMiningLabelBuilder;

public class CdsHeader extends AbapElement {

	public CdsHeader(String methodname, int linenumber, int elementStartindex, int startindex,
			IMiningLabelBuilder miningLabelBuilder) {
		super(methodname, linenumber, "no text", elementStartindex, startindex, miningLabelBuilder);
	}

}

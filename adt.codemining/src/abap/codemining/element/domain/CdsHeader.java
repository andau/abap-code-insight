package abap.codemining.element.domain;

import abap.codemining.label.IMiningLabelBuilder;

public class CdsHeader extends AbapElement {

	public CdsHeader(String methodname, int linenumber, int startindex, IMiningLabelBuilder miningLabelBuilder) {
		super(methodname, linenumber, startindex, miningLabelBuilder);
	}

}

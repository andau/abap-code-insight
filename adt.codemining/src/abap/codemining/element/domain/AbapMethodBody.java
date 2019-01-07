package abap.codemining.element.domain;

import abap.codemining.label.IMiningLabelBuilder;

public class AbapMethodBody extends AbapElement {

	public AbapMethodBody(String methodname, int linenumber, int startindex, IMiningLabelBuilder miningLabelBuilder) {
		super(methodname, linenumber, startindex, miningLabelBuilder);
	}

}

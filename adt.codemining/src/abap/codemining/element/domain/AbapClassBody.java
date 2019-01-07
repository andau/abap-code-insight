package abap.codemining.element.domain;

import abap.codemining.label.IMiningLabelBuilder;

public class AbapClassBody extends AbapElement {

	public AbapClassBody(String methodname, int linenumber, int startindex, IMiningLabelBuilder miningLabelBuilder) {
		super(methodname, linenumber, startindex, miningLabelBuilder);
	}

}

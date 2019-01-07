package abap.codemining.element.domain;

import abap.codemining.label.IMiningLabelBuilder;

public abstract class AbapElement implements IAbapElement {

	private final String methodname;
	private final int linenumber;
	private final int startindex;
	private final IMiningLabelBuilder miningLabelBuilder;

	public AbapElement(String methodname, int linenumber, int startindex, IMiningLabelBuilder miningLabelBuilder) {
		this.methodname = methodname;
		this.linenumber = linenumber;
		this.startindex = startindex;
		this.miningLabelBuilder = miningLabelBuilder;
	}

	@Override
	public int getLinenumber() {
		return linenumber;
	}

	@Override
	public String getElementname() {
		return methodname;
	}

	@Override
	public int getStartindex() {
		return startindex;
	}

	public IMiningLabelBuilder getMiningLabelBuilder() {
		return miningLabelBuilder;
	}

}

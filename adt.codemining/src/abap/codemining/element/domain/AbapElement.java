package abap.codemining.element.domain;

import abap.codemining.label.IMiningLabelBuilder;

public abstract class AbapElement implements IAbapElement {

	private final String methodname;
	private final int linenumber;
	private final int elementStartindex;
	private final int miningStartindex;
	private final IMiningLabelBuilder miningLabelBuilder;

	public AbapElement(String methodname, int linenumber, int elementStartindex, int miningStartindex,
			IMiningLabelBuilder miningLabelBuilder) {
		this.methodname = methodname;
		this.linenumber = linenumber;
		this.elementStartindex = elementStartindex;
		this.miningStartindex = miningStartindex;
		this.miningLabelBuilder = miningLabelBuilder;
	}

	@Override
	public int getLinenumber() {
		return linenumber;
	}

	@Override
	public int getElementPosition() {
		return elementStartindex;
	}

	@Override
	public String getElementname() {
		return methodname;
	}

	@Override
	public int getMiningStartindex() {
		return miningStartindex;
	}

	@Override
	public IMiningLabelBuilder getMiningLabelBuilder() {
		return miningLabelBuilder;
	}

}

package abap.codemining.element.domain;

import abap.codemining.label.IMiningLabelBuilder;

public abstract class AbapElement implements IAbapElement {

	private final String methodname;
	private final int linenumber;
	private final int elementStartindex;
	private final int miningStartindex;
	private final IMiningLabelBuilder miningLabelBuilder;
	private final String linetext;

	public AbapElement(String methodname, int linenumber, String linetext, int elementStartindex, int miningStartindex,
			IMiningLabelBuilder miningLabelBuilder) {
		this.methodname = methodname;
		this.linenumber = linenumber;
		this.elementStartindex = elementStartindex;
		this.miningStartindex = miningStartindex;
		this.miningLabelBuilder = miningLabelBuilder;
		this.linetext = linetext;
	}

	@Override
	public int getLinenumber() {
		return linenumber;
	}

	public String getLinetext() {
		return linetext;
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

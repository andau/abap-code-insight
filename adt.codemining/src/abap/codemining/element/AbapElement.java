package abap.codemining.element;

public abstract class AbapElement implements IAbapElement {

	private final String methodname;
	private final int linenumber;
	private final int startindex;

	public AbapElement(String methodname, int linenumber, int startindex) {
		this.methodname = methodname;
		this.linenumber = linenumber;
		this.startindex = startindex;
	}

	public int getLinenumber() {
		return linenumber;
	}

	public String getElementname() {
		return methodname;
	}

	public int getStartindex() {
		return startindex;
	}

}

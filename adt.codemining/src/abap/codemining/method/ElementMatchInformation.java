package abap.codemining.method;

public class ElementMatchInformation {

	private final String methodname;
	private final int startindex;
	private final int linenumber;

	public ElementMatchInformation(String methodname, int startindex, int linenumber) {
		this.methodname = methodname;
		this.startindex = startindex;
		this.linenumber = linenumber;
	}

	public String getClassname() {
		return methodname;
	}

	public int getStartindex() {
		return startindex;
	}

	public int getLinenumber() {
		return linenumber;
	}

}

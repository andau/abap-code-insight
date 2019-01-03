package abap.codemining.method;

public class ElementMatchInformation {

	private final String methodname;
	private final int startindex;

	public ElementMatchInformation(String methodname, int startindex) {
		this.methodname = methodname;
		this.startindex = startindex;
	}

	public String getClassname() {
		return methodname;
	}

	public int getStartindex() {
		return startindex;
	}

}

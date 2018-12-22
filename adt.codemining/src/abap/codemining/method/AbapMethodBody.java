package abap.codemining.method;

public class AbapMethodBody {

	private final String methodname;
	private final int linenumber;

	public AbapMethodBody(String methodname, int linenumber) {
		this.methodname = methodname;
		this.linenumber = linenumber;
	}

	public int getLinenumber() {
		return linenumber;
	}

	public String getMethodname() {
		return methodname;
	}

}

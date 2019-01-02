package abap.codemining.method;

public class AbapMethodBody {

	private final String methodname;
	private final int linenumber;
	private final int startindex; 

	public AbapMethodBody(String methodname, int linenumber, int startindex) {
		this.methodname = methodname;
		this.linenumber = linenumber;
		this.startindex = startindex; 
	}

	public int getLinenumber() {
		return linenumber;
	}

	public String getMethodname() {
		return methodname;
	}
	
	public int getStartindex() 
	{
		return startindex; 
	}

}

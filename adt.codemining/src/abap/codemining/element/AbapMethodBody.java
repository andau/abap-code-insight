package abap.codemining.element;

public class AbapMethodBody extends AbapElement {

	public AbapMethodBody(String methodname, int linenumber, int startindex) {
		super(methodname, linenumber, startindex);
	}

	@Override
	public boolean showRef() {
		return true;
	}

	@Override
	public boolean showSignature() {
		return true;
	}

}

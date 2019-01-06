package abap.codemining.element;

public class AbapClassBody extends AbapElement {

	public AbapClassBody(String methodname, int linenumber, int startindex) {
		super(methodname, linenumber, startindex);
	}

	@Override
	public boolean showRef() {
		return true;
	}

	@Override
	public boolean showSignature() {
		return false;
	}

}

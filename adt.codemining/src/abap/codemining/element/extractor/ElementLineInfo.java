package abap.codemining.element.extractor;

public class ElementLineInfo {
	private final int linenumber;
	private final int startpos;
	private final int endpos;

	public ElementLineInfo(int linenumber, int startpos, int endpos) {
		this.linenumber = linenumber;
		this.startpos = startpos;
		this.endpos = endpos;
	}

	public int getLinenumber() {
		return linenumber;
	}

	public int getStartpos() {
		return startpos;
	}

	public int getEndpos() {
		return endpos;
	}

}

package abap.codemining.editor;

public class TextEditorMiningEntryKey {

	private final String abapProjectName;
	private final String title;

	public TextEditorMiningEntryKey(String abapProjectName, String title) {
		this.abapProjectName = abapProjectName;
		this.title = title;
	}

	public String abapProjectName() {
		return abapProjectName;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != getClass()) {
			return false;
		}

		final TextEditorMiningEntryKey other = (TextEditorMiningEntryKey) o;
		return other.abapProjectName().equals(abapProjectName) && other.getTitle().equals(title);
	}

	@Override
	public int hashCode() {
		return abapProjectName.hashCode() ^ title.hashCode();
	}

}

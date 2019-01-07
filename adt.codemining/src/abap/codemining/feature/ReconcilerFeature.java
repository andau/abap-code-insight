package abap.codemining.feature;

public class ReconcilerFeature {

	private final boolean updateEnabled;

	public ReconcilerFeature(boolean updateEnabled) {
		this.updateEnabled = updateEnabled;
	}

	public boolean isUpdateEnabled() {
		return updateEnabled;
	}

}

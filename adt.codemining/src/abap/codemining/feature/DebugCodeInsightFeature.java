package abap.codemining.feature;

public class DebugCodeInsightFeature {

	private final boolean isActive;

	public DebugCodeInsightFeature(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isActive() {
		return isActive;
	}

}

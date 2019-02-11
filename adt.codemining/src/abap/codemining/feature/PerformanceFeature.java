package abap.codemining.feature;

public class PerformanceFeature {

	private final boolean isActive;
	private final int maxLines;

	public PerformanceFeature(boolean isActive, int maxLines) {
		this.isActive = isActive;
		this.maxLines = maxLines;
	}

	public boolean isActive() {
		return isActive;
	}

	public int getMaxLines() {
		return maxLines;
	}

}

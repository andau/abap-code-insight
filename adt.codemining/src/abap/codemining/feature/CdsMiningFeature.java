package abap.codemining.feature;

public class CdsMiningFeature implements IReferenceMiningFeature {

	private final boolean showReferenceCount;

	public CdsMiningFeature(boolean showReferenceCount) {
		this.showReferenceCount = showReferenceCount;
	}

	@Override
	public boolean isReferenceCountActive() {
		return showReferenceCount;
	}


}

package abap.codemining.feature;

public class ReferenceMiningFeature implements IReferenceMiningFeature {

	private final boolean showReferenceCount;

	public ReferenceMiningFeature(boolean showReferenceCount) {
		this.showReferenceCount = showReferenceCount;
	}

	@Override
	public boolean isReferenceCountActive() {
		return showReferenceCount;
	}


}

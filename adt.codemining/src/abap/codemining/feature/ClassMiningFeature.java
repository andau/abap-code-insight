package abap.codemining.feature;

public class ClassMiningFeature implements IReferenceMiningFeature {

	private final boolean showReferenceCount;

	public ClassMiningFeature(boolean showReferenceCount) {
		this.showReferenceCount = showReferenceCount;
	}

	@Override
	public boolean isReferenceCountActive() {
		return showReferenceCount;
	}

}

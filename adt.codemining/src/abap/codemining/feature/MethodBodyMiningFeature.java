package abap.codemining.feature;

public class MethodBodyMiningFeature implements IReferenceMiningFeature {

	private final boolean referenceCountActive;
	private final boolean signatureActive;

	public MethodBodyMiningFeature(boolean referenceCountActive, boolean signatureActive) {
		this.referenceCountActive = referenceCountActive;
		this.signatureActive = signatureActive;
	}

	@Override
	public boolean isReferenceCountActive() {
		return referenceCountActive;
	}

	public boolean isSignatureActive() {
		// TODO Auto-generated method stub
		return signatureActive;
	}

}

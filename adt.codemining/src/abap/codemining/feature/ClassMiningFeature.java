package abap.codemining.feature;

public class ClassMiningFeature implements IReferenceMiningFeature {

	private boolean classDefinitionReferenceEnabled;
	private boolean classImplemenationReferenceEnabled;
	private boolean methodDefinitionReferenceEnabled;
	private boolean methodImplementationReferenceEnabled;
	private boolean methodImplementationSignatureEnabled;
	private boolean classUnknownReferenceEnabled;
	private boolean classAttributeReferenceEnabled;

	public ClassMiningFeature() {
	}

	public void setClassImplemenationReferenceEnabled(boolean isEnabled) {
		this.classImplemenationReferenceEnabled = isEnabled;
	}

	public void setMethodImplmenationSignatureEnabled(boolean isEnabled) {
		this.methodImplementationSignatureEnabled = isEnabled;
	}

	public void setClassDefinitionReferenceEnabled(boolean isEnabled) {
		this.classDefinitionReferenceEnabled = isEnabled;
	}

	@Override
	public boolean isReferenceCountActive() {
		return classImplemenationReferenceEnabled || classDefinitionReferenceEnabled || methodDefinitionReferenceEnabled
				|| methodImplementationReferenceEnabled;
	}

	public boolean isClassDefinitionReferenceEnabled() {
		return classDefinitionReferenceEnabled;
	}

	public boolean isClassUnknownElementEnabled() {
		return classUnknownReferenceEnabled;
	}

	public boolean isMethodDefinitionReferenceEnabled() {
		return methodDefinitionReferenceEnabled;
	}

	public boolean isClassImplemenationReferenceEnabled() {
		return classImplemenationReferenceEnabled;
	}

	public boolean isMethodImplementationReferenceEnabled() {
		return methodImplementationReferenceEnabled;
	}

	public boolean isMethodImplementationSignatureEnabled() {
		return methodImplementationSignatureEnabled;
	}

	public boolean isClassAttributeReferenceEnabled() {
		return classAttributeReferenceEnabled;
	}

	public boolean isClassUnknownReferenceEnabled() {
		return classUnknownReferenceEnabled;
	}

	public void setClassUnknownElementEnabled(boolean isEnabled) {
		this.classUnknownReferenceEnabled = isEnabled;
	}

	public void setMethodDefinitionReferenceEnabled(boolean isEnabled) {
		methodDefinitionReferenceEnabled = isEnabled;
	}

	public void setMethodImplemenationReferenceEnabled(boolean isEnabled) {
		methodImplementationReferenceEnabled = isEnabled;

	}

}

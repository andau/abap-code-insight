package abap.codemining.feature;

public class ClassMiningFeature implements IReferenceMiningFeature {

	private boolean classDefinitionReferenceEnabled;
	private boolean classImplemenationReferenceEnabled;
	private boolean methodDefinitionReferenceEnabled;
	private boolean methodImplementationReferenceEnabled;
	private boolean methodImplementationSignatureEnabled;
	private boolean classUnknownReferenceEnabled;
	private boolean classAttributeReferenceEnabled;
	private boolean classTypeReferenceEnabled;

	public ClassMiningFeature() {
	}

	public void setClassDefinitionReferenceEnabled(boolean isEnabled) {
		this.classDefinitionReferenceEnabled = isEnabled;
	}

	public void setClassImplemenationReferenceEnabled(boolean isEnabled) {
		this.classImplemenationReferenceEnabled = isEnabled;
	}

	public void setMethodDefinitionReferenceEnabled(boolean isEnabled) {
		methodDefinitionReferenceEnabled = isEnabled;
	}

	public void setMethodImplemenationReferenceEnabled(boolean isEnabled) {
		methodImplementationReferenceEnabled = isEnabled;

	}

	public void setClassAttributeReferenceEnabled(boolean isEnabled) {
		this.classAttributeReferenceEnabled = isEnabled;
	}

	public void setClassTypeElementEnabled(boolean isEnabled) {
		this.classTypeReferenceEnabled = isEnabled;
	}

	public void setClassUnknownElementEnabled(boolean isEnabled) {
		this.classUnknownReferenceEnabled = isEnabled;
	}

	public void setMethodImplemenationSignatureEnabled(boolean isEnabled) {
		this.methodImplementationSignatureEnabled = isEnabled;
	}

	@Override
	public boolean isReferenceCountActive() {
		return classImplemenationReferenceEnabled || classDefinitionReferenceEnabled || methodDefinitionReferenceEnabled
				|| methodImplementationReferenceEnabled || classAttributeReferenceEnabled
				|| classUnknownReferenceEnabled || classTypeReferenceEnabled;
	}

	public boolean isClassDefinitionReferenceEnabled() {
		return classDefinitionReferenceEnabled;
	}

	public boolean isClassUnknownElementReferenceEnabled() {
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

	public boolean isClassTypeReferenceEnabled() {
		return classTypeReferenceEnabled;
	}

}

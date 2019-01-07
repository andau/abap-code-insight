package abap.codemining.label;

import java.net.URI;

import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.feature.ClassMiningFeature;

public class ClassMiningLabelBuilder extends ReferenceMiningLabelBuilder implements IMiningLabelBuilder {

	private final ClassMiningFeature classHeaderMiningFeature;

	public ClassMiningLabelBuilder(ClassMiningFeature classHeaderMiningFeature) {
		this.classHeaderMiningFeature = classHeaderMiningFeature;
	}

	@Override
	public String buildSignatureLabel(IAbapProject abapProject, URI uri, String doc)
			throws MiningLabelBuildingException {
		// signature label for classes is not implemented
		return null;
	}

	@Override
	public boolean showRef() {
		return classHeaderMiningFeature.isReferenceCountActive();
	}

	@Override
	public boolean showSignature() {
		return false;
	}

}

package abap.codemining.label;

import java.net.URI;

import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.feature.ReferenceMiningFeature;

public class ReferenceOnlyMiningLabelBuilder extends ReferenceMiningLabelBuilder implements IMiningLabelBuilder {

	private final ReferenceMiningFeature referenceMiningFeature;

	public ReferenceOnlyMiningLabelBuilder(ReferenceMiningFeature referenceMiningFeature) {
		this.referenceMiningFeature = referenceMiningFeature;
	}

	@Override
	public boolean showRef() {
		return referenceMiningFeature.isReferenceCountActive();
	}

	@Override
	public boolean showSignature() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String buildSignatureLabel(IAbapProject abapProject, URI uri, String doc)
			throws MiningLabelBuildingException {
		// TODO Auto-generated method stub
		return null;
	}

}

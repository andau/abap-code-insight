package abap.codemining.label;

import java.net.URI;

import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.feature.CdsMiningFeature;

public class CdsMiningLabelBuilder extends ReferenceMiningLabelBuilder implements IMiningLabelBuilder {

	private CdsMiningFeature cdsMiningFeature;

	public CdsMiningLabelBuilder(CdsMiningFeature cdsMiningFeature) {
		this.cdsMiningFeature = cdsMiningFeature; 
	}

	@Override
	public boolean showRef() {
		return cdsMiningFeature.isReferenceCountActive();
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

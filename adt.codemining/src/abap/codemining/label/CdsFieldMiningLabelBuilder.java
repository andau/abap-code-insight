package abap.codemining.label;

import java.net.URI;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.adt.AbapCodeServiceFactory;
import abap.codemining.adt.IAbapCodeElementInformation;
import abap.codemining.adt.ICodeElementInformationService;
import abap.codemining.feature.ReferenceMiningFeature;

public class CdsFieldMiningLabelBuilder extends ReferenceMiningLabelBuilder implements IMiningLabelBuilder {

	private final ReferenceMiningFeature referenceMiningFeature;
	private final AbapCodeServiceFactory abapCodeServiceFactory;

	public CdsFieldMiningLabelBuilder(ReferenceMiningFeature referenceMiningFeature) {
		this.abapCodeServiceFactory = new AbapCodeServiceFactory();
		this.referenceMiningFeature = referenceMiningFeature;
	}

	@Override
	public boolean showRef() {
		return false;
	}

	@Override
	public boolean showSignature() {
		return true;
	}

	@Override
	public String buildSignatureLabel(IAbapProject abapProject, URI uri, String doc)
			throws MiningLabelBuildingException {

		try {
			ICodeElementInformationService abapCodeElementInformationService;
			abapCodeElementInformationService = abapCodeServiceFactory
					.createAbapCodeElementInformation(abapProject.getDestinationId());

			final IAbapCodeElementInformation abapCodeElementInformation = abapCodeElementInformationService
					.getInfo(uri, doc);
			return abapCodeElementInformation.getSignatureLabel();
		} catch (final OutOfSessionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}

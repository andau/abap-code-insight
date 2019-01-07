package abap.codemining.label;

import java.net.URI;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.adt.AbapCodeServiceFactory;
import abap.codemining.adt.IAbapCodeElementInformation;
import abap.codemining.adt.ICodeElementInformationService;
import abap.codemining.feature.MethodBodyMiningFeature;

public class MethodMiningLabelBuilder extends ReferenceMiningLabelBuilder implements IMiningLabelBuilder {

	private final AbapCodeServiceFactory abapCodeServiceFactory;
	private final MethodBodyMiningFeature methodBodyMiningFeature;

	public MethodMiningLabelBuilder(MethodBodyMiningFeature methodBodyMiningFeature) {
		this.abapCodeServiceFactory = new AbapCodeServiceFactory();
		this.methodBodyMiningFeature = methodBodyMiningFeature;

	}

	@Override
	public String buildSignatureLabel(IAbapProject abapProject, URI uri, String docContent)
			throws MiningLabelBuildingException {
		try {
			ICodeElementInformationService abapCodeElementInformationService = abapCodeServiceFactory
					.createAbapCodeElementInformation(abapProject.getDestinationId());

			IAbapCodeElementInformation abapCodeElementInformation = abapCodeElementInformationService.getInfo(uri,
					docContent);
			return abapCodeElementInformation.getSignatureLabel();

		} catch (OutOfSessionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MiningLabelBuildingException(e);
		}
	}

	@Override
	public boolean showRef() {
		return methodBodyMiningFeature.isReferenceCountActive();
	}

	@Override
	public boolean showSignature() {
		// TODO Auto-generated method stub
		return methodBodyMiningFeature.isSignatureActive();
	}

}

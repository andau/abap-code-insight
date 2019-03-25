package abap.codemining.label;

import java.net.URI;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.adt.AbapCodeServiceFactory;
import abap.codemining.adt.IAbapCodeElementInformation;
import abap.codemining.adt.ICodeElementInformationService;

public class MethodBodyMiningLabelBuilder extends ReferenceMiningLabelBuilder implements IMiningLabelBuilder {

	private final AbapCodeServiceFactory abapCodeServiceFactory;
	private final boolean isReferenceCountEnabled;
	private final boolean isSignatureEnabled;

	public MethodBodyMiningLabelBuilder(boolean isReferenceCountEnabled, boolean isSignatureEnabled) {
		this.abapCodeServiceFactory = new AbapCodeServiceFactory();
		this.isReferenceCountEnabled = isReferenceCountEnabled;
		this.isSignatureEnabled = isSignatureEnabled;
	}

	@Override
	public String buildSignatureLabel(IAbapProject abapProject, URI uri, String docContent)
			throws MiningLabelBuildingException {
		try {
			final ICodeElementInformationService abapCodeElementInformationService = abapCodeServiceFactory
					.createAbapCodeElementInformation(abapProject.getDestinationId());

			final IAbapCodeElementInformation abapCodeElementInformation = abapCodeElementInformationService
					.getInfo(uri, docContent);
			return abapCodeElementInformation.getSignatureLabel();

		} catch (final OutOfSessionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MiningLabelBuildingException(e);
		}
	}

	@Override
	public boolean showRef() {
		return isReferenceCountEnabled;
	}

	@Override
	public boolean showSignature() {
		return isSignatureEnabled;
	}

}

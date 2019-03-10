package abap.codemining.label;

import java.net.URI;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.adt.AbapCodeServiceFactory;
import abap.codemining.adt.IAbapCodeElementInformation;
import abap.codemining.adt.ICodeElementInformationService;
import abap.codemining.feature.ClassMiningFeature;

public class ClassMiningLabelBuilder extends ReferenceMiningLabelBuilder implements IMiningLabelBuilder {

	private final AbapCodeServiceFactory abapCodeServiceFactory;
	private final ClassMiningFeature classMiningFeature;

	public ClassMiningLabelBuilder(ClassMiningFeature classMiningFeature) {
		this.abapCodeServiceFactory = new AbapCodeServiceFactory();
		this.classMiningFeature = classMiningFeature;
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
		return classMiningFeature.isReferenceCountActive();
	}

	@Override
	public boolean showSignature() {
		return false;
	}

}

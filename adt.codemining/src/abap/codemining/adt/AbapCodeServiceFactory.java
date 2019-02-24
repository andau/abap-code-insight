package abap.codemining.adt;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.abapsource.sources.AdtSourceServicesFactory;
import com.sap.adt.tools.abapsource.sources.IAdtSourceServicesFactory;
import com.sap.adt.tools.abapsource.sources.codeelementinformation.ICodeElementInformationBackendService;
import com.sap.adt.tools.abapsource.sources.objectstructure.IObjectStructureService;

import abap.codemining.adt.method.AbapMethodInformationService;

public class AbapCodeServiceFactory {

	public AbapMethodInformationService createAbapCodeElementInformation(String destination)
			throws OutOfSessionsException {
		final IAdtSourceServicesFactory adtSourceServicesFactory = AdtSourceServicesFactory.createInstance();
		final ICodeElementInformationBackendService codeElementInformationService = adtSourceServicesFactory
				.createCodeElementInformationService(destination);
		return new AbapMethodInformationService(codeElementInformationService);
	}

	public IObjectStructureService createObjectStructureService(String destination) throws OutOfSessionsException {
		final IAdtSourceServicesFactory adtSourceServicesFactory = AdtSourceServicesFactory.createInstance();
		final IObjectStructureService codeElementInformationService = adtSourceServicesFactory
				.createObjectStructureService(destination);
		return codeElementInformationService;
	}

}

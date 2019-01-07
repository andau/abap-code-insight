package abap.codemining.adt;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.abapsource.sources.AdtSourceServicesFactory;
import com.sap.adt.tools.abapsource.sources.IAdtSourceServicesFactory;
import com.sap.adt.tools.abapsource.sources.codeelementinformation.ICodeElementInformationBackendService;

import abap.codemining.adt.method.AbapMethodInformationService;

public class AbapCodeServiceFactory {

	public AbapMethodInformationService createAbapCodeElementInformation(String destination)
			throws OutOfSessionsException {
		IAdtSourceServicesFactory adtSourceServicesFactory = AdtSourceServicesFactory.createInstance();
		ICodeElementInformationBackendService codeElementInformationService = adtSourceServicesFactory
				.createCodeElementInformationService(destination);
		return new AbapMethodInformationService(codeElementInformationService);
	}

}

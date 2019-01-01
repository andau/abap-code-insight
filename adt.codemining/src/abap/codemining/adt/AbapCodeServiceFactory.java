package abap.codemining.adt;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.abapsource.sources.AdtSourceServicesFactory;
import com.sap.adt.tools.abapsource.sources.IAdtSourceServicesFactory;
import com.sap.adt.tools.abapsource.sources.codeelementinformation.ICodeElementInformationBackendService;

public class AbapCodeServiceFactory {

	public AbapCodeElementInformationService createAbapCodeElementInformation(String destination)
			throws OutOfSessionsException {
		IAdtSourceServicesFactory adtSourceServicesFactory = AdtSourceServicesFactory.createInstance();
		ICodeElementInformationBackendService codeElementInformationService = adtSourceServicesFactory
				.createCodeElementInformationService(destination);
		return new AbapCodeElementInformationService(codeElementInformationService);
	}

}

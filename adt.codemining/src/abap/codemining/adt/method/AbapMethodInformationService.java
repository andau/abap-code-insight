package abap.codemining.adt.method;

import java.net.URI;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.sap.adt.tools.abapsource.internal.sources.codeelementinformation.ICodeElement;
import com.sap.adt.tools.abapsource.sources.codeelementinformation.ICodeElementInformationBackendService;

import abap.codemining.adt.IAbapCodeElementInformation;
import abap.codemining.adt.ICodeElementInformationService;

public class AbapMethodInformationService implements ICodeElementInformationService {

	ICodeElementInformationBackendService codeElementInformationService;
	private final AbapMethodInformationAdapter abapCodeElementInformationAdapter;

	public AbapMethodInformationService(
			ICodeElementInformationBackendService codeElementInformationService) {
		this.codeElementInformationService = codeElementInformationService;
		this.abapCodeElementInformationAdapter = new AbapMethodInformationAdapter();
	}

	@Override
	public IAbapCodeElementInformation getInfo(URI uri, String docContent) {
		IProgressMonitor monitor = new NullProgressMonitor();
		ICodeElement codeElement = (ICodeElement) codeElementInformationService.getCodeElementInformation(uri,
				docContent, monitor);

		return abapCodeElementInformationAdapter.adapt(codeElement);
	}
}

package abap.codemining.adt;

import java.net.URI;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.sap.adt.tools.abapsource.internal.sources.codeelementinformation.ICodeElement;
import com.sap.adt.tools.abapsource.sources.codeelementinformation.ICodeElementInformationBackendService;

public class AbapCodeElementInformation implements ICodeElementInformation {

	ICodeElementInformationBackendService codeElementInformationService;

	public AbapCodeElementInformation(ICodeElementInformationBackendService codeElementInformationService) {
		this.codeElementInformationService = codeElementInformationService;
	}

	@Override
	public String getVisibility(URI uri, String arg1) {
		IProgressMonitor monitor = new NullProgressMonitor();
		ICodeElement codeElement = (ICodeElement) codeElementInformationService.getCodeElementInformation(uri, arg1,
				monitor);
		return codeElement.getProperty("visibility").getValue();
	}

}

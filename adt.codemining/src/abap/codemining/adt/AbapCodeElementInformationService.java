package abap.codemining.adt;

import java.net.URI;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.sap.adt.tools.abapsource.internal.sources.codeelementinformation.ICodeElement;
import com.sap.adt.tools.abapsource.internal.sources.codeelementinformation.ICodeElement.ICodeElementProperty;
import com.sap.adt.tools.abapsource.sources.codeelementinformation.ICodeElementInformationBackendService;
import com.sap.adt.tools.core.IAdtObjectReference;

import abap.codemining.utils.StringUtils;

public class AbapCodeElementInformationService implements ICodeElementInformationService {

	ICodeElementInformationBackendService codeElementInformationService;

	public AbapCodeElementInformationService(ICodeElementInformationBackendService codeElementInformationService) {
		this.codeElementInformationService = codeElementInformationService;
	}

	@Override
	public AbapCodeElementInformation getInfo(URI uri, String arg1) {
		IProgressMonitor monitor = new NullProgressMonitor();
		ICodeElement codeElement = (ICodeElement) codeElementInformationService.getCodeElementInformation(uri, arg1,
				monitor);
		String visibility = codeElement.getProperty("visibility") != null ? codeElement.getProperty("visibility").getValue() : StringUtils.EMPTY; 
		String level = codeElement.getProperty("level") != null ?  codeElement.getProperty("level").getValue() : StringUtils.EMPTY;
		String name =  codeElement.getName().toLowerCase(); 
		//findReturningParameter(codeElement.getChildren()); 
		return new AbapCodeElementInformation(visibility, level, name);
	}

	private String findReturningParameter(List<? extends IAdtObjectReference> references) {
		for(IAdtObjectReference reference : references)
		{
		
				List<? extends IAdtObjectReference> childReferences = reference.getChildren(); 
			    for(IAdtObjectReference childReference : childReferences)
			    {
			    	if (childReference.getName().equals("returning")) 
			    	{
			    		return reference.getName(); 
			    	}
			    }
		
		}
		return StringUtils.EMPTY; 
	}
	
	//CLAS/OOP 

}

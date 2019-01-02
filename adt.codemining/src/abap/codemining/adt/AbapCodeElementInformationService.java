package abap.codemining.adt;

import java.net.URI;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.sap.adt.tools.abapsource.internal.sources.codeelementinformation.ICodeElement;
import com.sap.adt.tools.abapsource.internal.sources.codeelementinformation.ICodeElement.ICodeElementProperty;
import com.sap.adt.tools.abapsource.sources.codeelementinformation.ICodeElementInformationBackendService;
import com.sap.adt.tools.core.IAdtObjectReference;

import abap.codemining.method.MethodParam;
import abap.codemining.method.MethodParamType;
import abap.codemining.utils.StringUtils;

public class AbapCodeElementInformationService implements ICodeElementInformationService {

	private static final String METHOD_PARAMETER_TYPE_RETURNING = "returning";
	private static final String METHOD_PARAM_TYPE_VOID = "void";
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
		MethodParam returningParameter =  findReturningParameter(codeElement.getCodeElementChildren()); 
		return new AbapCodeElementInformation(visibility, level, name, returningParameter);
	}

	private MethodParam findReturningParameter(List<? extends ICodeElement> references) {
		for(ICodeElement reference : references)
		{
		
				ICodeElementProperty elementProperty = reference.getProperty("paramType"); 
				ICodeElementProperty abapTypeProperty = reference.getProperty("abapType"); 
			    	if (elementProperty != null && elementProperty.getValue().equals(METHOD_PARAMETER_TYPE_RETURNING)) 
			    	{
			    		return new MethodParam(reference.getName(), abapTypeProperty.getValue(), MethodParamType.RETURNING); 
			    	}
			    
		
		}
		return new MethodParam(StringUtils.EMPTY, METHOD_PARAM_TYPE_VOID, MethodParamType.RETURNING); 
	}
	
	//CLAS/OOP 

}

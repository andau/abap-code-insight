package abap.codemining.general;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.core.model.util.ServiceNotAvailableException;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.adt.AbapCodeElementInformation;
import abap.codemining.adt.AbapCodeServiceFactory;
import abap.codemining.adt.ICodeElementInformationService;
import abap.codemining.method.MethodParam;
import abap.codemining.method.MethodParamType;
import abap.codemining.utils.StringUtils;

public class MiningLabelBuilder {

	private final AbapCodeServiceFactory abapCodeServiceFactory;
	private ReferencesEvaluator referencesEvaluator;

	public MiningLabelBuilder() {
		this.abapCodeServiceFactory = new AbapCodeServiceFactory();
	
	}

	public String build(IAbapProject abapProject, URI uri, String doc)
			throws OutOfSessionsException, ServiceNotAvailableException, IOException {
		if (referencesEvaluator == null) {
			initReferencesEvaluator(abapProject.getProject());
		}

		ICodeElementInformationService abapCodeElementInformationService = abapCodeServiceFactory
				.createAbapCodeElementInformation(abapProject.getDestinationId());
		AbapCodeElementInformation abapCodeElementInformation = abapCodeElementInformationService.getInfo(uri, doc);

		
		String impParameterLabel = buildParameterLabel(abapCodeElementInformation.getImpParameters()); 
		String expParameterLabel = buildParameterLabel(abapCodeElementInformation.getExpParameters()); 
		String expParameterSpace = expParameterLabel.equals(StringUtils.EMPTY) ? StringUtils.EMPTY : StringUtils.SPACE; 

        String level = abapCodeElementInformation.getLevel().equals("instance") ? StringUtils.EMPTY : abapCodeElementInformation.getLevel() + StringUtils.SPACE; 
		
		return abapCodeElementInformation.getVisibility() + StringUtils.SPACE + level 
		 + abapCodeElementInformation.getReturnValue().getLabel() + StringUtils.SPACE + abapCodeElementInformation.getName() + StringUtils.SPACE 
		 + "(" + impParameterLabel + expParameterSpace + expParameterLabel + ")";

	}

	public String buildReferencesLabel(IAbapProject abapProject, URI uri, String doc) throws ServiceNotAvailableException, IOException 
	{
		int references = computeReferences(abapProject.getProject(), uri);
		return references == 1 ? "1 reference" : references + StringUtils.SPACE + "references"; 
		
	}
	private String buildParameterLabel(Collection<MethodParam> parameters) {
		List<String> parameterLabels = new ArrayList<String>(); 
		
		for(MethodParam parameter : parameters) 
		{
			parameterLabels.add(getLabelForParameterType(parameter.getMethodParamType()) + parameter.getLabel()); 
		}
		
		return String.join(", ", parameterLabels); 
	}

	private String getLabelForParameterType(MethodParamType methodParamType) {
		switch(methodParamType) 
		{
		case IMPORTING: 
		return ""; 
		case EXPORTING: 
			return "EXP:"; 
		case RETURNING: 
			return "ret";  
		default: 
		return 	"undef";  
		}
	}

	private int computeReferences(IProject project, URI uri) throws ServiceNotAvailableException, IOException {
			
		return referencesEvaluator.getReferencesResult(uri);

	}

	private void initReferencesEvaluator(IProject project) {
		try {
			this.referencesEvaluator = new ReferencesEvaluator(project);
		} catch (ServiceNotAvailableException e) {
			this.referencesEvaluator = null;
		}
	}

}

package abap.codemining.general;

import java.io.IOException;
import java.net.URI;

import org.eclipse.core.resources.IProject;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.core.model.util.ServiceNotAvailableException;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.adt.AbapCodeElementInformation;
import abap.codemining.adt.AbapCodeServiceFactory;
import abap.codemining.adt.ICodeElementInformationService;
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

		String references = computeReferences(abapProject.getProject(), uri);
		
		String level = abapCodeElementInformation.getLevel().equals("instance") ? StringUtils.EMPTY : abapCodeElementInformation.getLevel() + StringUtils.SPACE; 
		return references + StringUtils.SPACE + "references;" + StringUtils.SPACE + abapCodeElementInformation.getVisibility() + StringUtils.SPACE + level 
		 + abapCodeElementInformation.getReturnValue().getLabel() + StringUtils.SPACE + abapCodeElementInformation.getName();

	}

	private String computeReferences(IProject project, URI uri) throws ServiceNotAvailableException, IOException {
			
		return Integer.toString(referencesEvaluator.getReferencesResult(uri));

	}

	private void initReferencesEvaluator(IProject project) {
		try {
			this.referencesEvaluator = new ReferencesEvaluator(project);
		} catch (ServiceNotAvailableException e) {
			this.referencesEvaluator = null;
		}
	}

}

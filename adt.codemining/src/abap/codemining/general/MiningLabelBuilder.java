package abap.codemining.general;

import java.io.IOException;
import java.net.URI;

import org.eclipse.core.resources.IProject;

import com.sap.adt.communication.exceptions.OutOfSessionsException;
import com.sap.adt.tools.core.model.util.ServiceNotAvailableException;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.adt.AbapCodeServiceFactory;
import abap.codemining.adt.ICodeElementInformation;
import abap.codemining.utils.StringUtils;

public class MiningLabelBuilder {

	private final AbapCodeServiceFactory abapCodeServiceFactory;
	private ReferencesEvaluator referencesEvaluator;

	public MiningLabelBuilder() {
		this.abapCodeServiceFactory = new AbapCodeServiceFactory();
		this.referencesEvaluator = new ReferencesEvaluator();

	}

	public String build(IAbapProject abapProject, URI uri, String doc)
			throws OutOfSessionsException, ServiceNotAvailableException, IOException {
		if (referencesEvaluator == null) {
			initReferencesEvaluator(abapProject.getProject());
		}

		ICodeElementInformation abapCodeElementInformation = abapCodeServiceFactory
				.createAbapCodeElementInformation(abapProject.getDestinationId());
		String visibility = abapCodeElementInformation.getVisibility(uri, doc);

		String references = computeReferences(abapProject.getProject(), uri);
		return references + StringUtils.SPACE + "references;" + StringUtils.SPACE + visibility;

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

package abap.codemining.label;

import java.io.IOException;
import java.net.URI;

import org.eclipse.core.resources.IProject;

import com.sap.adt.tools.core.model.util.ServiceNotAvailableException;
import com.sap.adt.tools.core.project.IAbapProject;

import abap.codemining.general.ReferencesEvaluator;

abstract class ReferenceMiningLabelBuilder implements IMiningLabelBuilder {

	private ReferencesEvaluator referencesEvaluator;

	@Override
	public String buildReferencesLabel(IAbapProject abapProject, URI uri, String doc)
			throws ServiceNotAvailableException, IOException {

		if (referencesEvaluator == null) {
			initReferencesEvaluator(abapProject.getProject());
		}

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

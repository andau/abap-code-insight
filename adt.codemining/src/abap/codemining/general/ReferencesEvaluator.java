package abap.codemining.general;

import java.io.IOException;
import java.net.URI;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.sap.adt.ris.model.usagereferences.IUsageReferenceRequest;
import com.sap.adt.ris.model.usagereferences.IUsageReferenceResult;
import com.sap.adt.ris.model.usagereferences.IUsageReferencedObjects;
import com.sap.adt.ris.model.usagereferences.IUsageReferencesFactory;
import com.sap.adt.ris.search.usagereferences.AdtRisUsageReferencesSearchServiceFactory;
import com.sap.adt.ris.search.usagereferences.IAdtRisUsageReferencesSearchService;
import com.sap.adt.tools.core.model.util.ServiceNotAvailableException;

import abap.codemining.utils.StringUtils;

public class ReferencesEvaluator {

	IAdtRisUsageReferencesSearchService usageReferencesSearchService;

	ReferencesEvaluator() {

	}

	public ReferencesEvaluator(IProject project) throws ServiceNotAvailableException {
		usageReferencesSearchService = AdtRisUsageReferencesSearchServiceFactory
				.createUsageReferencesSearchService(project, new NullProgressMonitor());
	}

	public String getReferencesResult(URI uri) throws ServiceNotAvailableException, IOException {
		IUsageReferenceRequest var2 = IUsageReferencesFactory.eINSTANCE.createUsageReferenceRequest();
		IUsageReferenceResult usageReferenceResult = usageReferencesSearchService.search(uri, var2,
				new NullProgressMonitor());
		IUsageReferencedObjects referencedObjects = usageReferenceResult.getReferencedObjects();
		int references = (int) referencedObjects.getReferencedObject().stream()
				.filter(item -> item.getUsageInformation() != null).count();
		return references == 1 ? "1 reference" : references + StringUtils.SPACE + "references";
	}

}

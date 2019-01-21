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

import abap.codemining.feature.FeatureFacade;
import abap.codemining.utils.StringUtils;

public class ReferencesEvaluator {

	IAdtRisUsageReferencesSearchService usageReferencesSearchService;
	private boolean showTestReferences;

	ReferencesEvaluator() {
	}

	public ReferencesEvaluator(IProject project) throws ServiceNotAvailableException {
		final FeatureFacade featureFacade = new FeatureFacade();
		this.showTestReferences = featureFacade.getTestReferenceFeature().isActive();

		usageReferencesSearchService = AdtRisUsageReferencesSearchServiceFactory
				.createUsageReferencesSearchService(project, new NullProgressMonitor());
	}

	public String getReferencesResult(URI uri) throws ServiceNotAvailableException, IOException {
		final IUsageReferenceRequest var2 = IUsageReferencesFactory.eINSTANCE.createUsageReferenceRequest();
		final IUsageReferenceResult usageReferenceResult = usageReferencesSearchService.search(uri, var2,
				new NullProgressMonitor());
		final IUsageReferencedObjects referencedObjects = usageReferenceResult.getReferencedObjects();

		final int references = (int) referencedObjects.getReferencedObject().stream()
				.filter(item -> item.getUsageInformation() != null).count();

		final String testlabel = getTestReferencesLabel(referencedObjects);

		final String refLabel = references == 1 ? "1 reference" : references + StringUtils.SPACE + "references";

		return refLabel + testlabel;
	}

	private String getTestReferencesLabel(final IUsageReferencedObjects referencedObjects) {
		String testlabel;
		if (showTestReferences) {
			final int testReferences = (int) referencedObjects.getReferencedObject().stream().filter(
					item -> item.getUsageInformation() != null && item.getUsageInformation().contains("includeTest"))
					.count();

			testlabel = testReferences == 0 ? StringUtils.EMPTY
					: (testReferences == 1 ? " (1 test)" : " (" + testReferences + StringUtils.SPACE + "tests)");
		} else {
			testlabel = StringUtils.EMPTY;
		}
		return testlabel;
	}

}
